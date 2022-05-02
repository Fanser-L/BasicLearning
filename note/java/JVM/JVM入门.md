# JVM入门

JVM是Java Virtual Machine（[Java虚拟机](https://baike.baidu.com/item/Java虚拟机/6810577)）的缩写，JVM是一种用于计算设备的规范，它是一个虚构出来的[计算机](https://baike.baidu.com/item/计算机/140338)，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。

虚拟机，是java能够实现跨平台的关键。

引入Java语言虚拟机后，Java语言在不同平台上运行时不需要重新编译。Java语言使用Java虚拟机屏蔽了与具体平台相关的信息，使得Java语言[编译程序](https://baike.baidu.com/item/编译程序/8290180)只需生成在Java虚拟机上运行的目标代码（[字节码](https://baike.baidu.com/item/字节码/9953683)），就可以在多种平台上不加修改地运行。

![img](https://s2.loli.net/2022/04/18/bXhEg7aN6joR3xF.png)

java虚拟机的基本结构

![image-20220418211917114](https://s2.loli.net/2022/04/18/OzFRCIyrqh2QUBN.png)

ClassLoader 

![image-20220418231532207](https://s2.loli.net/2022/04/18/rOpG5z47ixPfuD8.png)