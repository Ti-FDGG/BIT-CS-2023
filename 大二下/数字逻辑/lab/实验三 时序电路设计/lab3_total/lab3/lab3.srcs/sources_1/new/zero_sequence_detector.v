`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2025/05/22 20:04:03
// Design Name: 
// Module Name: zero_sequence_detector
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


// 定义模块
module zero_sequence_detector (
    input wire clk,   // 时钟信号
    input wire rst,   // 复位信号，高电平有效
    input wire I,     // 输入信号
    output reg  O     // 输出信号
);

    // 定义状态，使用 parameter 关键字提高可读性
    parameter STATE_1      = 3'b000; // 对应没有0的状态
    parameter STATE_0      = 3'b001; // 对应1个0的状态
    parameter STATE_00     = 3'b011; // 对应2个0的状态
    parameter STATE_000    = 3'b010; // 对应3个0的状态
    parameter STATE_0000_PLUS = 3'b100; // 对应4个及以上个0的状态

    // 声明状态寄存器和下一状态线
    reg [2:0] current_state; // 当前状态
    reg [2:0] next_state;    // 计算出的下一状态

    // =====================================================================
    // 时序逻辑：状态寄存器的更新
    // 在时钟上升沿或复位信号上升沿触发
    // =====================================================================
    always @(posedge clk or posedge rst) begin
        if (rst) begin
            // 复位时，回到初始状态
            current_state <= STATE_1;
        end else begin
            // 非复位时，在时钟上升沿更新当前状态为下一状态
            current_state <= next_state;
        end
    end

    // =====================================================================
    // 组合逻辑：计算下一状态
    // 根据当前状态和输入信号，计算下一个状态
    // 使用 case 语句描述状态转移表
    // =====================================================================
    always @(*) begin
        // 默认下一状态保持当前状态 (可选，但有助于处理未指定的情况)
        next_state = current_state; // 默认值，将在 case 语句中覆盖

        case (current_state)
            STATE_1: begin
                if (I == 0) begin
                    next_state = STATE_0;
                end else begin // I == 1
                    next_state = STATE_1;
                end
            end

            STATE_0: begin
                if (I == 0) begin
                    next_state = STATE_00;
                end else begin // I == 1
                    next_state = STATE_1;
                end
            end

            STATE_00: begin
                if (I == 0) begin
                    next_state = STATE_000;
                end else begin // I == 1
                    next_state = STATE_1;
                end
            end

            STATE_000: begin
                if (I == 0) begin
                    next_state = STATE_0000_PLUS;
                end else begin // I == 1
                    next_state = STATE_1;
                end
            end

            STATE_0000_PLUS: begin
                if (I == 0) begin
                    next_state = STATE_0000_PLUS; // 保持在结束状态
                end else begin // I == 1
                    next_state = STATE_1;
                end
            end

            // 处理无效状态编码 (通常不需要显式写出，综合工具会根据需求处理，
            // 但为了完整性，可以指定无效状态的转移，例如回到初始状态)
            default: begin
                next_state = STATE_1; // 无效状态转移到初始状态
            end
        endcase
    end

    // =====================================================================
    // 组合逻辑：计算输出
    // 根据当前状态和输入信号，计算输出信号 O
    // =====================================================================
    always @(*) begin
        // 默认输出为 0
        O = 1'b0;

        // 根据状态和输入确定输出
        if (current_state == STATE_0000_PLUS && I == 0) begin
            O = 1'b1;
        end
        // 其他情况输出默认为 0
    end

endmodule
