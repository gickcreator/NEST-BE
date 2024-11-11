package ssu.opensource.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Token;
import ssu.opensource.repository.TokenRepository;

@Component
@RequiredArgsConstructor
public class TokenRemover {
    private final TokenRepository tokenRepository;
    public void deleteToken(final Token token) {tokenRepository.delete(token);}
}
