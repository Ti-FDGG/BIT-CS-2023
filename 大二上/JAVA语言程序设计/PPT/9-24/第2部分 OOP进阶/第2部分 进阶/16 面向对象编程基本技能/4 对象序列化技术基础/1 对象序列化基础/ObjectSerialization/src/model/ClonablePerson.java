package model;


public class ClonablePerson
        extends SerialCloneableBase {
    private String name;
    private int age;
    public ClonablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //region "getter and setter"
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    //endregion

    @Override
    public String toString() {
        return name + "有" + age + "岁";
    }
}
