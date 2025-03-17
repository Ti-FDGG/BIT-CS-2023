package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//支持迭代的集合，必须实现Iterable<T>接口
public class MyDataCollection implements Iterable<MyData>{

    //内部封装真正的数据集合
    private final List<MyData> dataList=new ArrayList<>();

    @Override
    public Iterator<MyData> iterator() {
        //偷懒，直接使用JDK为ArrayList准备好的iterator
        return dataList.iterator();
    }

    public void add(MyData data){
        dataList.add(data);
    }
}
