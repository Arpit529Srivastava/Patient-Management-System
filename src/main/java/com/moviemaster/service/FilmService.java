package com.moviemaster.service;

import com.moviemaster.entity.Film;
import com.moviemaster.entity.ProductionHouse;
import com.moviemaster.exception.ResourceNotFoundException;
import com.moviemaster.repository.FilmRepository;
import com.moviemaster.repository.ProductionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ProductionHouseRepository productionHouseRepository;

    public Film addFilm(Long houseId, Film film) {
        ProductionHouse productionHouse = productionHouseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException("Production house not found with id: " + houseId));
        film.setProductionHouse(productionHouse);
        return filmRepository.save(film);
    }

    public List<Film> getFilmsByDirectorAndGenre(String director, String genre) {
        return filmRepository.findByDirectorAndGenre(director, genre);
    }

    public List<Film> getFilmsByProductionHouseName(String productionHouseName) {
        return filmRepository.findByProductionHouseProductionHouseNameIgnoreCase(productionHouseName);
    }

    public void deleteFilm(Long filmId) {
        if (!filmRepository.existsById(filmId)) {
            throw new ResourceNotFoundException("Film not found with id: " + filmId);
        }
        filmRepository.deleteById(filmId);
    }
}
