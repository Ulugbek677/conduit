package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.TagNameListDto;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public ResponseEntity<TagNameListDto> getPopularTags() {

        return ResponseEntity.ok(new TagNameListDto(tagRepository.getPopularTags()));
    }
}
