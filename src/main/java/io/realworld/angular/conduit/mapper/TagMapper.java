package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

public class TagMapper {


    public static Tag toEntity(TagDTO tagDTO) {
        return new Tag(
               null,
                tagDTO.getName()
        );
    }
    public static TagDTO toDto(Tag tag) {
        return new TagDTO(
                tag.getName()
        );
    }



}
