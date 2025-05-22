// TestBench for zero_sequence_detector

`timescale 1ns / 1ps // 定义仿真时间单位和精度

module zero_sequence_detector_tb;

    // 信号声明
    reg clk;
    reg rst;
    reg I;
    wire O;

    // 参数定义
    parameter PERIOD = 10; // 时钟周期 10ns (100MHz)

    // 待测试模块例化 (DUT)
    zero_sequence_detector dut (
        .clk(clk),
        .rst(rst),
        .I(I),
        .O(O)
    );

    // 时钟生成
    initial begin
        clk = 0;
        forever begin
            #(PERIOD / 2) clk = ~clk;
        end
    end

    // 激励生成
    initial begin
        // 打开波形文件
        $dumpfile("zero_sequence_detector.vcd");
        $dumpvars(0, zero_sequence_detector_tb);

        // 初始化输入信号
        I = 0;

        // 1. 进行复位
        rst = 1;
        #PERIOD; // 保持复位一个时钟周期
        rst = 0;
        #PERIOD; // 等待一个时钟周期，确保复位完成

        // 2. 模拟不同的输入序列

        $display("--- Starting Simulation ---");
        // 监控关键信号
        $monitor("Time=%0t, State=%b, rst=%b, I=%b, O=%b", $time, dut.current_state, rst, I, O);

        // 测试序列1: 0000 (应输出1)
        #PERIOD; I = 0; // 第一个0，状态应转到 001
        #PERIOD; I = 0; // 第二个0，状态应转到 011
        #PERIOD; I = 0; // 第三个0，状态应转到 010
        #PERIOD; I = 0; // 第四个0，状态应转到 100，输出应为1

        // 测试序列2: 1 (应复位状态到 000)
        #PERIOD; I = 1;

        // 测试序列3: 0010000 (测试复位和再次检测)
        #PERIOD; I = 0; // 001
        #PERIOD; I = 0; // 011
        #PERIOD; I = 1; // 1，复位到 000
        #PERIOD; I = 0; // 001
        #PERIOD; I = 0; // 011
        #PERIOD; I = 0; // 010
        #PERIOD; I = 0; // 100，输出应为1

        // 测试序列4: 保持在 0000+ 状态输入 0
        #PERIOD; I = 0; // 保持在 100，输出应为1

        // 测试序列5: 从 0000+ 状态输入 1
        #PERIOD; I = 1; // 复位到 000

        // 3. 结束仿真
        # (PERIOD * 5); // 再运行一段时间
        $display("--- Ending Simulation ---");
        $finish; // 结束仿真

    end

endmodule