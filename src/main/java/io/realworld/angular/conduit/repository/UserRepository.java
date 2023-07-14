package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select case when count(*) > 0 then 'true' else 'false' end " +
            "from articles a join follows f " +
            "on a.author_id = f.user_id " +
            "where a.author_id = ? and f.follower_id = ? ", nativeQuery = true)
    String isFollowedToArticleOwner(Long authorId, Long userId);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
