package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.*;
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query(value = "select t.name from ARTICLES\n" +
            "            join article_tag a on ARTICLES.id = a.article_id\n" +
            "            join tag t on a.tag_id = t.id\n" +
            "            group by t.id,t.name\n" +
            "            order by count(*) desc\n" +
            "            FETCH NEXT 5 ROWS ONLY",nativeQuery = true)
    List<String> getPopularTags();

    Optional<Tag> findByName(String name);
}
