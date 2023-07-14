package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.response.UserResponseWithToken;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users/login")
    public ResponseEntity<UserResponseWithToken> loginUser(@RequestBody UserResponseWithToken userResponseWithToken){
        return userService.loginUser(userResponseWithToken);
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponseWithToken> registerUser(@RequestBody UserResponseWithToken userResponseWithToken){
        return userService.registerUser(userResponseWithToken);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseWithToken> getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    public ResponseEntity<UserResponseWithToken> updateProfile(@RequestBody UserResponseWithToken userResponseWithToken){
        return userService.updateUser(userResponseWithToken);
    }
}
