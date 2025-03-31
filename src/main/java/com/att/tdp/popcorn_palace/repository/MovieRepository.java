package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> getByTitle(String movieTitle);

    Boolean existsByTitle(String movieTitle);

    void deleteByTitle(String movieTitle);
}
