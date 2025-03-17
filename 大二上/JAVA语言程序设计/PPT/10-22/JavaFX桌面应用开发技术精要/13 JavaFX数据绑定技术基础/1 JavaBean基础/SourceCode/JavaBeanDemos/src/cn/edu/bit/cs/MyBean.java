package cn.edu.bit.cs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class MyBean {

	// JavaBean属性 = 私有字段 + get方法 + set方法

	private int intField;
	public int getIntField() {
		return intField;
	}
	public void setIntField(int intField) {
		this.intField = intField;
	}

	// boolean类型的字段，其Get方法格式为：isFieldName()
	private boolean running;
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}

	// 支持索引的属性
	private int[] values = new int[] { 1, 2, 3, 4 };
	public int[] getValues() {
		return values;
	}
	public void setValues(int[] values) {
		this.values = values;
	}
	public int getValues(int index) {
		return values[index];
	}
	public void setValues(int index, int value) {
		values[index] = value;
	}

	// 支持绑定的属性
	private int bindableField = 0;
	// 用于触发事件
	private PropertyChangeSupport listeners =
			new PropertyChangeSupport(this);
	// 绑定属性的get/set方法
	public int getBindableField() {
		return bindableField;
	}
	public void setBindableField(int bindableField) {
		int oldBindableField = this.bindableField;
		this.bindableField = bindableField;
		// 触发“属性值更改”事件
		listeners.firePropertyChange("bindableField",
				oldBindableField, bindableField);
	}
	// 添加和移除属性改变事件监听器
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	public void removePropertyChangListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	// 约束属性
	private int score = 0;
	private VetoableChangeSupport vetoListeners =
			new VetoableChangeSupport(this);
	//约束属性的get/set方法
	public int getScore() {
		return score;
	}
	public void setScore(int score) throws PropertyVetoException {
		int oldValue = this.score;
		//通知“投票监听器”进行“投票”，不同意者抛出PropertyVetoException异常
		vetoListeners.fireVetoableChange("score", oldValue, score);
		this.score = score;
	}
	//添加与删除“投票监听器”
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoListeners.addVetoableChangeListener(listener);
	}
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoListeners.removeVetoableChangeListener(listener);
	}

}
