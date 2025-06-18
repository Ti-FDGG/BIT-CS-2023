`define SIMULATION_MODE // 定义仿真模式，上板烧录时需要注释掉

module clk_divider_counter (
    input  wire        clk,               // 系统时钟 (100MHz)
    input  wire        reset_n,           // 异步复位信号 (低电平有效)
    input  wire        timer_run_en,      // 计时运行使能 (来自FSM)
    input  wire        timer_reset_cmd,   // 计时复位命令 (来自FSM, 高电平有效)

    output reg [3:0]   ms_bcd_tens_out,   // 毫秒显示的十位 (代表百毫秒, 0-9)
    output reg [3:0]   ms_bcd_ones_out,   // 毫秒显示的个位 (代表十毫秒, 0-9)
    output reg [3:0]   sec_bcd_tens_out,  // 秒显示的十位 (0-5)
    output reg [3:0]   sec_bcd_ones_out,  // 秒显示的个位 (0-9)
    output reg         min_out,           // 分钟显示 (0-1，使用单独的LED显示)

    output reg         max_time_reached,  // 到达最大计时时间标志
    output wire        scan_clk_enable,   // 数码管扫描时钟使能
    output wire        blink_clk_enable   // 报警LED闪烁时钟使能
);

    // 参数定义
    `ifdef SIMULATION_MODE // 仿真模式加速1000倍
        localparam CNT_10MS_MAX = 1000 - 1;
        localparam CNT_SCAN_MAX = 100 - 1;
        localparam CNT_BLINK_MAX = 10000 - 1;
    `else // 上板烧录模式正常运行
        // 100MHz 时钟: 1 周期 = 10ns
        // 1. 10ms (100Hz) 计时脉冲计数器参数
        localparam CNT_10MS_MAX = 100_000_0 - 1; // 100MHz / 100Hz - 1 = 1,000,000 - 1

        // 2. 数码管扫描时钟使能 (扫描频率约 1kHz -> 4位整体刷新率250Hz)
        localparam CNT_SCAN_MAX = 100_000 - 1;   // 100MHz / 1kHz - 1

        // 3. 报警LED闪烁时钟使能 (闪烁频率约 5Hz)
        localparam CNT_BLINK_MAX = 10_000_000 - 1; // 100MHz / (2*5Hz) - 1 (产生约5Hz方波的半周期)
    `endif

    // 内部计数器
    reg [$clog2(CNT_10MS_MAX+1)-1:0] cnt_10ms;
    reg [$clog2(CNT_SCAN_MAX+1)-1:0] cnt_scan;
    reg [$clog2(CNT_BLINK_MAX+1)-1:0] cnt_blink;

    // 计时脉冲信号
    wire tick_10ms;

    // 内部计时值 (二进制)
    reg [6:0] ms_counter;  // 0-99
    reg [5:0] sec_counter; // 0-59
    reg       min_counter; // 0-1

    // 1. 产生各种时钟使能/脉冲
    assign tick_10ms = (cnt_10ms == CNT_10MS_MAX);
    assign scan_clk_enable = (cnt_scan == CNT_SCAN_MAX);
    assign blink_clk_enable = (cnt_blink == CNT_BLINK_MAX);

    always @(posedge clk or negedge reset_n) begin
        if (!reset_n) begin
            cnt_10ms  <= 0;
            cnt_scan  <= 0;
            cnt_blink <= 0;
        end else begin
            // 10ms 脉冲计数器
            if (cnt_10ms == CNT_10MS_MAX) begin
                cnt_10ms <= 0;
            end else begin
                cnt_10ms <= cnt_10ms + 1'b1;
            end

            // 数码管扫描使能计数器
            if (cnt_scan == CNT_SCAN_MAX) begin
                cnt_scan <= 0;
            end else begin
                cnt_scan <= cnt_scan + 1'b1;
            end

            // 报警闪烁使能计数器
            if (cnt_blink == CNT_BLINK_MAX) begin
                cnt_blink <= 0;
            end else begin
                cnt_blink <= cnt_blink + 1'b1;
            end
        end
    end

    // 2. 计时逻辑 (分、秒、毫秒)
    always @(posedge clk or negedge reset_n) begin
        if (!reset_n) begin
            ms_counter  <= 7'd0;
            sec_counter <= 6'd0;
            min_counter <= 1'b0;
            max_time_reached <= 1'b0;
        end else if (timer_reset_cmd) begin // FSM发出的复位命令
            ms_counter  <= 7'd0;
            sec_counter <= 6'd0;
            min_counter <= 1'b0;
            max_time_reached <= 1'b0;
        end else if (timer_run_en && tick_10ms) begin // 运行且10ms到
            if (max_time_reached) begin
                // 如果已达最大时间，计时值清零，并且FSM会转到ALARM状态并设置timer_run_en为0
            end else begin
                // 毫秒计数 (0-99)
                if (ms_counter == 7'd99) begin
                    ms_counter <= 7'd0;
                    // 秒计数 (0-59)
                    if (sec_counter == 6'd59) begin
                        sec_counter <= 6'd0;
                        // 分钟计数 (0-1)
                        if (min_counter == 1'b1) begin // 原来是1分59秒990毫秒
                            min_counter <= 1'b1; // 保持在1，并置位max_time_reached
                            max_time_reached <= 1'b1;
                        end else begin // 原来是0分59秒990毫秒
                            min_counter <= min_counter + 1'b1; // 0 -> 1
                        end
                    end else begin // 秒不进位
                        sec_counter <= sec_counter + 1'b1;
                    end
                end else begin // 毫秒不进位
                    ms_counter <= ms_counter + 1'b1;
                end

                // 更新最大时间到达标志
                // (1分59秒990毫秒)
                if (min_counter == 1'b1 && sec_counter == 6'd59 && ms_counter == 7'd99) begin
                    max_time_reached <= 1'b1;
                end else begin
                    max_time_reached <= 1'b0; // 如果中途复位，确保标志也被复位
                end
            end
        end else if (!timer_run_en && !timer_reset_cmd) begin // 暂停状态，且不是复位命令
             max_time_reached <= max_time_reached; // 保持状态
        end
    end

    // 3. 将二进制计数值转换为BCD码输出
    // 毫秒 (0-99)
    always @(*) begin
        ms_bcd_tens_out = ms_counter / 10;
        ms_bcd_ones_out = ms_counter % 10;
    end

    // 秒 (0-59)
    always @(*) begin
        sec_bcd_tens_out = sec_counter / 10;
        sec_bcd_ones_out = sec_counter % 10;
    end

    // 分 (0-1)
    always @(*) begin
        min_out = min_counter;
    end

endmodule