testbench.v
`timescale 1ns / 1ps

module testbench(
    );

    reg clk;
    reg rst_n;
    reg en;
    reg data_in;
    
    // 定义周期性的时钟信号
    parameter CYCLE = 10
    initial clk = 1'b0; // 定义仿真刚开始时的时钟信号值
    always #(CYCLE) clk = ~clk // 反转每隔CYCLE ns的时间时钟信号发生一次翻转

    // 定义复位信号
    initial begin
        rst_n=1'b0;
        #20 
        rst_n=1'b1; // 仿真开始20ns后发生复位
    end

    // 定义使能信号
    initial begin
        #20
        en = 1'b1; // 仿真开始20ns后使能信号有效
        #1000
        en = 1'b0; // 仿真开始1020ns后使能信号无效
    end

    // 定义数据输入
    initial begin
        ... // 按行为给相应激励输入data_in，测试仿真
    end

    // 例化待测模块test
    wire data_out;
    test test(
        .clk(clk), // 时钟
        .rst_n(rst_n), // 复位
        .en(en), // 使能
        .data_in(data_in), // 输入 
        .data_out(data_out) // 模块的输出可以直接从波形上观测到
    );

endmodule

