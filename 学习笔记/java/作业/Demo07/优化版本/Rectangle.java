package com.fanser.text.Demo07;

import java.util.Scanner;

public class Rectangle extends GeometricFigure{

    @Override
    public float perimeter() {
        System.out.println("接下来进行周长计算");
        System.out.print("该矩形的周长为:"+" ");
        return length*2 + width*2;
    }

    @Override
    public float area() {
        System.out.println("接下来进行面积计算");

        System.out.print("该矩形的面积为:"+" ");
        return length*width;
    }
    Scanner l = new Scanner(System.in);
    int length = l.nextInt();
    Scanner w = new Scanner(System.in);
    int width = w.nextInt();
}
