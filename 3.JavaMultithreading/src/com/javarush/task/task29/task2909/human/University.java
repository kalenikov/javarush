package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University extends Human {
    private List<Student> students;
    private String name;
    private int age;

    public University(String name, int age) {
        super(name, age);
        this.students = new ArrayList<>();
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student st : students) {
            if (st.getAverageGrade() == averageGrade) {
                return st;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student student = null;
        double maxAverageGrade = 0;
        for (Student st : students) {
            if (maxAverageGrade < st.getAverageGrade()) {
                maxAverageGrade = st.getAverageGrade();
                student = st;
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        Student student = null;
        double minAverageGrade = 10;
        for (Student st : students) {
            if (minAverageGrade > st.getAverageGrade()) {
                minAverageGrade = st.getAverageGrade();
                student = st;
            }
        }
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}