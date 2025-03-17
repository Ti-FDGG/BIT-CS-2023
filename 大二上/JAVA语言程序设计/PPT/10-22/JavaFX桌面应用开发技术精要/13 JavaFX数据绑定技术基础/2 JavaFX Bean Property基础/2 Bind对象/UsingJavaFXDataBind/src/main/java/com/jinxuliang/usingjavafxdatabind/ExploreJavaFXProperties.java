package com.jinxuliang.usingjavafxdatabind;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ExploreJavaFXProperties {

    public static void main(String[] args) {


        //bindAndUnbindOnePropertyToAnother();
        //bidirectionalBinding();
        //bindingLink();

        //fluentApiStyle();
        //useHeronsFormula();
        useHeronsFormula2();
    }

    // 单向绑定
    private static void bindAndUnbindOnePropertyToAnother() {
        //创建两个JavaFX Property
        IntegerProperty intProperty = new SimpleIntegerProperty(1024);
        IntegerProperty otherProperty = new SimpleIntegerProperty(0);
        System.out.println("intProperty.get()=" + intProperty.get());
        System.out.println("otherProperty.get() = " + otherProperty.get());
        System.out.println("Binding otherProperty to intProperty.");

        //在两个JavaFX Property中建立绑定关系,绑定一建立，两者之间的值立即同步
        otherProperty.bind(intProperty);

        //赋值测试
        System.out.println("otherProperty.get() = " + otherProperty.get());
        System.out.println("Calling intProperty.set(7168).");
        intProperty.set(7168);
        System.out.println("otherProperty.get() = " + otherProperty.get());
        //单向绑定，绑定的一方自己不能设置值，以下这句将引发RuntimeException
        //otherProperty.set(999);

        //解绑
        System.out.println("Unbinding otherProperty from intProperty.");
        otherProperty.unbind();

        //再次赋值测试
        System.out.println("otherProperty.get() = " + otherProperty.get());
        System.out.println("Calling intProperty.set(8192).");
        intProperty.set(8192);
        System.out.println("otherProperty.get() = " + otherProperty.get());
    }

    //双向绑定
    private static void bidirectionalBinding() {
        //创建两个JavaFX Property
        System.out.println("Constructing two StringProperty objects.");
        StringProperty prop1 = new SimpleStringProperty("");
        StringProperty prop2 = new SimpleStringProperty("");
        //建立双向绑定
        System.out.println("Calling bindBidirectional.");
        prop2.bindBidirectional(prop1);

        //在绑定双端都进行赋值测试
        System.out.println("Calling prop1.set(\"prop1 says: Hi!\")");
        prop1.set("prop1 says: Hi!");
        System.out.println("prop2.get() returned:");
        System.out.println(prop2.get());

        System.out.println("Calling prop2.set(prop2.get() + \"\\nprop2 says: Bye!\")");
        prop2.set(prop2.get() + "\nprop2 says: Bye!");
        System.out.println("prop1.get() returned:");
        System.out.println(prop1.get());
    }

    //构建单向绑定链
    private static void bindingLink() {
        //创建四个JavaFX Bean Property对象
        IntegerProperty i = new SimpleIntegerProperty(null, "i", 1024);
        LongProperty l = new SimpleLongProperty(null, "l", 0L);
        FloatProperty f = new SimpleFloatProperty(null, "f", 0.0F);
        DoubleProperty d = new SimpleDoubleProperty(null, "d", 0.0);
        System.out.println("Constructed numerical properties i, l, f, d.");
        System.out.println("i.get() = " + i.get());
        System.out.println("l.get() = " + l.get());
        System.out.println("f.get() = " + f.get());
        System.out.println("d.get() = " + d.get());
        //基于四个JavaFX Bean Property对象构建单向绑定链
        l.bind(i);
        f.bind(l);
        d.bind(f);

        System.out.println("Bound l to i, f to l, d to f.");
        System.out.println("i.get() = " + i.get());
        System.out.println("l.get() = " + l.get());
        System.out.println("f.get() = " + f.get());
        System.out.println("d.get() = " + d.get());
        System.out.println("Calling i.set(2048).");
        i.set(2048);
        System.out.println("i.get() = " + i.get());
        System.out.println("l.get() = " + l.get());
        System.out.println("f.get() = " + f.get());
        System.out.println("d.get() = " + d.get());
        //单向绑定链，不允许在中间改变其值，以下这句引发异常：RuntimeException
        //f.set(999.0f);
    }

    //采用级联调用方式，将多个JavaFX Property对象组合起来
    private static void fluentApiStyle() {
        //创建6个IntegerProperty，代表三角形三个点的直角坐标
        IntegerProperty x1 = new SimpleIntegerProperty(0);
        IntegerProperty y1 = new SimpleIntegerProperty(0);
        IntegerProperty x2 = new SimpleIntegerProperty(0);
        IntegerProperty y2 = new SimpleIntegerProperty(0);
        IntegerProperty x3 = new SimpleIntegerProperty(0);
        IntegerProperty y3 = new SimpleIntegerProperty(0);

        //构建三角形面积计算公式：S=1/2[(x1y2-x2y1)+(x2y3-x3y2)+(x3y1-x1y3)]
        final NumberBinding area = x1.multiply(y2)
                .add(x2.multiply(y3))
                .add(x3.multiply(y1))
                .subtract(x1.multiply(y3))
                .subtract(x2.multiply(y1))
                .subtract(x3.multiply(y2))
                .divide(2.0D);

        //格式化输出
        StringExpression output = Bindings.format(
                "For A(%d,%d), B(%d,%d), C(%d,%d), the area of triangle ABC is %3.1f",
                x1, y1, x2, y2, x3, y3, area);

        //设置三点坐标值
        x1.set(0);
        y1.set(0);
        x2.set(6);
        y2.set(0);
        x3.set(4);
        y3.set(3);
        //输出结果
        System.out.println(output.get());

    }

    //基于JavaFX的Binding对象，使用海伦公式计算三角形面积
    private static void useHeronsFormula() {
        DoubleProperty a = new SimpleDoubleProperty(0);
        DoubleProperty b = new SimpleDoubleProperty(0);
        DoubleProperty c = new SimpleDoubleProperty(0);
        //s=(a+b+c)/2
        DoubleBinding s = a.add(b).add(c).divide(2.0D);
        //在满足“两边之和大于第三边”的前提下，用海伦公式计算三角形面积
        final DoubleBinding areaSquared = new When(a.add(b).greaterThan(c)
                .and(b.add(c).greaterThan(a))
                .and(c.add(a).greaterThan(b)))
                //满足条件开始计算
                .then(s.multiply(s.subtract(a))
                        .multiply(s.subtract(b))
                        .multiply(s.subtract(c)))
                .otherwise(0.0D);
        //设定三边值
        a.set(3);
        b.set(4);
        c.set(5);
        System.out.printf("Given sides a = %1.0f, b = %1.0f, and c = %1.0f," +
                        " the area of the triangle is %3.2f\n", a.get(), b.get(), c.get(),
                Math.sqrt(areaSquared.get()));
    }

    //采用自定义Binding的方式使用海伦公式计算三角形面积
    private static void useHeronsFormula2() {
        final DoubleProperty a = new SimpleDoubleProperty(0);
        final DoubleProperty b = new SimpleDoubleProperty(0);
        final DoubleProperty c = new SimpleDoubleProperty(0);
        //重写基类的computeValue()方法，从此Binding对象的三个依赖对象中提取值进行计算
        DoubleBinding area = new DoubleBinding() {
            //使用初始化块，指明三个依赖对象
            {
                super.bind(a, b, c);
            }
            @Override
            protected double computeValue() {
                double a0 = a.get();
                double b0 = b.get();
                double c0 = c.get();
                //使用海伦公式计算三角形面积
                if ((a0 + b0 > c0) && (b0 + c0 > a0) && (c0 + a0 > b0)) {
                    double s = (a0 + b0 + c0) / 2.0D;
                    return Math.sqrt(s * (s - a0) * (s - b0) * (s - c0));
                } else {
                    return 0.0D;
                }
            }
        };

        a.set(3);
        b.set(4);
        c.set(5);
        System.out.printf("Given sides a = %1.0f, b = %1.0f, and c = %1.0f," +
                        " the area of the triangle is %3.2f\n", a.get(), b.get(), c.get(),
                area.get());
    }
}
