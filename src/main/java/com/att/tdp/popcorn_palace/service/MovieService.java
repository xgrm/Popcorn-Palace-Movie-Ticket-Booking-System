package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.MovieDTO;
import com.att.tdp.popcorn_palace.model.Movie;

import com.att.tdp.popcorn_palace.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findByIsDeleted(false).stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }

    public void save(Movie content) {
        movieRepository.save(content);
    }

    public Optional<Movie> findByTitle(String movieTitle) {
        return movieRepository.findByTitle(movieTitle);
    }

    @Modifying
    public void update(MovieDTO updates, String movieTitle) throws IllegalArgumentException{
        Movie existingMovie = this.movieRepository
                .getByTitleAndIsDeleted(movieTitle,false)
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

    public void addMovie(Movie content) throws IllegalArgumentException{
        if (!this.movieRepository.existsByTitle(content.getTitle())) {
            this.save(content);
        } else {
            throw new IllegalArgumentException("Movie already exists!");
        }
    }
    @Modifying
    @Transactional
    public void deleteByTitle(String movieTitle) throws IllegalArgumentException{
        Movie existingMovie = this.movieRepository
                .getByTitleAndIsDeleted(movieTitle,false)
                .orElseThrow(()-> new IllegalArgumentException("Movie not found."));
        existingMovie.setDeleted(true);
        this.movieRepository.save(existingMovie);
    }
}
