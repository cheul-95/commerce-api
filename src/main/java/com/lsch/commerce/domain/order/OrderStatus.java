package com.lsch.commerce.domain.order;

public enum OrderStatus {
    PENDING,    // 주문 생성, 결제 대기
    PAID,       // 결제 완료
    CANCELED    // 취소
}
