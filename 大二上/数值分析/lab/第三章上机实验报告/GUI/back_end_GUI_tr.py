import numpy as np

def create_tridiagonal_matrix(self):
    # 获取表格
    table = self.table_layout.itemAt(0).widget()

    # 将表格内容转化为矩阵
    matrix = np.zeros((self.size, 3))
    for i in range(self.size):
        for j in range(3):
            item = table.item(i, j)
            try:
                if item is not None:
                    matrix[i, j] = float(item.text())
                else:
                    matrix[i, j] = 0
            except ValueError:
                self.show_error_dialog()
                return

    # 转化为三对角矩阵
    tridiagonal_matrix = np.zeros((self.size, self.size))
    for i in range(self.size):
        if i == 0:
            tridiagonal_matrix[i, i] = matrix[i, 1]
            tridiagonal_matrix[i, i + 1] = matrix[i, 2]
        elif i == self.size - 1:
            tridiagonal_matrix[i, i] = matrix[i, 1]
            tridiagonal_matrix[i, i - 1] = matrix[i, 0]
        else:
            tridiagonal_matrix[i, i] = matrix[i, 1]
            tridiagonal_matrix[i, i - 1] = matrix[i, 0]
            tridiagonal_matrix[i, i + 1] = matrix[i, 2]

    # 发出信号并关闭窗口
    self.data_ready.emit(tridiagonal_matrix)
    self.close()