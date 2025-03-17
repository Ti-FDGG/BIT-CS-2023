package com.jinxuliang.model;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
    private String name;
    private double gpa;
    private int id;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(String name, double gpa, int id) {
        this.name = name;
        this.gpa = gpa;
        this.id = id;
    }

    public static List<Student> getExampleStudents() {
        Supplier<Student> supplier = () -> {
            double gpa = Math.random() * 4;
            int id = new Random().nextInt(100);
            return new Student("Student" + id, gpa, id);
        };
        var students = Stream.generate(supplier).limit(10)
                .collect(Collectors.toList());
        return students;
    }
}
