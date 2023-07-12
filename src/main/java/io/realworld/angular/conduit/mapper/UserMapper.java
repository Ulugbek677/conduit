package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.User;

public class UserMapper {

    public static ProfileDTO toDto(User user){
        return new ProfileDTO(
                user.getUsername(),
                user.getBio(),
                user.getImageUrl(),
                false
        );
    }

    public static User toEntity(ProfileDTO profileDTO){
        return null;
    }
}