import json
from funclib.funclib_elimination import solve_linear_system

def perform_calculation(payload):
    # 解析接收到的数据
    data = json.loads(payload)
    method = data["method"]
    coefficient_matrix = data["coefficient_matrix"]
    constant_vector = data["constant_vector"]

    L, U, X = solve_linear_system(coefficient_matrix, constant_vector, method)

    return L, U, X