package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getArticle(String slug);
}
