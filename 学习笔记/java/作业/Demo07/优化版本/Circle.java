package com.fanser.text.Demo07;

/*
   目标：
   1.能够输入多次
   2.能够判断输入值是否合法
   3.一次运行，能实例化多个圆类对象
   4.并且计算多个圆的周长和面积
   5.能够进行比较，比较多个圆之间大小（应该是可控制的选择性比较）

   分步化实现目标：
   1.键盘输入半径                             Scanner
   2.对半径进行赋值                            赋值语句
   3.判断输入值是否合法                          if判断
   4.输入多个半径进行多个圆的计算                    循环语句
   （1）将半径存到数组中，方便调用半径
   （2）从数组中调用每个值
   5.对这多个圆进行实例化
   （1）调用数组中的值放入到构造器中，构造多个对象
   （2）将实例化的对象装入ArrayList集合中，方便接下来的比较操作
   */
public class Circle extends GeometricFigure {
    @Override
    public float perimeter() {
        System.out.print("该圆的周长为:" + " ");
//        return (float) (Math.PI * 2 * DemoMain.class.R);
        return (float) Math.PI;
    }

    @Override
    public float area() {
        System.out.print("该圆的面积为:" + " ");
//        return (float) (Math.PI * r * r);
        return (float) Math.PI;
    }

}
