package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.BookingDTO;
import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.repository.BookingRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimesRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowtimesRepository showtimesRepository;
    public BookingService(BookingRepository bookingRepository, ShowtimesRepository showtimesRepository) {
        this.bookingRepository = bookingRepository;
        this.showtimesRepository = showtimesRepository;
    }
    @Modifying
    @Transactional
    public BookingDTO addMovie(Booking content) throws IllegalArgumentException{
        if(!showtimesRepository.existsByIdAndIsDeleted(content.getShowtimeId(),false)){
            throw new IllegalArgumentException("Showtime not exists!");
        }
        if(bookingRepository.existsByShowtimeIdAndSeatNumber(content.getShowtimeId(),content.getSeatNumber())){
            throw new IllegalArgumentException("This seat already taken!!");
        }
        return new BookingDTO(bookingRepository.save(content));
    }
}
