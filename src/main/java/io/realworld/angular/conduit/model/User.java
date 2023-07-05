package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String imageUrl;
    String username;
    String bio;
    String email;
    String password;
    @ManyToMany
    List<User> followers;
}
