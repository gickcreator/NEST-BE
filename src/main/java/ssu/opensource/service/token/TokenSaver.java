package ssu.opensource.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Token;
import ssu.opensource.repository.TokenRepository;

@Component
@RequiredArgsConstructor
public class TokenSaver {
    private final TokenRepository tokenRepository;
    public void save(final Token token) {tokenRepository.save(token);}
}
