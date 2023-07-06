package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.model.User;

public class UserMapper {

    public static UserDTO toDto(User user){
        return new UserDTO(
            user.getUsername(),
            user.getBio(),
            user.getImageUrl(),
            null,
            user.getEmail(),
            user.getPassword()
        );
    }

    public static User toEntity(UserDTO userDTO){
        return new User(
                null,
                userDTO.getImageUrl(),
                userDTO.getUsername(),
                userDTO.getBio(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                null,
                null

        );
    }
}
