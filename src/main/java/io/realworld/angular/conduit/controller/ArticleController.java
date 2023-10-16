package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleResponse articleResponse){
        return articleService.addArticle(articleResponse);
    }

    @PostMapping("/{slug}/favorite")
    public ResponseEntity<ArticleResponse> likeArticle(@PathVariable String slug){
        return articleService.likeArticle(slug);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable String slug){
        return articleService.getById(slug);
    }



    @GetMapping("/{slug}/comments")
    public ResponseEntity<CommentResponse> getArticleComments(@PathVariable String slug){
        return articleService.getArticleComments(slug);
    }
    @GetMapping()
    public ResponseEntity<ArticleResponse> getArticles(@RequestParam Optional<String> author,
                                                             @RequestParam Integer limit,
                                                             @RequestParam Integer offset,
                                                             @RequestParam Optional<String> favorited,
                                                             @RequestParam Optional<String> tag){
        return articleService.getArticlesPageable(author, limit, offset, favorited, tag);
    }

    @DeleteMapping("{slug}")
    public void deleteArticle(@PathVariable String slug){
        articleService.deleteArticle(slug);
    }

    @PutMapping()
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody ArticleResponse articleResponse){
        return articleService.updateArticle(articleResponse);
    }

    @GetMapping("/feed")
    public ResponseEntity<ArticleResponse> getArticlesByToken(@RequestParam Integer limit,
                                                          @RequestParam Integer offset){
        return articleService.getArticlesByToken(limit,offset);
    };

    @DeleteMapping("/{slug}/favorite")
    public ResponseEntity<ArticleResponse> deleteLike(@PathVariable String slug){
        return articleService.deleteLike(slug);
    }

    @PostMapping("/{slug}/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable String slug, @RequestBody CommentResponse commentResponse){
        return articleService.addComment(slug,commentResponse);
    }

    @DeleteMapping("/{slug}/comments/{id}")
    public void deleteComment(@PathVariable String slug, @PathVariable Long id){
        articleService.deleteComment(slug,id);
    }
}
