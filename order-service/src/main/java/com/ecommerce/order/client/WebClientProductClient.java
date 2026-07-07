package com.ecommerce.order.client;

import com.ecommerce.order.dto.ProductDto;
import com.ecommerce.order.exception.ProductNotFoundException;
import com.ecommerce.order.exception.ProductServiceUnavailableException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component("webClientProductClient")
public class WebClientProductClient implements ProductClient {

    private static final String PRODUCT_SERVICE_BASE_URL = "http://PRODUCT-SERVICE";

    private final WebClient webClient;

    public WebClientProductClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(PRODUCT_SERVICE_BASE_URL).build();
    }

    @Override
    public ProductDto getProduct(Long productId) {
        try {
            return webClient.get()
                    .uri("/products/{id}", productId)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new ProductNotFoundException(productId);
        } catch (WebClientResponseException ex) {
            throw new ProductServiceUnavailableException(ex.getStatusText(), ex);
        } catch (WebClientRequestException ex) {
            throw new ProductServiceUnavailableException("Product Service did not respond", ex);
        }
    }

    @Override
    public void updateStock(Long productId, int newStock) {
        try {
            webClient.put()
                    .uri("/products/{id}/stock/{stock}", productId, newStock)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new ProductNotFoundException(productId);
        } catch (WebClientResponseException.BadRequest ex) {
            throw ex;
        } catch (WebClientResponseException ex) {
            throw new ProductServiceUnavailableException(ex.getStatusText(), ex);
        } catch (WebClientRequestException ex) {
            throw new ProductServiceUnavailableException("Product Service did not respond", ex);
        }
    }
}
