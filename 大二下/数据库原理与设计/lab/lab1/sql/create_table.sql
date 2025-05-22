-- create_table.sql

-- 地区表
CREATE TABLE Region (
    region_id INT PRIMARY KEY, -- 地区编号，作为主键
    region_name VARCHAR(50) NOT NULL, -- 地区名称，不能为空
    comment TEXT -- 备注
);

-- 国家表
CREATE TABLE Nation (
    nation_id INT PRIMARY KEY, -- 国家编号，作为主键
    nation_name VARCHAR(50) NOT NULL, -- 国家名称，不能为空
    region_id INT, -- 地区编号
    comment TEXT, -- 备注

    -- 外键约束，引用地区表的地区编号
    FOREIGN KEY (region_id) REFERENCES Region(region_id)
);

-- 供应商基本表
CREATE TABLE Supplier (
    supplier_id INT PRIMARY KEY, -- 供应商编号，作为主键
    supplier_name VARCHAR(50), -- 供应商名称
    address VARCHAR(150), -- 供应商地址
    nation_id INT, -- 国家编号
    phone VARCHAR(20), -- 供应商电话，使用VARCHAR类型以便存储电话可能存在的特殊字符

    -- 外键约束，引用国家表的国家编号
    FOREIGN KEY (nation_id) REFERENCES Nation(nation_id)
);

-- 零件基本表
CREATE TABLE Part (
    part_id INT PRIMARY KEY, -- 零件编号，作为主键
    part_name VARCHAR(150), -- 零件名称
    mfgr VARCHAR(50), -- 制造商
    size VARCHAR(50), -- 零件尺寸，可以为NULL。注意数据集中的尺寸属性大部分无法用INT类型存储，只能用VARCHAR类型
    retail_price DECIMAL(10, 2) CHECK (retail_price > 0), -- 零件零售价，大于0
);

-- 零件供应联系表
CREATE TABLE PartSupp (
    part_id INT, -- 零件编号
    supplier_id INT, -- 供应商编号
    avail_qty INT CHECK (avail_qty > 0), -- 可用数量，大于0
    supply_price DECIMAL(10, 2) CHECK (supply_price > 0), -- 供应价格，大于0

    -- 联合主键，包含零件编号和供应商编号
    PRIMARY KEY (part_id, supplier_id),

    -- 外键约束，引用零件基本表和供应商基本表
    FOREIGN KEY (part_id) REFERENCES Part(part_id),
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
);

-- 顾客表
CREATE TABLE Customer (
    customer_id INT PRIMARY KEY, -- 顾客编号，作为主键
    name VARCHAR(50), -- 顾客姓名
    nation_id INT, -- 国籍编号

    -- 外键约束，引用国家表的国家编号
    FOREIGN KEY (nation_id) REFERENCES Nation(nation_id)
);

-- 订单表
CREATE TABLE Orders (
    order_id INT PRIMARY KEY, -- 订单编号，作为主键
    customer_id INT, -- 顾客编号
    order_date DATE, -- 订单日期
    total_amount DECIMAL(10, 2) CHECK (total_amount > 0), -- 订单总金额，大于0

    -- 外键约束，引用顾客表的顾客编号
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- 订单明细表
CREATE TABLE Lineitem (
    order_id INT, -- 订单编号
    part_id INT, -- 零件编号
    supplier_id INT, -- 供应商编号
    quantity INT CHECK (quantity > 0), -- 数量，大于0
    return_flag CHAR(1), -- 退货标记（Y/N）
    discount DECIMAL(5, 2) CHECK (discount >= 0.00 AND discount <= 1.00), -- 折扣（0.00到1.00之间）

    -- 联合主键，包含订单编号、零件编号和供应商编号
    PRIMARY KEY (order_id, part_id, supplier_id),

    -- 外键约束，引用订单表、零件基本表和供应商基本表
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (part_id) REFERENCES Part(part_id),
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
);