package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    ResponseEntity<ProfileDTO> getProfileByUsername(String username);

    ResponseEntity<ProfileDTO> addFollower(String username);

    ResponseEntity<ProfileDTO> deleteFollower(String username);
}
