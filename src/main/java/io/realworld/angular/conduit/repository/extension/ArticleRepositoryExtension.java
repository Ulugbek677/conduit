package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.response.ArticleResponse;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {
    List<ArticleResponse> getArticlePageableLikesPostAuthorPost(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset, Optional<String> favorited, Optional<String> tag);
    boolean isFavorited(Long userId, Long articleId);


}
