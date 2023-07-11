package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.LikeRepository;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final ArticleMapper articleMapper;
    private final EntityManager entityManager;
    @Override
    public ArticleResponse getArticlePageable(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset, Optional<String> favorited, Optional<String> tag) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);

        List<Predicate> predicates = new ArrayList<>();


        author.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("author").get("username"),value)));

        CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new));

        TypedQuery<Article> query1 = entityManager.createQuery(cQuery);

        offset.ifPresent(query1::setFirstResult);
        limit.ifPresent(query1::setMaxResults);
        List<Article> resultList = query1.getResultList();
        return ArticleResponse.builder().articles(resultList.stream().map(articleMapper::toDto).collect(Collectors.toList())).build();
    }

//    @Override
//    public boolean isFavorited(Long userId, Long articleId) {
//        String query = "select *\n" +
//                "            from  LIKES l\n" +
//                "            where l.USER_ID = ? and l.ARTICLE_ID = ?";
//        List resultList = entityManager.createNativeQuery(query)
//                .setParameter(1, userId)
//                .setParameter(2, articleId)
//                .getResultList();
//        return resultList.size()>0;
//    }
}
