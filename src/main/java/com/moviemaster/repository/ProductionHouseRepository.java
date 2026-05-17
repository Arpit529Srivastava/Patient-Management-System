package com.moviemaster.repository;

import com.moviemaster.entity.ProductionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionHouseRepository extends JpaRepository<ProductionHouse, Long> {

    @Query("SELECT ph FROM ProductionHouse ph WHERE SIZE(ph.films) >= :count")
    List<ProductionHouse> findByFilmCountGreaterThanOrEqual(@Param("count") int count);
}
