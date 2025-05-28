-- 创建用户BIT
CREATE USER 'BIT'@'localhost' IDENTIFIED BY '123456';

-- 赋予BIT查询lineitem表和更新lineitem表的discount字段的权限
GRANT SELECT ON TPCH.lineitem TO 'BIT'@'localhost';
GRANT UPDATE (discount) ON TPCH.lineitem TO 'BIT'@'localhost';

-- 收回BIT的所有权限
REVOKE ALL PRIVILEGES ON TPCH.lineitem FROM 'BIT'@'localhost';