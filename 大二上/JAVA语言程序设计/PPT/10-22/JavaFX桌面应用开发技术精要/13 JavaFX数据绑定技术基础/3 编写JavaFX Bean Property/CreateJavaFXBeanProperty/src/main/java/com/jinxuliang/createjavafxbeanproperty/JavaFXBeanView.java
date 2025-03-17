package com.jinxuliang.createjavafxbeanproperty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

/**
 * 代表视图类，在实际开发中，它封装用户可视的各种UI控件
 *
 * @author JinXuLiang
 */
public class JavaFXBeanView {
    //包容的模型对象
    private JavaFXBeanModel model;

    public JavaFXBeanView(JavaFXBeanModel model) {
        this.model = model;
        hookupChangeListeners();
    }

    //当Model的属性值更改时，此方法可以实现UI界面的刷新
    private void hookupChangeListeners() {
        model.numProperty().addListener(
                (observable, oldValue, newValue)
                        -> System.out.println("num属性值更改: 老值 = " + oldValue
                        + ", 新值 = " + newValue));

        model.strProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> System.out.println("str属性值更改: 老值 = " + oldValue
                        + ", 新值 = " + newValue));

        model.colorProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> System.out.println("color属性值更改: 老值 = " + oldValue
                        + ", 新值 = " + newValue));

    }
}
