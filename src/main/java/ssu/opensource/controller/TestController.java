package ssu.opensource.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssu.opensource.annotation.UserId;
import ssu.opensource.dto.test.TestDto;
import ssu.opensource.dto.test.TestInput;
import ssu.opensource.utils.JwtUtil;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    private final JwtUtil jwtUtil;

    @GetMapping("/test/security")
    public ResponseEntity<TestDto> testSecurity(
            @UserId final Long userId,
            @Valid @RequestBody final TestInput testInput
    ) {
        return ResponseEntity.ok(TestDto.builder().content(testInput.name() + " " + userId).build());
    }
}
