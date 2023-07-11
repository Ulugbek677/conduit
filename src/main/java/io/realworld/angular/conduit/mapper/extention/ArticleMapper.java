package io.realworld.angular.conduit.mapper.extention;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.LikeRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {})
public abstract class ArticleMapper{
    @Autowired
    protected LikeRepository likeRepository;
    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "author", source = "article", qualifiedByName = "toDtoAuthor")
    @Mapping(target="favoritesCount", expression="java(likeRepository.countByArticleId(article.getId()))")
    @Mapping(target="favorited", expression="java(likeRepository.existsByUserIdAndArticleId(4L, article.getId()))")
    public abstract ArticleDTO toDto(Article article);


    @Named("toDtoAuthor")
    public ProfileDTO toDtoAuthor(Article article){
        //TODO change userId
        if (article == null) return null;
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setBio(article.getAuthor().getBio());
        profileDTO.setUsername(article.getAuthor().getUsername());
        profileDTO.setImage(article.getAuthor().getImageUrl());
        profileDTO.setFollowing(userRepository.isFollowedToArticleOwner(article.getAuthor().getId(), 4L).equalsIgnoreCase("true"));
        return profileDTO;
    }

    public abstract Article toEntity(ArticleDTO articleDTO);
}
