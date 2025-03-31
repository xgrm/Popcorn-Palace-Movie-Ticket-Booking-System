package com.att.tdp.popcorn_palace.model;

import jakarta.persistence.*;
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

    @Column(name="movie_id",nullable = false)
    private Integer movieId;
    @Column(name="price",nullable = false)
    private Double price;
    @Column(name="theater",nullable = false)
    private String theater;
    @Column(name="start_time",nullable = false)
    private Date startTime;
    @Column(name="end_time",nullable = false)
    private Date endTime;
    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;
}
