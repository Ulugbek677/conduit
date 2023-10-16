package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.customexseptions.NoResourceFoundException;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    @Override
    public ResponseEntity<ProfileDTO> getProfileByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new NoResourceFoundException("Profile not found"));
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @Override
    public ResponseEntity<ProfileDTO> addFollower(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User follower = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        userRepository.addFollower(user.getId(),follower.getId());
        ProfileDTO profileDto = UserMapper.toDto(user);
        boolean currentUserFollow = userRepository.isCurrentUserFollow(user.getId(), follower.getId());
        profileDto.setFollowing(currentUserFollow);
        return ResponseEntity.ok(profileDto);
    }

    @Override
    public ResponseEntity<ProfileDTO> deleteFollower(String username) {
        User user =userRepository.findByUsername(username)
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User follower = userRepository.findByUsername(email)
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        userRepository.deleteFollower(user.getId(),follower.getId());
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
}
