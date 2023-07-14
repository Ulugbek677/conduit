package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagMapper {
    private final TagRepository tagRepository;

    public Tag toEntity(TagDTO tag) {
        if (tag == null) return null;
        AtomicReference<Tag> tg = new AtomicReference<>(new Tag());
        tagRepository.findByName(tag.getName())
                .ifPresentOrElse(
                        tg::set,
                        () -> tg.set(
                                tagRepository.save(
                                        new Tag(null, tag.getName())
                                )
                        )
                );
        return tg.get();
    }

    public List<Tag> toEntities(List<String> names) {
        List<Tag> tagData = tagRepository.findByNameIn(names);
        List<String> namesData = tagData.stream()
                .map(Tag::getName)
                .toList();

        List<Tag> newTags = names.stream()
                .filter(t -> !namesData.contains(t))
                .map(t -> new Tag(null, t))
                .collect(Collectors.toList());

        newTags = tagRepository.saveAll(newTags);

        tagData.addAll(newTags);
        return tagData;

    }

    public TagDTO toDto(Tag tag) {
        return new TagDTO(
                tag.getName()
        );
    }


}
