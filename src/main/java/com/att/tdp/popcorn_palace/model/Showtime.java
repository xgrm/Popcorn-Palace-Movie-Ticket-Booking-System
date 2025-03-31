package com.att.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Movie id is required")
    @Column(name="movie_id",nullable = false)
    private Integer movieId;

    @NotNull(message = "price is required")
    @Column(name="price",nullable = false)
    private Double price;

    @NotNull(message = "theater is required")
    @Column(name="theater",nullable = false)
    private String theater;

    @NotNull(message = "start_time is required")
    @Column(name="start_time",nullable = false)
    private Date startTime;

    @NotNull(message = "end_time is required")
    @Column(name="end_time",nullable = false)
    private Date endTime;

}
