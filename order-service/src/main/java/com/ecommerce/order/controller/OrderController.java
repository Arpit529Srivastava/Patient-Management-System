package com.ecommerce.order.controller;

import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductClient restTemplateProductClient;
    private final ProductClient webClientProductClient;

    public OrderController(OrderService orderService,
                            @Qualifier("restTemplateProductClient") ProductClient restTemplateProductClient,
                            @Qualifier("webClientProductClient") ProductClient webClientProductClient) {
        this.orderService = orderService;
        this.restTemplateProductClient = restTemplateProductClient;
        this.webClientProductClient = webClientProductClient;
    }

    @PostMapping("/rest-template")
    public ResponseEntity<OrderResponse> createOrderUsingRestTemplate(@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request, restTemplateProductClient, "REST_TEMPLATE");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/webclient")
    public ResponseEntity<OrderResponse> createOrderUsingWebClient(@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request, webClientProductClient, "WEB_CLIENT");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
