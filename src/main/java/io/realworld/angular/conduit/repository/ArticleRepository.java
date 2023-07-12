package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryExtension {
    @Query(value = "select count(*) from likes where article_id = ?1",nativeQuery = true)
    Long getLikesCount(Long article_id);

    @Query(value = "\n" +
            "select case when count(*) > 0 then 'true' else 'false' end\n" +
            "from LIKES where ARTICLE_ID=? and USER_ID = ?", nativeQuery = true)
    String isFavorited(Long articleId, Long userId);

    @Query(value = "insert into likes values(?,?)", nativeQuery = true)
    public void likeArticle(Long articleId, Long userId);


}
