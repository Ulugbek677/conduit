//package io.realworld.angular.conduit.mapper;
//
//import io.realworld.angular.conduit.dto.ArticleDTO;
//import io.realworld.angular.conduit.model.Article;
//import org.mapstruct.*;
//
////@Mapper(componentModel = "spring")
//public interface ArticleMapper  {
////    @Mapping(target = "slug", source = "article", qualifiedByName = "toDtoSlug")
////    @Mapping(target = "favorited", source = "article", qualifiedByName = "toDtoFavorited")
////    @Mapping(target = "favoritesCount", source = "article", qualifiedByName = "toDtoFavoritesCount")
////    @Mapping(target = "author", source = "article", qualifiedByName = "toDtoAuthor")
////    @Mapping(target = "favorited", ignore = true)
//    ArticleDTO toDto(Article article);
////
////    @BeforeMapping
////    default void changeDto(@MappingTarget ArticleDTO articleDTO, Article article){
////        articleDTO =  changeDto(article);
////    }
//
//    Article toEntity(ArticleDTO articleDTO);
//
////    @Named("toDtoSlug")
////    default String toDtoSlug(Article article){
////        if (article == null) return null;
////        return article.getTitle().concat("-").concat(String.valueOf(article.getId()));
////    }
////
////    @Named("toDtoFavorited")
////    default boolean toDtoFavorited(@Context UserRepository userRepository, Article article){
////        if (article == null) return false;
////        Long id = article.getId();
////        //TODO change userId
////        return userRepository.isFollowedToArticleOwner( id, 1L).equalsIgnoreCase("true");
////    }
////
////    @Named("toDtoFavoritesCount")
////    default Long toDtoFavoritesCount(@Context ArticleRepository articleRepository, Article article){
////        if (article == null) return null;
////        return articleRepository.getFavoritesCount(article.getId());
////    }
////
////    @Named("toDtoAuthor")
////    default ProfileDTO toDtoAuthor(@Context UserRepository userRepository, Article article){
////        //TODO change userId
////        if (article == null) return null;
////        ProfileDTO profileDTO = new ProfileDTO();
////        profileDTO.setBio(article.getAuthor().getBio());
////        profileDTO.setUsername(article.getAuthor().getUsername());
////        profileDTO.setImage(article.getAuthor().getImageUrl());
////        profileDTO.setFollowing(userRepository.isFollowedToArticleOwner(article.getAuthor().getId(), 0L).equalsIgnoreCase("true"));
////        return profileDTO;
////    }
//
////    public static Article toEntity(ArticleResponse articleResponse) {
////        return null;
////    }
////
////    public static ArticleDTO toDto(Article article) {
////        return new ArticleDTO(
////                article.getId(),
////                article.getTitle().concat("-").concat(String.valueOf(article.getId())),
////                article.getTitle(),
////                article.getDescription(),
////                article.getBody(),
////                article.getTags().stream()
////                        .map(TagMapper::toDto)
////                        .collect(Collectors.toList()),
////                article.getCreatedat(),
////                article.getUpdatedat(),
////                false,
////                0L,
////                UserMapper.toDto(article.getAuthor())
////        );
////    }
////
////    public static Article toEntity(ArticleDTO articleDTO) {
////        return new Article(
////                articleDTO.getId(),
////                articleDTO.getTitle(),
////                articleDTO.getDescription(),
////                articleDTO.getBody(),
////                articleDTO.getTagList().stream()
////                        .map(TagMapper::toEntity)
////                        .collect(Collectors.toList()),
////                articleDTO.getCreatedAt(),
////                articleDTO.getUpdateAt(),
////                UserMapper.toEntity(articleDTO.getAuthor())
////
////        );
////    }
//
//}