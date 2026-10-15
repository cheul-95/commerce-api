package com.lsch.commerce.domain.product.dto;

import com.lsch.commerce.domain.product.Product;

public record ProductResponse(
        Long id,
        String name,
        String categoryName,
        int price,
        int stock
) {
    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getCategory() != null ? p.getCategory().getName() : null,
                p.getPrice(),
                p.getStock()
        );
    }
}
