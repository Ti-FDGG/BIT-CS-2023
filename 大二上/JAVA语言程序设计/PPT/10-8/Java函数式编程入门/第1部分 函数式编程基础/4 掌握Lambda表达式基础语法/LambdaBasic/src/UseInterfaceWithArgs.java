

public class UseInterfaceWithArgs {
    public static void main(String[] args) {
        InterfaceWithArgs obj = (v1, v2) -> {
            int result = v1 + v2;
            System.out.println("The result is " + result);
        };
        obj.doSomething(100, 200);
    }
}
