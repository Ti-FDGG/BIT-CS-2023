import java.util.ArrayList;
import java.util.List;


public class GenericPolymorphism {
    public static void main(String[] args) {
        List<Number> nums = new ArrayList<>();
        nums.add(2);
        nums.add(3.14);
        for (Number number : nums) {
            System.out.println(number.getClass().getName());
        }
    }
}

