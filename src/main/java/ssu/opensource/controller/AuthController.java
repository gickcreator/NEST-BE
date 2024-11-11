package ssu.opensource.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssu.opensource.annotation.UserId;
import ssu.opensource.dto.auth.JwtTokensDto;
import ssu.opensource.service.auth.AuthService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login/google")
    public ResponseEntity<Void> googleLogin(
            @RequestParam final String code
    ) throws IOException {
        authService.googleLogin(code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/re-issue")
    public ResponseEntity<JwtTokensDto> reissueToken(
            @RequestHeader("Authorization") final String refreshToken
    ){
        return ResponseEntity.ok(authService.reissueToken(refreshToken));
    }

    @DeleteMapping("/auth/logout")
    public ResponseEntity<?> logout(
            @UserId final Long userId
    ){
        authService.logout(userId);
        return ResponseEntity.noContent().build();
    }
}