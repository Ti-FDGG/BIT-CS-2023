public class UseSimpleInterface {

    public static void main(String[] args) {

        SimpleInterface obj = () -> System.out.println("this is a lambda example");
        obj.doSomething();

        SimpleInterface obj2 = () -> {
            System.out.println("Hello");
            System.out.println("world");
        };

        obj2.doSomething();

    }
}


