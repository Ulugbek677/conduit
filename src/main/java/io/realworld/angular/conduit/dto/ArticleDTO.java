package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<TagDTO> tagList;
    private LocalDate createdAt;
    private LocalDate updateAt;
    private Boolean favorited;
    private Long favoritesCount;
    private UserDTO author;
}
