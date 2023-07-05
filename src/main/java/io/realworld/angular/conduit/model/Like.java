package io.realworld.angular.conduit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "likes")

public class Like {
    @ManyToOne
    User user;
    @ManyToOne
    Article article;

}
