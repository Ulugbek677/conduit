package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.LoginDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.JWTGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final JWTGeneratorService jwtGeneratorService;
    private final UserRepository userRepository;

    public static ProfileDTO toDto(User user){
        return new ProfileDTO(
                user.getUsername(),
                user.getBio(),
                user.getImageUrl(),
                false
        );
    }

    public  User toEntity(ProfileDTO profileDTO){
        if(profileDTO == null) return null;
        AtomicReference<User> us = new AtomicReference<>(new User(
                null,
                profileDTO.getImage(),
                profileDTO.getUsername(),
                profileDTO.getBio(),
                null,
                null,
                null
        ));
        userRepository.findByUsername(profileDTO.getUsername()).ifPresentOrElse(u -> {
            us.get().setId(u.getId());
            us.get().setEmail(u.getEmail());
            us.get().setPassword(u.getPassword());
            us.get().setFollowers(u.getFollowers());
        }, () -> {
            us.set(userRepository.save(us.get()));
        });
        return us.get();
    }

    public  User toEntityForLoginDto(LoginDTO loginDTO){
        return new User(
                loginDTO.getId(),
                loginDTO.getImagePath(),
                loginDTO.getUsername(),
                loginDTO.getBio(),
                loginDTO.getEmail(),
                loginDTO.getPassword(),
                null

        );
    }


    public LoginDTO toLoginDto(User user){

        return new LoginDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getBio(),
                user.getImageUrl(),
                jwtGeneratorService.jwtGenerate()
        );
    }
}