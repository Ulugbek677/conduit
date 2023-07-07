package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.model.Article;

import java.util.stream.Collectors;

public class ArticleMapper {
    public static Article toEntity(ArticleResponse articleResponse) {
        return null;
    }

    public static ArticleDTO toDto(Article article) {
        return new ArticleDTO(
                        article.getId(),
                article.getTitle().concat("-").concat(String.valueOf(article.getId())),
                        article.getTitle(),
                        article.getDescription(),
                        article.getBody(),
                        article.getTags().stream()
                                .map(TagMapper::toDto)
                                .collect(Collectors.toList()),
                        article.getCreatedat(),
                        article.getUpdatedat(),
                        false,
                        0L,
                        UserMapper.toDto(article.getAuthor())
                );
    }

    public static Article toEntity(ArticleDTO articleDTO){
        return new Article(
                articleDTO.getId(),
                articleDTO.getTitle(),
                articleDTO.getDescription(),
                articleDTO.getBody(),
                articleDTO.getTagList().stream()
                        .map(TagMapper::toEntity)
                        .collect(Collectors.toList()),
                articleDTO.getCreatedAt(),
                articleDTO.getUpdateAt(),
                UserMapper.toEntity(articleDTO.getAuthor())

        );
    }

}
