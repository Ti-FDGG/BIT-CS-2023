import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
    public static void main(String[] args) {
        Pair<String> pair = new Pair<>();
        //true
        System.out.println(pair instanceof Pair);

        List<String> strList = new ArrayList<String>();
        List<Integer> intList = new ArrayList<Integer>();
        //true
        System.out.println(
                strList.getClass() == intList.getClass()
        );

    }
}
