package com.jinxuliang.createjavafxbeanproperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//展示延迟初始化JavaFX属性的实现方法
public class JavaFXBeanModelLazy {
	//指定一个默认值
    private static final String DEFAULT_STR = "";
    private StringProperty str;

    //尝试读取属性值，如果此属性己经创建，则返回其设置值
    //否则，返回默认值
    public final String getStr() {
        if (str != null) {
            return str.get();
        } else {
            return DEFAULT_STR;
        }
    }

    //仅当赋值时才创建JavaFX bean属性对象
    public final void setStr(String str) {
        if ((this.str != null) || !(str.equals(DEFAULT_STR))) {
        	//调用strProperty()方法实例化JavaFX bean 属性对象
        	strProperty().set(str);
        }
    }
    //此方法负责创建JavaFX bean属性对象，并且保证只创建一次
    public StringProperty strProperty() {
        if (str == null) {
        	System.out.println("JavaFX Bean属性对象真正地创建！");
            str = new SimpleStringProperty(this, "str", DEFAULT_STR);
        }
        return str;
    }
    
    //测试延迟创建的JavaFX Bean属性
    public static void main(String[] args) {
    	
		JavaFXBeanModelLazy lazy=new JavaFXBeanModelLazy();
		System.out.println(lazy.getStr());
		System.out.println("第一次赋值：Hello");
		lazy.setStr("Hello");
		System.out.println("属性值："+lazy.getStr());
		
		System.out.println("\n第二次赋值：World");
		lazy.setStr("World");
		System.out.println("属性值："+lazy.getStr());
		
		System.out.println("\n第三次赋值：!");
		lazy.setStr("!");
		System.out.println("属性值："+lazy.getStr());
	}
    
}


