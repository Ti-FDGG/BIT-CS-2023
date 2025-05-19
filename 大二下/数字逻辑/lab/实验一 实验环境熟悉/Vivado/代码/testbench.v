`timescale 1ns / 1ps

module testbench(
    );
    reg A,B,C;
    
    initial begin
        A = 1'b0;
        #10 A = 1'b1;
        #20 A = 1'b0;
        #30 A = 1'b1;
    end
    
    initial begin
        B = 1'b0;
        #5 B = 1'b1;
        #10 B = 1'b0;
        #25 B = 1'b1;
    end
    
    initial begin
        C = 1'b1;
        #15 C = 1'b0;
        #20 C = 1'b1;
    end
    
    wire F;
    voter voter(
        .a(A),
        .b(B),
        .c(C),
        .f(F)
    );
    
endmodule
