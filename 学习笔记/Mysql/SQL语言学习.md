# SQL语言学习

## DQL语言

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
错误用法：select first_name + last_name as 姓名 from employee；
正确连接方式：select concat（first_nam，last_name）as 姓名;

### 条件查询

语法：
select 查询列表 form 表名 where 筛选条件：

- 按条件表达式筛选  `<  >  =  <= >= !=等价于<>`

例： 

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

模糊查询：

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

- is null  ` = 或者<>不能用于判断值是否为null`
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



## DML语言

全称 data manipulation language，数据操作语言。

## DDL语言

全称 data define language 数据定义语言。

## TCL语言

全称 transaction control language 事务控制语言。