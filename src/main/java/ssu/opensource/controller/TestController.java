package ssu.opensource.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssu.opensource.annotation.UserId;
import ssu.opensource.domain.Token;
import ssu.opensource.dto.auth.JwtTokensDto;
import ssu.opensource.dto.test.TestDto;
import ssu.opensource.dto.test.TestInput;
import ssu.opensource.service.token.TokenSaver;
import ssu.opensource.utils.JwtUtil;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    private final JwtUtil jwtUtil;
    private final TokenSaver tokenSaver;

    @GetMapping("/test/security")
    public ResponseEntity<TestDto> testSecurity(
            @UserId final Long userId,
            @Valid @RequestBody final TestInput testInput
    ) {
        return ResponseEntity.ok(TestDto.builder().content(testInput.name() + " " + userId).build());
    }

    @GetMapping("/test/token/{userId}")
    public ResponseEntity<JwtTokensDto> testToken(
            @PathVariable final Long userId
    ) {
        JwtTokensDto tokens = jwtUtil.generateTokens(userId);
        tokenSaver.save(Token.builder().id(userId).refreshToken(tokens.refreshToken()).build());
        return ResponseEntity.ok(tokens);
    }
}
