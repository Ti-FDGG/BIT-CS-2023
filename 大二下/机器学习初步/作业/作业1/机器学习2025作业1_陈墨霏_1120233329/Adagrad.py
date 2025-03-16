import matplotlib.pyplot as plt
from matplotlib.pyplot import cm
import numpy as np
import pandas as pd

df = pd.read_csv('lr_itr_Adagrad.csv')

#输入数据和标签
x_data = [338.,333.,328.,207.,226.,25.,179.,60.,208.,606.]
y_data = [640.,633.,619.,393.,428.,27.,193.,66.,226.,1591]

# 绘制损失函数曲线的图形
fig, ax = plt.subplots()
fig.set_size_inches(12, 8)
ax.set_title("Loss using Adagrad")

# 损失函数（采用MSE计算）
loss = lambda b, w: sum((y - (b + w * x)) ** 2 for x, y in zip(x_data, y_data)) / len(x_data)

for index, row in df.iterrows():
    #初始值和超参数设置
    b = -150
    w = 0

    lr = row['lr']
    iteration = int(row['iteration'])
    print(lr, iteration)
    print('index:', index)

    #w_list和b_list用于记录每一轮迭代的w和b值，用于绘图
    w_list = [0] * iteration
    b_list = [0] * iteration
    lr_b = 0
    lr_w = 0
    for i in range(iteration):
        b_grad = 0
        w_grad = 0
        #实现Adagrad算法
        for n in range(len(x_data)):
            # 求梯度
            b_grad = b_grad - 2.0*(y_data[n] - b - w*x_data[n])*1.0
            w_grad = w_grad - 2.0*(y_data[n] - b - w*x_data[n])*x_data[n]
        lr_b = lr_b + b_grad ** 2
        lr_w = lr_w + w_grad ** 2
        # 更新b和w
        b = b - lr * b_grad / np.sqrt(lr_b + 1e-8)
        w = w - lr * w_grad / np.sqrt(lr_w + 1e-8)

        w_list[i] = w
        b_list[i] = b

    # 绘图部分
    # w-b图
    plt.figure()
    plt.title("Linear Regression using Adagrad")
    #绘制每一个数据点
    plt.scatter(b_list, w_list, color="black", s=1)  # s is the size of the points
    plt.xlabel("b")
    plt.ylabel("w")
    plt.xlim(-200, -80)
    plt.ylim(-4, 5)
    plt.legend()
    plt.savefig('./figures/Adagrad/Adagrad_lr'+str(lr)+'_itr'+str(iteration)+'.png')

    # 绘制损失函数曲线
    ax.plot(range(iteration), [loss(b, w) for b, w in zip(b_list, w_list)], label=f'lr={lr}, iter={iteration}')

ax.set_xlabel("iteration")
ax.set_xlim(-5, 100)
ax.set_ylabel("loss")
ax.legend()
fig.savefig('./figures/Adagrad/loss_Adagrad.png')
# fig.show()