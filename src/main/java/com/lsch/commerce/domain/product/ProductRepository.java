package com.lsch.commerce.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // category 를 함께 조회해서 N+1 방지
    @EntityGraph(attributePaths = "category")
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
}
