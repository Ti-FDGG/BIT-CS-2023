import numpy as np
def composite_trapezoid_formula(f, a, b, M):
    h = (b - a) / M
    I = 0.5 * (f(a) + f(b))
    for i in range(1, M):
        I += f(a + i * h)
    I *= h

    # 求二阶导数的最大值
    x = np.linspace(a, b, 100)
    f_double_prime = f.derivative(f.get_variables()[0], 2) # f是一元函数，所以只有一个变量

    max_f_double_prime = max(abs(f_double_prime(x)))

    # 方法误差R
    R = -1 / 12 * h**2 * (b - a) * max_f_double_prime
    return I, R

def composite_simpson_formula(f, a, b, M):
    if M % 2 != 0:
        raise ValueError("M must be even for Simpson's rule")
    h = (b - a) / M
    I = f(a) + f(b)
    for i in range(1, M, 2):
        I += 4 * f(a + i * h)
    for i in range(2, M - 1, 2):
        I += 2 * f(a + i * h)
    I *= h / 3

    # 求四阶导数的最大值
    x = np.linspace(a, b, 100)
    f_fourth_prime = f.derivative(f.get_variables()[0], 4) # f是一元函数，所以只有一个变量

    max_f_fourth_prime = max(abs(f_fourth_prime(x)))

    # 方法误差R
    R = -1 / 180 * h**4 * (b - a) * max_f_fourth_prime
    return I, R

def composite_three_divide_eight_formula(f, a, b, M):
    if M % 3 != 0:
        raise ValueError("M must be a multiple of 3 for 3/8 rule")
    h = (b - a) / M
    I = f(a) + f(b)
    for i in range(1, M):
        if i % 3 == 0:
            I += 2 * f(a + i * h)
        else:
            I += 3 * f(a + i * h)
    I *= 3 * h / 8

    # 求四阶导数的最大值
    x = np.linspace(a, b, 100)
    f_fourth_prime = f.derivative(f.get_variables()[0], 4) # f是一元函数，所以只有一个变量

    max_f_fourth_prime = max(abs(f_fourth_prime(x)))

    # 方法误差R
    R = -3 / 80 * h**4 * (b - a) * max_f_fourth_prime
    return I, R

def composite_cotes_formula(f, a, b, M):
    if M % 4 != 0:
        raise ValueError("M must be a multiple of 4 for Cotes' rule")
    h = (b - a) / M
    I = 7 * f(a) + 7 * f(b)
    for i in range(1, M):
        if i % 4 == 0:
            I += 14 * f(a + i * h)
        elif i % 2 == 0:
            I += 12 * f(a + i * h)
        else:
            I += 32 * f(a + i * h)
    I *= 2 * h / 45

    # 求六阶导数的最大值
    x = np.linspace(a, b, 100)
    f_sixth_prime = f.derivative(f.get_variables()[0], 6) # f是一元函数，所以只有一个变量

    max_f_sixth_prime = max(abs(f_sixth_prime(x)))

    # 方法误差R
    R = -2 / 945 * h**6 * (b - a) * max_f_sixth_prime
    return I, R