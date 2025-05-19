`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2025/04/07 14:46:47
// Design Name: 
// Module Name: testbench
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
module testbench();
    reg [3:0] A; // 4-bit 输入向量 A
    wire [2:0] B; // 3-bit 输出向量 B

    // 例化待测模块 sqrt
    sqrt uut (
        .A(A),
        .B(B)
    );

    // 定义数据输入
    initial begin
        // 测试用例 1
        A = 4'b0000; // 输入为 0
        #100;
        
        // 测试用例 2
        A = 4'b0001; // 输入为 1
        #100;
        
        // 测试用例 3
        A = 4'b0010; // 输入为 2
        #100;
        
        // 测试用例 4
        A = 4'b0100; // 输入为 4
        #100;
        
        // 测试用例 5
        A = 4'b1000; // 输入为 8
        #100;
        
        // 测试用例 6
        A = 4'b1111; // 输入为 15
        #100;

        $stop; // 停止仿真
    end

endmodule
