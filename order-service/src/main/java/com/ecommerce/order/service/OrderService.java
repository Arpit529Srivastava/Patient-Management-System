package com.ecommerce.order.service;

import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.dto.ProductDto;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.exception.InsufficientStockException;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request, ProductClient productClient, String integrationMethod) {
        Order order = new Order();
        order.setIntegrationMethod(integrationMethod);

        BigDecimal total = BigDecimal.ZERO;
        for (var itemRequest : request.getItems()) {
            ProductDto product = productClient.getProduct(itemRequest.getProductId());

            if (product.getStock() == null || product.getStock() < itemRequest.getQuantity()) {
                throw new InsufficientStockException(itemRequest.getProductId(), itemRequest.getQuantity(),
                        product.getStock() == null ? 0 : product.getStock());
            }

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice());
            order.addItem(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            int newStock = product.getStock() - itemRequest.getQuantity();
            productClient.updateStock(product.getId(), newStock);
        }

        order.setTotalAmount(total);
        order.setStatus("CONFIRMED");
        Order saved = orderRepository.save(order);
        return OrderResponse.fromEntity(saved);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::fromEntity)
                .toList();
    }
}
