## java内存分配
java的内存需要划分成五个部分
### 栈（Stack）
存放的都是方法中的局部变量
方法运行一定要在栈当中。
局部变量：方法的参数，或者说{}内的变量
作用域：一旦超出作用域，立刻从栈内存中消失。
### 堆（Heap）
凡是 new 出来的东西都在堆内存中。
堆内存里的东西都有个地址值。16进制
堆内存里的数据都有默认值。基本上都是默认为0，根据数据类型的差别会有所不同。
### 方法区（Method Area）
存储 .class 相关信息，包含方法的信息。


### 本地方法栈（Native Method Stack）
与操作系统相关
### 寄存器（Pc Register）
与 CPU 相关。
