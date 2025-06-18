`timescale 1ns / 1ps

module tb_top_stopwatch;

    // Testbench 内部信号
    reg tb_sys_clk_pin;
    reg tb_reset_n_pin;
    reg tb_key_a_pin;
    reg tb_key_b_pin;
    reg tb_key_c_pin;

    // 从被测模块 (DUT - Design Under Test) 输出的信号
    wire [6:0] tb_seg_pins;
    wire [3:0] tb_an_pins;
    wire       tb_led_minute_pin;
    wire       tb_led_alarm_pin;

    // 实例化被测模块 (DUT)
    top_stopwatch uut (
        .sys_clk_pin    (tb_sys_clk_pin),
        .reset_n_pin    (tb_reset_n_pin),
        .key_a_pin      (tb_key_a_pin),
        .key_b_pin      (tb_key_b_pin),
        .key_c_pin      (tb_key_c_pin),

        .seg_pins       (tb_seg_pins),
        .an_pins        (tb_an_pins),
        .led_minute_pin (tb_led_minute_pin),
        .led_alarm_pin  (tb_led_alarm_pin)
    );

    // 时钟生成 (100MHz -> 周期 10ns)
    localparam CLK_PERIOD = 10; // ns
    always begin
        tb_sys_clk_pin = 1'b0;
        #(CLK_PERIOD / 2);
        tb_sys_clk_pin = 1'b1;
        #(CLK_PERIOD / 2);
    end

    // 仿真激励序列
    initial begin
        // 0. 初始化
        tb_reset_n_pin = 1'b1;
        tb_key_a_pin   = 1'b0;
        tb_key_b_pin   = 1'b0;
        tb_key_c_pin   = 1'b0;
        $display("SIM_INFO: 仿真开始");
        
        // 确保进入稳定IDLE状态
        tb_reset_n_pin = 1'b0;
        #(200);
        tb_reset_n_pin = 1'b1;
        repeat(10) @(posedge tb_sys_clk_pin);

        // 第一部分：测试IDLE状态下的按键C
        $display("SIM_INFO: 测试IDLE状态下的按键C");
        $display("SIM_INFO: [Test 1] 在IDLE状态下测试按键C (应无效果)");
        tb_key_c_pin = 1'b1; #(200); tb_key_c_pin = 1'b0;
        #(100 * 1000 * CLK_PERIOD); // 等待100ms, 观察计时器是否仍为0

        // 第二部分：测试正常的运行与暂停功能
        $display("SIM_INFO: 测试正常的运行与暂停功能");
        $display("SIM_INFO: [Test 2] 按下按键A开始计时 (IDLE -> RUNNING)");
        tb_key_a_pin = 1'b1; #(200); tb_key_a_pin = 1'b0;
        #(25 * 1000 * CLK_PERIOD); // 运行250ms

        $display("SIM_INFO: [Test 3] 按下按键B暂停计时 (RUNNING -> PAUSED)");
        tb_key_b_pin = 1'b1; #(200); tb_key_b_pin = 1'b0;
        #(100 * 1000 * CLK_PERIOD); // 暂停100ms，观察时间是否不变

        $display("SIM_INFO: [Test 4] 再按下按键A继续计时 (PAUSED -> RUNNING)");
        tb_key_a_pin = 1'b1; #(200); tb_key_a_pin = 1'b0;
        #(30 * 1000 * CLK_PERIOD); // 再运行300ms, 总计时约550ms

        // 第三部分：测试暂停状态下的复位功能
        $display("SIM_INFO: 测试暂停状态下的复位功能");
        $display("SIM_INFO: [Test 5] 先按下按键B暂停计时 (RUNNING -> PAUSED)");
        tb_key_b_pin = 1'b1; #(200); tb_key_b_pin = 1'b0;
        #(50 * 1000 * CLK_PERIOD); // 短暂暂停
        
        $display("SIM_INFO: [Test 6] 然后按下按键C复位 (PAUSED -> IDLE)");
        tb_key_c_pin = 1'b1; #(200); tb_key_c_pin = 1'b0;
        #(100 * 1000 * CLK_PERIOD); // 等待观察复位效果

        // 第四部分：测试运行状态下的复位功能
        $display("SIM_INFO: 测试运行状态下的复位功能");
        $display("SIM_INFO: [Test 7] 再次按下按键A开始计时 (IDLE -> RUNNING)");
        tb_key_a_pin = 1'b1; #(200); tb_key_a_pin = 1'b0;
        #(50 * 1000 * CLK_PERIOD); // 运行500ms
        
        $display("SIM_INFO: [Test 8] 然后按下按键C复位 (RUNNING -> IDLE)");
        tb_key_c_pin = 1'b1; #(200); tb_key_c_pin = 1'b0;
        #(100 * 1000 * CLK_PERIOD); // 等待观察复位效果

        // 第五部分：测试报警功能与该状态下的复位
        $display("SIM_INFO: 测试报警功能与该状态下的复位");
        $display("SIM_INFO: [Test 9 & 10] 启动计时器至最大时间 (IDLE -> RUNNING -> ALARM)");
        tb_key_a_pin = 1'b1; #(200); tb_key_a_pin = 1'b0; // [Test 9]
        
        // [Test 10] 等待到达最大时间 1:59:99
        #(11998 * 1000 * CLK_PERIOD); // 先走到最大时间前一个tick
        $display("SIM_INFO: 接近最大时间");
        #(1 * 1000 * CLK_PERIOD);   // 最后一个tick，此时应该达到最大时间
        
        #(200 * 1000 * CLK_PERIOD); // 维持报警状态200ms
        $display("SIM_INFO: 维持报警状态200ms");

        $display("SIM_INFO: [Test 11] 按下按键C复位 (ALARM -> IDLE)");
        tb_key_c_pin = 1'b1; #(200); tb_key_c_pin = 1'b0;
        #(100 * 1000 * CLK_PERIOD);

        $display("SIM_INFO: 仿真结束");
        $finish; // 结束仿真
    end

endmodule