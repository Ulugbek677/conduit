package io.realworld.angular.conduit.repository.extension;

public interface UserRepositoryExtension {
    void addFollower(Long userId, Long followerId);

    boolean isCurrentUserFollow(Long userId, Long followerId);

    void deleteFollower(Long id, Long id1);
}
