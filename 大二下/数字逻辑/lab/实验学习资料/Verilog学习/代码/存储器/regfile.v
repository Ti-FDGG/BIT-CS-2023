`timescale 1ns / 1ps

module regfile(
    input wire        clk,
    input wire        ren,
    input wire        wen,
    input wire[31:0]  wdata,
    input wire[4:0]   addr,
    output wire[31:0] rdata
    );

    reg[31:0] regfile[31:0];

    initial begin
        $readmemh("C:\\ram_data.txt", regfile);
    end

    assign rdata = (ren == 1'b1) ? regfile[addr] : 32'b0;

    always @(posedge clk) begin
        if (wen)
            regfile[addr] = wdata;
    end
endmodule

