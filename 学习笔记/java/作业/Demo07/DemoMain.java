package com.fanser.text.Demo07;

import java.util.Scanner;

public class DemoMain {
    public static void main(String[] args) {
        System.out.println("输入以下值来实现不同几何图形的相关计算");
        System.out.println("圆：1"+" "+"矩形：2"+" "+"三角形：3");
        Scanner r = new Scanner(System.in);
        int s = r.nextInt();
            switch (s) {
                case 1:
                    System.out.println("进行圆的相关计算");
                    System.out.println("====================");
                    System.out.println("输入圆的半径:");
                    Circle circle = new Circle();
                    System.out.println(circle.area());
                    System.out.println(circle.perimeter());
                    break;
                case 2:
                    System.out.println("进行矩形的相关计算");
                    System.out.println("====================");
                    System.out.println("请输入下列值:" + "length:" + ", width:");
                    Rectangle rectangle = new Rectangle();
                    System.out.println(rectangle.area());
                    System.out.println(rectangle.perimeter());
                    break;
                case 3:
                    System.out.println("进行三角形的相关计算");
                    System.out.println("====================");
                    System.out.println("请输入三条边的长度：");
                    Triangle triangle = new Triangle();
                    System.out.println(triangle.area());
                    System.out.println( triangle.perimeter());
                    break;
                default:
                    System.out.println("输入值错误，请重试");
                    break;
            }
    }
}
