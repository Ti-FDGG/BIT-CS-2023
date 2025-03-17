
import java.util.*;


public class TestGenericMethod {

    //声明一个泛型方法，该泛型方法中带一个T形参，
    static <T> void fromArrayToCollection(T[] arr,
                                          Collection<T> collection) {
        //在调用JDK所定义的泛型方法时无需指明类型参数，
        //编译器会进行类型推断，以确定具体的数据类型。
        Collections.addAll(collection, arr);
    }

    public static void main(String[] args) {

        Object[] objectArray = new Object[100];
        Collection<Object> objectCollection = new ArrayList<>();
        //泛型方法中的参数T,被推断为Object类型
        fromArrayToCollection(objectArray, objectCollection);

        String[] stringArray = new String[100];
        Collection<String> stringCollection = new ArrayList<>();
        //泛型方法中的参数T,被推断为String类型
        fromArrayToCollection(stringArray, stringCollection);

        Integer[] integerArray = new Integer[100];
        Collection<Number> numberCollection = new ArrayList<>();
        //泛型方法中的参数T,被推断为Number类型
        fromArrayToCollection(integerArray, numberCollection);

        Float[] floatArray = new Float[100];
        //泛型方法中的参数T,被推断为Number类型
        fromArrayToCollection(floatArray, numberCollection);

        Number[] numberArray = new Number[100];
        //泛型方法中的参数T,被推断为Number类型
        fromArrayToCollection(numberArray, numberCollection);

        //泛型方法中的参数T,被推断为Number类型
        fromArrayToCollection(numberArray, objectCollection);
        //泛型方法中的参数T,被推断为String类型，但numberArray是一个Number数组，
        //因为Number既不是String类型，也不是它的子类，所以出现编译错误
        //必须注释掉才能通过编译
        //fromArrayToCollection(numberArray, stringCollection);
    }
}
