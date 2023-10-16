package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {

    private final EntityManager entityManager;

    @Override
    public List<Article> getArticlePageable(Optional<String> author, Integer limit, Integer offset, Optional<String> favorited, Optional<String> tag) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);

        List<Predicate> predicates = new ArrayList<>();


        author.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("author").get("username"),value)));


        favorited.ifPresent(value->{
            ListJoin<Article, User> userJoin = root.joinList("likes");
            predicates.add(criteriaBuilder.equal(userJoin.get("username"),value));
        });

        tag.ifPresent(value->{
            ListJoin<Article, Tag> tagJoin = root.joinList("tagList");
            predicates.add(criteriaBuilder.equal(tagJoin.get("name"),value));
        });

        CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new));

        TypedQuery<Article> query1 = entityManager.createQuery(cQuery);
        query1.setFirstResult(offset);
        query1.setMaxResults(limit);
        List<Article> resultList = query1.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public void likeArticle(Long articleId, Long userId) {
        String query = "insert into likes values(?,?)";
        entityManager.createNativeQuery(query)
                .setParameter(1,articleId)
                .setParameter(2,userId)
                .executeUpdate();
    }
    @Transactional
    @Override
    public boolean isCurrentUserLiked(Long articleId,Long userId) {
        String query = "select * from likes where article_id = ? and user_id = ?";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter(1, articleId)
                .setParameter(2, userId)
                .getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public List<Article> getArticlesByFollower(Long id,Integer limit, Integer offset) {
        String query = "select a.* from articles a where a.author_id in\n" +
                "                         (select user_id from follows where follower_id = ?)\n" +
                "order by a.updatedat desc";
        return entityManager.createNativeQuery(query,Article.class)
                .setParameter(1,id)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();

    }

    @Override
    @Transactional
    public void deleteLike(Long userId, Long articleId) {
        String query = "delete from likes where article_id = ? and user_id = ?";
        entityManager.createNativeQuery(query)
                .setParameter(1,articleId)
                .setParameter(2,userId)
                .executeUpdate();
    }


}

