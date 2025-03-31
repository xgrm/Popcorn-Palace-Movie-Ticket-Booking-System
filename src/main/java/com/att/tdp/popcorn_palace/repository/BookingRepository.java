package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    boolean existsByShowtimeIdAndSeatNumber(Integer showtimeId, Integer seatNumber);
}
