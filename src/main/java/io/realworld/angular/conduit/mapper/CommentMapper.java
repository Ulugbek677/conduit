package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentMapper {
    private final ArticleMapper articleMapper;



    public CommentDTO toDto(Comment comment){
        return new CommentDTO(
                comment.getId(),
                UserMapper.toDto(comment.getAuthor()),
                null,
                comment.getBody(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
    public Comment toEntity(CommentDTO commentDto){
        return new Comment(
                commentDto.getId(),
                commentDto.getBody(),
                null,
                null,
                commentDto.getCreateAt(),
                commentDto.getUpdateAt()


        );
    }
}
