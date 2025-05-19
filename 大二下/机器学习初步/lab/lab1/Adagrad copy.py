import numpy as np
import matplotlib.pyplot as plt
from matplotlib.pyplot import cm
import pandas as pd

df = pd.read_csv('lr_itr_Adagrad.csv')

#导入的numpy包在实现Adagrad算法时可能会被使用到
#输入数据和标签
x_data = [338.,333.,328.,207.,226.,25.,179.,60.,208.,606.]
y_data = [640.,633.,619.,393.,428.,27.,193.,66.,226.,1591]

#初始值和超参数设置
b = -150
w = 0
lr = 1
iteration = 10000
lr_b = 0
lr_w = 0

#w_list和b_list用于记录每一轮迭代的w和b值，用于绘图
w_list = [0] * iteration
b_list = [0] * iteration
    
for i in range(iteration):
    b_grad = 0
    w_grad = 0
    #填空部分，实现Adagrad算法
    for n in range(len(x_data)):
        b_grad = b_grad - 2.0*(y_data[n] - b - w*x_data[n])*1.0
        w_grad = w_grad - 2.0*(y_data[n] - b - w*x_data[n])*x_data[n]
    lr_b = lr_b + b_grad ** 2
    lr_w = lr_w + w_grad ** 2
    # 更新b和w
    b = b - lr*b_grad/np.sqrt(lr_b)
    w = w - lr*w_grad/np.sqrt(lr_w)
    # b = b - lr*b_grad
    # w = w - lr*w_grad

    w_list[i] = w
    b_list[i] = b

#绘图部分
# w-b图

#设置背景
plt.figure()
plt.title("Linear Regression using Adagrad")

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
plt.title("Loss using Adagrad")
plt.plot(range(iteration), [loss(b, w) for b, w in zip(b_list, w_list)], color="black")
plt.xlabel("iteration")
plt.ylabel("loss")

plt.legend()
plt.show()
