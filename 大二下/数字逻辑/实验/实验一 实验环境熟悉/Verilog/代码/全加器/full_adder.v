`timescale 1ns / 1ps

module full_adder_1bit(
    input wire a, b, cin,
    output wire sum, cout
    );

    assign sum = (a ^ b) ^ cin;
    assign cout = (a & b) | ((a ^ b) & cin);
endmodule

module full_adder_4bit(
    input wire[3:0] a, b,
    output wire[3:0] sum,
    output wire cout
    );

    wire [3:0] carry;
    full_adder_1bit bit0(a[0], b[0],     1'b0, sum[0], carry[0]);
    full_adder_1bit bit1(a[1], b[1], carry[0], sum[1], carry[1]);
    full_adder_1bit bit2(a[2], b[2], carry[1], sum[2], carry[2]);
    full_adder_1bit bit3(a[3], b[3], carry[2], sum[3], carry[3]);

    assign cout = carry[3];
endmodule

