

SELECT * FROM customer;

SELECT * FROM orders;

# 주문자ID, 주문상품명, 주문수량, 주문자명을 INNER JOIN으로 조회

SELECT NAME
		, CUSTOMER.CUSTOMER_ID
		, PRODUCT_NAME
		, ORDER_CNT
FROM customer LEFT OUTER JOIN orders
ON customer.CUSTOMER_ID = orders.CUSTOMER_ID;

SELECT NAME
		, customer.CUSTOMER_ID
		, PRODUCT_NAME
		, ORDER_CNT
FROM orders RIGHT OUTER JOIN customer
ON customer.CUSTOMER_ID = orders.CUSTOMER_ID;

SELECT (SELECT NAME FROM customer WHERE CUSTOMER_ID = orders.CUSTOMER_ID) AS NAME
		, PRODUCT_NAME
		, ORDER_CNT
		, CUSTOMER_ID
FROM orders;

SELECT * FROM book_category;
SELECT * FROM book;

# BOOK_CATEGRY 테이블에 존재하는 모든 카테고리에 대한
# 카테고리 번호, 카테고리이름, 책이름, 책가격을 조회

SELECT BOOK_CATEGORY.CATE_CODE
		, CATE_NAME
		, BOOK_NAME
		, BOOK_PRICE
FROM book_category LEFT OUTER JOIN book
ON book_category.CATE_CODE = book.CATE_CODE;













