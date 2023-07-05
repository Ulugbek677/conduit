package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String body;
    @ManyToOne(targetEntity = User.class)
    User author;
    @ManyToOne(targetEntity = Article.class)
    Article article;

    LocalDateTime createdAt;
}
