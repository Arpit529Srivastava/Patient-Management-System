package com.ecommerce.order.client;

import com.ecommerce.order.dto.ProductDto;

public interface ProductClient {

    ProductDto getProduct(Long productId);

    void updateStock(Long productId, int newStock);
}
