## 1、整数除法

> 输入两个[int](https://so.csdn.net/so/search?q=int&spm=1001.2101.3001.7020)型整数，它们进行除法计算并返回商，**要求不得使用乘号\*，除号/以及求余符号%**，当发出溢出时，返回最大的整数值。假设除数不为0。例如，输入15和2，输出15/2，即7。
> （注意：int型范围是-2 ^31~-2 ^31-1）



首先要求不能使用乘号除号和求余符号，那就基本代表只能使用加减法了。另外int型的整数的大小，存在边界问题，超出边界的情况如何解决？

负数可以为-2^31,但是正数情况却不能为2^31，所以要先排除-2^31/-1这种情况，单独拎出来排除即可。

基本可以确定是使用递归的办法来不断的减被除数来进行求商。

考虑每次用被除数减去除数的计算方式来计算减的次数，当无法减的时候再进行统计减的次数，但有可能出现除数很小但是被除数远远大于除数的情况，于是为了提高效率，通过比较被除数与除数的2^n，再来进行减的操作，而商的值可以通过多次加n的值来计算商的值。

对上面步骤稍做调整，上例子比较直观，15/2，15不仅大于2，而且大于2的2倍，2的4倍，但小于2的8倍也就是16，于是让15减去8，因为被减去的是8也就是那个2的四倍，所以这一部分的商就是4，接下来对剩余的7和除数2进行比较，7大于2，大于2的2倍，但小于2的4倍，因此减去4也就是2的2倍得3，这一部分的商则是2，再接下来对剩余的3和除数2比较，发现3大于2，小于2的2倍，因此减去2的一倍得1，这一部分的商是1，最后剩的1比除数2小，所以1是余数，再把每一部分的商合并就可以求出商是多少了。

```java
package com.fanser.integer;
// 整数最大值为 -2的31次方 到 2的31次方-1
public class Division {
    public  int divide(int dividend, int divisor) {
        if (dividend == 0x80000000 && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 2;
        //将输入的值都倒置为负值，负值不会有溢出
        if (dividend > 0) {
            negative--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative--;
            divisor = -divisor;
        }
        int result =0;
        result = divideCore(dividend, divisor);
        return negative == 1 ? -result : result;
    }
// 判断除数与被除数的大小，为了更快计算结束，使用加的方式来实现多次计算
    public  int divideCore(int dividend, int divisor) {
        int result = 0;
        while (dividend <= divisor) {
            int value = divisor;
            int quotient = 1;
            while (value >= 0xc0000000 && dividend <= value + value) {
                quotient += quotient;
                value += value;
            }
            result += quotient;
            dividend -= value;
        }
        return result;
    }
}
```

## 2、二进制加法

> 给定两个 01 字符串 `a` 和 `b` ，请计算它们的和，并以二进制字符串的形式输出。

> 输入为 **非空** 字符串且只包含数字 `1` 和 `0`。

第一反应：把两个字符串转换成具体的数值，然后再将计算到的和在转回二进制。

这样的话，有可能这两个字符串非常的长，这样就会数值溢出了，结果不正确。所以还是得像十进制的加法那样，右边对齐，然后按竖式的方式来计算。

所以思考一下，考虑使用和十进制的加法相似的方法，采用进位的方式来完成相应的计算，考虑到需要右边对齐，所以需要对字符串进行倒置reverse（）计算，从右往左计算困难，因此换成从左到右即可。



先来看下 StringBuffer 和 StringBuilder 的区别。

| StringBuffer  | StringBuilder |
| ------------- | ------------- |
| 线程安全      | 非线程安全    |
| 同步          | 非同步        |
| 始于 Java 1.0 | 始于 Java 1.5 |
| 慢            | 快            |

由于String是不可变类，适合在需要被共享的场合中使用，当一个字符串经常被修改时，最好使用StringBuffer实现。如果用String保存一个经常被修改的字符串，该字符串每次修改时都会创建新的无用的对象，这些无用的对象会被垃圾回收器回收，会影响程序的性能，不建议这么做。

多线程的情况下，推荐使用的是StringBuffer，线程安全，单线程情况下使用StringBuilder会更加快速

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            //减去ascii码的0，因为都是二进制的1和0 所以减去的值刚刚好是1和0，然后再筛选进位
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            //将添加过后的值塞回去相加过后那个位置
            ans.append((char) (carry % 2 + '0'));
            //对进位操作，
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }
}
```

#### * StringBuffer是否实现了equals和hashCode方法

String实现了equals()方法和hashCode()方法，new String("java").equals(new String("java"))的结果为true；

而StringBuffer没有实现equals()方法和hashCode()方法，因此，new StringBuffer("java").equals(new StringBuffer("java"))的结果为false，将StringBuffer[对象存储](https://cloud.tencent.com/product/cos?from=10680)进Java集合类中会出现问题；





















