import numpy as np

def trapezoid_formula(f, a, b):
    h = b - a
    I = h / 2 * (f(a) + f(b))

    # 求二阶导数的最大值
    x = np.linspace(a, b, 100)

    f_double_prime = f.derivative(f.get_variables()[0], order=2) # f是一元函数，所以只有一个变量

    max_f_double_prime = max(abs(f_double_prime(x)))

    # 方法误差R
    R = -1 / 12 * h**3 * max_f_double_prime

    return I, R

def simpson_formula(f, a, b):
    h = (b - a) / 2
    I = h / 3 * (f(a) + 4 * f(a + h) + f(b))

    # 求四阶导数的最大值
    x = np.linspace(a, b, 100)
    f_fourth_prime = f.derivative(f.get_variables()[0], 4) # f是一元函数，所以只有一个变量

    max_f_fourth_prime = max(abs(f_fourth_prime(x)))

    # 方法误差R
    R = -1 / 90 * h**5 * max_f_fourth_prime
    return I, R

def three_divide_eight_formula(f, a, b):
    h = (b - a) / 3
    I = 3 / 8 * h * (f(a) + 3 * f(a + h) + 3 * f(a + 2 * h) + f(b))

    # 求四阶导数的最大值
    x = np.linspace(a, b, 100)
    f_fourth_prime = f.derivative(f.get_variables()[0], 4) # f是一元函数，所以只有一个变量

    max_f_fourth_prime = max(abs(f_fourth_prime(x)))

    # 方法误差R
    R = -3 / 80 * h**5 * max_f_fourth_prime
    return I, R

def cotes_formula(f, a, b):
    h = (b - a) / 4
    I = 2 * h / 45 * (7 * f(a) + 32 * f(a + h) + 12 * f(a + 2 * h) + 32 * f(a + 3 * h) + 7 * f(b))

    # 求六阶导数的最大值
    x = np.linspace(a, b, 100)
    f_sixth_prime = f.derivative(f.get_variables()[0], 6) # f是一元函数，所以只有一个变量

    max_f_sixth_prime = max(abs(f_sixth_prime(x)))

    # 方法误差R
    R = -8 / 945 * h**7 * max_f_sixth_prime
    return I, R

def general_newton_cotes_formula(f, a, b, nn):
    import pandas as pd
    df_coefficients = pd.read_excel("coefficients_nc.xlsx")
    h = (b - a) / nn
    I = 0
    for i in range(0, nn + 1):
        I += df_coefficients.loc[nn - 1, i] * f(a + i * h)
    I *= b - a

    # 方法误差R
    R = "" # 未实现
    return I, R

def read_coefficients_from_excel():
    import pandas as pd
    df = pd.read_excel("coefficients/coefficients_nc.xlsx")
    return df
