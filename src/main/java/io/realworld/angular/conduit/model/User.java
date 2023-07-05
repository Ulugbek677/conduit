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
    @JoinTable(name = "follows",
            joinColumns = {@JoinColumn(table = "users", referencedColumnName = "id", name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(table = "users", referencedColumnName = "id", name = "user_id")}) private List<User> likes;
    private List<User> followers;
}
