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
    private Long id;
    private String body;
    @ManyToOne(targetEntity = User.class)
    private User author;
    @ManyToOne(targetEntity = Article.class)
    private Article article;

    private LocalDateTime createdAt;
}
