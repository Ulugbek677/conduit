package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne(targetEntity = Article.class)
    private Article article;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
