package com.moviemaster.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "production_houses")
public class ProductionHouse {

    @Id
    private Long houseId;

    @NotNull
    @NotEmpty
    private String productionHouseName;

    @Min(1800)
    private int establishedYear;

    @NotNull
    @NotEmpty
    private String chairmanName;

    @NotNull
    @NotEmpty
    private String country;

    @OneToMany(mappedBy = "productionHouse", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Film> films = new ArrayList<>();

    public ProductionHouse() {
    }

    public ProductionHouse(Long houseId, String productionHouseName, int establishedYear, String chairmanName, String country) {
        this.houseId = houseId;
        this.productionHouseName = productionHouseName;
        this.establishedYear = establishedYear;
        this.chairmanName = chairmanName;
        this.country = country;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getProductionHouseName() {
        return productionHouseName;
    }

    public void setProductionHouseName(String productionHouseName) {
        this.productionHouseName = productionHouseName;
    }

    public int getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(int establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
