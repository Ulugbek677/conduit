package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {
    List<Article> getArticlePageable(Optional<String> author, Integer limit, Integer offset, Optional<String> favorited, Optional<String> tag);

    void likeArticle(Long articleId,Long userId);
    boolean isCurrentUserLiked(Long articleId,Long userId);

    List<Article> getArticlesByFollower(Long id,Integer limit, Integer offset);

    void deleteLike(Long userId, Long articleId);


}
