`timescale 1ns / 1ps

module testbench();
reg[3:0] a, b;
wire[3:0] sum;
wire cout;

initial begin
    a = 4'b0011;
    b = 4'b0110;
    #10;
    a = 4'b1001;
    b = 4'b0111;
end

full_adder_4bit adder(a, b, sum, cout);

endmodule

