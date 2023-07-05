package io.realworld.angular.conduit.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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
                    @JoinColumn(table = "tags", referencedColumnName = "id", name = "tag_id")})
    private List<Tag> tags;
    @ManyToOne
    private User author;

    private LocalDate publishDate;
    private LocalDate updateDate;



}