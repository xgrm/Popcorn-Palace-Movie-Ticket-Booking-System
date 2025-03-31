package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ShowtimesRepository extends JpaRepository<Showtime, Integer> {

    @Query("SELECT NOT EXISTS (SELECT 1 FROM Showtime WHERE theater = :theater AND isDeleted = FALSE AND (  (startTime <= :endTime AND endTime >= :startTime))) AS no_overlap ")
    boolean noShowtimeOverlap(String theater, Date startTime, Date endTime);

    boolean existsByIdAndIsDeleted(Integer showtimeId, boolean deleted);

    Optional<Showtime> findByIdAndIsDeleted(Integer showtimeId, boolean deleted);
}
