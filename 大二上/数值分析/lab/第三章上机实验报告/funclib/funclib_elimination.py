import numpy as np

def gaussian_lu(A, n, B):
    A = np.array(A)  # 确保 A 是一个 NumPy 数组
    L = np.eye(n)
    U = np.zeros((n, n))
    P = np.eye(n)
    
    for i in range(n):
        # 寻找列主元素
        max_row = np.argmax(abs(A[i:n, i])) + i
        if i != max_row:
            # 交换行
            A[[i, max_row], :] = A[[max_row, i], :]
            P[[i, max_row], :] = P[[max_row, i], :]
            if i > 0:
                L[[i, max_row], :i] = L[[max_row, i], :i]
        
        for j in range(i, n):
            sum_val = sum(L[i][k] * U[k][j] for k in range(i))
            U[i][j] = A[i][j] - sum_val
        for j in range(i+1, n):
            sum_val = sum(L[j][k] * U[k][i] for k in range(i))
            L[j][i] = (A[j][i] - sum_val) / U[i][i]
    
    X = solve_lu(L, U, np.dot(P, B))
    return L, U, X

def crout_lu(A, n, B):
    L = np.zeros((n, n))
    U = np.eye(n)
    
    for j in range(n):
        for i in range(j, n):
            sum_val = sum(L[i][k] * U[k][j] for k in range(j))
            L[i][j] = A[i][j] - sum_val
        for i in range(j+1, n):
            sum_val = sum(L[j][k] * U[k][i] for k in range(j))
            U[j][i] = (A[j][i] - sum_val) / L[j][j]
    
    X = solve_lu(L, U, B)
    return L, U, X

def sqrt_lu(A, n, B):
    L = np.zeros((n, n))
    U = np.zeros((n, n))
    
    for i in range(n):
        for j in range(i+1):
            sum_val = sum(L[i][k] * L[j][k] for k in range(j))
            if i == j:
                L[i][j] = np.sqrt(A[i][i] - sum_val)
            else:
                L[i][j] = (A[i][j] - sum_val) / L[j][j]
    
    U = L.T
    
    X = solve_lu(L, U, B)
    return L, U, X

def solve_lu(L, U, B):
    n = len(L)
    # 求解 LZ = B
    Z = np.zeros(n)
    for i in range(n):
        Z[i] = (B[i] - sum(L[i][j] * Z[j] for j in range(i))) / L[i][i]
    print(Z)

    # 求解 UX = Z
    X = np.zeros(n)
    for i in range(n-1, -1, -1):
        X[i] = (Z[i] - sum(U[i][j] * X[j] for j in range(i+1, n))) / U[i][i]
    print(X)
    return X

def solve_linear_system(A, B, mode):
    n = len(A)
    
    if mode == 'Gaussian':
        return gaussian_lu(A, n, B)
    elif mode == 'Crout':
        return crout_lu(A, n, B)
    elif mode == 'Sqrt':
        return sqrt_lu(A, n, B)
    else:
        raise ValueError("Invalid mode. Choose from 'Gaussian', 'Crout', or 'Sqrt'.")