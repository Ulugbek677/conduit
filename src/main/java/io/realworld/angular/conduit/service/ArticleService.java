package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getById(String slug);


    void deleteArticle(String slug);


    ResponseEntity<ArticleResponse> updateArticle(ArticleResponse articleResponse);

    ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse);

    ResponseEntity<ArticleResponse> getArticlesPageable(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset,Optional<String> favorited,Optional<String> tag);

    ResponseEntity<ArticleResponse> likeArticle(String slug);

    ResponseEntity<ArticleResponse> getArticlesByToken(Integer limit, Integer offset);

    ResponseEntity<ArticleResponse> deleteLike(String slug);

    ResponseEntity<CommentResponse> addComment(String slug, CommentResponse commentResponse);

    void deleteComment(String slug, Long id);

    ResponseEntity<CommentResponse> getArticleComments(String slug);
}
