# Java学生管理系统

这是一个基于Java的学生管理系统，用于新手学习Java编程

## 需求
采取控制台的方式去书写学生管理系统，实现对学生信息的增删改查
该系统主要功能如下：	

- 添加学生：通过键盘录入学生信息，添加到集合中	
- 删除学生：通过键盘录入要删除学生的学号，将该学生对象从集合中删除	
- 修改学生：通过键盘录入要修改学生的学号，将该学生对象其他信息进行修改	
- 查询学生：通过键盘录入要查询学生的学号，查看该学生的信息
- 退出系统：结束程序
## 分析与实现
### 初始菜单
学生管理系统主界面的搭建步骤

- 用输出语句完成主界面的编写
- 用Scanner实现键盘输入
- 用switch语句完成选择的功能
- 用循环完成功能结束后再次回到主界面
```java
"-------------欢迎来到学生管理系统----------------"
"1：添加学生"
"2：删除学生"
"3：修改学生"
"4：查询学生"
"5：查询所有学生"
"6：退出"
"请输入您的选择:"
```
```java
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
```
### 学生类
属性：id、姓名、年龄、家庭住址
```java
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
```
### 添加功能
键盘录入每一个学生信息并添加，需要满足以下要求：

- id唯一

学生管理系统的添加学生功能实现步骤

- 定义一个方法，接收ArrayList集合 
- 方法内完成添加学生的功能          

①键盘录入学生信息          
②根据录入的信息创建学生对象          
③将学生对象添加到集合中         
④提示添加成功信息 

- 在添加学生的选项里调用添加学生的方法
```java
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
```
### 删除功能
键盘录入要删除的学生id，需要满足以下要求：

- id存在删除
- id不存在，需要提示不存在，并回到初始菜单

学生管理系统的删除学生功能实现步骤

- 定义一个方法，接收ArrayList集合 
- 方法中接收要删除学生的学号 
- 遍历集合，获取每个学生对象 
- 使用学生对象的学号和录入的要删除的学号进行比较,如果相同，则将当前学生对象从集合中删除 
- 在删除学生选项里调用删除学生的方法
```java
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
```
### 修改功能
键盘录入要修改的学生id，需要满足以下要求

- id存在，继续录入其他信息
- id不存在，需要提示不存在，并回到初始菜单

学生管理系统的修改学生功能实现步骤

- 定义一个方法，接收ArrayList集合 
- 方法中接收要修改学生的学号 
- 通过键盘录入学生对象所需的信息，并创建对象 
- 遍历集合，获取每一个学生对象。并和录入的修改学生学号进行比较.如果相同，则使用新学生对象替换当前学生对象 
- 在修改学生选项里调用修改学生的方法
```java
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
```
### 查询功能
打印所有的学生信息，需要满足以下要求

- 如果没有学生信息，提示：当前无学生信息，请添加后再查询
- 如果有学生信息，需要按照以下格式输出。（不用过于纠结对齐的问题）

学生管理系统的查询学生功能实现步骤

- 定义一个方法，接收ArrayList集合 
- 方法内遍历集合，将学生信息进行输出 
- 在查看所有学生选项里调用查看学生方法
```java
id			姓名		年龄		家庭住址
heima001	张三		23		 南京
heima002	李四		24		 北京
heima003	王五		25		 广州
heima004	赵六	 	26		 深圳
```
```java
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
```
### id查重功能
```java
    public static boolean queId(String id, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.getId().equals(id))
                return true;
        }
        return false;
    }
```
### 获取索引功能
```java
    public static int getIndex(String id, ArrayList<Student> students) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }
```
## 完整代码
### 用户类
```java
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

```
### Main
```java
package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
```
# Java学生管理系统升级版
### 需求
为学生管理系统书写一个登陆、注册、忘记密码的功能。
只有用户登录成功之后，才能进入到学生管理系统中进行增删改查操作。
### 分析与实现
#### 登录界面
```java
System.out.println("欢迎来到学生管理系统");
System.out.println("请选择操作1登录 2注册 3忘记密码");
```
```java
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
```
#### 用户类
属性：用户名、密码、身份证号码、手机号码
```java
private String username;
private String password;
private String personID;
private String phoneNumber;
```
#### 注册功能
	1，用户名需要满足以下要求：
		验证要求：
			用户名唯一
			用户名长度必须在3~15位之间 
			只能是字母加数字的组合，但是不能是纯数字
	2，密码键盘输入两次，两次一致才可以进行注册。
	3，身份证号码需要验证。
		验证要求：
			长度为18位
			不能以0为开头
			前17位，必须都是数字
			最为一位可以是数字，也可以是大写X或小写x
	4，手机号验证。
		验证要求：
			长度为11位
			不能以0为开头
			必须都是数字
```java
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

```
#### 登录功能
	1，键盘录入用户名
	2，键盘录入密码
	3，键盘录入验证码
验证要求：
		用户名如果未注册，直接结束方法，并提示：用户名未注册，请先注册
		判断验证码是否正确，如不正确，重新输入
		再判断用户名和密码是否正确，有3次机会
```java
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
```
#### 忘记密码
       1，键盘录入用户名，判断当前用户名是否存在，如不存在，直接结束方法，并提示：未注册
    2，键盘录入身份证号码和手机号码
    3，判断当前用户的身份证号码和手机号码是否一致，
    		如果一致，则提示输入密码，进行修改。
    		如果不一致，则提示：账号信息不匹配，修改失败。
```java
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

```
#### 验证码规则
	长度为5
	由4位大写或者小写字母和1位数字组成，同一个字母可重复
	数字可以出现在任意位置
比如：
	aQa1K
```java
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
```
