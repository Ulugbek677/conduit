package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RequiredArgsConstructor
@Service
public class ArticleMapper {


   private final ArticleRepository articleRepository;
   private final UserMapper userMapper;
   private final TagMapper tagMapper;

    public  ArticleDTO toDto(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle().concat("-").concat(String.valueOf(article.getId())),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream().map(Tag::getName).collect(Collectors.toList()),
                article.getCreatedAt(),
                article.getUpdateAt(),
                articleRepository.isFavorited(article.getId(), 1L).equalsIgnoreCase("true"),
                articleRepository.getLikesCount(article.getId()),
                UserMapper.toDto(article.getAuthor())
        );
    }

    public  Article toEntity(ArticleDTO articleDTO) {
        if (articleDTO == null) return null;
        return new Article(
                articleDTO.getId(),
                articleDTO.getTitle(),
                articleDTO.getDescription(),
                articleDTO.getBody(),
                articleDTO.getTagList()
                        .stream()
                        .map(t -> tagMapper.toEntity(new TagDTO(t)))
                        .collect(Collectors.toList()),
                articleDTO.getCreatedAt(),
                articleDTO.getUpdateAt(),
                null,
                userMapper.toEntity(articleDTO.getAuthor())

        );
    }
}