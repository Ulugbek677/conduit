package io.realworld.angular.conduit.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.realworld.angular.conduit.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class ArticleResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ArticleDTO> articles;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArticleDTO article;
}
