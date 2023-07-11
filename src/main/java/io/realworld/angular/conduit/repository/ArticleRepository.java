package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryExtension {

}
