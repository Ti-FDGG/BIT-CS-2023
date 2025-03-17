import model.MyData;
import model.MyDataCollection;

public class TestMyDataCollection {
    public static void main(String[] args) {
        var myCollection = new MyDataCollection();
        //向自定义集合中添加新数据
        for (int i = 0; i < 3; i++) {
            myCollection.add(new MyData(i, "info " + i));
        }
        //迭代遍历集合元素
        for (var data : myCollection) {
            System.out.println(data);
        }
    }
}
