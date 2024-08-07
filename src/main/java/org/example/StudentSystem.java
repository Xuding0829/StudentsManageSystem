package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void StartSystem() {
        ArrayList<Student> students = new ArrayList<>();
        while(true) {
            System.out.println("-----------------欢迎来到学生管理系统-------------------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:查询所有学生");
            System.out.println("6:退出");
            System.out.println("请输入您的选择：");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> AddStudent(students);
                case "2" -> DelStudent(students);
                case "3" -> UpdStudent(students);
                case "4" -> SelStudent(students);
                case "5" -> SelAllStudent(students);
                case "6" -> Exit();
                default -> Other();
            }
        }
    }
    public static void AddStudent(ArrayList<Student> students) {
        Student student = new Student();
        String id = null;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("请输入学生id");
            id = scanner.next();

            boolean flag = queId(id, students);
            if(flag) {
                System.out.println("当前id已存在,请重新输入");
            } else {
                student.setId(id);
                break;
            }
        }

        String name = null;
        System.out.println("请输入学生姓名");
        name = scanner.next();
        student.setName(name);

        String age = null;
        System.out.println("请输入学生年龄");
        age = scanner.next();
        student.setAge(age);

        String address = null;
        System.out.println("请输入学生家庭地址");
        address = scanner.next();
        student.setAddress(address);

        students.add(student);

        System.out.println("当前学生信息已添加");
    }

    public static void DelStudent(ArrayList<Student> students) {
        System.out.println("请输入待删除学生的id");

        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();

        boolean flag = queId(id, students);
        if(flag) {
            int index = getIndex(id, students);
            students.remove(index);
            System.out.println("该学生信息已删除");
        } else {
            System.out.println("请输入正确的学生id");
        }
    }

    public static void UpdStudent(ArrayList<Student> students) {
        System.out.println("请输入待添加学生的id");

        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        boolean flag = queId(id, students);
        if(flag) {
            int index = getIndex(id, students);
            String name = null;
            System.out.println("请输入学生姓名");
            name = scanner.next();
            students.get(index).setName(name);

            String age = null;
            System.out.println("请输入学生年龄");
            age = scanner.next();
            students.get(index).setAge(age);

            String address = null;
            System.out.println("请输入学生家庭地址");
            address = scanner.next();
            students.get(index).setAddress(address);
        }
    }

    public static void SelStudent(ArrayList<Student> students) {
        System.out.println("请输入待查询学生的id");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        boolean flag = queId(id, students);
        if(flag) {
            int index = getIndex(id, students);
            System.out.println("学生id   姓名   年龄   地址");
            System.out.println(students.get(index));
        }
    }

    public static void SelAllStudent(ArrayList<Student> students) {
        System.out.println("学生id  姓名   年龄   地址");
        for(Student student : students) {
            System.out.println(student);
        }
    }

    public static void Exit() {
        System.out.println("退出");
        System.exit(0);
    }

    public static void Other() {
        System.out.println("没有这个选项");
    }

    public static boolean queId(String id, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.getId().equals(id))
                return true;
        }
        return false;
    }

    public static int getIndex(String id, ArrayList<Student> students) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }
}
