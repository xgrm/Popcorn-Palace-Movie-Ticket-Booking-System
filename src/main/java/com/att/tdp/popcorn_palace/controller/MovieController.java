package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.MovieDTO;
import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<MovieDTO> getAllMovies() {
        return movieService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public MovieDTO addAMovie(@Valid @RequestBody Movie content) {
        try {
            return movieService.addMovie(content);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("update/{movieTitle}")
    public void updateAMovie(@RequestBody @Valid MovieDTO updates, @PathVariable String movieTitle) {
        try {
            movieService.update(updates, movieTitle);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{movieTitle}")
    public void delete(@PathVariable String movieTitle) {
        try {
            movieService.deleteByTitle(movieTitle);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
