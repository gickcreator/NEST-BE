package ssu.opensource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssu.opensource.annotation.UserId;
import ssu.opensource.dto.user.UserDto;
import ssu.opensource.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(@UserId final Long userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
