import java.util.function.Supplier;

public class UseSupplier {
    public static void main(String[] args) {
        Supplier<ExampleClass> supplier = () -> new ExampleClass();
        System.out.println(supplier.get());
        //使用方法引用达到同样的目的
        Supplier<ExampleClass> supplier2 = ExampleClass::new;
        System.out.println(supplier2.get());

    }
}

class ExampleClass {
}
