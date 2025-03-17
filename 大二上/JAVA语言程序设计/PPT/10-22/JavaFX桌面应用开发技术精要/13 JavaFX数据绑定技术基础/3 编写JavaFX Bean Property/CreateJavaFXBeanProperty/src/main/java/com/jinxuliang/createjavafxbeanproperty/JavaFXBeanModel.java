package com.jinxuliang.createjavafxbeanproperty;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

/**
 * 展示自定义JavaFX Bean Property的基本方法
 * 此类同时可以作为JavaFX MVC应用中的数据源
 *
 * @author JinXuLiang
 */
public class JavaFXBeanModel {

    //整数型JavaFX Bean属性
    private IntegerProperty num =
            new SimpleIntegerProperty(this, "i", 0);

    public final int getNum() {
        return num.get();
    }

    public final void setNum(int num) {
        this.num.set(num);
    }

    public IntegerProperty numProperty() {
        return num;
    }

    //字符串型JavaFX Bean属性
    private StringProperty str =
            new SimpleStringProperty(this, "str", "Hello");

    public final String getStr() {
        return str.get();
    }

    public final void setStr(String str) {
        this.str.set(str);
    }

    public StringProperty strProperty() {
        return str;
    }

    //对象型JavaFX Bean属性
    private ObjectProperty<Color> color =
            new SimpleObjectProperty<>(
                    this, "color", Color.BLACK);

    public final Color getColor() {
        return color.get();
    }

    public final void setColor(Color color) {
        this.color.set(color);
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }


    @Override
    public String toString() {
        return "JavaFXBeanModelExample [i=" + num.get()
                + ", str=" + str.get() + ", color=" + color.get() + "]";
    }

    public static void main(String[] args) {
        System.out.println("创建两个JavaFXBeanModel对象：obj1和obj2\n");
        JavaFXBeanModel obj1 = new JavaFXBeanModel();
        JavaFXBeanModel obj2 = new JavaFXBeanModel();

        //构建一个监听器对象
        ChangeListener listener = (prop, oldValue, newValue) -> {
            System.out.println(prop.getClass().getSimpleName() + "属性值改变：老值["
                    + oldValue + "],新值[" + newValue + "]");
        };

        System.out.println("为obj2的三个属性挂接ChangeListener");
        //监听相应属性值的改变
        obj2.num.addListener(listener);
        obj2.str.addListener(listener);
        obj2.color.addListener(listener);

        System.out.println("\n将obj1的三个属性绑定到obj2的相应属性上。\n");
        obj1.num.bind(obj2.num);
        obj1.str.bind(obj2.str);
        obj1.color.bind(obj2.color);

        System.out.println("现在设置obj2的值：");
        obj2.setNum(100);
        obj2.setStr("Modified");
        obj2.setColor(Color.RED);
        System.out.println("\n现在，obj2的值为：" + obj2);

        System.out.println("\n因为obj1绑定到了obj2，所以obj2的属性值一改，obj1的值也同步更新,其值为：");
        System.out.println(obj1);


    }
}
