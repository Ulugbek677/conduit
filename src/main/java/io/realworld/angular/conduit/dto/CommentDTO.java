package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    @JsonProperty(value = "author")
    private ProfileDTO profileDto;
    @JsonProperty(value = "article")
    private ArticleDTO articleDto;

    private String body;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
