package com.moviemaster.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "films")
public class Film {

    @Id
    private Long filmId;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String director;

    @NotNull
    @NotEmpty
    private String genre;

    @Positive
    private double budget;

    @PastOrPresent
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "house_id")
    @JsonBackReference
    private ProductionHouse productionHouse;

    public Film() {
    }

    public Film(Long filmId, String title, String director, String genre, double budget, LocalDate releaseDate) {
        this.filmId = filmId;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.budget = budget;
        this.releaseDate = releaseDate;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ProductionHouse getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(ProductionHouse productionHouse) {
        this.productionHouse = productionHouse;
    }
}
