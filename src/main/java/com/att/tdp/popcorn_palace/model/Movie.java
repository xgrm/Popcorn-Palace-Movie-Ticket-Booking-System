package com.att.tdp.popcorn_palace.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Title is required")
    @Column(name="title", columnDefinition = "TEXT",nullable = false)
    private String title;
    @NotNull(message = "Genre is required")
    @Column(name="genre", columnDefinition = "TEXT",nullable = false)
    private String genre;
    @NotNull(message = "Duration is required")
    @Column(name="duration",nullable = false)
    private Integer duration;
    @NotNull(message = "Rating is required")
    @Column(name="rating",nullable = false)
    private Double rating;

    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be valid")
    @Max(value = 2100, message = "Release year must be realistic")
    @Column(name="releaseYear",nullable = false)
    private Integer releaseYear;
}
