package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;


public interface ShowtimesRepository extends JpaRepository<Showtime, Integer> {

    @Query("""

            SELECT NOT EXISTS (
                SELECT 1 
                FROM Showtime 
                WHERE theater = :theater 
                AND id <> COALESCE(:id, -1)
                AND (  (startTime <= :endTime AND endTime >= :startTime))) AS no_overlap 
            """)
    boolean noShowtimeOverlap(String theater, Date startTime, Date endTime,Integer id);



}
