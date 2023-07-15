package io.realworld.angular.conduit.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.realworld.angular.conduit.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CommentDTO comment;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<CommentDTO> comments;
}
