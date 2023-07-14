package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TagMapper {
    private final TagRepository tagRepository;

    public Tag toEntity(TagDTO tag) {
        if (tag == null) return null;
        AtomicReference<Tag> tg = new AtomicReference<>(new Tag());

        tagRepository.findByName(tag.getName()).ifPresentOrElse(tg::set, () -> tg.set(tagRepository.save(new Tag(null, tag.getName()))));
        return tg.get();
    }
    public TagDTO toDto(Tag tag) {
        return new TagDTO(
                tag.getName()
        );
    }



}
