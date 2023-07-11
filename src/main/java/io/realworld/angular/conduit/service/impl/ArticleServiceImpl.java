package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;
    private final EntityManager entityManager;


    @Override
    public ResponseEntity<ArticleResponse> getById(String slug) {
        int i = slug.lastIndexOf("-");
        Long id = Long.valueOf(slug.substring(i + 1));
        System.out.println("fjd;lafjdla;fjad;lfjadl;kfj"+" " + id);


        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Article article = optionalArticle.get();
        System.out.println(article.getTitle());
        ArticleDTO articleDTO = articleMapper.toDto(article, articleRepository, userRepository);

        return ResponseEntity.ok(ArticleResponse
                .builder()
                .article(articleDTO)
                .build());
    }

    @Override
    public void deleteArticle(String slug) {
        int i = slug.lastIndexOf("-");
        Long id = Long.valueOf(slug.substring(i + 1));
        articleRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<ArticleResponse> updateArticle(ArticleResponse articleResponse) {
        ArticleDTO articleDTO = articleResponse.getArticle();
        Article article = articleRepository.save(articleMapper.toEntity(articleDTO));
        return ResponseEntity.ok(ArticleResponse
                .builder()
                .article(articleMapper.toDto(article, articleRepository, userRepository))
                .build());
    }

    @Override
    public ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse) {
        ArticleDTO articleDTO = articleResponse.getArticle();
        Article article = articleMapper.toEntity(articleDTO);
        article = articleRepository.save(article);
        articleDTO = articleMapper.toDto(article, articleRepository, userRepository);
        return ResponseEntity.ok(ArticleResponse
                .builder()
                .article(articleDTO)
                .build());
    }

    @Override
    public ResponseEntity<List<ArticleResponse>> getArticlesPageable(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset, Optional<String> favorited, Optional<String> tag) {
        return ResponseEntity.ok(articleRepository.getArticlePageableLikesPostAuthorPost(author, limit, offset, favorited, tag));
    }


}
