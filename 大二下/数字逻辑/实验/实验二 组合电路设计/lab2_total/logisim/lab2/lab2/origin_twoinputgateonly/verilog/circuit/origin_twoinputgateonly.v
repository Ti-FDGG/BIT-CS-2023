/******************************************************************************
 ** Logisim-evolution goes FPGA automatic generated Verilog code             **
 ** https://github.com/logisim-evolution/                                    **
 **                                                                          **
 ** Component : origin_twoinputgateonly                                      **
 **                                                                          **
 *****************************************************************************/

module origin_twoinputgateonly( A_0,
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
   wire s_logisimNet22;
   wire s_logisimNet23;
   wire s_logisimNet24;
   wire s_logisimNet25;
   wire s_logisimNet26;
   wire s_logisimNet27;
   wire s_logisimNet28;
   wire s_logisimNet29;
   wire s_logisimNet3;
   wire s_logisimNet30;
   wire s_logisimNet31;
   wire s_logisimNet32;
   wire s_logisimNet33;
   wire s_logisimNet34;
   wire s_logisimNet35;
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
   assign s_logisimNet1  = A_0;
   assign s_logisimNet11 = A_3;
   assign s_logisimNet18 = A_2;
   assign s_logisimNet3  = A_1;

   /*******************************************************************************
   ** Here all output connections are defined                                    **
   *******************************************************************************/
   assign B_0 = s_logisimNet6;
   assign B_1 = s_logisimNet14;
   assign B_2 = s_logisimNet25;

   /*******************************************************************************
   ** Here all in-lined components are defined                                   **
   *******************************************************************************/

   // 非门
   assign s_logisimNet16 = ~s_logisimNet18;

   // 非门
   assign s_logisimNet19 = ~s_logisimNet3;

   // 非门
   assign s_logisimNet0 = ~s_logisimNet1;

   // 非门
   assign s_logisimNet2 = ~s_logisimNet11;

   /*******************************************************************************
   ** Here all normal components are defined                                     **
   *******************************************************************************/
   AND_GATE #(.BubblesMask(2'b00))
      GATES_1 (.input1(s_logisimNet11),
               .input2(s_logisimNet18),
               .result(s_logisimNet20));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_2 (.input1(s_logisimNet11),
               .input2(s_logisimNet18),
               .result(s_logisimNet32));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_3 (.input1(s_logisimNet2),
               .input2(s_logisimNet3),
               .result(s_logisimNet33));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_4 (.input1(s_logisimNet11),
               .input2(s_logisimNet19),
               .result(s_logisimNet21));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_5 (.input1(s_logisimNet16),
               .input2(s_logisimNet19),
               .result(s_logisimNet34));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_6 (.input1(s_logisimNet16),
               .input2(s_logisimNet3),
               .result(s_logisimNet22));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_7 (.input1(s_logisimNet20),
               .input2(s_logisimNet1),
               .result(s_logisimNet26));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_8 (.input1(s_logisimNet32),
               .input2(s_logisimNet3),
               .result(s_logisimNet29));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_9 (.input1(s_logisimNet2),
               .input2(s_logisimNet18),
               .result(s_logisimNet15));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_10 (.input1(s_logisimNet11),
                .input2(s_logisimNet16),
                .result(s_logisimNet5));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_11 (.input1(s_logisimNet33),
                .input2(s_logisimNet1),
                .result(s_logisimNet7));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_12 (.input1(s_logisimNet21),
                .input2(s_logisimNet0),
                .result(s_logisimNet8));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_13 (.input1(s_logisimNet34),
                .input2(s_logisimNet1),
                .result(s_logisimNet9));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_14 (.input1(s_logisimNet22),
                .input2(s_logisimNet0),
                .result(s_logisimNet10));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_15 (.input1(s_logisimNet2),
                .input2(s_logisimNet18),
                .result(s_logisimNet27));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_16 (.input1(s_logisimNet3),
                .input2(s_logisimNet1),
                .result(s_logisimNet30));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_17 (.input1(s_logisimNet11),
                .input2(s_logisimNet19),
                .result(s_logisimNet35));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_18 (.input1(s_logisimNet26),
                .input2(s_logisimNet29),
                .result(s_logisimNet25));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_19 (.input1(s_logisimNet15),
                .input2(s_logisimNet5),
                .result(s_logisimNet17));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_20 (.input1(s_logisimNet7),
                .input2(s_logisimNet8),
                .result(s_logisimNet13));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_21 (.input1(s_logisimNet9),
                .input2(s_logisimNet10),
                .result(s_logisimNet12));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_22 (.input1(s_logisimNet27),
                .input2(s_logisimNet30),
                .result(s_logisimNet28));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_23 (.input1(s_logisimNet11),
                .input2(s_logisimNet16),
                .result(s_logisimNet23));

   AND_GATE #(.BubblesMask(2'b00))
      GATES_24 (.input1(s_logisimNet35),
                .input2(s_logisimNet0),
                .result(s_logisimNet31));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_25 (.input1(s_logisimNet17),
                .input2(s_logisimNet13),
                .result(s_logisimNet14));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_26 (.input1(s_logisimNet12),
                .input2(s_logisimNet28),
                .result(s_logisimNet24));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_27 (.input1(s_logisimNet23),
                .input2(s_logisimNet31),
                .result(s_logisimNet4));

   OR_GATE #(.BubblesMask(2'b00))
      GATES_28 (.input1(s_logisimNet24),
                .input2(s_logisimNet4),
                .result(s_logisimNet6));


endmodule
