import json
import numpy as np

def gauss_formula(f, a, b, n):
    # 解析 JSON 文件
    with open('coefficients/coefficients_gauss.json', 'r') as file:
        coefficients = json.load(file)
    
    # 提取 n 对应的 ti, wi, 1/coef, deri_rank 的值
    ti = coefficients[str(n)]['ti']
    wi = coefficients[str(n)]['wi']
    one_divided_by_coef = coefficients[str(n)]['Rn']['1/coef']
    deri_rank = coefficients[str(n)]['Rn']['deri_rank']
    
    # 计算 xi 和 I
    xi = [(a + b) / 2 + (b - a) * t / 2 for t in ti]
    I = (b - a) / 2 * sum(w * f(x) for w, x in zip(wi, xi))
    
    # 求 deri_rank 阶导数的最大值
    x = np.linspace(a, b, 100)
    f_double_prime = f.derivative(f.get_variables()[0], deri_rank) # f 是一元函数，所以只有一个变量
    
    max_f_double_prime = max(abs(f_double_prime(x)))

    # 方法误差 R
    R = 1 / one_divided_by_coef * max_f_double_prime

    return I, R