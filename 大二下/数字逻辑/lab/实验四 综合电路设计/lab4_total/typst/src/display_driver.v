module display_driver (
    input  wire        clk,                 // 系统时钟
    input  wire        reset_n,             // 异步复位 (低有效)

    input  wire [3:0]  ms_bcd_tens_in,      // 百毫秒位 (0-9 BCD)
    input  wire [3:0]  ms_bcd_ones_in,      // 十毫秒位 (0-9 BCD)
    input  wire [3:0]  sec_bcd_tens_in,     // 秒的十位 (0-5 BCD)
    input  wire [3:0]  sec_bcd_ones_in,     // 秒的个位 (0-9 BCD)
    input  wire        min_in,              // 分钟 (0-1)

    input  wire        alarm_active_in,     // 报警激活信号
    input  wire        scan_clk_enable_in,  // 数码管扫描时钟使能
    input  wire        blink_clk_enable_in, // LED闪烁时钟使能

    output reg [6:0]   seg_out,             // 七段数码管段选信号 (A-G, G是seg_out[6])
    output reg [3:0]   an_out,              // 四位片选信号 (低电平有效)
    output wire        led_minute_out,      // 分钟LED输出
    output reg         led_alarm_out        // 报警LED输出
);

    // 内部信号
    reg [1:0] digit_sel_counter; // 数码管位选计数器 (00, 01, 10, 11)
    reg [3:0] current_bcd_val;   // 当前选通位要显示的BCD值
    reg blink_toggle_q;        // 用于LED闪烁的触发器

    // 分钟LED直接输出
    assign led_minute_out = min_in;

    // 报警LED闪烁逻辑
    always @(posedge clk or negedge reset_n) begin
        if (!reset_n) begin
            blink_toggle_q <= 1'b0;
            led_alarm_out  <= 1'b0;
        end else begin
            if (blink_clk_enable_in) begin
                blink_toggle_q <= ~blink_toggle_q; // 闪烁使能到来时翻转
            end

            if (alarm_active_in) begin
                led_alarm_out <= blink_toggle_q; // 报警时根据翻转信号输出
            end else begin
                led_alarm_out <= 1'b0; // 非报警状态，LED灭
            end
        end
    end

    // 数码管动态扫描 - 位选计数器
    always @(posedge clk or negedge reset_n) begin
        if (!reset_n) begin
            digit_sel_counter <= 2'b00;
        end else if (scan_clk_enable_in) begin // 扫描时钟使能到来时
            digit_sel_counter <= digit_sel_counter + 1'b1; // 00->01->10->11->00
        end
    end

    // 数码管动态扫描 - 根据位选选择要显示的BCD值和更新位选an_out
    // an_out: [3]秒十位, [2]秒个位, [1]百毫秒, [0]十毫秒
    always @(*) begin
        case (digit_sel_counter)
            2'b00: begin // 显示十毫秒位 (最右边的数码管)
                current_bcd_val = ms_bcd_ones_in;
                an_out          = 4'b0001; // 选中第0位
            end
            2'b01: begin // 显示百毫秒位
                current_bcd_val = ms_bcd_tens_in;
                an_out          = 4'b0010; // 选中第1位
            end
            2'b10: begin // 显示秒的个位
                current_bcd_val = sec_bcd_ones_in;
                an_out          = 4'b0100; // 选中第2位
            end
            2'b11: begin // 显示秒的十位 (最左边的数码管)
                current_bcd_val = sec_bcd_tens_in;
                an_out          = 4'b1000; // 选中第3位
            end
            default: begin // 理论上不会到这里
                current_bcd_val = 4'b0000; // 显示0
                an_out          = 4'b0000; // 全不选
            end
        endcase
    end

    // BCD码到七段数码管段选译码 (共阴极，高电平点亮)
    // seg_out: [6]=G, [5]=F, [4]=E, [3]=D, [2]=C, [1]=B, [0]=A
    always @(*) begin
        case (current_bcd_val)
            4'h0: seg_out = 7'b0111111; // 0: A,B,C,D,E,F
            4'h1: seg_out = 7'b0000110; // 1: B,C
            4'h2: seg_out = 7'b1011011; // 2: A,B,D,E,G
            4'h3: seg_out = 7'b1001111; // 3: A,B,C,D,G
            4'h4: seg_out = 7'b1100110; // 4: B,C,F,G
            4'h5: seg_out = 7'b1101101; // 5: A,C,D,F,G
            4'h6: seg_out = 7'b1111101; // 6: A,C,D,E,F,G
            4'h7: seg_out = 7'b0000111; // 7: A,B,C
            4'h8: seg_out = 7'b1111111; // 8: A,B,C,D,E,F,G
            4'h9: seg_out = 7'b1101111; // 9: A,B,C,D,F,G
            default: seg_out = 7'b1000000; // G (代表错误或无效输入，显示'-')
        endcase
    end

endmodule