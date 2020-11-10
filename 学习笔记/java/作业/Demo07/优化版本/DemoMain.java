package com.fanser.text.Demo07;

import java.util.ArrayList;
import java.util.Scanner;

public class DemoMain {
    public static void main(String[] args) {
        System.out.println("输入以下值来实现不同几何图形的相关计算");
        System.out.println("圆：1" + " " + "矩形：2" + " " + "三角形：3");
//        Scanner r = new Scanner(System.in);
//        int s = r.nextInt();
        System.out.println("输入要计算的圆的数量");
        Scanner scanner = new Scanner(System.in);//将要计算的圆的半径全输入到集合R中
        int count = scanner.nextInt();//一次计算的圆的个数为count个
//        switch (s) {
//            case 1:
        System.out.println("进行圆的相关计算");
        System.out.println("====================");
        System.out.println("输入圆的半径:");
        ArrayList<Circle> list = new ArrayList<>();//list集合  用于装实例化后的类
        ArrayList<Float> R = new ArrayList<>();//R集合 用于装输入的r
        //将多个半径r装入集合R中
        for (int i = 0; i < count; i++) {
            float num = scanner.nextInt();
            if (num > 0)                           //判断输入值是否合法
                R.add(num);
            else
                System.out.println("输入值错误");
        }
        //将实例化后的圆类装进list集合中
        for (int i = 0; i < R.size(); i++) {
            Circle circle = new Circle();
            list.add(circle);
        }
        //输出多个圆的面积和周长
        for (int i = 0; i < list.size(); i++) {
            Circle circle = list.get(i);
            String s1 = String.valueOf(i+1);
            System.out.println("圆"+s1);
            System.out.println(" " + circle.area());
            System.out.println("圆"+s1);
            System.out.println(" " + circle.perimeter());
        }
//                break;
//            case 2:
//                System.out.println("进行矩形的相关计算");
//                System.out.println("====================");
//                System.out.println("请输入下列值:" + "length:" + ", width:");
//                Rectangle rectangle = new Rectangle();
//                System.out.println(rectangle.area());
//                System.out.println(rectangle.perimeter());
//                break;
//            case 3:
//                System.out.println("进行三角形的相关计算");
//                System.out.println("====================");
//                System.out.println("请输入三条边的长度：");
//                Triangle triangle = new Triangle();
//                System.out.println(triangle.area());
//                System.out.println(triangle.perimeter());
//                break;
//            default:
//                System.out.println("输入值错误，请重试");
//                break;
//        }
//
    }

}

