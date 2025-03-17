

import java.util.Random;

public class UseMyHashSet {

    public static void main(String[] args)
            throws Exception {
        var myHashSet = new MyHashSet<Student>();
        Student stu = null;
        for (int i = 0; i < 5; i++) {
            //创建一个示例元素
            stu = createStudentObject();
            myHashSet.insert(stu);
            //相同元素追加两次，第二次不会成功
            myHashSet.insert(stu);
        }
        System.out.println(myHashSet);
        //返回true
        System.out.println(myHashSet.contains(stu));
    }

    private static Random ran = new Random();

    private static Student createStudentObject() {
        int ranValue = ran.nextInt(50);
        float gpa = (float) ranValue / 10;
        Student student = new Student("Student" + ranValue, gpa);
        return student;
    }
}
