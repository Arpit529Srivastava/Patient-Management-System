package com.ecommerce.order.dto;

import com.ecommerce.order.entity.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderResponse {

    private Long id;
    private Instant createdAt;
    private BigDecimal totalAmount;
    private String status;
    private String integrationMethod;
    private List<OrderItemResponse> items;

    public static OrderResponse fromEntity(Order order) {
        OrderResponse response = new OrderResponse();
        response.id = order.getId();
        response.createdAt = order.getCreatedAt();
        response.totalAmount = order.getTotalAmount();
        response.status = order.getStatus();
        response.integrationMethod = order.getIntegrationMethod();
        response.items = order.getItems().stream().map(OrderItemResponse::fromEntity).toList();
        return response;
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getIntegrationMethod() {
        return integrationMethod;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}
