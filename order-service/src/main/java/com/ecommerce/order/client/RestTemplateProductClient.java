package com.ecommerce.order.client;

import com.ecommerce.order.dto.ProductDto;
import com.ecommerce.order.exception.ProductNotFoundException;
import com.ecommerce.order.exception.ProductServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component("restTemplateProductClient")
public class RestTemplateProductClient implements ProductClient {

    private static final String PRODUCT_SERVICE_URL = "http://PRODUCT-SERVICE/products";

    private final RestTemplate restTemplate;

    public RestTemplateProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductDto getProduct(Long productId) {
        try {
            return restTemplate.getForObject(PRODUCT_SERVICE_URL + "/{id}", ProductDto.class, productId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ProductNotFoundException(productId);
        } catch (HttpStatusCodeException ex) {
            throw new ProductServiceUnavailableException(ex.getStatusText(), ex);
        } catch (ResourceAccessException ex) {
            throw new ProductServiceUnavailableException("Product Service did not respond", ex);
        }
    }

    @Override
    public void updateStock(Long productId, int newStock) {
        try {
            restTemplate.put(PRODUCT_SERVICE_URL + "/{id}/stock/{stock}", null, productId, newStock);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ProductNotFoundException(productId);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw ex;
            }
            throw new ProductServiceUnavailableException(ex.getStatusText(), ex);
        } catch (ResourceAccessException ex) {
            throw new ProductServiceUnavailableException("Product Service did not respond", ex);
        }
    }
}
