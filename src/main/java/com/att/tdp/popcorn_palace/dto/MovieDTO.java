package com.att.tdp.popcorn_palace.dto;

import com.att.tdp.popcorn_palace.model.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class MovieDTO {
    private Integer id;
    private String title;
    private String genre;
    private Integer duration;
    private Double rating;
    private Integer releaseYear;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.duration = movie.getDuration();
        this.rating = movie.getRating();
        this.releaseYear = movie.getReleaseYear();
    }
}
