INSERT IGNORE INTO category (id, name, created_at, updated_at) VALUES
  (1, '전자기기', NOW(), NOW()),
  (2, '생활용품', NOW(), NOW());

INSERT IGNORE INTO product (id, category_id, name, price, stock, created_at, updated_at) VALUES
  (1, 1, '무선 이어폰', 89000, 100, NOW(), NOW()),
  (2, 1, '기계식 키보드', 129000, 50, NOW(), NOW()),
  (3, 2, '텀블러', 19000, 200, NOW(), NOW()),
  (4, 2, '한정판 머그컵', 25000, 10, NOW(), NOW());
