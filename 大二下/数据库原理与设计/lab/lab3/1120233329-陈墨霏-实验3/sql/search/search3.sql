-- search3.sql

SELECT c.*
FROM customer c
JOIN nation n ON c.nation_id = n.nation_id
WHERE n.nation_name = '中国'
  AND c.customer_id IN (
    SELECT o.customer_id
    FROM orders o
    GROUP BY o.customer_id
    HAVING AVG(o.total_amount) > 500
  );