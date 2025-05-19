/******************************************************************************
 ** Logisim-evolution goes FPGA automatic generated Verilog code             **
 ** https://github.com/logisim-evolution/                                    **
 **                                                                          **
 ** Component : origin                                                       **
 **                                                                          **
 *****************************************************************************/

module origin( A_0,
               A_1,
               A_2,
               A_3,
               B_0,
               B_1,
               B_2 );

   /*******************************************************************************
   ** The inputs are defined here                                                **
   *******************************************************************************/
   input A_0;
   input A_1;
   input A_2;
   input A_3;

   /*******************************************************************************
   ** The outputs are defined here                                               **
   *******************************************************************************/
   output B_0;
   output B_1;
   output B_2;

   /*******************************************************************************
   ** The wires are defined here                                                 **
   *******************************************************************************/
   wire s_logisimNet0;
   wire s_logisimNet1;
   wire s_logisimNet10;
   wire s_logisimNet11;
   wire s_logisimNet12;
   wire s_logisimNet13;
   wire s_logisimNet14;
   wire s_logisimNet15;
   wire s_logisimNet16;
   wire s_logisimNet17;
   wire s_logisimNet18;
   wire s_logisimNet19;
   wire s_logisimNet2;
   wire s_logisimNet20;
   wire s_logisimNet21;
   wire s_logisimNet3;
   wire s_logisimNet4;
   wire s_logisimNet5;
   wire s_logisimNet6;
   wire s_logisimNet7;
   wire s_logisimNet8;
   wire s_logisimNet9;

   /*******************************************************************************
   ** The module functionality is described here                                 **
   *******************************************************************************/

   /*******************************************************************************
   ** Here all input connections are defined                                     **
   *******************************************************************************/
   assign s_logisimNet0 = A_2;
   assign s_logisimNet4 = A_1;
   assign s_logisimNet6 = A_3;
   assign s_logisimNet9 = A_0;

   /*******************************************************************************
   ** Here all output connections are defined                                    **
   *******************************************************************************/
   assign B_0 = s_logisimNet15;
   assign B_1 = s_logisimNet8;
   assign B_2 = s_logisimNet20;

   /*******************************************************************************
   ** Here all in-lined components are defined                                   **
   *******************************************************************************/

   // 非门
   assign s_logisimNet1 = ~s_logisimNet0;

   // 非门
   assign s_logisimNet3 = ~s_logisimNet4;

   // 非门
   assign s_logisimNet2 = ~s_logisimNet9;

   // 非门
   assign s_logisimNet13 = ~s_logisimNet6;

   /*******************************************************************************
   ** Here all normal components are defined                                     **
   *******************************************************************************/
   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_1 (.input1(s_logisimNet6),
               .input2(s_logisimNet0),
               .input3(s_logisimNet9),
               .result(s_logisimNet5));

   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_2 (.input1(s_logisimNet6),
               .input2(s_logisimNet0),
               .input3(s_logisimNet4),
               .result(s_logisimNet12));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_3 (.input1(s_logisimNet13),
               .input2(s_logisimNet0),
               .result(s_logisimNet16));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_4 (.input1(s_logisimNet6),
               .input2(s_logisimNet1),
               .result(s_logisimNet19));

   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_5 (.input1(s_logisimNet13),
               .input2(s_logisimNet4),
               .input3(s_logisimNet9),
               .result(s_logisimNet11));

   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_6 (.input1(s_logisimNet6),
               .input2(s_logisimNet3),
               .input3(s_logisimNet2),
               .result(s_logisimNet14));

   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_7 (.input1(s_logisimNet1),
               .input2(s_logisimNet3),
               .input3(s_logisimNet9),
               .result(s_logisimNet17));

   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_8 (.input1(s_logisimNet1),
               .input2(s_logisimNet4),
               .input3(s_logisimNet2),
               .result(s_logisimNet18));

   AND_GATE_4_INPUTS #(.BubblesMask(4'h0))
      GATES_9 (.input1(s_logisimNet13),
               .input2(s_logisimNet0),
               .input3(s_logisimNet4),
               .input4(s_logisimNet9),
               .result(s_logisimNet21));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_10 (.input1(s_logisimNet6),
                .input2(s_logisimNet1),
                .result(s_logisimNet7));

   AND_GATE_3_INPUTS #(.BubblesMask(3'b000))
      GATES_11 (.input1(s_logisimNet6),
                .input2(s_logisimNet3),
                .input3(s_logisimNet2),
                .result(s_logisimNet10));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_12 (.input1(s_logisimNet5),
                .input2(s_logisimNet12),
                .result(s_logisimNet20));

   OR_GATE_4_INPUTS #(.BubblesMask(4'h0))
      GATES_13 (.input1(s_logisimNet16),
                .input2(s_logisimNet19),
                .input3(s_logisimNet11),
                .input4(s_logisimNet14),
                .result(s_logisimNet8));

   OR_GATE_5_INPUTS #(.BubblesMask({1'b0, 4'h0}))
      GATES_14 (.input1(s_logisimNet17),
                .input2(s_logisimNet18),
                .input3(s_logisimNet21),
                .input4(s_logisimNet7),
                .input5(s_logisimNet10),
                .result(s_logisimNet15));


endmodule
