package com.att.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "showtimeId is required")
    private Integer showtimeId;


    @Column(name="seat_number",nullable = false)
    @NotNull(message = "seat_number is required")
    private Integer seatNumber;

    @NotNull(message = "user_id is required")
    @Column(name="user_id",nullable = false)
    private UUID userId;

}
