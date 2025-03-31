package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.ShowtimeDTO;
import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.repository.MovieRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimesRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;



@Service
public class ShowtimeService {
    private final ShowtimesRepository showtimesRepository;
    private final MovieRepository movieRepository;

    public ShowtimeService(ShowtimesRepository showtimesRepository, MovieRepository movieRepository) {
        this.showtimesRepository = showtimesRepository;
        this.movieRepository = movieRepository;
    }

    public ShowtimeDTO getShowtimeById(Integer showtimeId) throws IllegalArgumentException{
            return new ShowtimeDTO(
                    showtimesRepository.
                            findById(showtimeId).
                            orElseThrow(IllegalArgumentException::new));
    }
    private Showtime saveShowtime(Showtime content) throws IllegalArgumentException{

        if (!movieRepository.existsById(content.getMovieId())) {
            throw new IllegalArgumentException("Movie not exists!");
        } else if (content.getStartTime().after(content.getEndTime())) {
            throw new IllegalArgumentException("The Start Time is after the EndTime!");
        } else if(!showtimesRepository.noShowtimeOverlap(content.getTheater(),content.getStartTime(),content.getEndTime(),content.getId())) {
            throw new IllegalArgumentException("There are Showtime Overlap in this Theater!");
        }
        else{
            return showtimesRepository.save(content);
        }
    }
    public ShowtimeDTO addShowtime(Showtime content) throws IllegalArgumentException{
            return new ShowtimeDTO(this.saveShowtime(content));
    }

    @Modifying
    public void update(ShowtimeDTO updates, Integer showtimeId) throws IllegalArgumentException {
        Showtime existingShowtime= this.showtimesRepository.findById(showtimeId).orElseThrow(() -> new IllegalArgumentException("Showtime is not exist!"));
        if (updates.getMovieId() != null) {
            existingShowtime.setMovieId(updates.getMovieId());
        }
        if (updates.getPrice() != null) {
            existingShowtime.setPrice(updates.getPrice());
        }
        if (updates.getTheater() != null) {
            existingShowtime.setTheater(updates.getTheater());
        }
        if (updates.getStartTime() != null) {
            existingShowtime.setStartTime(updates.getStartTime());
        }
        if (updates.getEndTime() != null) {
            existingShowtime.setEndTime(updates.getEndTime());
        }
        this.saveShowtime(existingShowtime);
    }
    @Modifying
    @Transactional
    public void deleteById(Integer showtimeId) throws IllegalArgumentException{
        if(!showtimesRepository.existsById(showtimeId)){
            throw new IllegalArgumentException("The Showtime does not exist!");
        }
        showtimesRepository.deleteById(showtimeId);
    }
}
