package com.moviemaster.controller;

import com.moviemaster.entity.ProductionHouse;
import com.moviemaster.service.ProductionHouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productionhouses")
public class ProductionHouseController {

    @Autowired
    private ProductionHouseService productionHouseService;

    @PostMapping
    public ResponseEntity<ProductionHouse> addProductionHouse(@Valid @RequestBody ProductionHouse productionHouse) {
        ProductionHouse saved = productionHouseService.addProductionHouse(productionHouse);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<ProductionHouse> getProductionHouseById(@PathVariable Long houseId) {
        ProductionHouse productionHouse = productionHouseService.getProductionHouseById(houseId);
        return new ResponseEntity<>(productionHouse, HttpStatus.OK);
    }

    @PutMapping("/{houseId}/chairman")
    public ResponseEntity<ProductionHouse> updateChairmanName(@PathVariable Long houseId,
                                                               @RequestParam String chairmanName) {
        ProductionHouse updated = productionHouseService.updateChairmanName(houseId, chairmanName);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/filmcount")
    public ResponseEntity<List<ProductionHouse>> getByMinFilmCount(@RequestParam int count) {
        List<ProductionHouse> houses = productionHouseService.getProductionHousesByMinFilmCount(count);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }
}
