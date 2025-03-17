public class MyData {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increase() {
        value++;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "value=" + value +
                '}' + hashCode();
    }
}

