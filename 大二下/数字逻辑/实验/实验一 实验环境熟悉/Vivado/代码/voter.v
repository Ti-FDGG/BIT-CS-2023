`timescale 1ns / 1ps

module voter(
    // 输入端口声明
    input a,
    input b,
    input c,
    // 输出端口声明
    output f
);

wire ab, ac, bc; // 声明中间信号

// 结构化描述组合电路
and and1(ab, a, b); // a信号与b信号经过“与元件”后输出信号ab
and and2(ac, a, c);
and and3(bc, b, c);

or or1(f, ab, ac, bc); // ab, ac, bc经过“或元件”后输出最终表决结果

endmodule
