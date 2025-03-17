package serializesharedobject;


import model.Person;

public class Teacher
        implements java.io.Serializable {
    private String name;
    private Person student;

    public Teacher(String name, Person student) {
        this.name = name;
        this.student = student;
    }

    @Override
    public String toString() {
        return name + "的学生是" + student.getName();
    }

    //region getter and setter
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Person getStudent() {
        return this.student;
    }
//endregion
}
