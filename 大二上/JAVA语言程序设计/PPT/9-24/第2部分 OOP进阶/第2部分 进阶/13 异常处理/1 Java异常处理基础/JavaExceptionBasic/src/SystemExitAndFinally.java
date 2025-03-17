
//试着取消System.exit(0)这句的注释，会发现finally语句块并没有被执行！

public class SystemExitAndFinally {
    public static void main(String[] args) {
        try {
            System.out.println("in main");
            //System.exit(0);
            throw new Exception("Exception is thrown in main");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.exit(0);
        } finally {
            System.out.println("in finally");
        }
    }
}
