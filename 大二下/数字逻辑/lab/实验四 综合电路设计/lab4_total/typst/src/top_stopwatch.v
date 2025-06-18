module top_stopwatch (
    // 输入端口
    input  wire        sys_clk_pin,       // 系统时钟输入
    input  wire        reset_n_pin,       // 系统复位按键输入 (低电平有效)
    input  wire        key_a_pin,         // 按键A (开始/继续) (高电平有效)
    input  wire        key_b_pin,         // 按键B (暂停)     (高电平有效)
    input  wire        key_c_pin,         // 按键C (复位)     (高电平有效)

    // 输出端口
    output wire [6:0]  seg_pins,          // 七段数码管段选 (A-G, G=[6])
    output wire [3:0]  an_pins,           // 四位片选 (低有效, [3]是最左边)
    output wire        led_minute_pin,    // 分钟LED (高有效)
    output wire        led_alarm_pin      // 报警LED (高有效)
);

    // 内部信号线 (wires)
    wire key_a_pulse_w;
    wire key_b_pulse_w;
    wire key_c_pulse_w;

    wire timer_run_en_w;
    wire timer_reset_cmd_w;
    wire alarm_active_w;

    wire [3:0] ms_bcd_tens_w;
    wire [3:0] ms_bcd_ones_w;
    wire [3:0] sec_bcd_tens_w;
    wire [3:0] sec_bcd_ones_w;
    wire       min_val_w;
    wire       max_time_reached_w;
    wire       scan_clk_enable_w;
    wire       blink_clk_enable_w;

    // 实例化按键消抖模块 (每个按键一个)
    button_interface_debounce u_debounce_A (
        .clk            (sys_clk_pin),
        .reset_n        (reset_n_pin), // 所有模块共用一个主复位
        .key_raw_in     (key_a_pin),
        .key_pulse_out  (key_a_pulse_w)
    );

    button_interface_debounce u_debounce_B (
        .clk            (sys_clk_pin),
        .reset_n        (reset_n_pin),
        .key_raw_in     (key_b_pin),
        .key_pulse_out  (key_b_pulse_w)
    );

    button_interface_debounce u_debounce_C (
        .clk            (sys_clk_pin),
        .reset_n        (reset_n_pin),
        .key_raw_in     (key_c_pin),
        .key_pulse_out  (key_c_pulse_w)
    );

    // 实例化状态控制器模块
    state_controller u_state_controller (
        .clk                   (sys_clk_pin),
        .reset_n               (reset_n_pin),
        .key_a_pulse           (key_a_pulse_w),
        .key_b_pulse           (key_b_pulse_w),
        .key_c_pulse           (key_c_pulse_w),
        .max_time_reached_in   (max_time_reached_w),

        .timer_run_en_out      (timer_run_en_w),
        .timer_reset_cmd_out   (timer_reset_cmd_w),
        .alarm_active_out      (alarm_active_w)
    );

    // 实例化时钟分频与计时计数模块
    clk_divider_counter u_clk_divider_counter (
        .clk                (sys_clk_pin),
        .reset_n            (reset_n_pin),
        .timer_run_en       (timer_run_en_w),
        .timer_reset_cmd    (timer_reset_cmd_w),

        .ms_bcd_tens_out    (ms_bcd_tens_w),
        .ms_bcd_ones_out    (ms_bcd_ones_w),
        .sec_bcd_tens_out   (sec_bcd_tens_w),
        .sec_bcd_ones_out   (sec_bcd_ones_w),
        .min_out            (min_val_w),
        .max_time_reached   (max_time_reached_w),
        .scan_clk_enable    (scan_clk_enable_w),
        .blink_clk_enable   (blink_clk_enable_w)
    );

    // 实例化显示驱动模块
    display_driver u_display_driver (
        .clk                  (sys_clk_pin),
        .reset_n              (reset_n_pin),
        .ms_bcd_tens_in       (ms_bcd_tens_w),
        .ms_bcd_ones_in       (ms_bcd_ones_w),
        .sec_bcd_tens_in      (sec_bcd_tens_w),
        .sec_bcd_ones_in      (sec_bcd_ones_w),
        .min_in               (min_val_w),
        .alarm_active_in      (alarm_active_w),
        .scan_clk_enable_in   (scan_clk_enable_w),
        .blink_clk_enable_in  (blink_clk_enable_w),

        .seg_out              (seg_pins),
        .an_out               (an_pins),
        .led_minute_out       (led_minute_pin),
        .led_alarm_out        (led_alarm_pin)
    );

endmodule