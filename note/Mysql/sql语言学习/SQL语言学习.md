# SQL语言学习

structure query language,结构化查询语言。

## DQL

全称 data query language，数据查询语言。

### 基础查询

语法：
select 查询列表 from 表名；

特点：

1. 查询列表可以是：表中的字段、常量值、表达式、函数。
2. 查询结果是一个虚拟的表格。

先进入到对应的表中
use myemployees;

1. 查询表中的单个字段
 select last_name from employees;
2. 查询表中的多个字段
 select last_name，first_name,salary from employees;
3. 查询表中的所有字段
 select * from employees;
 select `` `first_name`,`last_name` ,...... `` from employees;
4. 查询常量值
 select 100;
 select 'john';
5. 查询表达式
 select 100%98;
6. 查询函数
 selct version();
7. 起别名 好处：
    1. 便于理解。
    2. 如果查询的字段有重名的情况，使用别名可以区分开来。
    用法：
        1. select last_name as 姓 ,first_name as 名 from employee;
        2. select last_name 姓 ,first_name 名 from employee;
8. 去重
select DISTINCT department_id from employees;
9. +号的作用
如果想要将姓和名连接成一个字段，并显示为 姓名
在mysql中+号只起运算符的作用，并不能起连接符的作用
且默认将不能转换成数值的字符串转为0，null值无论+任何值结果都为null
也有能够将null值转换成数值的方法 IFNULL(列，0);
错误用法：select first_name + last_name as 姓名 from employee；
正确连接方式：select concat（first_nam，last_name）as 姓名;
10. 显示表结构
DESCRIBE employees;
DESC employees;

### 条件查询

语法：
select 查询列表 form 表名 where 筛选条件：

- 按条件表达式筛选  `<  >  =  <= >= !=等价于<>`

例：查询员工中所有月薪高于12000的员工信息

``` txt
select 
    * 
from 
    employee 
where 
    salary>12000;
```

- 按逻辑运算符筛选  `&& || !  在mysql里 等价于 and or not`两种都能用

例：

``` txt
    select last_name,
            salary,
            commission_pct
    from
        employees
    where
        salary>=10000
    and
        salary<=20000;
```

### 模糊查询

like 、 between and、 in、 is null、is not null

- like 一般和通配符搭配使用
  - 通配符 % 表示任意多个字符，包含0个字符
  - 通配符 _ 表示任意单个字符

案例： 查询员工名中含字符a的员工信息

```txt
select
    *
from
    employees
where
    last_name
like
    '%a%';  //  %代表通配符，表示任意一个字符
```
案例： 查询员工名中第三个字符和第五个字符为a的员工名和工资

``` txt
select
    last_name,
    salary，
from
    employees
where
    last_name
like
    '__a_a%';  #表示第三个字符和第五个字符为a
```

- between and  等于<=和>=，值得注意的是不能顺序颠倒

案例：查询员工id在100和120之间的

```txt
select
    *
from
    employees
where
    employee_id>=100 and employees_id<120;
------------------------------------------
select
    *
from
    employees
where
    employee_id between 100 and 120;
```

- in   判断字段中的值是否属于in列表中的某一项

案例：查询员工的工种编号是IT_PROG、AD_VP、AD_PRES中的的一个员工名和工种编号

``` txt
select
    last_name,
    jb_id
from
    employees
where
    job_id = 'IT_PROG',job_id = 'AD_VP',job_id = 'AD_PRES';
-----------------------------------------------------------
select
    last_name,
    jb_id
from
    employees
where
    job_id in('IT_PROG','AD_VP','AD_PRES');
```

- is null  注意：`= 或者<>不能用于判断值是否为null`

案例：查询没有奖金的员工名和奖金率

``` txt
select
    last_name,
    commission_pct
from
    employees
where
    commission_pct is null;
----------------------------------------
select
    last_name,
    commission_pct
from
    employees
where
    commission_pct is not null;
```

### 排序查询

``` txt
select 查询列表
from 表
【where 筛选条件】
order by 排序列表【asc|desc】;
1. asc代表升序，desc代表降序 如果两个都不写，则默认升序。
2. order by 子句中可以支持单个字段、多个字段、表达式、函数、别名。
3. order by 子句一般位于语句的最后面，limit子句除外。
```

案例：查询员工信息，要求工资从高到低排序

select * from employees order by salary desc；

从低到高
select * from employees order by salary asc；

### 常见函数

``` txt
功能类似java中的方法
select 函数名()【from 表】;

分类：
    1.单行函数
    如：concat、length、ifnull等等
    2.分组函数
    sum、min、max、count、avg
    除count外，这些函数都忽略null值
```

#### 字符函数

- length 用于获取参数值的字节个数
select length('john');

- concat 用于连接字符串
select concat(last_name,_,first_name) as 姓名 from employees;

- upper 和 lower
select upper('john');
select lower('john');

- substr substring  剪切字符串
select substr('abcdefg',3) as out_put;

- instr 返回子串的第一次索引如果找不到则返回0
select instr('abcdefg',defg) as out_put;

- trim 去除空格
select trim('  aaaa  ') as out_put;
select trim('bb' from '  aabbaa  ') as out_put;

- lpad 用指定字符实现左填充指定长度
select lpad('刘俊荣'，2，*) as out_put；  输出：刘俊

- rpad 用指定字符实现右填充指定长度
select rpad('刘俊荣'，5，*) as out_put；  输出：**刘俊荣

- replace 替换
select replace('ljrababab','ab','dd') as out_put;
#### 数学函数

- round 四舍五入
select round(1.55);
select round(1.555,2);   输出1.56  代表保留小数点后两位数
- rand 随机数
- floor 向下取整
- ceil 向上取整
- mod 取余
- truncate 截断
#### 日期函数

- now 当前系统日期+时间
- curdate 当前系统日期
- curtime 当前系统时间
- date_format 将日期转换成字符
- str_to_date 将字符转换成日期
#### 流程控制函数

- if 处理双分支
- case 处理多分支
        情况1：处理等值判断
        情况2：处理条件判断
#### 其他函数
- version 版本
- database 当前库
- user 当前用户
#### 分组函数

- sum 求和
- max 最大值
- min 最小值
- avg 平均值
- count 计数   参数可以使用：字段、* 、常量值、一般放1  建议使用 *

都可以搭配distinct使用，用于去重
以上函数都忽略null值，除了count(*)

### 分组查询

``` txt
select 查询的字段，分组函数
from 查询的表
group by 分组的字段

特点：
1.可以按单个字段分组
2.和分组函数一同查询的字段最好是分组后的字段
3.分组筛选
                        针对的表        位置            关键字
    在分组前进行筛选    原始表          group by的前面   where
    在分组后进行筛选    分组后的结果集   group by的后面  having
4.可以按多个字段来进行分组，字段之间用逗号来进行分割
5.可以支持排序
6.having后可以支持别名

```

### 多表连接查询

``` txt
1.SQL92 语法：
    1. 等值连接的结果 = 多个表的交集
    2. n表连接，至少需要n-1个连接条件
    3. 多个表没有主次，没有顺序要求
    4. 一般为表起别名，提高可阅读性和性能
2.SQL99 语法：
    含义：1999年推出的sql语法
    支持：
        等值连接、非等值连接（内连接）
        外连接
        交叉连接
    语法：
    select 字段
    from 表1
    【inner|left outer|right outer|cross】 join 表2 on 连接条件
    【inner|left outer|right outer|cross】 join 表3 on 连接条件
    【where 筛选条件】
    【group by 分组字段】
    【having 分组后的筛选条件】
    【order by asc|desc|条件表达式】

    好处在于：语句上，连接条件和筛选条件实现了分离
3.自连接
    案例： 查询员工名和直接上级的名称
SQL 99:
    select e.last_name,m.last_name
    from employees e
    join employees m on e.manager_id = m.employee_id;
SQL 92:
    select e.last_name,m.last_name
    from employees e,employees m
    where e.manager_id = m.employee_id;
```

### 子查询

一条查询语句中嵌套了另一条完整的select查询语句，其中被嵌套的查询语句，称为子查询或者内查询。

特点：
1. 子查询都放在小括号内
2. 子查询可以放在from后面、select后面、where后面、having后面，但一般放在条件的右侧
3. 子查询优先于主查询执行，主查询使用了子查询的查询结果
4. 子查询根据查询结果的行数不同分为以下几类
    - 单行子查询
        - 结果集只有一行
        - 一般搭配单行操作符使用：> 、<、 =、 <>、 >=、 <=
        - 非法使用子查询的情况
            - a.子查询的结果为单一个值
            - b.子查询的结果为空
    - 多行子查询
        - 结果集有多行
        - 一般搭配多行操作符使用： any 、all 、in 、not in
        - in ：属于子查询中的任意一个结果就行
        - any、all 往往可以使用其他查询来代替

### 分页查询

实际的web项目中需要用户提交对应的分页查询的sql语句来实现页面的翻页。
``` txt
语法:
    select 字段
    from 表
    【where 筛选条件】
    【group by 分组字段】
    【having 条件】
    【order by asc|desc 排序的字段】
    limit 【起始的条目索引，】 条目数

特点：
    1. 起始索引从0开始
    2. limit子句肯定是在查询语句的最后面
    3. 公式： select * from 表 limit(page-1)*sizePerPage,sizePerPage
```

### 联合查询

union 联合 合并

语法：

	select 字段|常量|表达式|函数 【from 表】 【where 条件】 union 【all】
	select 字段|常量|表达式|函数 【from 表】 【where 条件】 union 【all】
	select 字段|常量|表达式|函数 【from 表】 【where 条件】 union  【all】
	.....
	select 字段|常量|表达式|函数 【from 表】 【where 条件】

特点：

	1、多条查询语句的查询的列数必须是一致的
	2、多条查询语句的查询的列的类型几乎相同
	3、union代表去重，union all代表不去重

## DML

全称 data manipulation language，数据操作语言。

### 插入

语法：

    insert into 表名(字段1，字段2 ....)
    values(值1，值2 ....)

特点：

    1. 字段类型和值类型一致或兼容，而且一一对应。
    2. 可以为空的字段，可以不用插入值，或者使用null填充。
    3. 不可以为空的字段，必须输入值。
    4. 字段个数和值的个数必须一致。
    5. 字段可以省略，代表默认所有字段，并且顺序和表中存储顺序一致。

### 删除

#### 方式1.DELETE

语法：

    单表的删除
    delete from 表名
    【where 筛选条件】
    ------------------------------------------
    多表的删除
    delete 别名1 ，别名2
    from 表名1 别名1 ，表名2 别名2
    where 连接条件
    and 筛选条件

#### 方式2.TRUNCATE

语法：

    truncate table 表名;

两种方式的区别：

    1. truncate 不能添加where 条件，而delete可以添加where 条件
    2. truncate 的效率会高一点点
    3. truncate 删除带自增长的列的表后，如果再插入数据，数据从1开始
    #  delete删除带自增长的列的表后，再插入数据，数据从上一次的断点开始
    4. truncate 删除不能回滚，delete删除可以回滚  回滚（rollback）

### 更改

语法：

    单表更改
    update 表名
    set 字段 = 新值，字段= 新值 ...
    【where 筛选条件】
    ----------------------------------------------
    多表更改
    update 表1 别名1 ，表2 别名2
    set 字段 = 新值 ，字段2 = 新值
    【where 筛选条件】

## DDL

全称 data define language 数据定义语言。

### 操作数据库

#### 创建库

语法：

    create database 【if not exists】 数据库名 【character set 字符集】;

#### 修改库

语法：

    alter database 库名 character set 字符集名

#### 删除库

语法：

    delete database 【if exists】库名;  //删库需谨慎啊

### 操作表

1. 创建表

语法：

    create table 【if not exists】 表名(
        字段名    字段类型   【约束】,
        字段名    字段类型   【约束】,
        字段名    字段类型   【约束】,
        ....
    )

2. 修改表

语法：

    1.添加列
    alter table 表名 add column 列名 类型 【first|after字段名】;
    2.修改列的类型或约束
    alter table 表名 modify column 列名  新类型 【新约束】;
    3.修改列名
    alter table 表名 change column 旧列名 新列名 类型;
    4.删除列
    alter table 表名 dorp column 列名;
    5.修改表名
    alter table 表名 rename 【to】 新表名;

3. 删除表

语法：

    drop table 【if exists】 表名;
    值得一提的是 if exists 只能用于表和库。

### 数据类型

1. 数值型
- tinyint 1字节
- smallint 2字节
- mediumint 3字节
- int 4字节
- bigint 8字节
特点：
    1.都可以设置有无符号，默认是有符号的，通过unsigned来设置无符号。
    2.如果超出范围会报错，越界了。
    3.长度可以不指定，会有一个默认的长度 指数值的长度，如果不够想使用0来填充，则需要使用zerofill，并且默认变成无符号类型。
2. 浮点型
定点数：decimal(M,D)   
浮点数：float(M,D) 4
       double(M,D) 8
特点：
    1.这里的M指显示的数值的长度，包括小数点后的位数，三位数显示4位数的最大值为999。D指小数点后显示几位小数。
    2.M和D都可以省略，但对于定点数，M的值默认为10，D默认为0。
    3.如果精度要求比较高，优先推荐使用定点数。
3. 字符型
char、varchar、binary、varbinary、enum、set、text、blob
char 固定长度的字符，写法为char(M)，最大长度不能超过M，其中M可以省略，默认为1  注意这里M是指字符长度
varchar 可变长度的字符，写法为varchar(M)，最大长度不能超过M，其中M不能省略。  这两的区别在于分配空间上和执行速度上。
4. 日期型
date日期 
year年  
datetime日期+时间     8
timestamp日期+时间    4     比较容易受时区，语法模式，版本影响，更能反映当前时区的真实时间。
5. Blob类型
一般是一些二进制的文件

### 常见的约束

- NOT NULL： 非空，该字段的值必须填
- UNIQUE： 唯一，该字段的值不可重复
- DEFAULT：默认值，该字段的值不用手动输入也有默认值
- CHECK： 检查，mysql中不适用
- PRIMARY KEY： 主键，该字段的值不可重复且非空
- FOREIGN KEY： 外键，该字段的值引用了另外表的字段。

主键和唯一：

    不同点：
    1.主键不能为空，唯一可以为NULL
    2.一个表至多只允许有一个主键，但可以有多个唯一
    相同点：
    1.都具有唯一性
    2.都支持组合键，但不推荐

外键：

    1.用于限制两个表的关系，从表的字段值引用了主表的某字段值
    2.外键列和主表的被引用列要求类型一致，意义一致，名称无意义
    3.主表的被引用要求是一个key，一般就是主键
    4.插入数据，先插入主表
      删除数据先删除从表
      有两种方式来删除主表的记录：
      方式一：级联删除
      ALTER TABLE stuinfo ADD CONSTRAINT fk_stu_major FOREIGN KEY(majorid) REFERENCES major(id) ON DELETE CASCADE;
      方式二：级联置空
      ALTER TABLE stuinfo ADD CONSTRAINT fk_stu_major FOREIGN KEY(majorid) REFERENCES major(id) ON DELETE SET NULL;

 创建表时添加约束：

    create table 表名(
        字段名 字段类型 NOT NULL ，     //非空
        字段名 字段类型 PRIMARY KEY ，  //主键
        字段名 字段类型 FOREIGN KEY ，  //外键
        字段名 字段类型 UNIQUE ，       //唯一
        字段名 字段类型 DEFAULT 值，    //设定默认值
        constraint 约束名 foreign key (字段名) reference 主表 (被引用列)
    );
    注意：
                    支持类型            约束名
    列级约束        除了外键            不可以取约束名
    表级约束        除了非空和默认      可以，但对主键无效

    列级约束可以在一个字段上追加多个，中间用空格符隔开，没有顺序要求。

修改表时添加或删除约束：

    1.非空
    添加非空
    alter table 表名 modify column 字段名 字段类型 not null;
    删除非空
    alter table 表名 modify column 字段名 字段类型;

    2.默认
    添加默认
    alter table 表名 modify column 字段名 字段类型 default 值;
    删除默认
    alter table 表名 modify column 字段名 字段类型;

    3.主键
    添加主键
    alter table 表名 add 【constraint 约束名】 primary key(字段名);
    删除主键
    alter table 表名 drop primary key;

    4.唯一
    添加唯一
    alter table 表名 add 【constraint 约束名】 unique(字段名);
    删除唯一
    alter table 表名 drop index 索引名;

    5.外键
    添加外键
    alter table 表名 add 【constraint 约束名】 foreign key(字段名) reference 主表(被引用列);
    删除外键
    alter table 表名 drop foreign key 约束名;

自增长列：
    
    特点：
    1.不用手动插入值，可以自动提供序列值，默认长度为1，步长为1 。
    auto_increment_increment
    如果需要更改起始值，手动插入值
    如果需要更改步长，更改系统变量
    set auto_increment_increment = 值;

    2.一个表至多有一个自增长列
    3.自增长列只能为数值类型
    4.自增长必须为一个key  (主键或外键)

    一、创建表时设置自增长列
        create table 表名(
            字段名 字段类型 约束 auto_increment
        );
    二、修改表时设置自增长列
    alter table 表名 modify column 字段名 字段类型 约束 auto_increment;
    三、删除自增长列
    alter table 表 modify column 字段名 字段类型 约束;

## TCL

全称 transaction control language 事务控制语言。

### 数据库事务

含义：通过一组逻辑操作单元，一组DML，将数据从一种状态切换到另一种状态。
特点：

    1.原子性：要么都执行，要么都回滚
    2.一致性：保证数据的状态操作前和操作状态后一致。
    3.多个事务操作数据库中同一个数据时，一个事务的执行不受另一个事务的干扰。
    4.一个事务一旦提交，则数据持久化到本地数据库，除非另一个事务对其进行修改。  

相关步骤：

    1.开启一个事务
    2.编写事务的一组逻辑操作单元
    3.提交事务或者回滚事务

### 事务的分类

隐式事务，没有明显的开始和结束事务的标志

    比如：insert update delete 本身就是一个事务

显示事务，有明显的开始和结束事务的标志

    1.开启事务
    取消自动提交事务的功能    set autocommit = false;
    2.编写事务的一组逻辑操作单元(多条sql语句)
    insert
    update
    delete
    3.提交事务或者回滚事务  commit; / rollback;

使用到的关键字：

    set autocommit = 0;
    start transacition;
    commit;
    rollback;

    savepoint; 断点
    commit to 断点
    rollback to 断点

事务的隔离级别：

事务并发问题如何发生？

    当多个事务操作同一个数据库的相同数据时发生。

事务的并发问题有哪些？

    脏读：一个事务读取到了另一个事务未提交的数据。
    不可重复读：同一个事务中，多次读取到的值不一致。
    幻读：一个事务读取数据时，另一个事务对数据进行了更新，第一个事务读取到了未更新的值。

如何避免事务的并发问题？

    通过设置事务的隔离级别
    1.read uncommitted 
    2.read commited             可以避免脏读
    3.repeatable read           可以避免脏读，不可重复读和一部分幻读
    4.serializable              可以避免脏读，补课重复读和幻读

设置隔离级别：

    set session |global  transaction isolation level1 隔离级别;

查看隔离级别：
    
    select @@tx_isolation;

## 其他

### 视图

理解成一张虚拟的表，视图的主要作用是用于查询的

为什么要使用视图，举个很好理解的例子：
比如说员工工资，这个内容比较敏感，但是操作数据库时又不得不显示出这个内容
所以使用视图来避免某些关键内容被其他人看见，数据库的操作员就能在不看见不该看见的内容的情况下同时对数据库进行操作了

|  |使用方式|占用物理空间|
|:-:|:-:|:-:|
|表|完全相同|占用|
|视图|完全相同|不占用，仅仅保存的是sql逻辑|

视图的好处：

    1.sql语句提高了重用性，效率高
    2.和表实现了分离，提高了安全性

1. 视图的创建

语法：

    create view 视图名
    as
    查询语句;

    这里几乎可以把视图当做是原来表的一个副本，然后对原表起了个别名，方便重复使用。

2. 视图数据的增删查改

语法：

    1.查看视图的数据
    select * from my_v4;
    selcet * from my_v1 where last_name = 'partners' ;

    2.插入视图的数据
    insert into my_v4 (last_name , department_id) values('ljr',90);

    3.修改视图的数据
    update my_v4 set last_name = 'lxf' where last_name = 'ljr';

    4.删除视图的数据
    delete from my_v4;

3. 某些视图不能更新

注意：视图一般用于查询的，而不是更新的，所以具备以下特点的视图都不允许更新

包含以下关键字的sql子句：分组函数、distinct、group by、having、union或者union all
常量视图
select 中包含子查询
join
from 一个不能更新的视图
where子句的子查询引用了from子句中的表

4. 视图逻辑的更新

    方式一：
	CREATE OR REPLACE VIEW test_v7
	AS
	SELECT last_name FROM employees
	WHERE employee_id>100;
    方式二：
	ALTER VIEW test_v7
	AS
	SELECT employee_id FROM employees;
	
	SELECT * FROM test_v7;

5. 视图的删除

    drop view 视图1，视图2,...;

6. 视图结构的查看

    DESC test_v7;
	SHOW CREATE VIEW test_v7;

### 存储过程

含义：一组经过预先编译的sql语句的集合
好处：

    1.提高了sql语句的重用性，减少开发程序员的压力
    2.提高了效率
    3.减少了传输次数

sql语句的内存分析：

    sql语句 -> 查询内存 -> 优化 -> 统计 -> 解析 -> 生成执行计划 -> 执行  (包括但不仅限于这些过程)
    如果是执行过的语句，第二次执行时速度会大大缩减，因为已经在查询缓存中保留了。
    mysql中主要有三大缓存区

    查询缓存：       
    sql语句执行之后会保存到这里，然后留下'记录'类似的东西，然后下次执行相同语句时，可以直接从查询内存跳转到执行，而不需要中间一系列的优化、统计、解析等等操作，节省大量时间，提高效率，直接返回值到sql语句中。 注意：这里不仅仅是内容要求一致，连指令的大小写也要区分，内容输出或许是一样的，但有一丝差别也会重新进行一系列操作。 select 和SELECT是不同的。

    数据缓存区：
    这里会将sql语句需要的表加载到数据缓存区中，然后经过一系列的操作，进行筛选其中的内容。

    日志缓存区：
    顾名思义，就是缓存一系列的日志的缓存区。

分类：

    1.无返回无参
    2.仅仅带in类型，无返回有参
    3.仅仅带out类型，有返回无参
    4.既带in又带out类型，有返回有参
    5.带inout，有返回有参
    注意：in out inout 都可以在一个存储过程中带多个

#### 创建存储过程

语法：

    create procedure 存储过程名(in|out|inout 参数名 参数类型，.....)
    begin
        存储过程体
    end
类似于方法：

    修饰符  返回类型 方法名(参数类型 参数1, ...){
        方法体;
    }
注意：

    1.需要设置新的结束标记
    delimiter 新的结束标记
    实例：
    delimiter $
    create procedure 存储过程名(in|out|inout 参数名 参数类型，.....)
    begin
        sql语句1;
        sql语句2;
    end $

    2.存储过程体重可以有多条sql语句，如果只有一条sql语句，可以忽略begin和end

    3.参数面前符号的意思：
    in：    该参数只能作为输入（该参数不能做返回值）
    out：   该参数只能作为输出（该参数只能作为返回值）
    inout： 该参数既能作为输入也能作为输出

#### 调用存储过程

语法：

    call 存储过程名(实参列表);

### 函数

#### 创建函数

学过的函数：length substr concat等等
语法：

    create function 函数名(参数名 参数类型) returns 返回值类型
    begin
        函数体
    end

#### 调用函数：

    select 函数名(实参列表);

#### 函数和存储过程的区别：

|  |关键字|调用语法|返回值|应用场景|
|:-:|--|--|--|--|--|--|
|函数|function|select 函数()|只能是一个|一般用于查询结果为一个值并返回，当且仅有一个返回值|
|存储过程|procedure|call 存储过程()|可以有0或者多个|一般用于更新|

### 流程控制结构

#### 系统变量

1. 全局变量

作用域：对于所有会话（连接）有效，但不能跨重启

    查看所有全局变量
    show global variables;
    查看满足要求的部分全局变量
    show global variables like '%char%';
    查看指定的系统变量的值
    select @@global.autocommit;
    为某个系统变量赋值
    set @@global.autocommit = 0;
    set GLOBAL autocommit = 0;
2. 会话变量

作用域：作用于当前会话（连接）有效
    
    查看所有会话变量
    show session variables;
    查看满足要求的部分会话变量
    show session variables like '%char%';
    查看指定的会话变量的值
    select @@autocommit;
    select @@session.tx_isolation;
    为某个会话变量赋值
    select @@session.tx_isolation = 'read-uncommitted';
    select session tx_isolation = 'read-committed';

#### 自定义变量

1. 用户变量

声明并初始化

    set @变量名 = 值;
    set @变量名 := 值;
    select @变量名 := 值;
赋值

    方式一：一般用于赋简单的值
    set @变量名 = 值;
    set @变量名 := 值;
    select @变量名 := 值;   

    方式二：一般用于给表中的字段赋值
    select 字段名或表达式 into 变量
    from 表;
使用

    select @变量名;
2. 局部变量

声明

    declare 变量名 类型 【default 值】;
赋值：

	方式一：一般用于赋简单的值
	SET 变量名=值;
	SET 变量名:=值;
	SELECT 变量名:=值;


	方式二：一般用于赋表 中的字段值
	SELECT 字段名或表达式 INTO 变量
	FROM 表;

使用：

	select 变量名
二者的区别:
|  |作用域|定义位置|语法|
|:-:|--|--|--|
|用户变量|当前会话|会话的任何位置|加@符号，不用指定类型|
|自定义变量|定义他的begin end中|begin end的第一句话|一般不用加@符号，需要指定类型|

### 分支

1. if函数
    语法：if(条件，值1，值2)        //有点像三段式
    特点：可以使用在任何位置
2. case语句
    语法：

        情况1： 类似于switch
        case 表达式
        when 值1 then 结果1或者语句1（如果是语句，则需要加上分号）
        when 值2 then 结果2或者语句2（如果是语句，则需要加上分号）
        when 值3 then 结果3或者语句3（如果是语句，则需要加上分号）
        ...
        else 值n then 结果n或者语句n（如果是语句，则需要加上分号）
        end 【case】（如果是放在begin end中需要加上case，如果放在select后面不需要）
    
    特点：可以用在任何位置
        

3. if elseif语句

    语法：

	    if 情况1 then 语句1;
	    elseif 情况2 then 语句2;
	    ...
	    else 语句n;
	    end if;

    特点：只能用在begin end中！！！！！！！！！！！！！！！


三者比较：
|  分支 |		应用场合|
|--|--|
|if函数|		简单双分支|
|case结构|	等值判断 的多分支|
|if结构	|	区间判断 的多分支|

### 循环

语法：


	【标签：】WHILE 循环条件  DO
		循环体
	END WHILE 【标签】;
	
特点：

	只能放在BEGIN END里面

	如果要搭配leave跳转语句，需要使用标签，否则可以不用标签

	leave类似于java中的break语句，跳出所在循环！！！
	