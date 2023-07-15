package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.customexseptions.NoResourceFoundException;
import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.mapper.CommentMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;



    @Override
    public ResponseEntity<ArticleResponse> getById(String slug) {
        String[] split = slug.split("-");
        if (split.length>1) {


            Long id = Long.parseLong(split[split.length - 1]);


            Optional<Article> optionalArticle = articleRepository.findById(id);
            if (optionalArticle.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            Article article = optionalArticle.get();

            ArticleDTO articleDTO = articleMapper.toDto(article);

            return ResponseEntity.ok(ArticleResponse
                    .builder()
                    .article(articleDTO)
                    .build());
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public void deleteArticle(String slug) {
        String[] split = slug.split("-");
        Long id = Long.parseLong(split[split.length-1]);
        articleRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<ArticleResponse> updateArticle(ArticleResponse articleResponse) {
        ArticleDTO articleDTO = articleResponse.getArticle();
        Article article = articleRepository.save(articleMapper.toEntity(articleDTO));
        return ResponseEntity.ok(ArticleResponse
                .builder()
                .article(articleMapper.toDto(article))
                .build());
    }

    @Override
    public ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse) {
        ArticleDTO articleDTO = articleResponse.getArticle();
        articleDTO.setCreatedAt(LocalDateTime.now());
        Article article = articleMapper.toEntity(articleDTO);
        if (article.getAuthor() == null) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NoResourceFoundException("user not found"));
            article.setAuthor(user);
        }
        article = articleRepository.save(article);
        articleDTO = articleMapper.toDto(article);
        return ResponseEntity.ok(ArticleResponse
                .builder()
                .article(articleDTO)
                .build());
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticlesPageable(Optional<String> author, Optional<Integer> limit, Optional<Integer> offset, Optional<String> favorited, Optional<String> tag) {
        List<Article> articles = articleRepository.getArticlePageable(author, limit, offset, favorited, tag);
        List<ArticleDTO> dto = articles.stream().map(articleMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(ArticleResponse.builder().articles(dto).build());
    }

    @Override
    public ResponseEntity<ArticleResponse> likeArticle(String slug) {
        String[] split = slug.split("-");
        Long id = Long.parseLong(split[split.length-1]);
        //TODO userid change
        Long userId = 1L;
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(" article not found"));

        if (!articleRepository.isFavorited(id, userId).equalsIgnoreCase("true")){
            articleRepository.likeArticle(id, userId);
        }

        ArticleDTO articleDTO = articleMapper.toDto(article);


        return ResponseEntity.ok(ArticleResponse.builder().article(articleDTO).build());
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticlesByToken(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleResponse> deleteLike(String slug) {
        return null;
    }

    @Override
    public ResponseEntity<CommentResponse> addComment(String slug, CommentResponse commentResponse) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        System.out.println(commentResponse.getComment());
        CommentDTO commentDTO = commentResponse.getComment();
        Comment comment = commentMapper.toEntity(commentDTO);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new NoResourceFoundException("user not found"));
        Article article = articleRepository.findById(id).orElseThrow(()->new NoResourceFoundException("Article not found"));
        comment.setArticle(article);
        comment.setAuthor(user);
        comment.setCreatedAt(LocalDateTime.now());
        Comment save = commentRepository.save(comment);
        CommentDTO dto = commentMapper.toDto(save);
        return ResponseEntity.ok(CommentResponse.builder().comment(dto).build());
    }

    @Override
    public void deleteComment(String slug, Long id) {

    }

    @Override
    public ResponseEntity<CommentResponse> getArticleComments(String slug) {
        String[] split = slug.split("-");
        Long id = Long.parseLong(split[split.length-1]);
        Article article = articleRepository.findById(id).orElseThrow(()->new NoResourceFoundException("Article not found"));

        List<Comment> comments = commentRepository.findByArticle(article);
        List<CommentDTO> dto = comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CommentResponse.builder().comments(dto).build());
    }


}
