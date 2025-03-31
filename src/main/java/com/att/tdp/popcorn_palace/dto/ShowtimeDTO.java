package com.att.tdp.popcorn_palace.dto;

import com.att.tdp.popcorn_palace.model.Showtime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ShowtimeDTO {
    private Integer id;
    private Integer movieId;
    private Double price;
    private String theater;
    private Date startTime;
    private Date endTime;
    public ShowtimeDTO(Showtime show){
        this.id = show.getId();
        this.movieId = show.getMovieId();
        this.price = show.getPrice();
        this.theater = show.getTheater();
        this.startTime = show.getStartTime();
        this.endTime = show.getEndTime();
    }
}
