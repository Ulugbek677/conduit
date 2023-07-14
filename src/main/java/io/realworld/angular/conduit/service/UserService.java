package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.UserResponseWithToken;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponseWithToken> loginUser(UserResponseWithToken userResponseWithToken);

    ResponseEntity<UserResponseWithToken> registerUser(UserResponseWithToken userResponseWithToken);

    ResponseEntity<UserResponseWithToken> getCurrentUser();

    ResponseEntity<UserResponseWithToken> updateUser(UserResponseWithToken userResponse);
}
