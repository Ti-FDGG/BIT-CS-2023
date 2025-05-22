`timescale 1ns / 1ps

module testbench();

    reg        clk;
    reg        ren;
    reg        wen;
    reg[4:0]   addr;
    reg[31:0]  wdata;
    wire[31:0] rdata;

    regfile regfile0(clk, ren, wen, wdata, addr, rdata);

    initial begin
        clk = 1'b0;
        ren = 1'b0;
        wen = 1'b0;
        addr = 5'b00011;
        wdata = 32'h0;
        #12
        wen = 1'b1;
        wdata = 32'h0a0a0a0a;
        #5
        wen = 1'b0;
        #2
        ren = 1'b1;
    end

    always #5 clk = ~clk;

endmodule

