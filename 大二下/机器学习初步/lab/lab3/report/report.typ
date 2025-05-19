#import "template-mllab3.typ": cover, report-body, appendix
#import "@preview/numbly:0.1.0": numbly

#show: doc => {
  cover(
    subject: "机器学习初步实验报告",
    title: "作业3：Q-learning和DQN",
    college: "计算机学院",
    major: "计算机科学与技术",
    class: "07112304",
    author: "陈墨霏",
    student-id: "1120233329",
  )
  report-body(
    class: "07112304",
    student-id: "1120233329",
    author: "陈墨霏",
    header: "机器学习初步实验报告",
    title: "作业3：Q-learning和DQN",
    doc
  )
}

#show heading.where(level: 2): it => align(center)[
  #it
] 
#set heading(numbering: numbly(
  "",
  "第{2:一}部分",
  "任务{3}：",
  "",
  "",
))

#let taskdescrption(term) = {
  align()[
    #block(
      text(
        font: "Kaiti",
        size: 12pt,
        [#term]
      ),
      outset: (x: 1pt, y: 3pt)
    )
  ]
}



== Q-Learning算法的实现和测试


=== Q-Learning算法补全与描述

#taskdescrption("补全RL.py中Q-learning方法的代码，提交代码实现截图，并解释实现思路。")

==== 实现思路

首先对算法所用到的函数和变量进行初始化。这包括：对Q函数的初始化；初始化动作数量`nActions`、奖励列表`rewardList`、温度参数`temperature`。特别要说明的是，这里为温度参数设置最小值为1，是为了防止温度过小导致在Boltzmann exploration的计算时溢出。

之后，进入训练阶段。训练阶段是一个次数为`nEpisodes`的大循环，取回合数`episode`作为计数变量，表明每一次循环是一个回合（episode），可以形象理解为从一局游戏开始到游戏结束。每一次循环，先初始化当前状态为`s0`，之后进入一个小循环，取步数`step`作为计数变量，表明每一次循环是一个步（step），可以形象理解为在当前局游戏中进行了一次操作。

对于每一个`step`，首先结合结合ε-贪婪和Boltzmann exploration选择动作（action）。

ε-贪婪策略：  
在每一步中，以概率ε选择一个随机动作（探索），以概率1-ε选择当前Q值最大的动作（利用）。这种策略在训练初期可以更多地探索环境，而在训练后期逐渐趋向于利用已有的知识，从而在探索与利用之间取得平衡。

Boltzmann exploration：
在每一步中，根据当前状态的Q值计算每个动作的概率分布，然后根据这个分布选择一个动作。由于分布的形式与物理上的Boltzmann分布类似，因此称为Boltzmann exploration，相应的参数也就被称为`temperature`，起到的效果也与温度在Boltzmann分布中起到的效果类似（`temperature`越高，分布越接近均匀分布；`temperature`越低，动作选择更集中于高 Q 值的动作）。这个方法可以在一定程度上避免陷入局部最优解。分布如下：
$
P(a|s) = frac(exp(Q(s, a) / "temperature"), sum_a' exp(Q(s, a') / "temperature"))
$
#h(2em) // 每一个公式之后的第一段被认为是“首段”，默认不缩进。因此手动缩进两个字符
选择完毕动作后，执行动作并观察环境的反馈（奖励`reward`和下一个状态`nextState`）。根据奖励更新累计奖励列表`rewardList`。根据反馈更新Q值。Q值的更新公式如下：
$
Q(s, a) = Q(s, a) + alpha * ("reward" + gamma * max_a' Q(s', a') - Q(s, a))
$

其中，`alpha`是学习率，取$alpha = 1 / ("episode" + 1)$。`gamma`是折扣因子。一个小循环的最后，更新当前状态`currentState`为下一个状态`nextState`。进行完所有的步数后，进入下一个回合。

进行完所有的回合后，训练结束。计算最终策略`policy`。
$
"policy" = limits("argmax")_a Q(s, a)
$


下面是全部的`qLearning`函数的代码实现截图。

#figure(
  image("assets/p1_t1_qlearningcode.png", width: 80%),
  caption: "Q-learning算法补全",
)

=== 利用TestRL.py对Q-learning算法进行简单测试

#taskdescrption("运行TestRL.py并提交运行截图。")
==== 运行结果

运行结果如下。

#figure(
  image("assets/p1_t2_testrl.png", width: 80%),
  caption: "TestRL.py运行结果",
)

=== 利用TestMaze.py对Q-learning算法进行较为复杂的测试

#taskdescrption("运行TestMaze.py并提交运行截图，并分析生成的Q-learning.jpg的含义和曲线的走势。")

==== 运行结果和分析

运行结果如下。由于终端输出很长，这里只展示一部分。

#figure(
  image("assets/p1_t3_testmaze.png", width: 80%),
  caption: "TestMaze.py运行结果",
)
#h(2em)
下面是输出的`Q-learning.jpg`的图像。

#figure(
  image("assets/Q-learning.jpg", width: 80%),
  caption: "TestMaze.py运行结果",
)
#h(2em)

理解`TestMaze.py`的代码逻辑后，可对该图做以下分析。

该图是一个折线图，展现的是随着`epsilon`值的变化，奖励`reward`的变化。横坐标是回合数`episode`，纵坐标是多次（100次）采样取平均得到的样本平均奖励列表`rewardList_avg`。

值得注意的是，在MDP的奖励模型的构建当中，也有一个平均奖励（average_reward）的概念，往往指的是针对不同状态的间也即步数来求平均。不过这里显然指的并不是这个概念。

从图中可以看出，四条曲线虽然均不断波动，但均呈现出先增加后趋于稳定的趋势。这表明随着`episode`的增加，算法逐渐收敛到一个较优的策略，且策略的表现逐渐稳定。

此外，不同`epsilon`曲线的收敛速度和最终的稳定值也有所不同。其中，`epsilon`为0.05的和为0.1的基本曲线重合，而之后随着`epsilon`增大到0.3、0.5，曲线高度则逐步降低，奖励值减少。这表明合理的超参数设置可以改善最终策略的性能。

#pagebreak()

== 利用Q-learning算法完成迷宫导航任务

=== Q-Learning算法补全与描述

#taskdescrption("本部分提供q-learning.py，迷宫导航任务环境的相关设置已经在代码中给出，同学们需要补全Q-learning算法，提交补全代码部分截图。
注意该任务是随机初始化四个障碍和目的地，如果出现路径不存在的情况，请重新运行。")

==== 实现思路

这里的Q-learning算法与上面所描述的Q-learning算法基本一致，下面仅介绍作业要求补全的部分的实现思路。

动作选取部分，利用已经实现好的`select_action`函数进行动作选择。

```py
global current_pos, epsilon_delta, epsilon
#此处需要进行动作选取,给action赋值
action = select_action(current_pos)
```

Q-learning算法实现部分，对于未到达最终状态的情况，采用贝尔曼方程更新Q函数。对于到达最终状态的情况，直接用当前奖励更新Q函数。

```py
if new_pos not in terminals:
    #此处实现Q-learning算法
    # 未到达最终状态，则采用贝尔曼方程更新Q函数
    Q[current_pos][action] += alpha * (
        current_reward + gamma * max(Q[new_pos]) - Q[current_pos][action]
    )
else:
    # 此处实现Q-learning算法
    # 到达最终状态，则直接用当前奖励更新Q函数
    Q[current_pos][action] += alpha * (current_reward - Q[current_pos][action])
```

下面是全部的`step`函数的代码实现截图。

#figure(
  image("assets/p2_t1_qlearningcode.png", width: 80%),
  caption: "Q-learning算法补全",
)

=== 运行并完成迷宫导航任务

#taskdescrption("提交运行结果截图，并进行算法分析。")

下面是`q_learning.py`运行窗口初始状态和最终状态的截图。

#figure(
  image("assets/p2_t2_qlearningtest-1.png", width: 80%),
  caption: "迷宫导航任务初始状态",
)

#figure(
  image("assets/p2_t2_qlearningtest-2.png", width: 80%),
  caption: "迷宫导航任务中间状态",
)

#figure(
  image("assets/p2_t2_qlearningtest-3.png", width: 80%),
  caption: "迷宫导航任务最终状态",
)
#h(2em)

可以看到，初始状态下，Agent对于迷宫中的障碍物和目的地并没有认知，右侧V值的可视化界面为全黑。随着Agent的不断探索，Agent逐渐对迷宫中的障碍物和目的地有了认知，右侧V值的可视化界面逐渐变为的障碍物部分的红色色调逐渐变亮，目的地部分的绿色色调逐渐变亮。最终，Agent成功找到了从起点到终点的路径，相应的障碍物和目的地也被用正确的颜色标出。

#pagebreak()

== 利用DQN模型玩Flappy bird游戏以及DQN算法的改进

#taskdescrption("注：为了方便起见，本部分除了作业要求外，对工程代码做了一些其他修改。所有的修改附近我都添加了以“修改：”开头的注释。其中的部分修改，我也会在下面提到。")

=== 测试训练DQN模型并进行演示

#taskdescrption("理解所给Project的逻辑，训练模型，利用模型玩Flappy bird游戏，并提交演示视频。")

==== Pytorch版本

训练模型，运行以下命令：
```bash
python dqn.py train
```
每隔25000个`iteration`就会保存一次模型。代码中预先设定的最高训练次数`number_of_iterations`为2000000。

训练完成后，准备进行演示。为了方便选择模型，我在代码中添加了通过`iteration`选择模型的逻辑。运行以下命令以进行模型测试，其中`<iteration>`为你想要选择的模型的`iteration`。默认值为2000000。：
```bash
python dqn.py test <iteration>
```

运行测试，根据测试结果，统计得到不同迭代次数下小鸟能顺利通过管道的次数：

#align(
  center,
)[
  #block(
    width: 80%,
    grid(
      columns: (1fr, 1fr, 1fr),
      rows: 1,
      gutter: 10pt,
      figure(
        image("assets/p3_t1_torch30w.jpg"),
        caption: "30万 1-2次",
        numbering: none
      ),
      figure(
        image("assets/p3_t1_torch80w.jpg"),
        caption: "80万 12-18次",
        numbering: none
      ),
      figure(
        image("assets/p3_t1_torch200w.jpg"),
        caption: "200万 几乎死不掉",
        numbering: none
      )
    )
  )
]

#h(2em)
可以看到，随着模型训练迭代次数的增加，模型的表现也越来越好。

==== Tensorflow版本

对于TensorFlow版本的工程，特别要提到的一点是，原本的代码中虽然能够在训练（同时也在展示）过程中“加载”模型文件，但是从实现效果可以看出，这一“加载”操作无论对于在中途中断训练后从断点开始训练，还是对于展示出的加载断点处保存的模型，均没有起到作用。

我在训练时租用了AutoDL云服务器，但是在云服务器上难以显示游戏的图形窗口，因此需要在服务器端训练，下载模型文件到本地之后，接着运行相关文件继续训练并演示。而这个问题的存在影响到了我的这一算力使用策略。

通过分析代码实现逻辑，发现原因在于原来的工程中只对于网络参数进行了保存，而并没有保存timeStep、epsilon、replayMemory等非网络参数，但是对于训练与演示过程也有影响的（比如epsilon的值直接影响着动作选择的策略）参数。因此，我在原有的代码基础上，添加了对这些参数的保存和加载逻辑。

训练并演示模型，运行以下命令：
```bash
python FlappyBirdDQN.py
```

根据演示结果，统计得到不同迭代次数下小鸟能顺利通过管道的次数：

#align(
  center,
)[
  #block(
    width: 80%,
    grid(
      columns: (1fr, 1fr, 1fr),
      rows: 1,
      gutter: 10pt,
      figure(
        image("assets/p3_t1_tf25w.jpg"),
        caption: "25万 1-2次",
        numbering: none
      ),
      figure(
        image("assets/p3_t1_tf70w.jpg"),
        caption: "70万 6-10次",
        numbering: none
      ),
      figure(
        image("assets/p3_t1_tf160w.jpg"),
        caption: "160万 几乎死不掉",
        numbering: none
      )
    )
  )
]

#h(2em)
可以看到，随着模型训练迭代次数的增加，模型的表现也越来越好。

=== 为DQN模型添加Target Network机制

#taskdescrption("为DQN模型添加Target Network机制，提交Target Network实现的代码截图，并描述其实现思路。")

==== Pytorch版本

下面是`dqn_addTargetNetwork.py`中添加Target Network机制的代码实现截图。

#figure(
  image("assets/p3_t2_TNNcode_torch-1.png", width: 80%),
  caption: "Target Network初始化",
)
#h(2em)
初始化部分，创建一个NeuralNetwork实例，将其赋值给`target_model`变量，并相应的使用GPU加速。之后，将当前模型的参数复制到`target_model`中。

使用`eval()`方法将`target_model`设置为评估模式，目的是为了让它在推理时稳定输出，不引入训练态的随机性和参数漂移。

#figure(
  image("assets/p3_t2_TNNcode_torch-2.png", width: 80%),
  caption: "Target Network计算Q值",
)
#h(2em)
使用Target Network的输出来计算Q值，而不是直接使用主网络的输出。

#figure(
  image("assets/p3_t2_TNNcode_torch-3.png", width: 80%),
  caption: "Target Network更新",
)
#h(2em)
每隔10000次迭代，将当前模型的参数复制到`target_model`中，对其进行更新。

==== Tensorflow版本

下面是`BrainDQN_NIPS_addTargetNetwork.py`中添加Target Network机制的代码实现截图。

#figure(
  image("assets/p3_t2_TNNcode_tf-1.png", width: 80%),
  caption: "类的初始化部分的更改",
)
#h(2em)
这一部分添加了`TARGET_UPDATE_FREQUENCY`变量，表示Target Network更新的频率，每隔1000个`timeStep`更新一次。另外，还在`__init__`函数中添加了对Target Network的初始化逻辑。

#figure(
  image("assets/p3_t2_TNNcode_tf-2.png", width: 80%),
  caption: "Target Network初始化",
)
#h(2em)
初始化逻辑的实现。与Pytorch版本过程类似，先通过`identity()`函数创建一个与当前模型相同的模型，然后将其赋值给`target_model`变量。之后，利用`assign()`函数将当前模型的参数复制到`target_model`中。

其中还添加了一个列表`updateTargetNetwork`，用于存储更新 Target Network 权重的操作。这些操作会在训练过程中定期执行，将主网络的权重复制到 Target Network。

#figure(
  image("assets/p3_t2_TNNcode_tf-3.png", width: 80%),
  caption: "Target Network计算Q值",
)
#h(2em)

这一部分添加了`target_model`的计算逻辑。与Pytorch版本过程类似，使用Target Network的输出来计算Q值，而不是直接使用主网络的输出。

#figure(
  image("assets/p3_t2_TNNcode_tf-4.png", width: 80%),
  caption: "Target Network更新",
)
#h(2em)

当`timeStep`整除`TARGET_UPDATE_FREQUENCY`时，通过`session.run()`函数执行`updateTargetNetwork`列表中的操作，将当前模型的参数复制到`target_model`中。


=== 训练改进后的DQN模型、演示以及与改进前的对比分析

#taskdescrption("重新训练模型，利用模型玩Flappy bird游戏，提交演示视频；绘制训练过程中奖励的变化图(包括两条曲线：原始DQN和加了Target Network的DQN)，并进行算法分析（Target Network对模型的影响）。")

==== Pytorch版本

重新训练模型，利用得到的模型进行演示。
```bash
python dqn_addTargetNetwork.py train
python dqn_addTargetNetwork.py test <iteration> # 默认值为2000000
```

得到不同迭代次数下小鸟能顺利通过管道的次数：

#align(
  center,
)[
  #block(
    width: 70%,
    grid(
      columns: (1fr, 1fr, 1fr),
      rows: 1,
      gutter: 10pt,
      figure(
        image("assets/p3_t1_torch30w.jpg"),
        caption: "30万 1-2次",
        numbering: none
      ),
      figure(
        image("assets/p3_t1_torch80w.jpg"),
        caption: "80万 16-20次",
        numbering: none
      ),
      figure(
        image("assets/p3_t1_torch200w.jpg"),
        caption: "200万 几乎死不掉",
        numbering: none
      )
    )
  )
]

#h(2em)
可以看出，在训练中期，模型的表现有了明显的提升。由于Pytorch版本的工程中没有给出随着episode变化而变化的reward的记录，因此这里就不展示reward的变化图了。

==== Tensorflow版本

重新训练模型，利用得到的模型进行演示。为了切换运行有Target Network的模型和没有Target Network的模型，我在代码中添加了一个参数，运行以下命令：

```bash
python FlappyBirdDQN.py <useTargetNetwork>
```
#h(2em)
其中`<useTargetNetwork>`为`False`（默认值）或`True`，分别表示不使用Target Network和使用Target Network。

得到不同迭代次数下小鸟能顺利通过管道的次数：

#align(
  center,
)[
  #block(
    width: 70%,
    grid(
      columns: (1fr, 1fr, 1fr),
      rows: 1,
      gutter: 10pt,
      figure(
        image("assets/p3_t3_tf20w.jpg"),
        caption: "20万 1-5次",
        numbering: none
      ),
      figure(
        image("assets/p3_t3_tf55w.jpg"),
        caption: "55万 6-10次",
        numbering: none
      ),
      figure(
        image("assets/p3_t3_tf135w.jpg"),
        caption: "135万 几乎死不掉",
        numbering: none
      )
    )
  )
]

#h(2em)
可以看出，达到与不加Target Network的模型相同效果所需要的迭代次数明显减少。

TensorFlow版本的工程提供了对于reward的记录，因此可以绘制出reward随episode的变化图。

#align(
  center,
)[
  #block(
    width: 80%,
    grid(
      columns: (1fr, 1fr),
      rows: 1,
      figure(
        image("assets/p3_t3_withoutTNN_r-e_graph_tf.png"),
        caption: "reward随episode的变化图",
        numbering: none
      ),
      figure(
        image("assets/p3_t3_withTNN_r-e_graph_tf.png"),
        caption: "reward随episode的变化图（添加Target Network）",
        numbering: none
      )
    )
  )
]

#h(2em)
注意两张图的reward的纵坐标尺度，右图的纵坐标尺度是左图的10倍。

为了更清晰的对比，可以将两张图合并到一起：

#figure(
  image("assets/p3_t3_vs_r-e_graph_tf.png"),
  caption: "reward随episode的变化图",
)
#h(2em)

可以看到，添加Target Network后，模型的收敛速度明显加快，最终的表现也更加稳定。

==== Target Network对模型的影响

从reward随episode的变化图可以看出，添加Target Network后，模型的收敛速度明显加快，最终的表现也更加稳定。这是因为Target Network的引入有效缓解了训练过程中Q值更新的不稳定性。

在原始DQN中，Q值的更新依赖于当前网络的预测值，而当前网络的参数在训练过程中不断变化，这可能导致目标值（target）和预测值之间的关系不稳定，从而影响训练效果。而Target Network通过引入一个独立的网络来计算目标值，并定期将当前网络的参数复制到Target Network中，避免了目标值和预测值之间的直接耦合，从而提高了训练的稳定性和收敛速度。

因此，Target Network是DQN算法中一个重要的改进机制，能够显著提升模型的训练效果和稳定性。


#appendix("提交文件清单及说明")

==== 报告文档

- `机器学习2025作业3_陈墨霏_1120233329.docx`: 作业报告Word文档

==== 第一部分

- `RL.py`: Q-learning算法的实现代码
- `TestRL.py`: Q-learning算法的简单测试代码
- `TestMaze.py`: Q-learning算法的复杂测试代码

==== 第二部分

- `q_learning.py`: 迷宫导航任务的代码

==== 第三部分

- `Flappybird-pytorch/`: Pytorch版本的DQN玩Flappy bird工程代码。由于模型文件过大，因此仅包含最后一个模型文件。
- `Flappybird-tensorflow/`: Tensorflow版本的DQN玩Flappy bird工程代码。此外，其中还包含了`draw.ipynb`，用于绘制reward随episode的变化图。由于模型文件过大，因此仅包含最后一个模型文件（其中还包含了我为了使得工程能够正常的从训练与演示断点正常加载模型而包含的其他相关训练时产生的模型文件）。
- `演示视频/`: 演示视频文件夹，包含了Pytorch版本和Tensorflow版本的的最好训练模型的演示视频。
 - `tf_tnn_1350000.mp4`: TensorFlow版本，添加Target Network，timeStep=1350000。
 - `tf_without_tnn_1600000.mp4`: TensorFlow版本，不添加Target Network，timeStep=1600000。
 - `torch_tnn_2000000.mp4`: Pytorch版本，添加Target Network，iteration=2000000。
 - `torch_without_tnn_2000000.mp4`: Pytorch版本，不添加Target Network，iteration=2000000。