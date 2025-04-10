#import "template.typ": report-body, appendix
#import "@preview/cetz:0.3.4": canvas, draw, tree

#show: doc => report-body(
  class: "07112304",
  student-id: "1120233329",
  author: "陈墨霏",
  header: "数据库原理与设计实验报告",
  title: "实验2 标准SQL语言和简单查询",
  doc
)

#set enum(numbering: "1）")

#let sqlrequest(term) = {
  align(center)[
    #block(
      text(
        font: "SimHei",
        size: 12pt,
        term
      ),
      width: 80%,
      stroke: (thickness: 1pt, dash: "solid"),
      outset: (x: 1pt, y: 3pt)
    )
  ]
  text()[#h(0.0em)] // 用来使得块级元素后分段
}

== 实验目的

掌握SQL程序设计基本规范、熟练运用SQL语言实现数据基本查询，包括单表、分组统计查询、连接查询等。

== 实验内容

- 导入实验数据集
- 用SQL语句实现以下查询：
  + 查询所有供应商的名称、地址、联系电话。
  + 查询2014年1~10月间的总价大于1000元的订单的编号、顾客编号等订单的所有信息。
  + 统计每个顾客的订购金额。
  + 查询订单平均金额超过1000元的顾客编号及其姓名。
  + 查询与“金仓集团”在同一个国家的供应商编号、名称和地址信息。
  + 查询供应价格大于零售价格的零件名、制造商名、零售价格和供应价格。
  + 查询顾客“阿波罗”订购的订单编号、总价及其订购的零件编号、数量和零售价格。

*注：查询结果截图粘贴到实验报告中。*

== 实验步骤

=== 导入实验数据集

==== 数据集说明

由于实验没有提供统一的数据集，因此我使用 `Qwen 2.5 Max` AI生成了一些数据，并以 `.csv` 格式保存。每个表格的第一行是表头，每张表包含10条或11条数据。以下是 `supplier.csv` 的内容，作为示例。

```csv
supplier_id,supplier_name,address,nation_id,phone
1,红魔馆后勤部,幻想乡红魔乡,1,001-12345678
2,魔理沙的魔法商店,魔法之森深处,2,002-87654321
3,河城荷取工业,迷途竹林入口,3,003-11223344
4,妖梦的刀剑铺,冥界边境,4,004-55667788
5,咲夜管家服务,天界南门,5,005-99887766
6,地灵殿物资处,旧地狱核心区,6,006-13579246
7,风祝的神社商店,博丽神社旁,8,007-24681357
8,铃仙的月面物流,月之都环形山,10,008-36912587
9,八云紫的隙间贸易,妖怪之山山脚,7,009-15935748
10,火焰猫燐的怨灵回收站,旧地狱怨灵聚集地,6,010-75395168
11,金仓集团,幻想乡仓库区,1,011-88888888
```

==== 数据集导入

由于实验中是首先建立好了表，然后再导入数据，因此在导入数据时需要注意先后顺序，以满足表间的外键约束（参照完整性约束）关系。

在实验一中，已经得到过以下参照关系图：

#figure(
  image("fig/表数据关系图.png", width: 50%),
  caption: [表数据关系图],
)
#text()[#h(0.0em)] // 用来使得块级元素后分段

将关系图进行拓扑排序，得到的其中一种导入数据的顺序如下：
```text
Region → Nation → Supplier → Customer → Part → PartSupp → Orders → Lineitem
```
于是按照以上顺序进行数据导入。

===== 方式一：命令行输入SQL语句导入

例如，导入 `region.csv` 数据时，使用以下命令：
```sql
LOAD DATA INFILE '../lab2/data/region.csv'
INTO TABLE Supplier
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS -- 忽略表头
(supplier_id, supplier_name, address, nation_id, phone);
```
注意到报错：
```text
The MySQL server is running with the --secure-file-priv option so it cannot execute this statement
```
这是因为MySQL的安全选项限制了文件的读写权限。解决方法是将数据文件放在MySQL的默认目录下，或者在MySQL配置文件中修改 `secure-file-priv` 的值。

使用以下命令查看当前的 `secure-file-priv` 设置：
```sql
SHOW VARIABLES LIKE 'secure_file_priv';
```
之后将数据文件放在MySQL的默认目录下，或者在MySQL配置文件中修改 `secure-file-priv` 的值。
然后重新执行导入命令即可。

\

===== 方式二：DataGrip图形化界面导入

SQL语句的导入方式受到MySQL安全选项的限制，导入时还需要注意文件路径的问题，很不方便。而DataGrip不依赖 `LOAD DATA INFILE`，因此不受 `secure_file_priv` 的限制，可以从任意路径导入数据。

例如，导入 `region.csv` 数据时，使用以下步骤：

在DataGrip中，右键点击 `region` 表，选择 `导入/导出` — `从文件导入数据`。

#figure(
  image("fig/导入1.png", width: 50%),
  caption: [导入数据],
)
#text()[#h(0.0em)] // 用来使得块级元素后分段

选择合适路径，点击`确定`。弹出`导入`窗口后，按照默认设置，点击`确定`，即可完成导入。

#figure(
  image("fig/导入4.png", width: 50%),
  caption: [导入成功],
)
#text()[#h(0.0em)] // 用来使得块级元素后分段

其余数据通过同样方式按照顺序依次导入即可。

=== 查询数据

发起查询请求前，注意先选择架构：
```sql
USE tpch;
```

==== 查询请求一

#sqlrequest(
  "查询所有供应商的名称、地址、联系电话。"
)

```sql
SELECT supplier_name, address, phone
FROM supplier;
```
#block()[
解释：
- `SELECT` 语句用于选择要查询的列，`supplier_name`、`address` 和 `phone` 是要查询的列名。
- `FROM` 语句用于指定要查询的表，`supplier` 是要查询的表名。
]

==== 查询请求二

#sqlrequest(
  "查询2014年1~10月间的总价大于1000元的订单的编号、顾客编号等订单的所有信息。"
)

```sql
SELECT *
FROM orders
WHERE order_date BETWEEN '2014-01-01' AND '2014-10-31'
  AND total_amount > 1000;
```
#block()[
解释：
- `SELECT *` 语句用于选择所有列，`*` 表示选择所有列。
- `FROM` 语句用于指定要查询的表，`Orders` 是要查询的表名。
- `WHERE` 语句用于指定查询条件，`order_date BETWEEN '2014-01-01' AND '2014-10-31'` 表示查询日期在2014年1月1日至2014年10月31日之间的订单，`total_price > 1000` 表示查询总价大于1000元的订单。
]

==== 查询请求三

#sqlrequest(
  "统计每个顾客的订购金额。"
)

```sql
SELECT customer_id, SUM(total_amount) AS total_amount_sum
FROM orders
GROUP BY customer_id;
```
#block()[
解释：
- `SELECT` 语句用于选择要查询的列，`customer_id` 是顾客编号，`SUM(total_amount)` 用于计算每个顾客的订购金额总和，并将其命名为 `total_amount_sum`。
- `FROM` 语句用于指定要查询的表，`orders` 是要查询的表名。
- `GROUP BY` 语句用于将结果按顾客编号分组，`customer_id` 是要分组的列名。
]

\

==== 查询请求四

#sqlrequest(
  "查询订单平均金额超过1000元的顾客编号及其姓名。"
)

```sql
SELECT customer_id, name
FROM customer
WHERE customer_id IN (
    SELECT customer_id
    FROM orders
    GROUP BY customer_id
    HAVING AVG(total_amount) > 1000
);
```
#block()[
解释：
- `SELECT` 语句用于选择要查询的列，`customer_id` 是顾客编号，`name` 是顾客姓名。
- `FROM` 语句用于指定要查询的表，`customer` 是要查询的表名。
- `WHERE` 语句用于指定查询条件，`customer_id IN (...)` 表示查询顾客编号在子查询结果中的顾客。
- 子查询 `SELECT customer_id FROM orders GROUP BY customer_id HAVING AVG(total_amount) > 1000` 用于计算每个顾客的平均订购金额，并筛选出平均金额超过1000元的顾客编号。
]

==== 查询请求五

#sqlrequest(
  "查询与“金仓集团”在同一个国家的供应商编号、名称和地址信息。"
)

```sql
SELECT supplier_id, supplier_name, address
FROM supplier
WHERE nation_id = (
    SELECT nation_id
    FROM supplier
    WHERE supplier_name = '金仓集团'
);
```
#block()[
解释：
- `SELECT` 语句用于选择要查询的列，`supplier_id` 是供应商编号，`supplier_name` 是供应商名称，`address` 是供应商地址。
- `FROM` 语句用于指定要查询的表，`supplier` 是要查询的表名。
- `WHERE` 语句用于指定查询条件，`nation_id = (...)` 表示查询与“金仓集团”在同一个国家的供应商。
- 子查询 `SELECT nation_id FROM supplier WHERE supplier_name = '金仓集团'` 用于获取“金仓集团”的国家编号。
]

\

==== 查询请求六

#sqlrequest(
  "查询供应价格大于零售价格的零件名、制造商名、零售价格和供应价格。"
)

```sql
SELECT part_name, supplier_name, retail_price, supply_price
FROM part
JOIN partsupp ON part.part_id = partsupp.part_id
JOIN supplier ON partsupp.supplier_id = supplier.supplier_id
WHERE supply_price > retail_price;
```
#block()[
解释：
- `SELECT` 语句用于选择要查询的列，`part_name` 是零件名称，`supplier_name` 是供应商名称，`retail_price` 是零售价格，`supply_price` 是供应价格。
- `FROM` 语句用于指定要查询的表，`part` 是要查询的零件表，`partsupp` 是零件供应表，`supplier` 是供应商表。
- `JOIN` 语句用于连接多个表，`ON` 语句用于指定连接条件，`part.part_id = partsupp.part_id` 和 `partsupp.supplier_id = supplier.supplier_id` 分别表示零件表和零件供应表的连接条件。
- `WHERE` 语句用于指定查询条件，`supply_price > retail_price` 表示查询供应价格大于零售价格的零件。
]

==== 查询请求七

#sqlrequest(
  "查询顾客“阿波罗”订购的订单编号、总价及其订购的零件编号、数量和零售价格。"
)

```sql
SELECT 
    o.order_id, 
    o.total_amount, 
    l.part_id, 
    l.quantity, 
    p.retail_price
FROM 
    Orders o
JOIN 
    Customer c ON o.customer_id = c.customer_id
JOIN 
    Lineitem l ON o.order_id = l.order_id
JOIN 
    Part p ON l.part_id = p.part_id
WHERE 
    c.name = '阿波罗';
```
#block()[
解释：
- `SELECT` 语句用于选择要查询的列，`o.order_id` 是订单编号，`o.total_amount` 是订单总价，`l.part_id` 是零件编号，`l.quantity` 是订购数量，`p.retail_price` 是零售价格。
- `FROM` 语句用于指定要查询的表，`Orders` 是订单表，`Customer` 是顾客表，`Lineitem` 是订单明细表，`Part` 是零件表。
- `JOIN` 语句用于连接多个表，`ON` 语句用于指定连接条件，`o.customer_id = c.customer_id`、`o.order_id = l.order_id` 和 `l.part_id = p.part_id` 分别表示订单表和顾客表、订单表和订单明细表、订单明细表和零件表的连接条件。
- `WHERE` 语句用于指定查询条件，`c.name = '阿波罗'` 表示查询顾客名称为“阿波罗”的订单。
]

== 实验结果及分析

=== 查询结果

==== 查询结果一

#figure(
  image("fig/查询1.png", width: 50%),
  caption: [查询所有供应商的名称、地址、联系电话],
)
#block()[
分析：
- 查询结果显示了所有供应商的名称、地址和联系电话。
- 可以看到，供应商的名称、地址和联系电话都被正确地查询出来了。
]

==== 查询结果二

#figure(
  image("fig/查询2.png", width: 50%),
  caption: [查询2014年1~10月间的总价大于1000元的订单的编号、顾客编号等订单的所有信息],
)
#block()[
分析：
- 查询结果显示了符合条件的订单的所有信息。
- 使用了 `BETWEEN` 语句来筛选日期范围，简化了日期条件的表达。
- 查询结果验证了 `total_amount > 1000` 的条件，确保了总价大于1000元的订单被正确筛选出来。
]

==== 查询结果三

#figure(
  image("fig/查询3.png", width: 50%),
  caption: [统计每个顾客的订购金额],
)
#block()[
分析：
- 查询结果显示了每个顾客的订购金额总和。
- 使用了 `SUM` 函数对金额进行汇总，并结合 `GROUP BY` 按顾客编号分组。
- 结果表明，SQL聚合函数和分组操作能够有效地统计数据。
]

==== 查询结果四

#figure(
  image("fig/查询4.png", width: 50%),
  caption: [查询订单平均金额超过1000元的顾客编号及其姓名],
)
#block()[
分析：
- 查询结果显示了符合条件的顾客编号及其姓名。
- 使用了子查询和 `HAVING` 子句，筛选出平均金额超过1000元的顾客。
- `HAVING` 子句的作用是对分组后的数据进行过滤，与 `WHERE` 的作用不同。
]

==== 查询结果五

#figure(
  image("fig/查询5.png", width: 50%),
  caption: [查询与“金仓集团”在同一个国家的供应商编号、名称和地址信息],
)
#block()[
分析：
- 查询结果显示了与“金仓集团”在同一个国家的供应商信息。
- 使用了子查询来获取“金仓集团”的国家编号，并通过 `WHERE` 条件进行匹配。
- 子查询的嵌套使用提高了查询的灵活性。
]

==== 查询结果六

#figure(
  image("fig/查询6.png", width: 50%),
  caption: [查询供应价格大于零售价格的零件名、制造商名、零售价格和供应价格],
)
#block()[
分析：
- 查询结果显示了符合条件的零件及其相关信息。
- 使用了多表连接 (`JOIN`) 来关联零件、供应商和供应价格表。
- `supply_price > retail_price` 条件确保了筛选出供应价格大于零售价格的记录。
]

==== 查询结果七

#figure(
  image("fig/查询7.png", width: 50%),
  caption: [查询顾客“阿波罗”订购的订单编号、总价及其订购的零件编号、数量和零售价格],
)
#block()[
分析：
- 查询结果显示了顾客“阿波罗”订购的订单及其详细信息。
- 使用了多表连接 (`JOIN`) 来关联订单、顾客、订单明细和零件表。
- `WHERE` 条件确保了仅查询顾客名称为“阿波罗”的记录。
- 查询展示了复杂查询中多表关联的强大功能。
]

=== 实验总结

本次实验完成了SQL语言的基本操作，包括单表查询、分组统计查询、连接查询等任务。通过导入实验数据集并执行多种查询操作，验证了SQL语句的正确性和查询结果的准确性。同时，实验中还解决了数据导入过程中遇到的权限问题，熟悉了不同工具的使用方法。

== 实验收获与体会

通过本次实验，我对SQL语言的基本语法和查询操作有了更深入的理解，掌握了如何使用SQL进行数据查询和分析。同时，我也认识到在实际应用中，数据的导入和处理是一个重要的环节，需要注意数据的完整性和一致性。通过实验，我提高了自己的SQL编程能力，为后续的数据库学习打下了基础。

#appendix()

以下是本实验中涉及的主要程序文件及其说明：

1. `search.sql`：包含了所有的查询请求和对应的SQL语句。

以上文件均已在实验中详细说明，并附有必要的注释以便理解和复现实验过程。