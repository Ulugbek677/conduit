package io.realworld.angular.conduit.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String body;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_tag",
            joinColumns = {@JoinColumn(table = "articles", referencedColumnName = "id", name = "article_id")},
            inverseJoinColumns = {
                    @JoinColumn(table = "tag", referencedColumnName = "id", name = "tag_id")})
    private List<Tag> tags;

    private LocalDate createdat;
    private LocalDate updatedat;
    @ManyToOne
    private User author;


}
