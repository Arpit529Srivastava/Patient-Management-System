package com.moviemaster.repository;

import com.moviemaster.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByDirectorAndGenre(String director, String genre);

    List<Film> findByProductionHouseProductionHouseNameIgnoreCase(String productionHouseName);
}
