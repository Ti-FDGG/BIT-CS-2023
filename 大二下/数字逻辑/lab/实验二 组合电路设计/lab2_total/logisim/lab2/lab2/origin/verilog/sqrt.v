module sqrt (
   input [3:0] A,
   output [2:0] B
);

   // 中间信号的导线
   wire s_logisimNet0, s_logisimNet1, s_logisimNet2, s_logisimNet3;
   wire s_logisimNet4, s_logisimNet5, s_logisimNet6, s_logisimNet7;
   wire s_logisimNet8, s_logisimNet9, s_logisimNet10, s_logisimNet11;
   wire s_logisimNet12, s_logisimNet13, s_logisimNet14, s_logisimNet15;
   wire s_logisimNet16, s_logisimNet17, s_logisimNet18, s_logisimNet19;
   wire s_logisimNet20, s_logisimNet21;

   // 输入连接
   assign s_logisimNet0 = A[2];
   assign s_logisimNet4 = A[1];
   assign s_logisimNet6 = A[3];
   assign s_logisimNet9 = A[0];

   // 输出连接
   assign B[0] = s_logisimNet15;
   assign B[1] = s_logisimNet8;
   assign B[2] = s_logisimNet20;

   // 逻辑门
   assign s_logisimNet1 = ~s_logisimNet0;
   assign s_logisimNet3 = ~s_logisimNet4;
   assign s_logisimNet2 = ~s_logisimNet9;
   assign s_logisimNet13 = ~s_logisimNet6;

   assign s_logisimNet5 = s_logisimNet6 & s_logisimNet0 & s_logisimNet9;
   assign s_logisimNet12 = s_logisimNet6 & s_logisimNet0 & s_logisimNet4;
   assign s_logisimNet16 = ~s_logisimNet6 & s_logisimNet0;
   assign s_logisimNet19 = s_logisimNet6 & ~s_logisimNet0;
   assign s_logisimNet11 = ~s_logisimNet6 & s_logisimNet4 & s_logisimNet9;
   assign s_logisimNet14 = s_logisimNet6 & ~s_logisimNet4 & ~s_logisimNet9;
   assign s_logisimNet17 = ~s_logisimNet0 & ~s_logisimNet4 & s_logisimNet9;
   assign s_logisimNet18 = ~s_logisimNet0 & s_logisimNet4 & ~s_logisimNet9;
   assign s_logisimNet21 = ~s_logisimNet6 & s_logisimNet0 & s_logisimNet4 & s_logisimNet9;
   assign s_logisimNet7 = s_logisimNet6 & ~s_logisimNet0;
   assign s_logisimNet10 = s_logisimNet6 & ~s_logisimNet4 & ~s_logisimNet9;

   assign s_logisimNet20 = s_logisimNet5 | s_logisimNet12;
   assign s_logisimNet8 = s_logisimNet16 | s_logisimNet19 | s_logisimNet11 | s_logisimNet14;
   assign s_logisimNet15 = s_logisimNet17 | s_logisimNet18 | s_logisimNet21 | s_logisimNet7 | s_logisimNet10;

endmodule