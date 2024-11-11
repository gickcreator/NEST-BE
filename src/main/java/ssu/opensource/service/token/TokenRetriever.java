package ssu.opensource.service.token;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Token;
import ssu.opensource.exception.NotFoundException;
import ssu.opensource.exception.code.NotFoundErrorCode;
import ssu.opensource.repository.TokenRepository;

@Component
@RequiredArgsConstructor
public class TokenRetriever {
    private final TokenRepository tokenRepository;

    public Long findMemberIdByRefreshToken(final String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_REFRESH_TOKEN)
        ).getId();
    }

    public Token findById(final Long userId){
        return tokenRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_REFRESH_TOKEN)
        );
    }
}
