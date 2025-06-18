#import "@preview/fletcher:0.5.7": diagram, node, edge

#let ziti(body) = text(font: (
  (name: "Times New Roman", covers: "latin-in-cjk"), // 西文字体
  "SimSun" // 中文字体
), lang: "zh")[#body]

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
