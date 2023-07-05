package io.realworld.angular.conduit.model;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class Follow {
    @ManyToOne
    User user;
    @ManyToOne
    User follower;
    LocalDate createdDate;
}
