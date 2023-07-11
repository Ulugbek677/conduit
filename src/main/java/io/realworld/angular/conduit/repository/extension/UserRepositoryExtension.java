package io.realworld.angular.conduit.repository.extension;

public interface UserRepositoryExtension {
    Boolean isFollowedToArticleOwner(Long authorId, Long userId);
}
