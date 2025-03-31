package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.MovieDTO;
import com.att.tdp.popcorn_palace.model.Movie;

import com.att.tdp.popcorn_palace.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }

    public Movie save(Movie content) {
        return movieRepository.save(content);
    }

    @Modifying
    public void update(MovieDTO updates, String movieTitle) throws IllegalArgumentException{
        Movie existingMovie = this.movieRepository
                .getByTitle(movieTitle)
                .orElseThrow(()-> new IllegalArgumentException("Movie not found."));
        if(updates.getTitle() != null){
            existingMovie.setTitle(updates.getTitle());
        }
        if(updates.getGenre() != null){
            existingMovie.setGenre(updates.getGenre());
        }
        if(updates.getDuration() != null){
            existingMovie.setDuration(updates.getDuration());
        }
        if(updates.getRating() != null){
            existingMovie.setRating(updates.getRating());
        }
        if(updates.getReleaseYear() != null){
            existingMovie.setReleaseYear(updates.getReleaseYear());
        }
        this.movieRepository.save(existingMovie);
    }

    public MovieDTO addMovie(Movie content) throws IllegalArgumentException{
        if (!this.movieRepository.existsByTitle(content.getTitle())) {
            return new MovieDTO(this.save(content));
        } else {
            throw new IllegalArgumentException("Movie already exists!");
        }
    }
    @Modifying
    @Transactional
    public void deleteByTitle(String movieTitle) throws IllegalArgumentException{
        if(!movieRepository.existsByTitle(movieTitle)){
            throw new IllegalArgumentException("Movie not found.");
        }
        this.movieRepository.deleteByTitle(movieTitle);
    }
}
