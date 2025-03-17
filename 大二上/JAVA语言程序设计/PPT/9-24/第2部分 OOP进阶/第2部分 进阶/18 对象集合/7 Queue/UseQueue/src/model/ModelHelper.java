package model;

import java.util.Random;

public class ModelHelper {
    private static Random ran=new Random();
    public static MyClass createMyClass(){
        var obj=new MyClass();
        int ranValue=ran.nextInt(100);
        obj.setNum(ranValue);
        obj.setInfo("info "+ranValue);
        return obj;
    }
}
