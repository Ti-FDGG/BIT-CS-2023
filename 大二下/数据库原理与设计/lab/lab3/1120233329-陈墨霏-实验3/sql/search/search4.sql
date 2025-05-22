-- search4.sql

SELECT p.*
FROM part p
JOIN lineitem l ON l.part_id = p.part_id
JOIN orders o ON o.order_id = l.order_id
JOIN customer c ON c.customer_id = o.customer_id
WHERE c.name = '薜融'

EXCEPT

SELECT p.*
FROM part p
JOIN lineitem l ON l.part_id = p.part_id
JOIN orders o ON o.order_id = l.order_id
JOIN customer c ON c.customer_id = o.customer_id
WHERE c.name = '宣荣揣'