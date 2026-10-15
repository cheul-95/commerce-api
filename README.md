# commerce-api

커머스 주문/재고 API (Spring Boot 포트폴리오 프로젝트)

## 기술 스택
- Java 25, Spring Boot 4, Spring Data JPA, Spring Security
- MySQL 8.4, Redis 7
- Docker Compose, Swagger(springdoc)

## 로컬 실행
```bash
# 1. DB/Redis 실행 (MySQL은 로컬 DB와 안 겹치게 3307 포트)
docker compose up -d

# 2. 앱 실행 (IntelliJ에서 CommerceApplication 실행해도 됨)
./gradlew bootRun
```

- Swagger: http://localhost:8080/swagger-ui.html
- 상품 목록: http://localhost:8080/api/products

## 진행 계획
- [x] 프로젝트 뼈대, 엔티티 설계 (Member / Category / Product / Order / OrderItem)
- [ ] 1주차: 회원가입/로그인 (JWT), 상품 조회
- [ ] 2주차: 장바구니, 주문 생성/취소, 토스페이먼츠 테스트 결제
- [ ] 3주차: 재고 동시성 제어 (비관적 락 → Redis 분산락 비교), 테스트, EC2 배포

## 핵심 개선 포인트 (작성 예정)
- 재고 동시성: 동시 주문 100건 시 락 적용 전/후 결과 비교
- N+1: fetch join / batch size 적용 전/후 쿼리 수 비교
