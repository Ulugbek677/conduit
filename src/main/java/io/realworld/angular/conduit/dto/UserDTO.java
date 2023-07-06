package io.realworld.angular.conduit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String bio;
    private String imageUrl;
    private Boolean following;

    private String email;
    private String password;

}
