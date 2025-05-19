import numpy as np
import matplotlib.pyplot as plt
from matplotlib.pyplot import cm
import pandas as pd

df = pd.read_csv('lr_itr_GD.csv')

#输入数据和标签
x_data = [338.,333.,328.,207.,226.,25.,179.,60.,208.,606.]
y_data = [640.,633.,619.,393.,428.,27.,193.,66.,226.,1591]

#初始值和超参数设置
b = -150
w = 0
lr = 1.1e-6
iteration = 100000

#w_list和b_list用于记录每一轮迭代的w和b值，用于绘图
w_list = [0] * iteration
b_list = [0] * iteration

for i in range(iteration):
    b_grad = 0
    w_grad = 0
    #实现梯度下降算法
    for n in range(len(x_data)):
        b_grad = b_grad - 2.0*(y_data[n] - b - w*x_data[n])*1.0
        w_grad = w_grad - 2.0*(y_data[n] - b - w*x_data[n])*x_data[n]
    # 更新b和w
    b = b - lr*b_grad
    w = w - lr*w_grad

    w_list[i] = w
    b_list[i] = b

#绘图部分
# w-b图

#设置背景
plt.figure()
plt.title("Linear Regression using GD")

#绘制每一个数据点
plt.scatter(b_list, w_list, color="black", s=1)  # s is the size of the points
plt.xlabel("b")
plt.ylabel("w")

plt.xlim(-200, -80)
plt.ylim(-4, 5)

plt.legend()
plt.show()

# loss-iteration图
# 损失函数
loss = lambda b, w: sum((y - (b + w * x)) ** 2 for x, y in zip(x_data, y_data)) / len(x_data)

plt.figure()
plt.title("Loss using GD")
plt.plot(range(iteration), [loss(b, w) for b, w in zip(b_list, w_list)], color="black")
plt.xlabel("iteration")
plt.ylabel("loss")

plt.legend()
plt.show()
