package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.customexseptions.EmailAlreadyRegisteredException;
import io.realworld.angular.conduit.customexseptions.NoResourceFoundException;
import io.realworld.angular.conduit.customexseptions.UsernameAlreadyRegisteredException;
import io.realworld.angular.conduit.dto.response.UserResponseWithToken;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<UserResponseWithToken> loginUser(UserResponseWithToken userResponseWithToken) {
        User user = userRepository
                .findByEmail(userResponseWithToken.getUser().getEmail())
                        .orElseThrow(() -> new NoResourceFoundException("Not found"));
        if( ! passwordEncoder.matches(userResponseWithToken.getUser().getPassword(),user.getPassword())){
            throw  new NoResourceFoundException("not authenticated");
        };

        Authentication auth = new UsernamePasswordAuthenticationToken
                (user.getEmail(), null,List.of(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(new UserResponseWithToken(userMapper.toLoginDto(user)));
    }

    @Override
    public ResponseEntity<UserResponseWithToken> registerUser(UserResponseWithToken userResponseWithToken) {

        userRepository.findByUsername(userResponseWithToken.getUser().getUsername())
                .ifPresent(value -> {throw new UsernameAlreadyRegisteredException("has already been taken");
                });

        userRepository.findByEmail(userResponseWithToken.getUser().getEmail())
                        .ifPresent(value -> {throw new EmailAlreadyRegisteredException("has already been taken");
                        });

        userResponseWithToken.getUser().setPassword(passwordEncoder.encode(userResponseWithToken.getUser().getPassword()));

        User save = userRepository.save(userMapper.toEntityForLoginDto(userResponseWithToken.getUser()));



        Authentication auth = new UsernamePasswordAuthenticationToken
                (save.getEmail(), null,List.of(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok( new UserResponseWithToken(userMapper.toLoginDto(save)));
    }
    @Override
    public ResponseEntity<UserResponseWithToken> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        return ResponseEntity.ok(new UserResponseWithToken(userMapper.toLoginDto(user)));
    }

    @Override
    public ResponseEntity<UserResponseWithToken> updateUser(UserResponseWithToken userResponseWithToken) {


        userRepository.findByUsername(userResponseWithToken.getUser().getUsername())
                .ifPresent(value -> {throw new UsernameAlreadyRegisteredException("has already been taken");
                });

        userRepository.findByEmail(userResponseWithToken.getUser().getEmail())
                .ifPresent(value -> {throw new EmailAlreadyRegisteredException("has already been taken");
                });


        User user = userMapper.toEntityForLoginDto(userResponseWithToken.getUser());



        User save1 = userRepository.save(user);

        return ResponseEntity.ok(new UserResponseWithToken(userMapper.toLoginDto(save1)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        return org.springframework.security.core.userdetails.User.builder().build();
    }
}
