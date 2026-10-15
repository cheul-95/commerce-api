package com.lsch.commerce.domain.order;

import com.lsch.commerce.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int orderPrice;   // 주문 시점 가격 (상품 가격이 바뀌어도 유지)

    @Column(nullable = false)
    private int quantity;

    /** 주문 항목 생성 + 재고 차감 */
    public static OrderItem create(Product product, int quantity) {
        product.decreaseStock(quantity);
        OrderItem item = new OrderItem();
        item.product = product;
        item.orderPrice = product.getPrice();
        item.quantity = quantity;
        return item;
    }

    void assignOrder(Order order) {
        this.order = order;
    }

    void restoreStock() {
        product.increaseStock(quantity);
    }

    public int getTotalPrice() {
        return orderPrice * quantity;
    }
}
