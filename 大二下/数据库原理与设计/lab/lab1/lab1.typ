#import "@preview/numbly:0.1.0":numbly

#import "@preview/cuti:0.3.0": show-cn-fakebold

#set text(font: ("Times New Roman", "SimSun"), size: 12pt)
#show par: set par(leading: 14pt, justify: true,first-line-indent: 2em)
// 这里的leading难道是从8pt开始算起的吗？设置14pt，实际效果是22pt？

#set page(
  header: [
  #align(center)[数据库原理与设计实验报告]
  #v(-8pt) // 页眉内容与线的距离
  #line(length: 100%, stroke: (thickness: 1pt, dash: "solid"))
],
  numbering: "1"
)

#set heading(numbering: numbly(
  "",
  "{2:一}、",
  "{2}.{3}",
  "{2}.{3}.{4}",
))
#show heading.where(level: 2): it => {
  set text(font: "SimHei", size: 14pt)
  it
}
#show heading.where(level: 3): it => {
  set text(font: "SimHei", size: 13pt)
  it
}
#show heading.where(level: 4): it => {
  set text(font: "SimHei", size: 12pt)
  it
}
#show heading: it =>  {
    it
    par()[#text()[#h(0.0em)]]
}

#show raw.where(block: false): it => box(
    fill: luma(230),
    radius: 1pt,
    outset: (y: 3pt), // 设置outset不会影响行布局，而inset则会使得box内部文字比行内其他文字要高
    text(it, font: ("Consolas", "KaiTi"), size: 12pt)
)
#show raw.where(block: true): it => block(
    fill: luma(230),
    width: 100%,
    radius: 5pt,
    inset: 8pt,
    text(it, font: ("Consolas", "KaiTi"))
)

#show link: it => {
  set text(fill: blue)
  show-cn-fakebold(it)
}

// #set enum(
//   numbering: "（1）",
// )

#let title = text(font: "SimHei", size: 18pt)[实验1 关系数据库系统环境和数据库建立]

#align(center, text(17pt)[
  *#title*
])

#align(center)[
  #block(
    width: 80%,
  )[
    #grid(
      columns: (1fr, 1fr, 1fr),
      align(center)[
        *班级：*#underline()[07112304] 
      ],
      align(center)[
        *学号：*#underline()[1120233329]
      ],
      align(center)[
        *姓名：*#underline(evade: false)[陈墨霏]
      ],
    )         
  ]
]

== 实验目的

安装和配置数据库管理系统，设置系统环境变量，选择合适的数据库管理工具，为后续的数据库开发和操作提供良好的支持环境。

掌握关系数据库的基本操作，熟悉数据库DDL语言的使用，能够创建数据库、定义模式和基本表结构，为后续数据库操作和应用开发打下坚实基础。

== 实验内容

+	定义数据库，采用中文字符集创建名为TPCH的数据库
+	定义模式，在数据库TPCH中创建名为Sales的模式
+	定义基本表

#align(
  center,
  block(
    width: 65%,
      figure(
        table(
          columns: 3,
          stroke: 1pt,
          table.header([表名], [所含列], [表含义]),
          [Region], [地区编号，地区名称，备注], [地区表],
          [Nation], [国家编号，国家名称，地区编号，备注], [国家表],
          [Supplier], [供应商编号，供应商名称，供应商地址，国家编号，供应商电话], [供应商基本表],
          [Part], [零件编号，零件名称，制造商，尺寸，零售价格], [零件基本表],
          [PartSupp], [零件编号，供应商编号，可用数量，供应价格], [零件供应联系表],
          [Customer], [顾客编号，姓名，国籍编号], [顾客表],
          [Orders], [订单编号，顾客编号，订单日期，订单总金额], [订单表],
          [Lineitem], [订单编号，零件编号，供应商编号，数量，退货标记，折扣[0.00, 1.00]], [订单明细表],
      )
    )
  )
)
注：Part表部分数据缺少尺寸。

== 实验步骤

=== 实验环境及工具

本实验在Windows 11操作系统下进行，使用MySQL 8.0.32版本的数据库管理系统。

MySQL是一个开源的关系数据库管理系统，广泛应用于Web应用程序和企业级应用中。它支持多种数据类型、索引、视图、存储过程等功能，具有高性能和可扩展性。可以从 #link("https://dev.mysql.com/downloads/mysql/")[*官方网站*] 下载并安装。在安装时注意将`mysql.exe`添加到
系统环境变量，以便在命令行中直接使用MySQL命令。

#figure(
  image("fig/install0.png", width: 65%),
  caption: [MySQL数据库官方网站页面],
)

#figure(
  image("fig/install1.png", width: 65%),
  caption: [MySQL安装界面],
)

#figure(
  image("fig/sysenvconfig.png", width: 65%),
  caption: [系统环境变量配置],
)
#text()[#h(0.0em)] // 用来使得块级元素后分段

为了进行更加高效的数据库操作，我选择了JetBrains公司出品的数据库管理工具 #link("https://www.jetbrains.com/datagrip/")[*DataGrip*] 进行操作。DataGrip是一个跨平台的数据库管理工具，支持多种数据库系统，包括MySQL、PostgreSQL、Oracle等。它提供了强大的SQL编辑器、数据可视化和调试功能，能够帮助开发者更高效地进行数据库操作。

#figure(
  image("fig/DataGrip基础界面.png", width: 65%),
  caption: [DataGrip基础界面],
)

=== 连接到MySQL数据库

==== 使用MySQL Workbench连接

MySQL Workbench是MySQL官方提供的图形化管理工具，可以方便地连接和管理MySQL数据库。以下是使用MySQL Workbench连接到MySQL数据库的步骤：

打开MySQL Workbench，可以看到下方MySQL Connection部分中已经创建好了一个本地实例（Local Instance）。直接点击该实例即可连接到本地MySQL数据库。

#figure(
  image("fig/workbench_conn1.png", width: 65%),
  caption: [MySQL Workbench连接到本地数据库],
)

#figure(
  image("fig/workbench_conn2.png", width: 65%),
  caption: [连接成功],
)

==== 使用命令行连接

图形化界面可能更加简单易用，但是对于一些复杂操作的执行，命令行可能更加高效。以下是使用命令行连接到MySQL数据库的步骤：

打开命令行，输入以下命令连接到MySQL数据库：
```bash
mysql -h localhost -P 3306 -u root -p
```
#text()[#h(0.0em)] // 用来使得块级元素后分段

其中，`-h`指定主机名，`-P`指定端口号，`-u`指定用户名，`-p`表示需要输入密码。连接成功后，可以看到MySQL的命令行提示符。或可直接输入以下命令连接到MySQL数据库：
```bash
mysql -u root -p
```
#text()[#h(0.0em)] // 用来使得块级元素后分段

输入密码后即可连接到本地MySQL数据库。

#figure(
  image("fig/cli_conn.png", width: 65%),
  caption: [命令行连接到MySQL数据库],
)

==== 使用DataGrip连接

在DataGrip中，点击左上角的`+`号，选择`数据源`，然后选择`MySQL`。在弹出的窗口中输入连接信息，包括主机名（此处为`localhost`）、端口号（此处为`3306`）、用户名和密码。点击`测试连接`按钮测试连接是否成功，如果成功则可以点击`确定`按钮保存连接信息。 

#figure(
  image("fig/datagrip_conn1.png", width: 65%),
  caption: [新建数据源],
)

#figure(
  image("fig/datagrip_conn2.png", width: 65%),
  caption: [连接配置以及测试连接],
)

#figure(
  image("fig/datagrip_conn3.png", width: 65%),
  caption: [连接成功],
)

=== 数据库的建立

考虑到DataGrip集成了美观易用的图形化界面，以及简洁高效的命令行接口，接下来的实验将主要借助DataGrip进行MySQL数据库的建立和操作。

右键数据库，点击`新建` — `查询控制台`（或按快捷键`Ctrl+Shift+Q`），创建一个新的查询控制台，在其中可以输入SQL语句进行数据库操作。

==== 定义数据库/定义模式

MySQL 中没有`模式`（`schema`）的概念（`database`就是`schema`）。使用下列命令创建数据库并切换到该数据库：

```sql
-- create_db.sql

CREATE DATABASE TPCH CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE TPCH;
```
#figure(
  image("fig/createdb.png", width: 65%),
  caption: [创建数据库TPCH],
)

==== 定义基本表

题目已提供了属性描述，但未提供每个属性的具体数据类型，因此在定义基本表时，为了避免在导入数据时出现类型错误，需要先对于数据集有一基本了解。在乐学平台上提供了实验数据集的下载链接，点击下载即可得到本课程的实验数据集。之后可以打开数据集来查看数据集的具体内容。

使用以下命令创建基本表：

```sql
-- create_table.sql

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
```

#figure(
  image("fig/createTable.png", width: 65%),
  caption: [创建基本表],
)

== 实验结果及分析

本实验借助MySQL和DataGrip成功创建了TPCH数据库，并在其中定义了Sales模式和基本表。通过图形化界面和命令行两种方式，完成了数据库的创建和基本表的定义。

通过右键`表`—`图`—`显示图`（或按快捷键`Ctrl+Alt+Shift+U`）来显示当前情况下的表数据关系图。

#figure(
  image("fig/表数据关系图.png", width: 50%),
  caption: [表数据关系图],
)
#text()[#h(0.0em)] // 用来使得块级元素后分段

可以看到，创建的基本表中包含了各个表的主键和外键约束，确保了数据的完整性和一致性。这些约束不仅能够有效防止数据冗余和不一致，还能够通过外键关系建立表与表之间的关联，为后续的查询和操作提供了便利。此外，合理的属性设计和数据类型选择也为数据库的高效运行奠定了基础。

== 实验收获与体会

通过本次实验，我熟悉了MySQL数据库的安装与配置过程，掌握了基本的数据库操作方法，包括创建数据库、定义表结构及设置主外键约束等。借助DataGrip工具，我体验到了图形化界面带来的便捷性，同时也加深了对SQL语句的理解和应用能力。

此外，本实验让我认识到数据库设计的重要性。合理的表结构设计和属性约束不仅能提高数据存储的效率，还能确保数据的完整性和一致性。这为后续的数据库开发和复杂查询操作奠定了坚实的基础。

#pagebreak()

#set heading(numbering: numbly(
  "", // use {level:format} to specify the format
  "", // if format is not specified, arabic numbers will be used
  "{3}.", // here, we only want the 3rd level
  "",
))
== 附录：程序清单及说明

以下是本实验中涉及的主要程序文件及其说明：

1. `create_db.sql`：
  包含创建数据库TPCH的SQL语句，以及切换到该数据库的命令。

2. `create_table.sql`：
  包含定义基本表的SQL语句，包括表结构、主键和外键约束等。

以上文件均已在实验中详细说明，并附有必要的注释以便理解和复现实验过程。




