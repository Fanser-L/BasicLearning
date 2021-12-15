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

## IOC：控制反转，把创建对象的过程交给Spring进行管理

### 入门案例

通过Spring来创建一个user类（工厂模式）

![工厂模式](img/图1.png)

1. 先新建一个普通的User类
2. 添加jar包，Spring的四大基本jar包，aop bean core exception
3. 创建一个spring的xml文件
4. 创建一个bean对象
5. 通过ApplicationContext 来装入加载的Spring配置文件
6. 获取Spring配置文件


基于xml方式注入属性

1. set方法注入
   
``` java
/*
* 使用set方式来注入属性
* */

public class Book {
    //创建相应的属性
    private String bname;
    private String bauthor;
    //创建相应的set方法

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
}
```

1. 使用spring配置文件配置对象创建，配置属性注入
   

``` xml
    <bean id="book" class="com.fanser.spring5.root.Book">
        <property name="bname" value="spring"></property>
        <property name="bauthor" value="xxxx"></property>
    </bean>
```

第二种注入方式：通过有参构造器方式注入属性

1. 创建相应的类
``` java
/*
* 创建对象类
* */

public class Order {
    private String oname;
    private String oadress;

    public Order(String oname, String oadress) {
        this.oname = oname;
        this.oadress = oadress;
    }
}
```
2. 在spring配置文件中进行配置

``` xml
 <bean id="order" class="com.fanser.spring5.root.Order">
        <constructor-arg name="oname" value="电脑"></constructor-arg>
        <constructor-arg name="oadress" value="China"></constructor-arg>
<!--        <constructor-arg index="0" value="硬盘"></constructor-arg>-->
<!--        <constructor-arg index="1" value="江西"></constructor-arg>-->
</bean>

```
3. p名称空间注入（了解）

``` xml
 xmlns:p="http://www.springframework.org/schema/p"
 <bean id="book" class="com.fanser.spring5.root.Book" p:bname="java" p:bauthor="xyz"> </bean>
```

### IOC操作bean管理（xml注入其他类型属性）

- 注入属性：字面量（内容）
1. null值
`<property name="bname"><null/></property>`
2. 属性值含特殊符号
`<property name="bauthor"><value><![CDATA[<<ljr>>]]></value></property>`

- 注入属性：外部bean
1. 创建两个类 service类和dao类
2. 在service类中调用dao里面的方法
3. 在spring配置文件中进行配置

一般流程：
通过在service类中来创建dao类对象的实例来调用相应的方法
使用spring来简化这部分的操作：
使用bean来分别创建这两个类，创建相对应的类实例后，进行相应的赋值等操作，这里注意，一个类需要使用另一个类的方法时，需要对相应的类的bean的id进行一个类似“引用”的操作

``` xml
<!--    配置service 和dao 对象创建-->
        <bean id="userDao" class="com.fanser.spring5.dao.UserDaoImpl"></bean>
        <bean id="userService" class="com.fanser.spring5.service.UserServiceImpl">
<!--                注意这里对属性值的赋值，要求跟上面新初始化的对象名字统一
                    ref 内的内容为通过bean创建的id值，即对象名
 -->
                <property name="userDao" ref="userDao"></property>
        </bean>
```

- 注入属性：内部bean和级联赋值
``` xml
<!-- 内部bean -->

<bean id="emp" class="com.fanser.spring5.bean.Emp">
    <property name="ename" value="lucy"></property>
    <property name="gender" value="女"></property>
    <property name="dept">
        <bean id="dept" class="com.fanser.spring5.bean.Dept">
            <property name="dname" value="人事部"></property>
        </bean>
    </property>
</bean>

<!--    级联赋值 方式一 -->

<bean id="emp" class="com.fanser.spring5.bean.Emp">
    <property name="ename" value="lucy"></property>
    <property name="gender" value="女"></property>
    <property name="dept" ref="dept">
    </property>
</bean>
<bean id="dept" class="com.fanser.spring5.bean.Dept">
    <property name="dname" value="财务部"></property>
</bean>

<!-- 级联赋值 方式二 -->

<bean id="emp" class="com.fanser.spring5.bean.Emp">
    <property name="ename" value="lucy"></property>
    <property name="gender" value="女"></property>
    <property name="dept" ref="dept"></property>
    <property name="dept.dname" value="技术部">
    </property>
</bean>
```

注意：在级联赋值方式二中，需要相对应的get方法（一对多关系中多的一方）


注入集合（Collection type）属性：

与注入其他属性相似，但是由于集合是多个对象都要装进同一个集合中，所以xml文档的书写有小部分的不同之处。
需要将多个属性注入同一个集合中，所以直接通过value属性不足以完成全部操作。

``` xml
<!--    数组方式-->
    <bean id="stu" class="com.fanser.spring5demo2.collectiontype.root.Stu">
        <property name="courses">
            <array>
                <value>java</value>
                <value>php</value>
            </array>
        </property>

<!--    list集合方式-->

        <property name="list">
            <list>
                <value>java</value>
                <value>php</value>
            </list>
        </property>

<!--    map集合方式-->
        <property name="map">
            <map>
                <entry key="JAVA" value="java"></entry>
                <entry key="JS" value="javascript"></entry>
            </map>
        </property>
<!--    set集合方式-->
        <property name="sets">
            <set>
                <value>AA</value>
                <value>BB</value>
            </set>
        </property>
    </bean>
</beans>
```

这样也还是有一些不方便的地方，尝试把其中的属性从bean中抽取出来，这样调用的时候就能够实现不同的集合能够调用同样的内容了。所以接下来使用新的util方式来抽取属性内容。  

在xml文件默认的xs名称空间中调整其中的内容，添加上这一行（只需要把bean换成util即可）

``` xml
http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
```

然后是使用这部分的内容，就像之前学的p名称空间注入，都有特别的格式。

``` xml
<!--        提取list集合中注入的属性-->
        <util:list id="booklist">
            <value>java</value>
            <value>python</value>
            <value>c++</value>
        </util:list>
```

像这样提取出来的属性就能在多个集合中共享这部分内容了，进一步减少了代码的冗余。

### IOC操作Bean管理（FactoryBean）

Spring中有两种类型的bean，一种是普通bean，一种是工厂bean（FactoryBean）

1. 普通bean，配置文件定义的bean类型就是返回类型。
2. 工厂Bean，在配置文件定义的bean类型可以和返回类型不一致。 ps：其实有点类似多态的操作。


