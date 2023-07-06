package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private  final UserRepository userRepository;

    @Override
    public ResponseEntity<ArticleResponse> getArticle(String slug) {
        int i = slug.lastIndexOf("-");
        Long id = Long.valueOf(slug.substring(i + 1));

        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        //TODO change userId
        boolean favorited = articleRepository.isFavorited(0L, id);
        Boolean following = userRepository.isFollowedToArticleOwner(0L, id);
        long favoritesCount = articleRepository.getFavoritesCount(id);
        Article article = optionalArticle.get();
        ArticleDTO articleDTO = ArticleMapper.toDto(article);
        articleDTO.getAuthor().setFollowing(following);
        articleDTO.setFavorited(favorited);
        articleDTO.setFavoritesCount(favoritesCount);
        return ResponseEntity.ok(new ArticleResponse(articleDTO));
    }
}
