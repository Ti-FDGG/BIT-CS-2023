-- search1.sql

SELECT DISTINCT c.* 
FROM customer c
JOIN orders o ON o.customer_id = c.customer_id
JOIN lineitem l ON l.order_id = o.order_id
JOIN supplier s ON s.supplier_id = l.supplier_id
JOIN part p ON p.part_id = l.part_id
WHERE s.supplier_name = '徐州市泰力公司矿山设备四厂' 
	AND p.part_name = '活塞式减压阀';