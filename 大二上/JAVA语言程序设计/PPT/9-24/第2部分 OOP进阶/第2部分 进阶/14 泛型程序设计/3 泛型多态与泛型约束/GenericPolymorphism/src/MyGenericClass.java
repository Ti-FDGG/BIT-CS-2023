

public class MyGenericClass<T extends Number> {

    public static void main(String[] args) {

        //由于Integer和Double都派生自Number
        //所以，可以用它作为泛型参数，不会引发编译错误
        MyGenericClass<Integer> ai = new MyGenericClass<>();
        MyGenericClass<Double> ad = new MyGenericClass<>();

        //下面代码将引起编译错误
        //因为String不是Number的子类型。
        //MyGenericClass<String> as = new MyGenericClass<>();

    }
}

