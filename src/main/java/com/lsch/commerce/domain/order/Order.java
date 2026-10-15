package com.lsch.commerce.domain.order;

import com.lsch.commerce.domain.member.Member;
import com.lsch.commerce.global.entity.BaseTimeEntity;
import com.lsch.commerce.global.exception.BusinessException;
import com.lsch.commerce.global.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")   // ORDER 는 SQL 예약어
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public static Order create(Member member, List<OrderItem> items) {
        Order order = new Order();
        order.member = member;
        order.status = OrderStatus.PENDING;
        items.forEach(order::addItem);
        order.totalPrice = items.stream().mapToInt(OrderItem::getTotalPrice).sum();
        return order;
    }

    private void addItem(OrderItem item) {
        items.add(item);
        item.assignOrder(this);
    }

    public void markPaid() {
        if (status != OrderStatus.PENDING) {
            throw new BusinessException(ErrorCode.INVALID_ORDER_STATUS);
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.CANCELED) {
            throw new BusinessException(ErrorCode.INVALID_ORDER_STATUS);
        }
        items.forEach(OrderItem::restoreStock);
        status = OrderStatus.CANCELED;
    }
}
