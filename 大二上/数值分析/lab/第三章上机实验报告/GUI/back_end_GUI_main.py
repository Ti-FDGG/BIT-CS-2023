from PyQt6.QtWidgets import QTableWidgetItem, QTableWidget

def handle_tridiagonal_matrix_data(self, data):        
    # 清除旧表格
    for i in reversed(range(self.table_layout1.count())):
        widget = self.table_layout1.itemAt(i).widget()
        if widget is not None:
            widget.deleteLater()

    # 获取矩阵的大小
    size = data.shape[0]

    # 创建一个新的 size×size 表格
    table = QTableWidget(size, size, self)
    table.setHorizontalHeaderLabels([f"列{i+1}" for i in range(size)])
    table.setVerticalHeaderLabels([f"行{i+1}" for i in range(size)])
    self.table_layout1.addWidget(table)

    # 设置单元格的横纵宽度相等
    cellsize = self.cellsize
    for i in range(size):
        table.setRowHeight(i, cellsize)
    for i in range(size):
        table.setColumnWidth(i, cellsize)

    # 填充表格数据
    for i in range(size):
        for j in range(size):
            item = QTableWidgetItem(str(data[i, j]))
            table.setItem(i, j, item)

def pack_data(self, index):
    methods = ["Gaussian", "Crout", "Sqrt"]
    method = methods[index]

    # 获取第一个表格数据（系数矩阵）
    try:
        table1 = self.table_layout1.itemAt(0).widget()
    except AttributeError:
        self.show_error_dialog("请先创建系数矩阵和常数向量")
        return

    size = table1.rowCount()
    coefficient_matrix = []
    for i in range(size):
        row = []
        for j in range(size):
            item = table1.item(i, j)
            try:
                row.append(float(item.text()) if item is not None else 0)
            except ValueError:
                self.show_error_dialog("表格内容应为数值")
                return
        coefficient_matrix.append(row)

    # 获取第二个表格数据（常数向量）
    table2 = self.table_layout2.itemAt(0).widget()
    if table2 is None:
        self.show_error_dialog("请先创建系数矩阵和常数向量")
        return

    constant_vector = []
    for i in range(size):
        item = table2.item(i, 0)
        try:
            constant_vector.append(float(item.text()) if item is not None else 0)
        except ValueError:
            self.show_error_dialog("表格内容应为数值")
            return

    # 打包数据
    payload = {
        "method": method,
        "coefficient_matrix": coefficient_matrix,
        "constant_vector": constant_vector
    }

    return payload
