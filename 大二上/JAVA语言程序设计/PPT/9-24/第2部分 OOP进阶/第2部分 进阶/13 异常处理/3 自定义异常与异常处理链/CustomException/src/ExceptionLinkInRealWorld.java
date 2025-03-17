
//自定义的异常类,通常用于代表特定的业务逻辑层的异常
//表明在处理业务过程中出现了某种“意外”
class MyBusinessException extends Exception {
    public MyBusinessException(String Message) {
        super(Message);
    }
    public MyBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyBusinessException(Throwable cause) {
        super(cause);
    }
    //可以添加其他字段，以表明特定的业务逻辑相关的信息
}

public class ExceptionLinkInRealWorld {

    public static void main(String args[]) {
        try {
            someBusinessMethod();
            doesNotThrowException();
            //可以按照特定流程的步骤，在此集成多个方法
            //……
        } catch (MyBusinessException e) {
            //处理业务逻辑相关的异常
            //如果有多个，就写多个catch块
        } catch (Exception e) {
           //处理所有未捕获的异常
        }
    }

    //某个实现业务逻辑的方法，当检测到出现非法状态时，
    //可以主动抛出一个“业务逻辑”异常
    public static void someBusinessMethod()
            throws MyBusinessException {
        try {
            System.out.println("Method throwException");
//            if(someThingIsWrong)
//                throw new MyBusinessException("someThingIsWrong");
        } catch (Exception e) {
            //JDK引发的，或者其它特定的异常，可以根据实际情况，
            //选择一个异常处理策略：
            //（1）直接本地处理
            //（2) 不处理，直接抛出，让方法的调用者处理
            //（3）将它转换为一个自定义业务逻辑异常，再抛出
            throw new MyBusinessException("做XXX事情时出错了！", e);
        } finally {
            //在此完成一些收尾处理工作，比如写日志
        }
    }

    //普通的，无需特殊处理的方法
    public static void doesNotThrowException() {
        try {
            System.out.println("Method doesNotThrowException");
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            System.err.println(
                    "Finally executed in doesNotThrowException");
        }
        System.out.println(
                "End of method doesNotThrowException");
    }
}

