package com.fanser.text.Demo07;

import java.util.Scanner;

public class Circle extends GeometricFigure {
    @Override
    public float perimeter() {
        System.out.print("该圆的周长为:"+" ");
        return (float) (Math.PI*2*r);
    }

    @Override
    public float area() {
        System.out.print("该圆的面积为:"+" ");
        return (float)(Math.PI*r*r);
    }

    Scanner s = new Scanner(System.in);
    int r = s.nextInt();
    public Circle() {

    }
}
