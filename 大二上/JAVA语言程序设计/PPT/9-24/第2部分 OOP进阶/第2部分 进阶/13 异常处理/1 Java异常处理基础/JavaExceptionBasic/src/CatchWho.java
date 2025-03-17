public class CatchWho {
    public static void main(String[] args) {
        test1();
        System.out.println("------------");
        test2();
    }

    private static void test1() {
        try {
            try {
                throw new ArrayIndexOutOfBoundsException();
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(  "ArrayIndexOutOfBoundsException" +  "/内层try-catch");
            }
            throw new ArithmeticException();
        }
        catch(ArithmeticException e) {
            System.out.println("发生ArithmeticException");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(  "ArrayIndexOutOfBoundsException" + "/外层try-catch");
        }
    }

    private static void test2(){
        try {
            try {
                throw new ArrayIndexOutOfBoundsException();
            }
            catch(ArithmeticException e) {
                System.out.println( "ArrayIndexOutOfBoundsException" + "/内层try-catch");
            }
            throw new ArithmeticException();
        }
        catch(ArithmeticException e) {
            System.out.println("发生ArithmeticException");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println( "ArrayIndexOutOfBoundsException" + "/外层try-catch");
        }
    }
}