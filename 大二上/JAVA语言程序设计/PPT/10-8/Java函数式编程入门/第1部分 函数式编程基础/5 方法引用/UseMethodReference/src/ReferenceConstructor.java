import java.util.function.Function;

public class ReferenceConstructor {
    public static void main(String[] args) {
        Function<Integer, SomeClass> creator = SomeClass::new;
        System.out.println(creator.apply(100).getValue());
    }
}

class SomeClass {
    private int value;

    public SomeClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
