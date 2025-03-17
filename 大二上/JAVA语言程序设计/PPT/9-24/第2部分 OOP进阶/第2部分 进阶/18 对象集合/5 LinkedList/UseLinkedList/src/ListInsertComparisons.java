import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListInsertComparisons {

    public static long insertMid(List<String> list, List<String> more) {
        int target = list.size() / 2;
        long start = System.nanoTime();

        for (String s : more) {
            list.add(target++, s);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public static long insertIter(List<String> list, List<String> more) {
        int target = list.size() / 2;
        long start = System.nanoTime();
        ListIterator<String> iter = list.listIterator(target);
        for (String s : more) {
            iter.add(s);
        }
        long end = System.nanoTime();
        return end - start;
    }
    
    public static void main(String[] args) {
        final int COUNT = 250_000;
        String [] items = new String[COUNT];
        for (int i = 0; i < COUNT; i++) {
            items[i] = "" + i;
        }
        List<String> starting = Arrays.asList(items);
        List<String> ll = new LinkedList<>();
        ll.addAll(starting);
        System.out.printf("Time to insert 250K in middle of linked list"
                + "%,12.6f secs\n", insertMid(ll, starting) / 1_000_000_000.0);
        
        ll = new LinkedList<>();
        ll.addAll(starting);
        System.out.printf("Time to insert 250K using ListIterator"
                + "%,12.6f secs\n", insertIter(ll, starting) / 1_000_000_000.0);
    }
}
