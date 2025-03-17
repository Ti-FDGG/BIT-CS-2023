
public class Pair<T> {

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }

    private T first;
    private T second;
}

class PairTest {

    public static void main(String[] args) {
        String[] strs = new String[]{"a", "b", "c"};
        Pair<String> result = minmax(strs);
        System.out.printf("Min:%1$s  Max:%2$s\n",
                result.getFirst(),
                result.getSecond());

    }

    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }
        return new Pair<String>(min, max);
    }
}
