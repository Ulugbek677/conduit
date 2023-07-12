package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponse {
    CommentDTO commentDTO;
}
