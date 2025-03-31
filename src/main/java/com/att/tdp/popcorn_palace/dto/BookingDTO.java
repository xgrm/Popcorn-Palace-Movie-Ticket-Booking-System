package com.att.tdp.popcorn_palace.dto;

import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.model.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class BookingDTO {
    private UUID bookingId;
    private Integer showtimeId;
    private Integer seatNumber;
    private UUID userId;
    public BookingDTO(Booking booking){
        this.bookingId = booking.getBookingId();
        this.showtimeId = booking.getShowtimeId();
        this.seatNumber = booking.getSeatNumber();
        this.userId = booking.getUserId();
    }
}
