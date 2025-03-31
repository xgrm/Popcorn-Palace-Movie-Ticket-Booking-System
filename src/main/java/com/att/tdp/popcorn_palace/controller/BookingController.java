package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.BookingDTO;
import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public BookingDTO addBooking(@Valid @RequestBody Booking content) {
        try {
            return bookingService.addMovie(content);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
