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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private  final UserRepository userRepository;


    @Override
    public ResponseEntity<ArticleResponse> getById(String slug) {
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

    @Override
    public void deleteArticle(String slug) {
        int i = slug.lastIndexOf("-");
        Long id = Long.valueOf(slug.substring(i+1));
        articleRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<ArticleResponse> updateArticle(ArticleResponse articleResponse) {
        ArticleDTO articleDTO = articleResponse.getArticle();
        Article article = articleRepository.save(ArticleMapper.toEntity(articleDTO));
        return ResponseEntity.ok(new ArticleResponse(ArticleMapper.toDto(article)));
    }

    @Override
    public ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse) {
        ArticleDTO articleDTO = articleResponse.getArticle();
        Article article = ArticleMapper.toEntity(articleDTO);


        return ResponseEntity.ok(new ArticleResponse(
                ArticleMapper.toDto( articleRepository.save(article)))
        );
    }

    @Override
    public ResponseEntity<List<ArticleResponse>> getSortByPageable(Optional<Date> sortColumnName, Optional<Integer> pageNum, Optional<Integer> size) {
        return null;
    }


}
