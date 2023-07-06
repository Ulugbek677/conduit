package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagMapper {
    public static Tag toEntity(TagDTO tagDTO){
        return null;
    }

    public static TagDTO toDto(Tag tag){
//        return new TagDTO(
//                tag.getName(),
//                tag.getArticles().stream()
//                        .map(ArticleMapper::toDto)
//                        .collect(Collectors.toList())
//        );
       return null;
    }
}
