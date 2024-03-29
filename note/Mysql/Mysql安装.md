# 数据库概述

数据库优点：

1. 可以持久化数据到本地
2. 结构化查询数据

主流的数据库有：

Mysql：使用人数最多的数据库，主要特点在于便宜好用，很多小公司对其青睐。

Oracle：甲骨文公司，虽然实力强厚，但是太贵了，不仅仅是软件贵，后期服务的维护也需要收费。

DB2：IBM公司，相对来说是用于处理海量的数据。

Sql Server： 微软，只能安装在windows操作系统下。

-------------------------------------

DB: 数据库 Datebase

DBMS：数据库管理系统 Datebase Manger System

SQL：结构化查询语言 Structure Query Language ，专门用来与数据库通信的语言。

SQL优点：

+ 不是某个特定数据库供应商专用的语言，几乎所有DBMS都支持SQL。
+ 简单易学
+ 虽然简单易学，但是是一种强力的语言，灵活使用可以进行非常复杂的和高级的数据库操作。

数据库特点：

1. 将数据放在表中，表再放在库中
2. 一个数据库中可以有多个表，每个表都有名字，具有唯一标识性。表名具有唯一性。
3. 表具有一些特性，这些特性定义了表再数据库中如何存储，类似于java中类的概念。
4. 表由列组成，在数据库中称为字段，所有表都是由一个或者多个列组成的。类比于java中属性的概念。
5. 表中的数据存储是按行存储的。类比于java中对象的概念。


## 数据库系统概念

- DB：一组相关数据的集合
- DBMS：介于用户和OS之间的数据库管理软件
- DBS：采用数据库的计算机操作系统
- 联系的元数：一个联系有关的实体集个数
- 1:1联系：实体集E1中的每个实体至多与E2中一个实体存在一种联系
- 1:n联系：实体集E1中的每个实体可以与E2中任意个实体有联系，而E2中每个实体至多与E1中一个实体有联系
- m:n联系：实体集E1中的每个实体可以与E2中任意个实体有联系，反之，E2中的每个实体也可以和E1中有任意个实体联系
- 数据模型：能对现实世界数据进行抽象的模型
- 概念模型：表达用户需求观点的DB全局逻辑结构
- 逻辑模型：表达计算机实现观点的DB全局逻辑结构

-------------------------

- 事务：构成单一逻辑工作单元的操作集合
- 数据库的原子性（automatic）：一个事务对数据库的所有操作，是一个不可分割的工作单元。操作要么全部执行，要么全不执行。
- 数据库的一致性（consistency）：数据不会因为事务的执行而遭到破坏。
- 数据库的隔离性（isolation）：在多个事务并发执行时，系统应保证这些事务先后单独执行时的结果应该是一致的。
- 数据库的持久性（durability）：应该事务一旦完成全部操作后，它对数据库的所有更新应该永久的反应在数据库中，不会丢失。即使以后系统发生故障也是如此。

------------------------

- 数据库的可恢复性：系统把DB从被破坏，不正确的状态恢复到最近一个正确的状态，DBMS的这种功能被称为DB的可恢复性。

-------------------------

- 并发操作：在多用户共享系统中，许多事务可能对同一数据进行操作，这种操作被称为并发操作。
- 封锁：封锁是系统保证对数据项的访问以互斥的方式进行的一种手段。
- X锁：事务T对某数据增加了X锁，其他事务要对该数据操作要等T对该数据解除X锁才能进行操作。
- S锁：事务T对某数据添加了S锁，仍允许其他事务对该数据添加S锁，但在所有S锁解除前，不允许其他事务对该数据添加X锁。

------------------------

- 调度：事务的执行次序被称为调度。
- 串行调度：多个事务依次执行被称为串行调度。
- 并发调度：利用分时的方法同时处理多个事务，被称为并发调度。
- 可串行化调度和不可串行化调度：如果一个并发调度的执行结果与某一串行调度的执行结果等价，那么这个并发调度称为可串行化调度，否则被称为不可串行化调度。



# Mysql安装使用

Mysql是隶属于MySqlAB公司，后来被Oracle公司收购（08年被SUN公司收购，09年sun公司被Oracle公司收购）

## MySql优点

- 成本低，开源性代码，一般可以免费试用
- 性能高，执行速度比较快，可移植性比较强
- 简单，容易安装和使用

## Mysql服务的开启和关闭

net start [mysql的名字]
net stop [mysql的名字]

## Mysql服务的登陆和退出

- 登陆
`mysql -h localhost -P 3306 -u root -proot`
`mysql -u root -proot` 本地端的服务器可以直接省略部分命令
  - -h host指连接服务器 后面localhost可以不接空格
  - -P Port指连接的服务器端口 默认端口是3306 端口号可以在 .ini的配置文件中查看
  - -u user指用户名
  - -p password指密码，这里注意可以直接在p后回车，这样输入密码时不显示密码，在p后接密码时不能有空格。

- 退出
`exit`

## Mysql常用命令

- 1.显示全部数据库
  - show databases; //注意每条语句后要加上;或者/g   这里犯了个很笨的错误，datebases 敲半天都是错误命令，真蠢。
- 2.打开指定的库
  - use 数据库名; //进入到某个数据库
- 3.查看当前库的表
  - show tables;
  - show tables from 数据库名;  //在其他库里查看某个数据库内的表
- 4.select database();  //查看当前所在的数据库
- 5.创建一个表
  - creat table 表名(
    列名 列类型,
    列名 列类型,
    ......
    );
- 6.查看表结构
  - desc 表名;
- 7.查看服务器版本号
  - 已登录的情况
    - select version();
  - 未登录到mysql服务端的情况
    - mysql -V
    - mysql -version

## mysql的语法规范

1. 不区分大小写，但是建议关键字大写，表名列名小写
2. 每条命令用分号结尾或者/g结尾，推荐是使用分号结尾
3. 每条命令可以根据需要进行缩进或者换行
4. 注释：
4.1 单行注释：`#注释`
4.2 单行注释：`-- 注释`
4.3 多行注释：`/* 注释 */`
