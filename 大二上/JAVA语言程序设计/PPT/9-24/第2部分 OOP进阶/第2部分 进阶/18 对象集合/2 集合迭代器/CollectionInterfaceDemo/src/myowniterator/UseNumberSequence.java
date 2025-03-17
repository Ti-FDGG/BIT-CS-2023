package myowniterator;

import java.util.Iterator;

public class UseNumberSequence {
    public static void main(String[] args) {
        var sequence = new NumberSequence(1, 3, 15);
        for (Integer i : sequence) {
            System.out.println(i);
        }
    }
}
