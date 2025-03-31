package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.MovieDTO;
import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public void update(Map<String, Object> updates, Movie existingMovie) throws IllegalArgumentException{
        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    if (value instanceof String) {
                        existingMovie.setTitle((String) value);
                    } else {
                        throw new IllegalArgumentException("Value for 'title' must be a String");
                    }
                    break;
                case "genre":
                    if (value instanceof String) {
                        existingMovie.setGenre((String) value);
                    } else {
                        throw new IllegalArgumentException("Value for 'genre' must be a String");
                    }
                    break;
                case "duration":
                    if (value instanceof Integer) {
                        existingMovie.setDuration((Integer) value);
                    } else {
                        throw new IllegalArgumentException("Value for 'duration' must be a Integer");
                    }
                    break;
                case "rating":
                    if (value instanceof Double) {
                        existingMovie.setRating((Double) value);
                    } else {
                        throw new IllegalArgumentException("Value for 'rating' must be a Double ");
                    }
                    break;
                case "releaseYear":
                    if (value instanceof Integer) {
                        existingMovie.setReleaseYear((Integer) value);
                    } else {
                        throw new IllegalArgumentException("Value for 'releaseYear' must be a Integer");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid key: " + key);
            }
        });

        this.save(existingMovie);
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
        if (this.movieRepository.existsByTitle(movieTitle)) {
            this.movieRepository.deleteByTitle(movieTitle);
        } else {
            throw new IllegalArgumentException("Movie not exists!");
        }
    }
}
