# 卷积神经网络
import torch
import torch.nn as nn
import pickle
import random
import time

# pytorch 官方文档：https://pytorch.org/docs/stable/index.html
# numpy 官方文档：https://numpy.org/doc/stable/

def load_data():
    # 加载mnist.pkl中数据，从中取：训练集6000条数据，测试集1000条数据，每一条数据是28*28的图片，标签是对应类别
    x_train, y_train, x_test, y_test = pickle.load(open("mnist.pkl", 'rb'))
    x_train, y_train = x_train[:6000], y_train[:6000]
    x_test, y_test = x_test[:1000], y_test[:1000]
    # 将数据转为张量形式
    x_train = torch.FloatTensor(x_train)
    y_train = torch.LongTensor(y_train)
    x_test = torch.FloatTensor(x_test)
    y_test = torch.LongTensor(y_test)
    # 数据第二维上增加一个维度，张量形状从(6000,28,28)变为(6000,1,28,28)
    x_train = x_train.unsqueeze(1)
    x_test = x_test.unsqueeze(1)
    return x_train, y_train, x_test, y_test


class Cnn_net(nn.Module):
    def __init__(self):
        super().__init__()
        # 初始化模型层
        # 采用nn.Sequential函数构建第一层，第一层内包含一个卷积层，ReLu激活函数和一个最大池化层。
        # 卷积层的输入通道为1，输出通道为32，卷积核大小为5，步长为（1，1），填充值为2；最大池化层和大小为2，步长为2
        self.layer1 = nn.Sequential(
            nn.Conv2d(1, 32, kernel_size=5, stride=1, padding=2),  # 卷积层
            nn.ReLU(),  # 激活函数
            nn.MaxPool2d(kernel_size=2, stride=2)  # 最大池化层
        )
        # 第二层内也包含一个卷积层，ReLu激活函数和一个最大池化层
        # 卷积层的输入通道为32，输出通道为64，卷积核大小为5，步长为（1，1），填充值为2；最大池化层和大小为2，步长为2
        self.layer2 = nn.Sequential(
            nn.Conv2d(32, 64, kernel_size=5, stride=1, padding=2),  # 卷积层
            nn.ReLU(),  # 激活函数
            nn.MaxPool2d(kernel_size=2, stride=2)  # 最大池化层
        )
        # 设置正则化层
        self.dropout = nn.Dropout()
        # 设置线性层参数（第一层），输入通道为7*7*64，输出通道为1000；
        self.fc1 = nn.Linear(7*7*64, 1000)
        
        # 设置线性层参数（第二层），输入通道为1000，输出通道为10
        self.fc2 = nn.Linear(1000, 10)

    def forward(self, x):
        #构建前向传播函数，填空部分
        # 特征提取模块
        out = self.layer1(x)
        out = self.layer2(out)

        # 经过正则化层
        out = self.dropout(out)

        # 将数据展平，out.size(0)表示batch_size，-1表示自动计算
        out = out.view(out.size(0), -1)

        out = self.fc1(out)
        out = self.fc2(out)

        # 返回结果
        x = out

        return x


def train(x_train, y_train, x_test, y_test, BATCH_SIZE, model, loss_function, optimizer):
    train_N = x_train.shape[0]
    # 训练150轮后train loss基本不变，通常建议训练70轮以上即可
    for epoch in range(70):
        batchindex = list(range(int(train_N / BATCH_SIZE)))# 按照批次大小，划分训练集合，得到对应批数据索引
        random.shuffle(batchindex)# 打乱索引值，便于训练
        for i in batchindex:
            # 选取对应批次数据的输入和标签
            batch_x = x_train[i*BATCH_SIZE: (i+1)*BATCH_SIZE]
            batch_y = y_train[i*BATCH_SIZE: (i+1)*BATCH_SIZE]
            # 模型预测
            y_hat = model(batch_x)
            loss = loss_function(y_hat, batch_y)
            optimizer.zero_grad()#梯度清零
            loss.backward()#计算梯度
            optimizer.step()#更新参数

        # test
        y_hat = model(x_test)
        y_hat = torch.max(y_hat, 1)[1].data.squeeze()
        score = torch.sum(y_hat == y_test).float() / y_test.shape[0]
        print(f"epoch:{epoch},train loss: {loss:.4f}, test accuracy: {score:.4f}, time:{time.strftime('%Y-%m-%d %H:%M:%S', time.localtime()) }")
    print("陈墨霏's procedure has been completed")#将XXX改为你的中文名字，最终提交运行截图


def cnn():
    # 加载数据
    x_train, y_train, x_test, y_test = load_data()
    # 设置训练模型使用批数据的大小
    BATCH_SIZE = 100
    # 加载模型
    model = Cnn_net()
    # 设置损失函数
    loss_function = nn.CrossEntropyLoss()
    # 设置优化器
    optimizer = torch.optim.Adam(model.parameters(), lr=0.001)
    # 训练并测试模型
    train(x_train, y_train, x_test, y_test,
          BATCH_SIZE, model, loss_function, optimizer)


cnn()
