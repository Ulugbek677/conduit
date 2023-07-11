package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndArticleId(Long userId, Long articleId);

    long countByArticleId(Long articleId);

}
