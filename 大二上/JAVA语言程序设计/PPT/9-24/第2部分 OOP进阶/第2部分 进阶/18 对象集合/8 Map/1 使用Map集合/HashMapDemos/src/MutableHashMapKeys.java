import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MutableHashMapKeys {

    public static void main(String[] args) {
        final Map<MutableString, String> brokenMap = new HashMap<>();
        final String value = "abc";
        final MutableString key = new MutableString(value);
        //把Key值为abc的对象，加入到HashMap中
        brokenMap.put(key, value);
        //输出：abc-->abc
        System.out.println(key+"-->"+brokenMap.get(key));
        //输出：Map中的所有元素：{abc=abc}
        System.out.println("Map中的所有元素："+brokenMap);
        //好，现在修改key的值
        key.set("def");
        //输出：def-->null
        System.out.println(key+"-->"+brokenMap.get(key));
        //输出：Map中的所有元素：{def=abc}
        System.out.println("Map中的所有元素："+brokenMap);
    }

    private static class MutableString {
        private String value;
        public MutableString(final String value) {
            set(value);
        }
        public String get() {
            return value;
        }
        public void set(final String value) {
            Objects.requireNonNull(value);
            this.value = value;
        }
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final MutableString that = (MutableString) o;
            return value.equals(that.value);
        }
        public int hashCode() {
            return value.hashCode();
        }
        public String toString() {
            return value;
        }
    }
}
