package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    String name;
    int age;
    private List<Student> students = new ArrayList<Student>();

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students)
            if (student.getAverageGrade() == averageGrade)
                return student;
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double max = 0;
        Student result = null;
        for (Student student : students)
            if (student.getAverageGrade() > max)
            {
                max = student.getAverageGrade();
                result = student;
            }
        return result;
    }

    public Student getStudentWithMinAverageGrade() {
        double min = students.get(0).getAverageGrade();
        Student result = null;
        for (Student student : students)
            if (student.getAverageGrade() < min)
            {
                min = student.getAverageGrade();
                result = student;
            }
         return result;
    }

    public void expel (Student student) {
        if (student != null)
            students.remove(student);
    }
}