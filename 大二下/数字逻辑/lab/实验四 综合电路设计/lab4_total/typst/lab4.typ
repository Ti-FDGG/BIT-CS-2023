#import "template-dllab.typ": report-body
#import "@preview/fletcher:0.5.7": diagram, node, edge

#import table: cell, header

#show: doc => report-body(
  title: "实验四 综合电路设计实验报告",
  class_s1: "07112303",
  student-id_s1: "1120231863", 
  name_s1: "左逸龙",
  phone-number_s1: "18680517248",
  class_s2: "07112304",
  student-id_s2: "1120233329", 
  name_s2: "陈墨霏",
  phone-number_s2: "13126146305",
  doc
)

#let ziti(body) = text(font: (
  (name: "Times New Roman", covers: "latin-in-cjk"), // 西文字体
  "Noto Serif CJK SC" // 中文字体
), lang: "zh")[#body]

#show figure: set block(breakable: true)

#let module_impl(
  name: "",
  function: "",
  code: []
) = {
  figure(
    table(
      columns: (1fr),
      cell(
        align: left,
        fill: rgb("#bfbfbf"),
        [
          #block(inset: 5pt)[
            #ziti()[*#name*]
          ]
        ]
      ),
      cell(
        align: left,
        fill: rgb("#bfbfbf"),
        [
          #block(inset: 5pt)[
            #ziti()[#function]
          ]
        ]
      ),
      cell(
        breakable: true,
        align: left,
        [
          // #block(inset: (left: 5pt, top: 5pt))[
          //   #text(font: (
          //   (name: "Consolas", covers: "latin-in-cjk"), // 西文字体
          //   "Noto Serif CJK SC" // 中文字体
          // ))[#code]
          // ]
          #block(inset: (left: 5pt))[
            #raw(code, lang: "verilog", block: true)
          ]
        ]
      ),
    )
  )
}


== 实验题目

==== 短跑计时器设计与实现（难度系数：0.9） 

- 短跑计时器描述如下： 
    - 短跑计时器显示分、秒、毫秒； 
    - "毫秒"用两位数码管显示：百位、十位； 
    - "秒"用两位数码管显示：十位、个位； 
    - "分"用一位LED灯显示，LED灯"亮"为1分； 
    - 最大计时为1分59秒99，超限值时应可视或可闻报警； 
    - 三个按键开关：计时开始/继续（A）、计时停止/暂停（B）、复位/清零（C）

    键控流程如下：

#figure(
	diagram(
		node-stroke: .1em,
		
		spacing: 1em,
		node((0,0), [#ziti[计时开始/继续（A）]], name: <A>, shape: rect, stroke: none),
    node((0,1), [#ziti[计时停止/暂停（B）]], name: <B>, shape: rect, stroke: none),
    node((0,2), [#ziti[计时复位/清零（C）]], name: <C>, shape: rect, stroke: none),

    edge(<A>, <B>, "-|>", shift: -20pt),
    edge(<B>, <A>, "-|>", shift: -15pt),
    edge(<B>, <C>, "-|>"),
    edge(<C>, "r,u,u,l", "-|>"), // 注意表示折线箭头指示方向的字符串中，逗号与字母间没有空格
	),
	caption: "短跑计时器的键控流程",
)

== 电路设计 

=== 输入输出设计

短跑计时器电路包含5个输入信号和4个输出信号。输入信号包括系统时钟(`sys_clk_pin`)、系统复位(`reset_n_pin`)以及三个按键控制信号(`key_a_pin`、`key_b_pin`、`key_c_pin`)。其中系统时钟提供100MHz的基准时钟，系统复位为低电平有效，用于将系统恢复到初始状态。三个按键分别用于控制计时的开始/继续、停止/暂停以及复位/清零操作。

输出信号包括七段数码管段选信号(`seg_pins`)、位选信号(`an_pins`)以及两个LED指示灯(`led_minute_pin`、`led_alarm_pin`)。七段数码管用于显示时间，其中段选信号控制各段点亮，位选信号控制显示位置。分钟LED用于指示是否达到1分钟，报警LED用于指示计时是否超过最大计时值(1分59秒99)。


#figure(
	table(
    stroke: (x, y) => if x == 0 {
        (
        left: (
          none
        ), 
        rest: (
          thickness: 1pt,
        ), 
        )
      } else if x == 3 {
        (right: (
          none
        ),
        rest: (
          thickness: 1pt
        )
        )
      } else {
        (thickness: 1pt,)
      },
    row-gutter: (0pt, 0pt, 0pt, 0pt, 0pt, 3pt, 0pt),
		columns: (1fr, 1fr, 1fr, 1fr),
    table.hline(stroke: 1.3pt),
		[输入符号], [名称], [值为1的含义], [值为0的含义],
		[sys_clk_pin], [系统时钟], [时钟上升沿], [时钟下降沿],
		[reset_n_pin], [系统复位], [正常工作], [系统复位],
		[key_a_pin], [按键A], [计时开始/继续], [无操作],
		[key_b_pin], [按键B], [计时停止/暂停], [无操作],
		[key_c_pin], [按键C], [计时复位/清零], [无操作],
    [输出符号], [名称], [值为1的含义], [值为0的含义],
		[seg_pins], [七段数码管段选], [段点亮], [段点灭],
		[an_pins], [数码管位选], [位选有效], [位选无效],
		[led_minute_pin], [分钟LED], [分钟指示], [无指示],
		[led_alarm_pin], [报警LED], [报警指示], [无报警],
    table.hline(stroke: 1.3pt),
	),
	caption: "短跑计时器的输入和输出变量",
)

=== 模块设计

==== 按键消抖模块button_interface_debounce

按键消抖模块是短跑计时器的基础输入处理模块，其主要功能是对原始按键输入进行消抖处理并产生单周期脉冲信号。该模块采用三级同步器结构，通过`key_state_q0`、`key_state_q1`和`key_state_q2`三个寄存器实现按键状态的稳定采样和边沿检测。模块内部包含一个20位的消抖计数器，在100MHz系统时钟下可实现10ms的消抖时间。当检测到按键状态发生变化时，计数器会立即复位；当按键状态保持稳定且计数器达到预设值时，才会更新稳定的按键状态。最后通过比较相邻两个稳定状态，在检测到上升沿时产生一个时钟周期的有效脉冲信号，确保每次按键操作只触发一次响应。

==== 状态控制模块state_controller

状态控制模块是整个计时器的核心控制单元，采用Moore型状态机实现，包含`IDLE`（空闲）、`RUNNING`（运行）、`PAUSED`（暂停）和`ALARM`（报警）四个状态。该模块根据按键输入和最大计时时间到达信号，控制计时器的运行、暂停、复位以及报警状态。

在`IDLE`状态下，系统等待开始信号；`RUNNING`状态下，计时器正常运行，可响应暂停或复位命令；`PAUSED`状态下，计时器暂停，等待继续或复位命令；`ALARM`状态下，系统发出报警信号，等待复位命令。模块输出包括计时器运行使能、复位命令和报警激活信号，这些信号直接控制其他模块的工作状态。


状态控制模块采用Moore型状态机实现，其输出仅取决于当前状态。在`IDLE`状态下，`timer_run_en_out`为0表示计时器停止运行，`timer_reset_cmd_out`为1表示持续请求复位计时器，`alarm_active_out`为0表示无报警。在`RUNNING`状态下，`timer_run_en_out`为1表示计时器正常运行，`timer_reset_cmd_out`为0表示不复位，`alarm_active_out`为0表示无报警。在`PAUSED`状态下，`timer_run_en_out`为0表示计时器暂停，`timer_reset_cmd_out`为0表示不复位，`alarm_active_out`为0表示无报警。在`ALARM`状态下，`timer_run_en_out`为0表示计时器停止运行，`timer_reset_cmd_out`为0表示不复位，`alarm_active_out`为1表示激活报警。

根据上述对于状态的说明和状态转移的描述，可以得到以下的短跑计时器的状态转移表。

#figure(
  table(
    stroke: none,
    columns: (1fr, 1fr, 1fr, 1fr, 1fr),
    table.hline(stroke: 1.3pt),
    [当前状态], table.vline(), [当前状态状态码], table.vline(), [转换条件], table.vline(), [下一状态], table.vline(), [下一状态状态码],
    table.hline(),
    [IDLE], [00], [key_a_pulse], [RUNNING], [01],
    [], [], [其他情况], [IDLE], [00],
    table.hline(),
    [RUNNING], [01], [key_b_pulse], [PAUSED], [10],
    [], [], [key_c_pulse], [IDLE], [00],
    [], [], [max_time_reached\_ in], [ALARM], [11],
    [], [], [其他情况且未超时], [RUNNING], [01],
    table.hline(),
    [PAUSED], [10], [key_a_pulse], [RUNNING], [01],
    [], [], [key_c_pulse], [IDLE], [00],
    [], [], [其他情况], [PAUSED], [10],
    table.hline(),
    [ALARM], [11], [key_c_pulse], [IDLE], [00],
    [], [], [其他情况], [ALARM], [11],
    table.hline(stroke: 1.3pt),
  ),
  caption: "短跑计时器的状态转移表"
)

#text()[#h(0.0em)] // 用来使得块级元素后分段

根据状态转移表，可以绘制出以下的短跑计时器的状态机图。

#figure(
	diagram(
		node-stroke: .1em,
		spacing: 6em,
		node((0,0), [IDLE], radius: 2.8em, name: <IDLE>),
		node((1,0), [RUNNING], radius: 2.8em, name: <RUNNING>),
		node((2,0), [PAUSED], radius: 2.8em, name: <PAUSED>),
		node((3,0), [ALARM], radius: 2.8em, name: <ALARM>),
		
		edge(<IDLE>, <RUNNING>, [key_a_pulse], "-|>", shift: 13pt),
		edge(<RUNNING>, <PAUSED>, [key_b_pulse], "-|>", shift: 13pt),
		edge(<RUNNING>, <IDLE>, [key_c_pulse], "-|>", shift: 13pt),
		edge(<RUNNING>, <ALARM>, [max_time_reached_in], "-|>", bend: -40deg),
		edge(<PAUSED>, <RUNNING>, [key_a_pulse], "-|>", shift: 13pt),
		edge(<PAUSED>, <IDLE>, [key_c_pulse], "-|>", bend: 40deg),
		edge(<ALARM>, <IDLE>, [key_c_pulse], "-|>", bend: 55deg),
		
		edge(<IDLE>, <IDLE>, [#ziti()[其他情况]], "-|>", bend: 120deg),
		edge(<RUNNING>, <RUNNING>, [#ziti()[其他情况且未超时]], "-|>", bend: 120deg),
		edge(<PAUSED>, <PAUSED>, [#ziti()[其他情况]], "-|>", bend: 120deg),
		edge(<ALARM>, <ALARM>, [#ziti()[其他情况]], "-|>", bend: 120deg),
	),
	caption: "短跑计时器的状态机图",
)

==== 计数器模块clk_divider_counter

计数器模块是计时器的核心计时单元，负责产生各种时钟信号并进行时间计数。该模块首先通过分频产生10ms的计时脉冲，同时生成数码管扫描时钟和报警LED闪烁时钟。在计时功能方面，模块采用BCD码计数方式，分别对毫秒（0-99）、秒（0-59）和分钟（0-1）进行计数。毫秒计数采用7位二进制计数器，通过组合逻辑转换为两位BCD码输出；秒计数采用6位二进制计数器，同样转换为两位BCD码；分钟则使用1位二进制计数器。模块还实现了最大计时时间（1分59秒990毫秒）的检测功能，当达到最大时间时，会置位`max_time_reached`信号，触发报警状态。

==== 输出显示模块display_driver

输出显示模块负责将计时器的数值转换为可视化的显示信号。该模块通过七段数码管和LED灯两种显示器件来展示计时信息。七段数码管采用共阴极接法，其段选信号（a-g）高电平有效，分别控制数码管的7个发光段，从高位到低位依次为a、b、c、d、e、f、g。同时，模块还通过LED灯来显示分钟和报警状态，其中报警LED采用闪烁方式提示。

该模块采用动态扫描方式驱动4位七段数码管，显示顺序为秒十位、秒个位、百毫秒位和十毫秒位。模块内部包含一个2位计数器用于位选控制，通过`scan_clk_enable`信号控制扫描频率。在显示控制方面，模块将输入的BCD码转换为七段数码管的段选信号，同时控制位选信号`an_out`实现动态扫描。对于分钟显示，模块直接驱动LED灯；对于报警状态，模块通过`blink_clk_enable`信号控制报警LED的闪烁效果。整个显示过程采用共阴极数码管，段选信号高电平有效，位选信号低电平有效，确保了显示效果的清晰和稳定。

=== 模块连接

根据上述模块的功能描述，我们可以将各个模块连接起来，形成完整的短跑计时器系统。系统的顶层模块将状态机控制器、计数器模块和显示驱动模块连接在一起，实现计时、控制和显示功能。下图展示了各个模块之间的连接关系。

#figure(
  image("assets/top_stopwatch_circuit.png"),
  caption: "短跑计时器的电路模块图"
)

== 电路实现 

#let top_stopwatch = read("src/top_stopwatch.v")

#module_impl(
  name: "模块：top_stopwatch",
  function: "功能：短跑计时器顶层模块，实例化并连接所有子模块。",
  code: top_stopwatch
)

\

#let clk_divider_counter = read("src/clk_divider_counter.v")

#module_impl(
  name: "模块：clk_divider_counter",
  function: "功能：
1. 产生10ms的计时脉冲 (tick_10ms)
2. 进行分钟、秒、毫秒(百毫秒、十毫秒)的BCD计数
3. 判断是否达到最大计时时间 (1分59秒990毫秒)
4. 产生数码管动态扫描使能信号 (scan_clk_enable)
5. 产生报警LED闪烁使能信号 (blink_clk_enable)
",
  code: clk_divider_counter
)

\

#let button_interface_debounce = read("src/button_interface_debounce.v")

#module_impl(
  name: "模块：button_interface_debounce",
  function: "功能：对按键输入进行消抖处理，并产生单周期脉冲信号。
按键按下（输入高电平）时，输出一个时钟周期的有效脉冲。
",
  code: button_interface_debounce
)

\

#let state_controller = read("src/state_controller.v")

#module_impl(
  name: "模块：state_controller",
  function: "功能：控制计时器的启停、复位以及报警状态，实现短跑计时器的状态机控制逻辑。
状态: IDLE, RUNNING, PAUSED, ALARM
",
  code: state_controller
)

\

#let display_driver = read("src/display_driver.v")

#module_impl(
  name: "模块：display_driver",
  function: "功能：
1. 将输入的BCD码时间值译码成七段数码管段选信号。
2. 实现4位七段数码管的动态扫描。
3. 驱动分钟LED和报警LED。
显示顺序 (an_out[3] → an_out[0]): 秒十位, 秒个位, 百毫秒位, 十毫秒位
",
  code: display_driver
)

== 电路验证 

=== TestBench

#let tb_top_stopwatch = read("src/tb_top_stopwatch.v")

#module_impl(
  name: "TestBench: tb_top_stopwatch",
  function: "功能: 用于仿真验证 top_stopwatch 模块，测试3项核心功能。
仿真速度已加速1000倍。
",
  code: tb_top_stopwatch
)


=== 仿真结果 

#block()[在Testbench当中，我们希望验证短跑计时器的3项核心功能：]
- 正常计时功能
- 四种状态下的复位功能
- 报警功能

通过合理的编排，我们将这3项核心功能的测试融入到了依次进行的6个阶段当中，以下是各个阶段的说明以及对应的仿真结果： 

==== 1)	IDLE状态下的复位功能

按下按键C复位，验证IDLE状态下的复位功能。预期结果是计时仍为0，实际结果与预期相符：

#figure(
  image("assets/tb1.png", width: 90%),
  caption: "IDLE状态下的复位功能"
)

==== 2)	正常计时功能当中的运行与暂停功能

- #ziti()[*Step.1*)]	按下按键A开始计时，验证IDLE->RUNNING。预期结果为计时由零开始逐渐增加，实际结果与预期相符，限于篇幅无法在此处展现具体数值，但可以看出数字正在变化：

#figure(
  image("assets/tb2.png", width: 90%),
  caption: "正常计时功能当中的运行与暂停功能"
)

- #ziti()[*Step.2*)]	再按下按键B暂停计时，验证RUNNING->PAUSED。预期结果为计时开始保持不变，实际结果与预期相符：

#figure(
  image("assets/tb3.png", width: 90%),
  caption: "正常计时功能当中的运行与暂停功能"
)

- #ziti()[*Step.3*)]	此时再按下按键A开始计时，验证PAUSED->RUNNING。预期结果为计时继续增加，实际结果与预期相符：

#figure(
  image("assets/tb4.png", width: 90%),
  caption: "正常计时功能当中的运行与暂停功能"
)


==== 3)	暂停状态下的复位功能

- #ziti()[*Step.1*)]	首先按下按键B暂停计时。该操作已得到验证，不再过多赘述。
- #ziti()[*Step.2*)]	随后按下按键C复位，验证PAUSED->IDLE。预期结果为计时清零，实际结果与预期相符：

#figure(
  image("assets/tb5.png", width: 90%),
  caption: "暂停状态下的复位功能"
)


==== 4)	运行状态下的复位功能

- #ziti()[*Step.1*)]	首先按下按键A开始计时。该操作已得到验证，不再过多赘述。
- #ziti()[*Step.2*)]	随后按下按键C复位，验证RUNNING->IDLE。预期结果为计时清零，实际结果与预期相符：

#figure(
  image("assets/tb6.png", width: 90%),
  caption: "运行状态下的复位功能"
)


==== 5)	正常计时功能当中的“进位”功能

由于短跑计时器对于分钟的处理与秒、毫秒有所不同，因此此处单独验证。按下按键A开始计时，并等待时间到达一分钟。预期结果为`tb_led_minute_pin`跳变为1，实际结果与预期相符：

#figure(
  image("assets/tb7.png", width: 90%),
  caption: "正常计时功能当中的“进位”功能"
)

==== 6)	报警功能与该状态下的复位

- #ziti()[*Step.1*)]	等待计时到达两分钟，验证RUNNING->ALARM。预期结果为计时清零，同时`tb_led_alarm_pin`数值在0、1之间反复跳变(即LED灯闪烁报警)，实际结果与预期相符：
 
#figure(
  image("assets/tb8.png", width: 90%),
  caption: "报警功能与该状态下的复位"
)

- #ziti()[*Step.2)*]	按下按键C复位，验证ALARM->IDLE。预期结果为计时、`tb_led_minute_pin`、`tb_led_alarm_pin`均清零，计时器回归初始状态，实际结果与预期相符：

#figure(
  image("assets/tb9.png", width: 90%),
  caption: "报警功能与该状态下的复位"
)

#text()[#h(0.0em)] // 用来使得块级元素后分段
 
总之，我们设计的短跑计时器的3项核心功能在Testbench当中得到了很好的验证，证明了设计思路与Verilog代码的有效性。


== 电路上板 

=== 管脚配置

结合EES-338开发板的管脚分配说明书，以及源代码输入输出变量设置，可以得到如下图中表格的管脚配置：

#figure(
  image("assets/管脚配置.png", width: 90%),
  caption: "短跑计时器的管脚配置"
)

=== 上板情况

在Vivado中依次完成管脚配置、生成比特流文件、开发板连接和代码上板。

上板之后，可以观察到初始状态下，右侧四位数码管亮起，显示"0000"。

当按下开始键时，计时器进入计时状态，数码管开始显示实时计时的秒数和毫秒数。随着时间推移，当计时达到1分钟时，分钟指示灯自动点亮，提示已经计时超过1分钟。当计时达到1分59秒99毫秒时，系统触发报警状态，报警指示灯开始闪烁，同时计时器停止计时。

在计时过程中，用户可以随时通过暂停键控制计时器的运行状态。按下暂停键后，计时器暂停计时，数码管显示的数字保持不变。此时再次按下开始键，计时器将继续从暂停时刻继续计时。无论是在暂停状态还是计时状态，按下复位键都会使计时器回到初始状态：分钟指示灯和报警指示灯熄灭，数码管显示归零为"0000"。

#figure(
  grid(
    columns: 2,
    gutter: 10pt,
    image("assets/初始状态.jpg"),
    image("assets/开始计时.jpg"),
    image("assets/1分钟.jpg"),
    image("assets/超时.jpg"),
  ),
  caption: "上板情况"
)

#text()[#h(0.0em)] // 用来使得块级元素后分段

具体操作演示请参考录制的视频。

== 实验心得 

==== 左逸龙

本次短跑计时器实验是一次完整的数字电路设计与验证实践。我主要负责了电路的 `Verilog` 代码实现与仿真验证工作，而陈墨霏同学则负责了电路的概要设计和最终的板级烧录与验证。通过这次合作，我对数字逻辑设计的全流程有了更深刻的理解。

在电路实现阶段，我首先面临的挑战是数码管的显示机制。我初次了解到数码管并非静态显示，而是通过动态扫描方式进行分时点亮。这要求我在 `display_driver` 模块中精确控制位选和段选信号的时序。分模块设计理念在这一阶段展现了其优势。当我发现数码管显示异常时，能够迅速锁定 `display_driver` 模块进行排查，而无需检查整个系统代码。

代码编写过程中，我对时序逻辑和并行处理的理解得到了深化。例如，对非阻塞赋值 (`<=`) 的认识从语法层面提升到了其背后硬件寄存器并行更新的特性。这帮助我更好地预判信号在仿真中的传播行为。在设计 `state_controller` 模块时，确保状态机所有可能的跳转路径都得到覆盖是一大难点。我初期曾遗漏了从运行状态直接复位到空闲状态的路径，导致仿真中计时器无法立即重置，这强调了状态转移完整性的重要性。此外，在 `clk_divider_counter` 模块中，我通过设计多级分频器，将 $100 #text[MHz]$ 的系统时钟转换为 $10 #text[ms]$ 的计时脉冲以及数码管扫描和报警所需的辅助时钟。这个过程使我直观地体会到了数字系统中不同时间尺度的生成与转换。同时，在编写诸如 `reg counter` 这样的计数器时，我开始尝试将其映射为实际的触发器阵列，这种硬件思维有助于写出更符合可综合要求的代码。

仿真验证是本次实验中我投入精力最多的部分。起初，我将 `Testbench` 编写视为一项单纯的作业要求，但很快，它的实际价值就得以体现。在初次仿真时，我发现按键激励未能在波形图中正确显示。经过排查，我首先定位到 `Testbench` 中 `task` 参数传递的作用域问题，理解了直接通过 `inout reg` 参数修改信号电平的局限性。随后，尽管修正了激励方式，按键脉冲在宏观波形图中依然不可见，这促使我认识到 Vivado 仿真器波形优化机制的存在，并通过延长按键脉冲的持续时间来解决显示问题。最关键的一次调试经历是，我在 `clk_divider_counter` 模块中启用了仿真加速模式，却遗漏了在 `button_interface_debounce` 模块中同步调整消抖计时参数。这意味着，加速后的仿真按键脉冲持续时间远不足以满足原始的消抖计数要求，导致按键事件始终无法被识别。这个问题的发现让我深刻理解了跨模块参数同步的重要性，以及调试时需要全面检查所有相关时序参数的必要性。通过这些具体的调试过程，我的思维模式从单纯的代码编写转向了“先怀疑测试方法，再深入设计逻辑”的分析思路。同时，我对波形图的“阅读”能力也得到了锻炼，学会了如何将波形上的高低电平变化与代码中的逻辑状态和变量值准确对应。

在与组员的协作中，我负责的仿真验证与组员负责的板级烧录形成了闭环。当组员在板级测试中反馈数码管显示异常时，我基于仿真中的经验，迅速检查了 `display_driver` 模块中段选和位选信号的极性设置，最终发现存在一处高电平有效与低电平有效的混淆，修正后板载显示恢复正常。此外，我们也共同认识到，即使仿真结果完美，实际烧录到板上仍可能遇到问题，这其中，引脚约束的准确性尤为关键。我们初期在进行引脚约束时，由于未充分对照开发板手册，导致频繁出现接线错误，这凸显了在设计初期就进行细致引脚规划的重要性。

总的来说，本次实验最大的收获在于，我掌握了“仿真驱动开发”的流程。即在编写核心功能代码之前，优先构建并完善验证环境，用仿真验证设计的正确性，这一实践显著提升了开发效率，并减少了后期调试的复杂性。这次完整的项目经历，也为我未来学习《计算机组成原理》中控制单元的状态机设计、以及《操作系统》中进程调度算法提供了具象的硬件实践基础。对严谨的硬件调试思维的培养，也将对未来可能从事的芯片设计或嵌入式开发岗位产生积极影响。如果让我重新开始这个实验，我将从一开始就投入更多精力完善仿真测试用例，特别是覆盖各种边界条件和异常路径。我相信，这种前期的验证投入能够极大程度地减少后续百分之八十的调试时间，进一步提高开发效率。


==== 陈墨霏

在本次短跑计时器实验中，我主要负责了电路的概要设计和最终的板级验证工作。通过与左逸龙同学的密切配合，我不仅深入理解了数字电路的设计流程，更在实践中体会到了硬件设计从理论到实现的完整过程。

在电路设计过程中，我深刻体会到了模块化设计理念的重要性。整个短跑计时器被合理地划分为4个功能明确的子模块和1个顶层综合模块（`top_stopwatch`），这种模块化的设计不仅使代码结构清晰，也大大提高了开发效率和代码的可维护性。通过这次实践，我对时序逻辑电路的设计有了更深层次的理解。在理论学习阶段，我们主要关注状态机的状态转移和输出逻辑，但在实际下板开发时，我发现这仅仅是整个设计过程中的一小部分。例如，在实现`state_controller`模块时，我们不仅要考虑状态机的正确性，还要考虑如何将其与实际的硬件设备进行交互。这让我意识到，硬件设计不仅仅是逻辑设计的实现，更是对硬件特性的深入理解和应用。在开发过程中，我们遇到了一个典型的硬件特性问题：数码管显示异常。通过和左逸龙同学的细致排查，我们发现问题的根源在于对位选信号有效电平的错误理解。我们错误地将位选信号认定为负有效，导致本该点亮的一位数码管不被点亮，而另外三位却被点亮。这个问题的解决过程让我深刻认识到，在实际硬件开发中，对硬件特性的准确理解是多么重要。此外，通过这次实验，我也深入理解了状态机设计中的关键概念，包括状态转移表、状态机图等工具的使用。这些工具不仅帮助我们清晰地表达设计意图，也为后续的代码实现和调试提供了重要参考。总的来说，这次实验让我对数字电路设计有了更全面的认识，从理论到实践，从逻辑设计到硬件实现，每一个环节都让我受益匪浅。

在本次实验中，我还负责了电路的上板验证工作。通过实践，我系统地掌握了Vivado开发环境下的完整上板流程，包括引脚约束配置、约束文件生成、综合实现、比特流文件生成以及最终的开发板烧录等环节。虽然这些操作步骤相对固定，投入的精力不及电路设计阶段，但这个过程对细节的把控要求极高。在初期上板过程中，由于对引脚约束的疏忽，我曾多次出现接线错误，导致系统无法正常工作。例如，在一次调试中，我将七段数码管的位选信号高低位顺序颠倒，直接导致了显示异常。这些经历让我深刻认识到，在硬件开发中，严谨细致的工作态度与扎实的技术功底同样重要。这种对细节的重视不仅适用于实验环境，更是实际工程开发中不可或缺的职业素养。

通过与左逸龙同学的密切合作，我们不仅顺利完成了实验本身的设计与实现，还共同完成了后续的视频拍摄、报告撰写等收尾工作。这次合作经历让我受益匪浅，特别是在技术层面，我从队友那里学习到了许多宝贵的开发思路和实用工具。这些收获不仅对本次实验大有裨益，也将对我未来的学习和工作产生积极影响。在此，我向我的队友表示由衷的感谢。

通过本次短跑计时器的设计与实现，我不仅深化了对《数字逻辑》课程中状态机设计、时序逻辑等核心概念的理解，更重要的是建立起了对数字系统设计的整体认知框架。这些实践经验为我后续学习《计算机组成原理》中的控制单元设计、《操作系统》中的进程调度等课程奠定了坚实的硬件基础。同时，在实验过程中培养的严谨的硬件调试思维和模块化设计理念，也将对我未来可能从事的芯片设计、嵌入式开发等职业领域产生深远影响。