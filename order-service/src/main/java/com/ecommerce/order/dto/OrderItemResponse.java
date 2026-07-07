package com.ecommerce.order.dto;

import com.ecommerce.order.entity.OrderItem;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;

    public static OrderItemResponse fromEntity(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();
        response.productId = item.getProductId();
        response.productName = item.getProductName();
        response.quantity = item.getQuantity();
        response.price = item.getPrice();
        return response;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
