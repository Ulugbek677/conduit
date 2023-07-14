package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.TagNameListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    ResponseEntity<TagNameListDto> getPopularTags();
}
