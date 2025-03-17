import java.util.ArrayList;
import java.util.List;

public class GenericWithWildcards {
    public static void main(String[] args) {

        //创建两个泛型集合
        List<Parent> parentList = new ArrayList<>();
        parentList.add(new Parent());
        //Child对象可以加入Parent集合中
        parentList.add(new Child());

        List<Child> childList = new ArrayList<>();
        //Parent对象不能加放到Child集合中
        //childList.add(new Parent());
        childList.add(new Child());

        //以下代码无法通过编译
        //parentList = childList;

        //以下代码是正确的！
        //使用wildCard定义的集合，可以引用任何一个泛型集合对象
        List<?> wildCardList = parentList;
        wildCardList = childList;
        wildCardList = new ArrayList<String>();
        //可以给“?”添加约束,限制接收的集合对象必须是Parent的子类
        //现在，这个变量就可以接收“父“与”子“类型的泛型集合类型了
        List<? extends Parent> classExtendsParentList = parentList;
        classExtendsParentList = childList;
        //以下这句代码将无法编译，因为String没有派生自Parent，不满足泛型约束
        //classExtendsParentList = new ArrayList<String>();

        doSomethingWithList(parentList);
        doSomethingWithList(childList);

        printList(parentList);
        printList(childList);


    }

    //使用wildcard，统一处理子类和父类集合
    static void doSomethingWithList(List<? extends Parent> parents) {
        for (var p : parents) {
            p.working();
        }
    }

    //使用wildcard，输出任何一种类型的集合内容
    static void printList(List<?> list) {
        for (var item : list)
            System.out.println(item);
    }


}

class Parent {
    void working() {
        System.out.println("Parent working");
    }
}

class Child extends Parent {
    @Override
    void working() {
        System.out.println("Child working");
    }
}
