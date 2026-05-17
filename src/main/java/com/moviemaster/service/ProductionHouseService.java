package com.moviemaster.service;

import com.moviemaster.entity.ProductionHouse;
import com.moviemaster.exception.ResourceNotFoundException;
import com.moviemaster.repository.ProductionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionHouseService {

    @Autowired
    private ProductionHouseRepository productionHouseRepository;

    public ProductionHouse addProductionHouse(ProductionHouse productionHouse) {
        return productionHouseRepository.save(productionHouse);
    }

    public ProductionHouse getProductionHouseById(Long houseId) {
        return productionHouseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException("Production house not found with id: " + houseId));
    }

    public ProductionHouse updateChairmanName(Long houseId, String chairmanName) {
        ProductionHouse productionHouse = productionHouseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException("Production house not found with id: " + houseId));
        productionHouse.setChairmanName(chairmanName);
        return productionHouseRepository.save(productionHouse);
    }

    public List<ProductionHouse> getProductionHousesByMinFilmCount(int count) {
        return productionHouseRepository.findByFilmCountGreaterThanOrEqual(count);
    }
}
