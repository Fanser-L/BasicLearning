
## Static
专用于class内部，Static（静态），表示共同使用的数据
简单来举个例子
有这么一个类Class ,内部都是一个班级学生的信息
那么代码上就可以这样书写
```java
package com.fanser.day02.demo01;

/*
回忆一下，标准类有哪些特征
1.成员变量用private修饰
2.无参构造器
3.getter和setter方法       //可以使用Alt + Ins 键 来快捷输入
4.全参构造器（有参构造器）
* */
public class Student {
    private String name;
    private int age;
    static String room; //所在教室，注意这里使用的是static  表示本类总全部成员均可调用这部分内容

    public Student() {

    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
```java 实例化
package com.fanser.day02.demo01;

public class StaticField {
    public static void main(String[] args) {

        Student one = new Student("tom", 20);
        Student two = new Student("jerry", 19);
        one.room = "cs.01";
//        two.room = "cs.02";
//        这里发现，如果写入这部分代码，连同上面对one的room的写入的room一样遭到了修改
        System.out.println("姓名：" + one.getName() + "年龄：" + one.getAge()+"教室："+one.room);
        System.out.println("姓名：" + two.getName() + "年龄：" + two.getAge()+"教室："+two.room);
        //结果可知  输出的教室其实是同一班的同学，说明每个对象都能对static关键字修饰的变量进行修改


    }
}
```
静态方法也是一样的
但是调用静态方法的方法可以通过对象来直接调用或者通过类来进行调用。
一般来说，用对象来调用静态方法虽然可行，但是不推荐这样使用
而用类名来调用才是正常使用方法