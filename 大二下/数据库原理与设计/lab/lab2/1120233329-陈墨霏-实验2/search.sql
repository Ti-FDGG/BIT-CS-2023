-- 查询所有供应商的名称、地址、联系电话
SELECT supplier_name, address, phone
FROM supplier;

-- 查询2014年1~10月间的总价大于1000元的订单的编号、顾客编号等订单的所有信息
SELECT *
FROM orders
WHERE order_date BETWEEN '2014-01-01' AND '2014-10-31'
  AND total_amount > 1000;

-- 统计每个顾客的订购金额
SELECT customer_id, SUM(total_amount) AS total_amount_sum
FROM orders
GROUP BY customer_id;

-- 查询订单平均金额超过1000元的顾客编号及其姓名
SELECT customer_id, name
FROM customer
WHERE customer_id IN (
    SELECT customer_id
    FROM orders
    GROUP BY customer_id
    HAVING AVG(total_amount) > 1000
);

-- 查询与“金仓集团”在同一个国家的供应商编号、名称和地址信息
SELECT supplier_id, supplier_name, address
FROM supplier
WHERE nation_id = (
    SELECT nation_id
    FROM supplier
    WHERE supplier_name = '金仓集团'
);

-- 查询供应价格大于零售价格的零件名、制造商名、零售价格和供应价格
SELECT part_name, supplier_name, retail_price, supply_price
FROM part
JOIN partsupp ON part.part_id = partsupp.part_id
JOIN supplier ON partsupp.supplier_id = supplier.supplier_id
WHERE supply_price > retail_price;

-- 查询顾客“阿波罗”订购的订单编号、总价及其订购的零件编号、数量和零售价格
SELECT 
    o.order_id, 
    o.total_amount, 
    l.part_id, 
    l.quantity, 
    p.retail_price
FROM 
    orders o
JOIN 
    customer c ON o.customer_id = c.customer_id
JOIN 
    lineitem l ON o.order_id = l.order_id
JOIN 
    part p ON l.part_id = p.part_id
WHERE 
    c.name = '阿波罗';