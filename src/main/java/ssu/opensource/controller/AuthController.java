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

    @PostMapping("/auth/login/google")
    public ResponseEntity<JwtTokensDto> googleLogin(
            @RequestParam final String code
    ) {
        return ResponseEntity.ok(authService.googleLogin(code));
    }

    @PostMapping("/auth/re-issue")
    public ResponseEntity<JwtTokensDto> reissueToken(
            @UserId Long userId
    ){
        return ResponseEntity.ok(authService.reissueToken(userId));
    }

    @DeleteMapping("/auth/logout")
    public ResponseEntity<Void> logout(
            @UserId final Long userId
    ){
        authService.logout(userId);
        return ResponseEntity.noContent().build();
    }
}