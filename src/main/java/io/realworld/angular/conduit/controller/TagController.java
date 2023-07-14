package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.TagNameListDto;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    @GetMapping()
    public ResponseEntity<TagNameListDto> getPopularTags(){
        return tagService.getPopularTags();
    }
}
