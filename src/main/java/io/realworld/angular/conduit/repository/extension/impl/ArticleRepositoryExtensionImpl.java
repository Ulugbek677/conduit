package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.LikeRepository;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final EntityManager entityManager;
    @Override
    public List<ArticleResponse> getArticlePageableLikesPostAuthorPost(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset, Optional<String> favorited, Optional<String> tag) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Article> query = cb.createQuery(Article.class);
        Root<Article> articleRoot = query.from(Article.class);

        List<Predicate> predicates = new ArrayList<>();


        if (author.isPresent()) {
            Join<Article, User> joinAuthor = articleRoot.join("author");
            predicates.add(cb.equal(joinAuthor.get("username"), author.get()));
        }


//        public List<Article> getArticlesPageable(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag)
//        {
//            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
//            Root<Article> root = query.from(Article.class);
//
//            List<Predicate> predicates = new ArrayList<>();
//
        //            author.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("profile").get("user").get("username"),value)));
        //
//            favorited.ifPresent(value->{
//                ListJoin<Article, Profile> profileJoin = root.joinList("likes");
//                predicates.add(criteriaBuilder.equal(profileJoin.get("user").get("username"),value));
//            });
//
//            tag.ifPresent(value->{
//                ListJoin<Article, Tag> tagJoin = root.joinList("tags");
//                predicates.add(criteriaBuilder.equal(tagJoin.get("name"),value));
//            });
//
//            CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new));
//            return entityManager.createQuery(cQuery).setFirstResult(offset).setMaxResults(limit).getResultList();
//        }


        return null;
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
