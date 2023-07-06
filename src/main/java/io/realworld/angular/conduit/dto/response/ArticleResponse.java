package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private ArticleDTO article;
}
