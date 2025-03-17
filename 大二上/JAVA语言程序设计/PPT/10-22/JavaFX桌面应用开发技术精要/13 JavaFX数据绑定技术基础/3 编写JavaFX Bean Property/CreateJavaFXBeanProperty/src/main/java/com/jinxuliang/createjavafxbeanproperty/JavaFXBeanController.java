package com.jinxuliang.createjavafxbeanproperty;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * 控制器，集成了模型和视图
 *
 * @author JinXuLiang
 */
public class JavaFXBeanController {
    //模型
    private JavaFXBeanModel model;
    //视图
    private JavaFXBeanView view;

    public JavaFXBeanController(JavaFXBeanModel model,
                                JavaFXBeanView view) {
        this.model = model;
        this.view = view;
    }

    //修改整数属性的值
    public void incrementIPropertyOnModel() {
        model.setNum(model.getNum() + 1);
    }

    //修改字符串属性的值
    public void changeStrPropertyOnModel() {
        final String str = model.getStr();
        if (str.equals("Hello")) {
            model.setStr("World");
        } else {
            model.setStr("Hello");
        }
    }

    // 修改颜色属性的值
    public void switchColorPropertyOnModel() {
        Random random = new Random();
        Color color = new Color(
                random.nextDouble(),
                random.nextDouble(),
                random.nextDouble(), 0.8);
        model.setColor(color);
    }
}
