class AboutException {
    public static void main(String[] a) {
//        exceptionCauseProgramTerminate();
//        tryCatchLetProgramContinue();
//        throwMyExceptions();
//        variableInTry1();
//        variableInTry2();
    }

    static void exceptionCauseProgramTerminate() {
        int i = 1, j = 0, k;
        //如果不注释掉以下这句，可以看到异常抛出，程序中止
        k = i / j;
        System.out.println("result:" + k);
    }

    static void tryCatchLetProgramContinue() {
        int i = 1, j = 0, k;
        //可以使用try...catch结构来捕获并处理异常，
        //以避免程序提前中止
        try {
            k = i / j;    // 引发被零除异常
        } catch (ArithmeticException e) {
            System.out.println("被0除。  " + e.getMessage());
        } finally {
            System.out.println("无论如何，都要完成收尾工作");
        }
    }

    static void throwMyExceptions() {
        try {
            //必要时，可以使用以下这句“主动抛出异常”
            throw new Exception("某些不好的事情发生了……");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("无论如何，都要完成收尾工作");
        }
    }

    static void variableInTry1() {
        try {
            int i = 100;
            int j = 0;
            System.out.println(i / j);
        } catch (ArithmeticException e) {
            //以下代码尝试访问变量i和j，是无法通过编译的
            //System.out.println(i + "除以" + j + "出错！");
        }
    }

    static void variableInTry2() {
        int i = 100;
        int j = 0;
        try {
            System.out.println(i / j);
        } catch (ArithmeticException e) {
            //由于变量外置，现在可以访问得到i和j
            System.out.println(i + "除以" + j + "出错！");
        }
    }

}
