package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getById(String slug);


    void deleteArticle(String slug);


    ResponseEntity<ArticleResponse> updateArticle(ArticleResponse articleResponse);

    ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse);

    ResponseEntity<List<ArticleResponse>> getSortByPageable(Optional<Date> sortColumnName, Optional<Integer> pageNum, Optional<Integer> size);
}
