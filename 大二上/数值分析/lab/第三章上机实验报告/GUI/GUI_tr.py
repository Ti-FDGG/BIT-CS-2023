from PyQt6.QtWidgets import QApplication, QMainWindow, QLabel, QTableWidget, QVBoxLayout, QWidget, QLineEdit, QPushButton, QScrollArea, QHBoxLayout, QMessageBox, QTableWidgetItem
from PyQt6.QtCore import Qt, pyqtSignal
import numpy as np
import GUI.back_end_GUI_tr as back_end_GUI_tr

class TridiagonalMatrixWindow(QMainWindow):
    data_ready = pyqtSignal(np.ndarray)

    def __init__(self, size):
        super().__init__()

        self.size = size
        self.cellsize = 50

        # 获取屏幕的几何信息
        screen_geometry = QApplication.primaryScreen().geometry()
        ax = (screen_geometry.width() - self.width()) // 2
        ay = (screen_geometry.height() - self.height()) // 2
        aw = screen_geometry.width() // 6
        ah = screen_geometry.height() // 2

        self.setWindowTitle("三对角矩阵")
        self.setGeometry(ax, ay, aw, ah)

        self.setWindowFlags(self.windowFlags() & ~Qt.WindowType.WindowMaximizeButtonHint)

        # 创建一个中心窗口部件
        central_widget = QWidget(self)
        self.setCentralWidget(central_widget)

        # 创建一个垂直布局
        layout = QVBoxLayout(central_widget)

        # 创建一个按钮
        self.button = QPushButton("创建三对角系数矩阵", self)
        self.button.clicked.connect(self.create_tridiagonal_matrix)
        layout.addWidget(self.button)

        # 创建一个滚动区域
        self.scroll_area = QScrollArea(self)
        self.scroll_area.setWidgetResizable(True)

        # 创建一个容器部件来放置表格
        self.table_container = QWidget()
        self.table_layout = QVBoxLayout(self.table_container)
        self.scroll_area.setWidget(self.table_container)

        # 将滚动区域添加到主布局
        layout.addWidget(self.scroll_area)

        # 创建表格
        self.create_table()

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
        size = self.size

        # 清除旧表格
        for i in reversed(range(self.table_layout.count())):
            widget = self.table_layout.itemAt(i).widget()
            if widget is not None:
                widget.deleteLater()

        # 创建一个新的 n×3 表格
        self.init_table(size, 3, self.table_layout)

    def show_error_dialog(self):
        msg = QMessageBox(self)
        msg.setIcon(QMessageBox.Icon.Warning)
        msg.setWindowTitle("错误")
        msg.setText("表格内容应为数值")
        msg.setStandardButtons(QMessageBox.StandardButton.Ok)
        msg.exec()

    def create_tridiagonal_matrix(self):
        back_end_GUI_tr.create_tridiagonal_matrix(self)