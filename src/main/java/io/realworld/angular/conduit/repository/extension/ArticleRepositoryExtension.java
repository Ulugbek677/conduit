package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {
    List<Article> getArticlePageable(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset, Optional<String> favorited, Optional<String> tag);





}
