## 一、Mybatis

- MyBatis本是apache的一个[开源项目](https://baike.baidu.com/item/开源项目/3406069)iBatis，2010年这个[项目](https://baike.baidu.com/item/项目/477803)由apache software foundation迁移到了[google code](https://baike.baidu.com/item/google code/2346604)，并且改名为
  MyBatis。2013年11月迁移到[Github](https://baike.baidu.com/item/Github/10145341)
- 优点：
  - 简单易学
  - 灵活
  - sql和代码的分离，提高了可维护性。
  - 提供映射标签，支持对象与数据库的orm字段关系映射。
  - 提供对象关系映射标签，支持对象关系组建维护。
  - 提供xml标签，支持编写动态sql。
  - 用户群体最大

- MyBatis 是一款优秀的**持久层**框架，它支持自定义 SQL、存储过程以及高级映射。
- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。



### 持久化

**什么是持久化？**

持久化是将程序数据在[持久](https://baike.baidu.com/item/持久/5702771)状态和[瞬时](https://baike.baidu.com/item/瞬时/3471916)状态间转换的机制。通俗的讲，就是瞬时数据（比如内存中的数据，是不能永久保存的）持久化为持久数据（比如持久化至数据库中，能够长久保存）。

就是将瞬时状态的数据转化成持久状态。

通俗点将，就是保存，比如说把肉放冰箱里，然后来长时间保存肉，通过罐头的方式来长期保存食品等等。

数据持久化：数据库（jdbc），io文件持久化

**为什么要持久化？**

有些对象/数据不能丢失

内存也比较贵

**什么是持久层**

- 完成持久化工作的代码块

dao、service、controller.....

层之间的界限明显

### 1、 环境配置

- idea

- maven-3.6.1

- jdk1.8

- Junit

### 2、Mybatis使用

首先是项目文件的配置，通过maven的父项目来更好的解决子项目的依赖问题

```xml
<!--    父工程-->
    <groupId>org.example</groupId>
    <artifactId>Mybatis</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
<!--    子工程-->
    <modules>
        <module>demo-01</module>
    </modules>
```

导入mybatis依赖

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.2</version>
</dependency>
```


MyabtisUtils配置（获取连接）

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// sqlSessionFactory 工厂模式来构建sqlSession
public class MybatisUtils {
    //扩大作用域，方便调用
    private static SqlSessionFactory sqlSessionFactory;
    //静态代码块来确保资源被加载，相当于jdbc preparedStatement() 来获取sql语句和资源。
    static {
        {
            try {
                String resource = "mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static SqlSession getSqlSession(){
        //直接使用不了，需要在上方扩大作用域
        return sqlSessionFactory.openSession();
    }

}
```

获取sql语句 -->Mapper.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace=绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.fanser.dao.UserDao">
<!--   select id "就是指重写的方法的名字"-->
    <select id="getUserList" resultType="com.fanser.pojo.User">
        select * from mybatis.user;
    </select>
</mapper>
```

对表中数据进行遍历

### 3、测试

容易出现的错误：

- BindingException: Type interface com.fanser.dao.UserDao is not known to the MapperRegistry.

这里代表配置文件中的Mapper.xml文件没有导入

解决方案就是在mybatis配置文件中配置<mappers></mappers>中注册mapper.xml的文件路径，其中有class和resource等属性，填入具体路径即可。

**注意**：在xml配置文件中，&标识符需要加入特殊标识才能被判断为标识符  在xml文件中 `&`符号的正确表示方式为`&amp;`

```xml
<!--数据库连接核心配置-->
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?uslSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
<!--    每一个Mapper.xml都需要在配置文件中进行注册-->
    <mappers>
        <mapper resource="com/fanser/dao/userMapper.xml"/>
    </mappers>
```

- IOException: Could not find resource com/fanser/dao/userMapper.xml

意思是找不到相对应的xml资源文件，一般情况下的maven资源文件都放在resource文件夹中，放在其他文件夹中会无法读取，所以解决方案是添加pom.xml中文件过滤。

```xml
       <build>
            <resources>
                <resource>
                    <directory>src/main/java</directory>
                    <includes>
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>true</filtering>
                </resource>
                <resource>
                    <directory>src/main/resources</directory>
                    <includes>
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>true</filtering>
                </resource>
            </resources>
        </build>
```

不同文件目录的情况下，参考路径来进行配置即可。



#### 常见错误

- 标签不能匹配错误
- 空指针异常，申请xml文件资源时重定义了
- resource必须绑定mapper
- 重复插入同样的主键值 

### 4、Map和模糊查询

使用Map的优点：

- 可以定制化传入sql语句的参数，不需要根据实体类完全一致的传入
- 可以传入可变个参数值，只传入一个值的情况下也可以实现
- Map的类型定义比较简单，需要传入什么就往里丢什么

```java
Map<String,Object> map =  new HashMap<String,Obejct>();
```



使用Like模糊查询时：

传入 #{} 的内容应该加上通配符，而不应该在语句上做修改，如 select * form user where name like %#{}%

这样可能会有sql注入的问题存在。

### 5、配置解析

- mybatis-config.xml
- MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：

- configuration（配置）
  - [properties（属性）](https://mybatis.org/mybatis-3/zh/configuration.html#properties)
  - [settings（设置）](https://mybatis.org/mybatis-3/zh/configuration.html#settings)
  - [typeAliases（类型别名）](https://mybatis.org/mybatis-3/zh/configuration.html#typeAliases)
  - [typeHandlers（类型处理器）](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
  - [objectFactory（对象工厂）](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
  - [plugins（插件）](https://mybatis.org/mybatis-3/zh/configuration.html#plugins)
  - environments（环境配置）
    - environment（环境变量）
      - transactionManager（事务管理器）
      - dataSource（数据源）
  - [databaseIdProvider（数据库厂商标识）](https://mybatis.org/mybatis-3/zh/configuration.html#databaseIdProvider)
  - [mappers（映射器）](https://mybatis.org/mybatis-3/zh/configuration.html#mappers)

#### 1. 外部配置文件

经典的外部配置文件有：db.properties 一般用于连接数据的数据会存放在这部分中

在Mybatis的配置文件中可以使用properties标签来引入外部配置文件，然后读取配置文件中的配置

```xml
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?uslSSL=true&amp;
                useUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
```

这部分的内容中 value的值原本是使用${drive}等标识符来代替其中的内容的，目的就是为了更好的从配置文件中读取这些配置。

因此，我们可以改成这样：

```xml
    <properties resource="db.properties">
        <property name="username" value="root"/>
    </properties>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}}"/>
                <property name="url" value="${url}}"/>
                <property name="username" value="${username}}"/>
                <property name="password" value="${password}}"/>
            </dataSource>
        </environment>
    </environments>
```

注意这里的properties，同时有在xml文件中写入的部分，但也有外部引入的部分，这里我们优先使用的是外部引入的配置，内部的配置不一定会生效。

如果一个属性在不只一个地方进行了配置，那么，MyBatis 将按照下面的顺序来加载：

- 首先读取在 properties 元素体内指定的属性。
- 然后根据 properties 元素中的 resource 属性读取类路径下属性文件，或根据 url 属性指定的路径读取属性文件，并覆盖之前读取过的同名属性。
- 最后读取作为方法参数传递的属性，并覆盖之前读取过的同名属性。

因此，通过方法参数传递的属性具有最高优先级，resource/url 属性中指定的配置文件次之，最低优先级的则是 properties 元素中指定的属性。

**事务管理器（transactionManager）**

在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"）：

- JDBC – 这个配置直接使用了 JDBC 的提交和回滚设施，它依赖从数据源获得的连接来管理事务作用域。
- MANAGED – 这个配置几乎没做什么。它从不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。 默认情况下它会关闭连接。然而一些容器并不希望连接被关闭，因此需要将 closeConnection 属性设置为 false 来阻止默认的关闭行为。例如:

总之知道mybatis中的事务管理器不只有jdbc一种即可，还有一个managed，这种是用于老的技术的一种事务管理器，新的spring中完全不需要使用。
