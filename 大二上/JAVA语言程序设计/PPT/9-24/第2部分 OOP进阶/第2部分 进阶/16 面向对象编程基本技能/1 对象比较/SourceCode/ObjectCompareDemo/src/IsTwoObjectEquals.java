
class MyClass {

    public int InnerValue;
    public String InnerString;

    public boolean equals(Object obj) {
        boolean result = obj instanceof MyClass;
        if (!result) {
            return false;
        } else {
            MyClass other = (MyClass) obj;
            return (other.InnerValue == this.InnerValue)
                    && (other.InnerString.equals(this.InnerString));
        }

    }
}

public class IsTwoObjectEquals {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.InnerString = "Hello";
        obj.InnerValue = 100;

        MyClass other = new MyClass();
        other.InnerString = "Hello";
        other.InnerValue = 100;

        System.out.println(obj.equals(other));
    }

}
