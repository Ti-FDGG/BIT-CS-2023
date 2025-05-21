import json
import numpy as np
from PyQt6.QtCore import Qt
from PyQt6.QtWidgets import QTableWidgetItem, QApplication, QMainWindow, QLabel, QTableWidget, QVBoxLayout, QWidget, QLineEdit, QPushButton, QScrollArea, QHBoxLayout, QMessageBox, QComboBox
import GUI.back_end_GUI_main as back_end_GUI_main
import funclib.calculate as calculate
from GUI.GUI_tr import TridiagonalMatrixWindow

class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.cellsize = 50
        self.init_ui()

    def init_ui(self):
        self.setWindowTitle("消元法解线性方程组程序")
        self.set_geometry()
        central_widget = self.create_central_widget()
        layout = QVBoxLayout(central_widget)
        input_layout = self.create_input_layout()
        layout.addLayout(input_layout)
        scroll_layout = self.create_scroll_layout()
        layout.addLayout(scroll_layout)

    def set_geometry(self):
        screen_geometry = QApplication.primaryScreen().geometry()
        ax = (screen_geometry.width() - self.width()) // 2
        ay = (screen_geometry.height() - self.height()) // 2
        aw = screen_geometry.width() // 2
        ah = screen_geometry.height() // 2
        self.setGeometry(ax, ay, aw, ah)

    def create_central_widget(self):
        central_widget = QWidget(self)
        self.setCentralWidget(central_widget)
        return central_widget

    def create_input_layout(self):
        input_layout = QHBoxLayout()
        self.input_box = QLineEdit(self)
        self.input_box.setPlaceholderText("请输入正整数")
        self.combo_box = QComboBox(self)
        self.combo_box.addItems(["创建一般系数矩阵", "创建三对角系数矩阵"])
        self.combo_box.activated.connect(self.on_create_combobox_changed)
        self.method_combo_box = QComboBox(self)
        self.method_combo_box.addItems(["高斯消元法", "克劳特消元法", "平方根法"])
        self.method_combo_box.activated.connect(self.on_method_combobox_changed)
        input_layout.addWidget(self.input_box)
        input_layout.addWidget(self.combo_box)
        input_layout.addWidget(self.method_combo_box)
        return input_layout

    def create_scroll_layout(self):
        scroll_layout = QHBoxLayout()
        self.scroll_area1, self.table_layout1 = self.create_scroll_area("系数矩阵 A")
        self.scroll_area2, self.table_layout2 = self.create_scroll_area("常数向量 B")
        self.scroll_area3, self.table_layout3 = self.create_scroll_area("解向量 X")
        scroll_layout.addLayout(self.create_container_layout(self.scroll_area1, "系数矩阵 A"))
        scroll_layout.addLayout(self.create_container_layout(self.scroll_area2, "常数向量 B"))
        scroll_layout.addLayout(self.create_container_layout(self.scroll_area3, "解向量 X"))
        scroll_layout.setStretch(0, 10)
        scroll_layout.setStretch(1, 3)
        scroll_layout.setStretch(2, 3)
        return scroll_layout

    def create_scroll_area(self, label_text):
        scroll_area = QScrollArea(self)
        scroll_area.setWidgetResizable(True)
        table_container = QWidget()
        table_layout = QVBoxLayout(table_container)
        scroll_area.setWidget(table_container)
        return scroll_area, table_layout

    def create_container_layout(self, scroll_area, label_text):
        container_layout = QVBoxLayout()
        label = QLabel(label_text)
        container_layout.addWidget(label)
        container_layout.addWidget(scroll_area)
        return container_layout


    def init_table(self, rows, columns, layout):
        table = QTableWidget(rows, columns, self)
        table.setHorizontalHeaderLabels([f"列{i+1}" for i in range(columns)])
        table.setVerticalHeaderLabels([f"行{i+1}" for i in range(rows)])
        layout.addWidget(table)

        # 设置单元格的横纵宽度相等
        for i in range(rows):
            table.setRowHeight(i, self.cellsize)
        for i in range(columns):
            table.setColumnWidth(i, self.cellsize)

    def create_table(self):
        # 获取用户输入的边长
        try:
            size = int(self.input_box.text())
            if not (isinstance(size, int) and size > 0 and size < 300):
                raise ValueError
        except ValueError:
            self.show_error_dialog("系数矩阵阶数应为小于300的正整数！")
            return

        # 清除旧表格
        for i in reversed(range(self.table_layout1.count())):
            widget = self.table_layout1.itemAt(i).widget()
            if widget is not None:
                widget.deleteLater()

        for i in reversed(range(self.table_layout2.count())):
            widget = self.table_layout2.itemAt(i).widget()
            if widget is not None:
                widget.deleteLater()

        for i in reversed(range(self.table_layout3.count())):
            widget = self.table_layout3.itemAt(i).widget()
            if widget is not None:
                widget.deleteLater()

        # 创建一个新的正方形表格
        self.init_table(size, size, self.table_layout1)

        # 创建一个新的 n×1 表格列
        self.init_table(size, 1, self.table_layout2)

        # 创建一个新的 n×1 表格列用于显示解
        self.init_table(size, 1, self.table_layout3)

    def show_error_dialog(self, message):
        msg = QMessageBox()
        msg.setIcon(QMessageBox.Icon.Warning)
        msg.setText(message)
        msg.setStandardButtons(QMessageBox.StandardButton.Ok)
        msg.exec()

        # 选择输入框中的文本
        self.input_box.selectAll()

    def on_create_combobox_changed(self, index):
        self.create_table()
        if index == 1:
            try:
                size = int(self.input_box.text())
                if size < 3:
                    raise ValueError
            except ValueError:
                self.show_error_dialog("三对角矩阵阶数至少为3！")
                return
            self.open_tridiagonal_matrix_window(size)

    def open_tridiagonal_matrix_window(self, size):
        self.tridiagonal_window = TridiagonalMatrixWindow(size=size)
        self.tridiagonal_window.setWindowModality(Qt.WindowModality.ApplicationModal)
        self.tridiagonal_window.data_ready.connect(self.handle_tridiagonal_matrix_data)
        self.tridiagonal_window.show()
    
    def handle_tridiagonal_matrix_data(self, data):        
        back_end_GUI_main.handle_tridiagonal_matrix_data(self, data)

    def on_method_combobox_changed(self, index):
        payload = back_end_GUI_main.pack_data(self, index)
        if payload is not None:
            # try:
            L, U, X = calculate.perform_calculation(json.dumps(payload))
            # except:
            #     self.show_error_dialog("计算出现错误！")
            #     return
            self.fill_result_table(X)
        else:
            return

    def fill_result_table(self, result):
        try:
            table3 = self.table_layout3.itemAt(0).widget()
        except AttributeError:
            self.show_error_dialog("请先创建结果表格")
            return

        size = len(result)
        table3.setRowCount(size)
        table3.setColumnCount(1)
        for i in range(size):
            if np.isnan(result[i]):
                self.show_error_dialog("无法计算！")
                return
            else:
                item = QTableWidgetItem(str(result[i]))
                table3.setItem(i, 0, item)