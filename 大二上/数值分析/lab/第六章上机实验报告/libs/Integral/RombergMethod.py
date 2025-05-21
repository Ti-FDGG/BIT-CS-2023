from libs.Integral.NewtonCotesFormula import trapezoid_formula

def calculate_next_trapezoid(f, a, b, T_previous, k):
    h = (b - a) / (2 ** k)  # 计算当前步的 h
    summation = sum(f(a + (2 * i - 1) * h) for i in range(1, 2 ** (k - 1) + 1))
    T_current = 0.5 * T_previous + h * summation
    print(f"T_{2 ** k} = {T_current}")
    return T_current

def calculate_next_simpson(T_current, T_previous, k):
    S_current = (4 * T_current - T_previous) / 3
    print(f"S_{2 ** (k - 1)} = {S_current}")
    return S_current

def calculate_next_cotes(S_current, S_previous, k):
    C_current = (16 * S_current - S_previous) / 15
    print(f"C_{2 ** (k - 2)} = {C_current}")
    return C_current

def calculate_next_romberg(C_current, C_previous, k):
    R_current = (64 * C_current - C_previous) / 63
    print(f"R_{2 ** (k - 3)} = {R_current}")
    return R_current

def check_convergence(current_value, previous_value, tolerance):
    return abs(current_value - previous_value) < tolerance

def romberg_method(f, a, b, tolerance=1e-6):
    T = []
    S = []
    C = []
    R = []
    previous_value = None
    current_value = None

    # 计算初始的 T_2^0
    initial_trapezoid, _ = trapezoid_formula(f, a, b)
    print(f"T_{2 ** 0} = {initial_trapezoid}")
    T.append(initial_trapezoid)
    previous_value = T[-1]
    k = 1

    while True:
        # 计算下一个 T
        if k > 1:
            previous_value = current_value
        current_value = calculate_next_trapezoid(f, a, b, T[-1], k)
        if check_convergence(current_value, previous_value, tolerance):
            return current_value
        T.append(current_value)

        if k >= 1:
            # 计算下一个 S
            previous_value = current_value
            current_value = calculate_next_simpson(T[-1], T[-2], k)
            if check_convergence(current_value, previous_value, tolerance):
                return current_value
            S.append(current_value)

        if k >= 2:
            # 计算下一个 C
            previous_value = current_value
            current_value = calculate_next_cotes(S[-1], S[-2], k)
            if check_convergence(current_value, previous_value, tolerance):
                return current_value
            C.append(current_value)

        if k >= 3:
            # 计算下一个 R
            previous_value = current_value
            current_value = calculate_next_romberg(C[-1], C[-2], k)
            if check_convergence(current_value, previous_value, tolerance):
                return current_value
            R.append(current_value)

        k += 1
