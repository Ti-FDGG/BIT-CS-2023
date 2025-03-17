package cn.edu.bit.cs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Random;

public class JavaBeanTest {

    public static void main(String[] args) {
        testPropertyChangeEvent();
        //testConstrainedProperties();
    }

    private static void testPropertyChangeEvent() {
        MyBean bean = new MyBean();
        //添加事件监听器
        bean.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String info = String.format("属性名:%s,老值:%s,新值:%s",
                        evt.getPropertyName(), evt.getOldValue().toString(),
                        evt.getNewValue().toString());
                System.out.println(info);
            }
        });
        //触发两次“PropertyChange”事件
        bean.setBindableField(100);
        bean.setBindableField(200);
    }

    private static void testConstrainedProperties() {
       // 添加一个“成绩有效性”监听器
        var scoreValidateListener = new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt)
                    throws PropertyVetoException {
                int newValue = Integer.parseInt(evt.getNewValue().toString());
                String propertyName = evt.getPropertyName();
                if (propertyName.equals("score") && (newValue < 0 || newValue > 100)) {
                    throw new PropertyVetoException("有效的成绩在0分与100分之间", evt);
                }
            }
        };
        //挂接JavaBean与监听器
        MyBean bean = new MyBean();
        bean.addVetoableChangeListener(scoreValidateListener);
        try {
            //随机生成一个整数
            int scoreValue = new Random().nextInt(200);
            System.out.println("要设定的成绩为" + scoreValue);
            //只要生成的数值不在[0,100]之间，将抛出异常
            bean.setScore(scoreValue);
        } catch (PropertyVetoException e) {
            System.out.println(e.getMessage());
        }
    }
}
