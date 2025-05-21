import numpy as np
import json

def double_composite_simpson_formula(f, bounds, m, n):
    '''
    二重积分的复化Simpson公式
    :param f: 被积函数
    :param bounds: 积分区间，格式为 [[x0, x1], [y0, y1]]
    :param m: x方向的分段数
    :param n: y方向的分段数
    :return: 积分值
    '''
    
    x0, x1 = bounds[0]
    y0, y1 = bounds[1]
    hx = (x1 - x0) / (m - 1)  # x方向的步长
    hy = (y1 - y0) / (n - 1)  # y方向的步长
    
    # 创建网格点
    x_vals = np.linspace(x0, x1, m)
    y_vals = np.linspace(y0, y1, n)
    
    # 计算积分
    I = 0.0
    for i in range(m):
        for j in range(n):
            xi = x_vals[i]
            yj = y_vals[j]
            weight_x = 1 if i == 0 or i == m-1 else (4 if i % 2 == 1 else 2)
            weight_y = 1 if j == 0 or j == n-1 else (4 if j % 2 == 1 else 2)
            I += f(xi, yj) * weight_x * weight_y
    
    I *= hx * hy / 9
    return I

def double_gauss_formula(f, bounds, m, n):
    '''
    二重积分的Gauss求积公式
    :param f: 被积函数
    :param bounds: 积分区间，格式为 [[x0, x1], [y0, y1]]
    :param m: x方向的分段数
    :param n: y方向的分段数
    :return: 积分值
    '''
    x0, x1 = bounds[0]
    y0, y1 = bounds[1]

    # 解析 JSON 文件
    with open('coefficients/coefficients_gauss.json', 'r') as file:
        coefficients = json.load(file)

    # 提取 m 对应的 ti 和 wi 字段
    ti_m = coefficients[str(m)]['ti']
    wi_m = coefficients[str(m)]['wi']
    ti_n = coefficients[str(n)]['ti']
    wi_n = coefficients[str(n)]['wi']

    # 计算积分
    I = 0.0
    for i in range(m):
        for j in range(n):
            xi = (x1 - x0) / 2 * ti_m[i] + (x1 + x0) / 2
            yj = (y1 - y0) / 2 * ti_n[j] + (y1 + y0) / 2
            I += wi_m[i] * wi_n[j] * f(xi, yj)
    
    I *= (x1 - x0) / 2 * (y1 - y0) / 2
    return I
