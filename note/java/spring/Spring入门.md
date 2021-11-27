## Spring概述

> 为了解决企业应用开发的复杂性问题

1. Spring是轻量级的开源的javaEE框架
2. Spring可以解决企业应用开发的复杂性
3. Spring有两个核心部分：IOC 和 Aop
    - IOC：控制反转，把创建对象的过程交给Spring进行管理
    - Aop：面向切面，不修改源代码进行功能增强
4. Spring特点
    - 方便解耦，简化开发
    - Aop编程支持
    - 方便程序的测试
    - 方便可以和其他框架进行整合
    - 方便进行事务操作
    - 降低API的使用难度

Spring下载地址：
<https://repo.spring.io/ui/native/release/org/springframework/spring/>

## 入门案例

通过Spring来创建一个user类

1. 先新建一个普通的User类
2. 添加jar包，Spring的四大基本jar包，aop bean core exception
3. 创建一个spring的xml文件
4. 创建一个bean对象
5. 通过ApplicationContext 来装入加载的Spring配置文件
6. 获取Spring配置文件


