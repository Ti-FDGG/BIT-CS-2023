package com.jinxuliang.createjavafxbeanproperty;

//测试MVC模式是否工作正常
public class JavaFXBeanMVCTest {
	
	public static void main(String[] args) {
		//创建模型和视图对象，将其“注入到控制器中"
		JavaFXBeanModel model = new JavaFXBeanModel();
		JavaFXBeanView view = new JavaFXBeanView(model);
		JavaFXBeanController controller =
				new JavaFXBeanController(model, view);
		
		//控制器修改模型对象的属性值，视图对象将得到通知
		controller.incrementIPropertyOnModel();
		controller.changeStrPropertyOnModel();
		controller.switchColorPropertyOnModel();
		
	}
}

