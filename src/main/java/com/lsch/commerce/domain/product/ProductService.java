package com.lsch.commerce.domain.product;

import com.lsch.commerce.domain.product.dto.ProductResponse;
import com.lsch.commerce.global.exception.BusinessException;
import com.lsch.commerce.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponse> search(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword == null ? "" : keyword, pageable)
                .map(ProductResponse::from);
    }

    public ProductResponse get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        return ProductResponse.from(product);
    }
}
