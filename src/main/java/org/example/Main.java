package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        while(true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作 1登录 2注册 3忘记密码 4退出");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> Login(users);
                case "2" -> Register(users);
                case "3" -> Forget(users);
                case "4" -> Exit();
                default -> Other();
            }
        }
    }

    public static void Login(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();

        boolean flag = QueUsername(username, users);
        if(!flag){
            System.out.println("用户名不存在,请注册~");
            return;
        }

        System.out.println("请输入密码");
        String password = scanner.next();

        while(true) {
            String rightcode = GetCode();
            System.out.println("当前验证码为: " + rightcode);
            System.out.println("请输入验证码");
            String input = scanner.next();
            if(rightcode.equals(input)) {
                System.out.println("验证码正确~");
                break;
            } else {
                System.out.println("请输入正确的验证码~");
            }
        }

        User user = new User(username, password, null, null);
        flag = QueUser(user, users);
        if(flag) {
            System.out.println("登录成功~");
            StudentSystem ss = new StudentSystem();
            ss.StartSystem();
        } else {
            System.out.println("登陆失败,用户名或密码错误");
             // 可添加功能: 失败多次后锁定账号
        }
    }

    public static void Register(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        String username = null;
        while(true) {
            System.out.println("请输入用户名,用户名长度要求在3-15之间,由数字和英文字母组成~");
            username = scanner.next();
            boolean flag = CheckUsername(username);
            if(!flag) {
                System.out.println("用户名格式不满足条件，需要重新输入");
                continue;
            }

            flag = QueUsername(username, users);
            if(flag) {
                System.out.println("用户名已存在,请重新输入");
            } else {
                System.out.println("用户名可使用");
                break;
            }
        }

        String password = null;
        while(true) {
            System.out.println("请输入密码");
            password = scanner.next();
            System.out.println("请再次输入密码");
            String password2 = scanner.next();
            if(password.equals(password2)) {
                System.out.println("两次密码一致，继续录入其他数据");
                break;
            } else {
                System.out.println("两次密码不一致，请重新输入");
            }
        }

        String phoneNumber = null;
        while(true) {
            System.out.println("请输入手机号");
            phoneNumber = scanner.next();
            boolean flag = CheckPhoneNumber(phoneNumber);
            if(flag) {
                System.out.println("手机号格式正确,继续录入其他信息");
                break;
            } else {
                System.out.println("手机号码格式有误，请重新输入");
            }
        }

        String personId = null;
        while(true) {
            System.out.println("请输入身份id");
            personId = scanner.next();
            boolean flag = CheckPersonId(personId);
            if(flag) {
                System.out.println("身份id正确");
                break;
            } else {
                System.out.println("身份id格式有误，请重新输入");
            }
        }

        User u = new User(username, password, personId, phoneNumber);
        users.add(u);
        System.out.println("注册成功~");

//        for(User user : users) {
//            System.out.println(user);
//        }
    }

    public static boolean CheckUsername(String username) {
        int length = username.length();
        if(length < 3 || length > 15) {
            return false;
        }

        for(int i = 0; i < length; i++) {
            char ch = username.charAt(i);
            if(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                continue;
            } else {
                return false;
            }
        }

        int cnt = 0;
        for(int i = 0; i < length; i++) {
            char ch = username.charAt(i);
            if(ch >= '0' && ch <= '9')
                cnt++;
        }
        if(cnt == 0)
            return false;
        return true;
    }

    @Test
    public void CheckUsernameTest() {
        System.out.println(CheckUsername("xuding1234"));
        System.out.println(CheckUsername("xudi--=ng1234"));
        System.out.println(CheckUsername("xu%%%4"));
    }

    public static boolean CheckPhoneNumber(String phoneNumber) {
        int len = phoneNumber.length();
        if(len != 11)
            return false;

        for(int i = 0; i < len; i++) {
            char ch = phoneNumber.charAt(i);
            if(i == 0 && ch != '1')
                return false;
            if(ch > '9' || ch < '0')
                return false;
        }
        return true;
    }

    @Test
    public void CheckPhoneNumber() {
        System.out.println(CheckPhoneNumber("xuding1234"));
        System.out.println(CheckPhoneNumber("13111111111"));
        System.out.println(CheckPhoneNumber("133433332"));
    }

    public static boolean CheckPersonId(String personId) {
        int len = personId.length();
        if(len != 18)
            return false;

        for(int i = 0; i < len - 1; i++) {
            char ch = personId.charAt(i);
            if(i == 0 && ch == '0')
                return false;
            if(ch > '9' || ch < '0')
                return false;
        }

        char ch = personId.charAt(len - 1);
        if((ch >= '0' && ch <= '9') || (ch == 'x' || ch == 'X'))
            return true;
        else
            return false;
    }


    @Test
    public void CheckPersonId() {
        System.out.println(CheckPersonId("xuding1234"));
        System.out.println(CheckPersonId("13111111111"));
        System.out.println(CheckPersonId("330682200408290911"));
        System.out.println(CheckPersonId("3306820000000000t"));
    }

    public static void Forget(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();

        boolean flag = QueUsername(username, users);
        if(!flag) {
            System.out.println("当前用户不存在");
            return;
        }

        System.out.println("请输入身份id");
        String personId = scanner.next();
        System.out.println("请输入手机号");
        String phoneNumber = scanner.next();

        User u = new User();
        for(User user : users) {
            if(user.getUsername().equals(username) && user.getPersonId().equals(personId) && user.getPhoneNumber().equals(phoneNumber)) {
                u = user;
                break;
            }
        }

        String password = null;
        while(true) {
            System.out.println("请输入新的密码");
            password = scanner.next();
            System.out.println("请再次确认你的密码");
            String password2 = scanner.next();

            if(password.equals(password2)) {
                u.setPassword(password);
                System.out.println("修改成功");
                break;
            } else {
                System.out.println("两次密码不一致,请重新输入");
            }
        }
    }

    public static void Exit() {
        System.out.println("谢谢使用,再见~");
        System.exit(0);
    }

    public static void Other() {
        System.out.println("请输入正确的指令~");
    }

    public static boolean QueUsername(String username, ArrayList<User> users) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean QueUser(User user, ArrayList<User> users) {
        for(User user1 : users) {
            if(user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public static String GetCode() {
        ArrayList<Character> ch = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            ch.add((char) ('A' + i));
            ch.add((char) ('a' + i));
        }

        for(int i = 0; i < 10; i++) {
            ch.add((char) ('0' + i));
        }

        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            int index = random.nextInt(ch.size());
            char c = ch.get(index);
            code.append(c);
        }
        return code.toString();
    }

    @Test
    public void CodeTest() {
        System.out.println(GetCode());
    }
}