package com.lsch.commerce.domain.product;

import com.lsch.commerce.global.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    @DisplayName("재고를 차감한다")
    void decreaseStock() {
        Product product = Product.create(null, "텀블러", 19000, 10);

        product.decreaseStock(3);

        assertThat(product.getStock()).isEqualTo(7);
    }

    @Test
    @DisplayName("재고보다 많이 주문하면 예외가 발생한다")
    void decreaseStock_outOfStock() {
        Product product = Product.create(null, "텀블러", 19000, 2);

        assertThatThrownBy(() -> product.decreaseStock(3))
                .isInstanceOf(BusinessException.class)
                .hasMessage("재고가 부족합니다.");
    }
}
