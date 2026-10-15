package com.lsch.commerce.domain.product;

import com.lsch.commerce.global.entity.BaseTimeEntity;
import com.lsch.commerce.global.exception.BusinessException;
import com.lsch.commerce.global.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    public static Product create(Category category, String name, int price, int stock) {
        Product product = new Product();
        product.category = category;
        product.name = name;
        product.price = price;
        product.stock = stock;
        return product;
    }

    /**
     * 재고 차감. 동시 주문 시 이 메서드만으로는 꼬일 수 있음 → 3주차에 락 적용 후 비교.
     */
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        if (this.stock < quantity) {
            throw new BusinessException(ErrorCode.OUT_OF_STOCK);
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
}
