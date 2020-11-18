package com.fanser.text.Demo10;

import java.util.Arrays;

public class InputArray {
    public static void main(String[] args) {
        int[] a = {100, 200, 300};
        System.out.println(a.length);
        System.out.println(a);
        int[][] b = {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}};
        int max = 0;
        for (int x = 0; x < b.length ;x++){
            for (int y = 0; y < b[x].length; y++){
                System.out.print(b[x][y]);
                if(b[x].length>max){
                    max = b[x].length;
                }
            }
            System.out.println();
        }
        System.out.println("该数组中一维数组的个数为" + max);
        System.out.println(b[4][2]);
        b[4] = a;
        System.out.println(b[4][2]);
    }
}

