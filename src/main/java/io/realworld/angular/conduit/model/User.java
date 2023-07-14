package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
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
            joinColumns = {@JoinColumn(table = "users", referencedColumnName = "id", name = "follower_id")},
            inverseJoinColumns = {
                    @JoinColumn(table = "users", referencedColumnName = "id", name = "user_id")})
    private List<User> followers;

}
