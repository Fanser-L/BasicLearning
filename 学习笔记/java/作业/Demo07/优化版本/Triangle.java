package com.fanser.text.Demo07;

import java.util.Scanner;

public class Triangle extends GeometricFigure {

    @Override
    public float perimeter() {
        for (int i = 0; i < 1; ) {
            System.out.println("进行周长计算");
            System.out.println("周长为：");
            if (a+b<=c||a+c<=b||b+c<=a) {
                System.out.println("不能构成三角形，重新输入");
            } else
                i++;
        }
        //如果不能组成三角形返回0，表示错误，能组成三角形则返回周长值
        if (a+b<=c||a+c<=b||b+c<=a) {
            return 0;
        } else {
            return a+b+c;
        }
    }

    @Override
    public float area() {
        for (int i = 0; i < 1; ) {
            System.out.println("进行面积计算");
            System.out.println("面积为:");
            //判断能否组成三角形
            if (a+b<=c||a+c<=b||b+c<=a) {
                System.out.println("不能构成三角形，重新输入");
            } else
                i++;
        }
        //如果不能组成三角形返回0，表示错误，能组成三角形则返回周长值
        if (a+b<=c||a+c<=b||b+c<=a) {
            return 0;
        } else {
            float p = (float) ((a+b+c) / 2.0);
            return (float) Math.sqrt(p * (p - a) * (p - b) * (p - c));//海伦公式
        }
    }

    Scanner A = new Scanner(System.in);
    private final int a = A.nextInt();
    Scanner B = new Scanner(System.in);
    private final int b = B.nextInt();
    Scanner C = new Scanner(System.in);
    private final int c = C.nextInt();

}
