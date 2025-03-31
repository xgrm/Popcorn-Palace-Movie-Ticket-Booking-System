package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.ShowtimeDTO;
import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.service.MovieService;
import com.att.tdp.popcorn_palace.service.ShowtimeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{showtimeId}")
    public ShowtimeDTO getShowtimeById(@PathVariable Integer showtimeId) {
        try {
            return showtimeService.getShowtimeById(showtimeId);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Showtime not exists!");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void addAShowtime(@Valid @RequestBody Showtime content) {
        try {
            showtimeService.addShowtime(content);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{showtimeId}")
    public void updateAMovie(@RequestBody @Valid ShowtimeDTO updates, @PathVariable Integer showtimeId) {
        try {
            showtimeService.update(updates, showtimeId);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{showtimeId}")
    public void delete(@PathVariable Integer showtimeId) {
        try {
            showtimeService.deleteById(showtimeId);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
