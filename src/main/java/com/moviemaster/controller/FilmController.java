package com.moviemaster.controller;

import com.moviemaster.entity.Film;
import com.moviemaster.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping("/{houseId}")
    public ResponseEntity<Film> addFilm(@PathVariable Long houseId, @Valid @RequestBody Film film) {
        Film saved = filmService.addFilm(houseId, film);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Film>> getFilmsByDirectorAndGenre(@RequestParam String director,
                                                                  @RequestParam String genre) {
        List<Film> films = filmService.getFilmsByDirectorAndGenre(director, genre);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/productionhouse/{productionHouseName}")
    public ResponseEntity<List<Film>> getFilmsByProductionHouseName(@PathVariable String productionHouseName) {
        List<Film> films = filmService.getFilmsByProductionHouseName(productionHouseName);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long filmId) {
        filmService.deleteFilm(filmId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
