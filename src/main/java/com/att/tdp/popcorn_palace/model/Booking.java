package com.att.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID bookingId;

    @Column(name="showtime_id",nullable = false)
    private Integer showtimeId;
    @Column(name="seat_number",nullable = false)
    private Integer seatNumber;
    @Column(name="user_id",nullable = false)
    private UUID userId;

}
