package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.customexseptions.NoResourceFoundException;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    @GetMapping("/{username}")
    public ResponseEntity<ProfileDTO> getProfileByUsername(@PathVariable String username){
        return profileService.getProfileByUsername(username);
    }


    @PostMapping("/{username}/follow")
    public ResponseEntity<ProfileDTO> addFollower(@PathVariable String username){
        return profileService.addFollower(username);
    }

    @DeleteMapping("/{username}/follow")
    public ResponseEntity<ProfileDTO> deleteFollower(@PathVariable String username){
        return profileService.deleteFollower(username);
    }
}
