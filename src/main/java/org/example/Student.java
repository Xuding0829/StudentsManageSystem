package org.example;

import java.util.Objects;

public class Student {
    private String sid;            // 学生id
    private String name;           // 学生姓名
    private String age;            // 学生年龄
    private String address;        // 学生所在地

    public Student(String id, String name, String age, String address) {
        this.sid = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Student() {

    }

    public String getId() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sid, student.sid) && Objects.equals(name, student.name) && Objects.equals(age, student.age) && Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name, age, address);
    }

    @Override
    public String toString() {
        return sid + "   " + name + "  " + age + "   " + address;
    }
}
