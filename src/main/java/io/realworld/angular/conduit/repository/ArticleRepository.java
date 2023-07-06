package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "select count(*) from ARTICLES a join LIKES l on a.ID = l.ARTICLE_ID where a.id = ?", nativeQuery = true)
    long getFavoritesCount(Long id);

    @Query(value = "select case when count(*) > 0 then true else false end\n" +
            "from ARTICLES a join LIKES l on a.ID = l.ARTICLE_ID\n" +
            "where l.USER_ID = ? and a.ID = ?", nativeQuery = true)
    boolean isFavorited(Long userId, Long id);
}
