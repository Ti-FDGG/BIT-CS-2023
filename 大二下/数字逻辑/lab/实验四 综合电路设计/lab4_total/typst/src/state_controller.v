module state_controller (
    input  wire clk,                   // 系统时钟
    input  wire reset_n,               // 异步复位 (低有效)
    input  wire key_a_pulse,           // 按键A脉冲 (开始/继续)
    input  wire key_b_pulse,           // 按键B脉冲 (停止/暂停)
    input  wire key_c_pulse,           // 按键C脉冲 (复位/清零)
    input  wire max_time_reached_in,   // 最大计时时间到达信号

    output reg  timer_run_en_out,      // 计时器运行使能
    output reg  timer_reset_cmd_out,   // 计时器复位命令 (高有效)
    output reg  alarm_active_out       // 报警激活信号
);

    // 状态定义
    localparam S_IDLE    = 2'b00; // 空闲/复位状态
    localparam S_RUNNING = 2'b01; // 计时运行状态
    localparam S_PAUSED  = 2'b10; // 计时暂停状态
    localparam S_ALARM   = 2'b11; // 报警状态

    // 状态寄存器
    reg [1:0] current_state;
    reg [1:0] next_state;

    // 状态转移逻辑 (时序逻辑: 更新当前状态)
    always @(posedge clk or negedge reset_n) begin
        if (!reset_n) begin
            current_state <= S_IDLE;
        end else begin
            current_state <= next_state;
        end
    end

    // 次态逻辑 (组合逻辑: 根据当前状态和输入决定下一状态)
    always @(*) begin
        next_state = current_state; // 默认保持当前状态
        case (current_state)
            S_IDLE: begin
                if (key_a_pulse) begin
                    next_state = S_RUNNING;
                end
                // 按下C在IDLE状态下保持IDLE，复位命令将在输出逻辑中处理
            end
            S_RUNNING: begin
                if (key_b_pulse) begin
                    next_state = S_PAUSED;
                end else if (key_c_pulse) begin
                    next_state = S_IDLE;
                end else if (max_time_reached_in) begin
                    next_state = S_ALARM;
                end
            end
            S_PAUSED: begin
                if (key_a_pulse) begin
                    next_state = S_RUNNING;
                end else if (key_c_pulse) begin
                    next_state = S_IDLE;
                end
            end
            S_ALARM: begin
                if (key_c_pulse) begin
                    next_state = S_IDLE;
                end
            end
            default: begin
                next_state = S_IDLE;
            end
        endcase
    end

    // 输出逻辑 (组合逻辑: 根据当前状态确定输出)
    always @(*) begin
        // 默认输出
        timer_run_en_out    = 1'b0;
        timer_reset_cmd_out = 1'b0;
        alarm_active_out    = 1'b0;

        case (current_state)
            S_IDLE: begin
                timer_run_en_out    = 1'b0;
                timer_reset_cmd_out = 1'b1; // 在IDLE状态，持续请求复位计时器
                alarm_active_out    = 1'b0;
            end
            S_RUNNING: begin
                timer_run_en_out    = 1'b1;
                timer_reset_cmd_out = 1'b0;
                alarm_active_out    = 1'b0;
            end
            S_PAUSED: begin
                timer_run_en_out    = 1'b0;
                timer_reset_cmd_out = 1'b0;
                alarm_active_out    = 1'b0;
            end
            S_ALARM: begin
                timer_run_en_out    = 1'b0; // 报警时停止计时
                timer_reset_cmd_out = 1'b0;
                alarm_active_out    = 1'b1;
            end
            default: begin // 安全起见，默认行为同IDLE
                timer_run_en_out    = 1'b0;
                timer_reset_cmd_out = 1'b1;
                alarm_active_out    = 1'b0;
            end
        endcase
    end

endmodule