package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryExtension {
    @Query(value = "select count(*) from ARTICLES a join LIKES l " +
            "on a.ID = l.ARTICLE_ID where a.id = ?", nativeQuery = true)
    long getFavoritesCount(Long id);

}
