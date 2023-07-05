package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String  email;

    @Column(nullable = false)
    private String userName;
    private String bio;
    private String imageUrl;
    @Column(nullable = false)
    private  String password;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_id")
    private List<Article> articles;
*/
}
