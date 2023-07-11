package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
