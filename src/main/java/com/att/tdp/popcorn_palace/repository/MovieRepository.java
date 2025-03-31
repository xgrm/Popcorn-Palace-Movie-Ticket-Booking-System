package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByIsDeleted(Boolean deleted);

    Optional<Movie> findByTitle(String movieTitle);

    boolean existsByTitle(String title);

    void deleteByTitle(String movieTitle);

    boolean existsByIdAndIsDeleted(Integer movieId,Boolean deleted);
}
