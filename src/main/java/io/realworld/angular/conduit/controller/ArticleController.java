package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.response.ArticleResponse;
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

    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleResponse articleResponse){
        return articleService.addArticle(articleResponse);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable String slug){
        return articleService.getById(slug);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getArticles(@RequestParam Optional<Date> sortColumnName,
                                                             @RequestParam Optional<Integer> pageNum,
                                                             @RequestParam Optional<Integer> size){
        return articleService.getSortByPageable(sortColumnName, pageNum, size);
    }

    @DeleteMapping("{slug}")
    public void deleteArticle(@PathVariable String slug){
        articleService.deleteArticle(slug);
    }

    @PutMapping()
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody ArticleResponse articleResponse){
        return articleService.updateArticle(articleResponse);
    }
}
