package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.User;

import java.time.LocalDate;
import java.util.List;

public class ArticleDTO {
    String slug;
    String title;
    String description;
    String body;
    List<TagDTO> tagList;
    LocalDate createdAt;
    LocalDate update;
    Boolean favorited;
    Long favoritesCount;
    UserDTO author;
}
