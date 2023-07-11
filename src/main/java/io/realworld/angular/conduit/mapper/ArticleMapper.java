package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.LikeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ArticleMapper{
    @Autowired
    protected LikeRepository likeRepository;

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "author", source = "article", qualifiedByName = "toDtoAuthor")
    @Mapping(target="favoritesCount", expression="java(likeRepository.countByArticleId(article.getId()))")
    @Mapping(target="favorited", expression="java(likeRepository.existsByUserIdAndArticleId(4L, article.getId()))")
    public abstract ArticleDTO toDto(Article article);

    @Named("toDtoAuthor")
    public ProfileDTO toDtoAuthor(Article article){
        return userMapper.toDto(article.getAuthor());
    }

    public abstract Article toEntity(ArticleDTO articleDTO);
}
