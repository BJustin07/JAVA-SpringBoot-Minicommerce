INSERT INTO customer (customer_name, customer_number, customer_address)
VALUES
    ('John Doe', 'CUST-001', 'Manila, Philippines'),
    ('Jane Smith', 'CUST-002', 'Quezon City, Philippines'),
    ('Michael Tan', 'CUST-003', 'Cavite, Philippines'),
    ('Sarah Lim', 'CUST-004', 'Makati, Philippines');

INSERT INTO orders
(order_number, order_date, customer_id)
VALUES
    (1001, '2026-06-01', 1),
    (1002, '2026-06-01', 2),
    (1003, '2026-06-02', 3);

INSERT INTO product (product_code, product_description, quantity, price)
VALUES
    ('PROD-001', 'Coca Cola', 100, 50.00),
    ('PROD-002', 'Piattos', 200, 35.00),
    ('PROD-003', 'Snickers', 150, 45.00);

--INSERT INTO orders_products (orders_id, product_id)
--VALUES
--    (1,1),
--    (1,2),
--    (1,3),
--    (2,2),
--    (2,1),
--    (3,1),
--    (3,2);