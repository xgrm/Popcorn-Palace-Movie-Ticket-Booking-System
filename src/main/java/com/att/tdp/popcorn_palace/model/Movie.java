package com.att.tdp.popcorn_palace.model;


import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "movies")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", columnDefinition = "TEXT",nullable = false)
    private String title;
    @Column(name="genre", columnDefinition = "TEXT",nullable = false)
    private String genre;
    @Column(name="duration",nullable = false)
    private Integer duration;
    @Column(name="rating",nullable = false)
    private Double rating;
    @Column(name="releaseYear",nullable = false)
    private Integer releaseYear;
    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;
}
