package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ArticleResponse;
import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.model.Article;

import java.time.LocalDate;
import java.util.List;

public class ArticleMapper {
    public static Article toEntity(ArticleResponse articleResponse){
        return new Article(
                null,
                articleResponse.getArticle().getTitle(),
                articleResponse.getArticle().getDescription(),
                articleResponse.getArticle().getBody(),
                null,
                null,
                UserMapper.toEntity(articleResponse.getArticle().getAuthor()),
                articleResponse.getArticle().getCreatedAt(),
                articleResponse.getArticle().getUpdateAt()
        );
    }
    public static ArticleResponse toDto(Article article){
//        ArticleDTO articleDTO = new ArticleDTO(
//                null,
//                null,
//                article.getTitle(),
//                article.getDescription(),
//                article.getBody(),
//                article.getTags()
//                                .stream()
//                                        .map(tag -> TagMapper.toDto())
//                article.getPublishDate(),
//                article.getUpdateDate(),
//                null,
//                0L,
//                UserMapper.toDto(article.getAuthor())
//
//        );



        return new ArticleResponse(

        );
    }

}
