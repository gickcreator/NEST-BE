package ssu.opensource.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ssu.opensource.domain.Token;
import ssu.opensource.domain.User;
import ssu.opensource.dto.auth.JwtTokensDto;
import ssu.opensource.feign.google.GoogleAuthClient;
import ssu.opensource.feign.google.GoogleInfoClient;
import ssu.opensource.feign.google.GoogleTokenResponse;
import ssu.opensource.feign.google.GoogleUserInfoResponse;
import ssu.opensource.service.google.GoogleService;
import ssu.opensource.service.token.TokenRemover;
import ssu.opensource.service.token.TokenRetriever;
import ssu.opensource.service.token.TokenSaver;
import ssu.opensource.service.user.UserRetriever;
import ssu.opensource.service.user.UserUpdater;
import ssu.opensource.utils.JwtUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUrl;

    private final JwtUtil jwtUtil;
    private final TokenRetriever tokenRetriever;
    private final UserRetriever userRetriever;
    private final UserUpdater userUpdater;
    private final TokenSaver tokenSaver;
    private final TokenRemover tokenRemover;
    private final GoogleService googleService;

    @Transactional
    public JwtTokensDto reissueToken(final Long userId){
        JwtTokensDto tokensDto = jwtUtil.generateTokens(userId);

        String userKey = userId.toString() + ":" + tokensDto.refreshToken();
        tokenSaver.save(Token.builder().id(userKey).refreshToken(tokensDto.refreshToken()).build());
        return tokensDto;
    }

    @Transactional
    public void logout(final Long userId){
        Token refreshToken = tokenRetriever.findById(userId);
        tokenRemover.deleteToken(refreshToken);
    }

    @Transactional
    public JwtTokensDto googleLogin(final String code) {
        GoogleTokenResponse googleTokenResponse = googleService.getToken(code, clientId, clientSecret, redirectUrl);
        GoogleUserInfoResponse googleUserInfoResponse = googleService.getUserInfo(googleTokenResponse.accessToken());
        User user = userRetriever.findBySerialIdAndEmailOrGet(
                googleUserInfoResponse.sub(),
                googleUserInfoResponse.givenName(),
                googleUserInfoResponse.familyName(),
                googleUserInfoResponse.picture(),
                googleUserInfoResponse.email()
        );
        if (user.getFamilyName() == null && user.getGivenName() == null){
            userUpdater.updateName(user, googleUserInfoResponse.givenName(), googleUserInfoResponse.familyName());
        }
        JwtTokensDto jwtTokensDto = jwtUtil.generateTokens(user.getId());

        String userKey = user.getId() + ":" + jwtTokensDto.refreshToken();
        tokenSaver.save(Token.builder().id(userKey).refreshToken(jwtTokensDto.refreshToken()).build());
        return jwtTokensDto;
    }
}
