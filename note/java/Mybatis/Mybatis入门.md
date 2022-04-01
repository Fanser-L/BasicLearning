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

#### curd

简而言之，增删改查



**mapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace=绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.fanser.dao.UserMapper">
    <!--   select id "就是指重写的方法的名字"-->
    <select id="getUserList" resultType="com.fanser.pojo.User">
        select *
        from mybatis.user;
    </select>
    <!--    为什么删了parameterType="int" 也能正确读取到其中的内容？ 因为从getUserById中传入的值只有一个int类型的，多个类型怎么办？-->
    <select id="getUserById" resultType="com.fanser.pojo.User" parameterType="int">
        select *
        from mybatis.user
        where id = #{id};
    </select>
    <insert id="insertUser" parameterType="com.fanser.pojo.User" useGeneratedKeys="true" keyProperty="id">
        insert into mybatis.user( id,name, pwd) values(id=#{id},name=#{name},pwd=#{pwd});
    </insert>
    <update id="updateUser" parameterType="com.fanser.pojo.User">
        update mybatis.user set name=#{name},pwd=#{pwd}  where id=#{id};
    </update>
    <delete id="deleteNull" parameterType="String">
        delete from mybatis.user where name is #{name};
    </delete>
</mapper>
```

另一方面就是接口和实现类

```java
public interface UserMapper {
    List<User> getUserList();
    User getUserById( int id);
    int insertUser(User user);
    int updateUser(User user);
    int deleteNull(String name);
}
```

- 通过`namespace`来定向到接口，如何再从 `mapper`内部的sql语句中查询对应`id`的方法(id的值其实就是方法名)

- parameterType是指传入值的类型，在jdbc中是使用 ？ 标识符来代替传入值，在mybatis中使用#{实体类属性}来传入字段

具体使用:

```java
package dao;

import com.fanser.dao.UserMapper;
import com.fanser.pojo.User;
import com.fanser.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MapperTest {
    @Test
    public void test(){
//        1、获取sqlSession
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        2、获取资源文件，执行sql语句
//        方式一：getMapper
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        sqlSession.close();
    }
    @Test
    public void insertUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int ww = mapper.insertUser(new User("王五",20, "123457"));
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int lucy = mapper.updateUser(new User("lucy", 1, "123312"));
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void deleteNull(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteNull(null);
        sqlSession.commit();
        sqlSession.close();
    }
}
```

![image-20220305185811662](C:\Users\86159\Desktop\Basic\note\java\Mybatis\image-20220305185811662.png)

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

#### 2.别名优化

**类型别名（typeAliases）**

类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写。例如：

```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
```

```xml
<select id="getUserList" resultType="com.fanser.pojo.User">
    select *
    from mybatis.user;
</select>
```

用于优化别名，这样的话，比如说使用上方的resultType，那我们就不许要吧全包名都打全了，只需要给他设个别名，自定义为user即可。

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如

```xml
<typeAliases>
  <package name="domain.blog"/>
</typeAliases>
```

这样的话就将整个包里的类都自动起别名了，别名为该类的首字母小写（其实直接使用原类名其实也不是不可以）

当然，这样的话相比对单个类的起别名来说会不会更加不方便一些呢？确实对自由的起别名变得没那么方便了，但也不是毫无办法，我们可以采用注解的方式来给相应的类起别名，如：

```java
@Alias("author")
public class Author {
    ...
}
```

#### 3.Settings

这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。 下表描述了设置中各项设置的含义、默认值等。

主要需要注意的有：

| 设置名             | 描述                                                         | 有效值        | 默认值 |
| :----------------- | :----------------------------------------------------------- | :------------ | :----- |
| cacheEnabled       | 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。     | true \| false | true   |
| lazyLoadingEnabled | 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 `fetchType` 属性来覆盖该项的开关状态。 | true \| false | false  |
| logImpl | 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。 | SLF4J \| LOG4J(deprecated since 3.5.9) \| LOG4J2 \| JDK_LOGGING \| COMMONS_LOGGING \| STDOUT_LOGGING \| NO_LOGGING | 未设置 |

这个logimpl中的log4j2，值得注意的是，这里的恰巧前一阵子出大bug了                 好像是sql注入的问题？反正问题严重。

...

#### 4.其他配置

- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins（插件） 插件可以从maven仓库中查找，目前学习阶段就不使用了

#### 5.映射器Mapper

之前创建第一个Mybatis项目时，我们使用了

```xml
<!--使用相对路径的方式来引入-->
<mappers>
    <mapper resource="com/fanser/dao/userMapper.xml"/>
</mappers>
```

用这种方式来映入Mapper.xml文件，但其实可以使用多种方式来实现xml文件的引入。

如：

```xml
<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
```

但是需要注意这种方式，使用类名来引入，那我们需要xml文件和类在同一文件夹目录下，且类名和xml文件名完全一致，否则将会报错。

```xml
<!-- 将包内的映射器接口实现全部注册为映射器 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

使用包来引入也是一样的，需要类名和xml文件名完全一致，且放在同一目录下。

还有一种方式就不做介绍和使用了。





Mapper

- 映射器是一些绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。

 映射器实例并不需要被显式地关闭。尽管在整个请求作用域保留映射器实例不会有什么问题，但是你很快会发现，在这个作用域上管理太多像 SqlSession 的资源会让你忙不过来。 因此，最好将映射器放在方法作用域内。就像下面的例子一样：

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
  BlogMapper mapper = session.getMapper(BlogMapper.class);
  // 你的应用逻辑代码
}
```

尽管现在似乎idea编译器会自动提示你需要将这部分的代码用try/catch来包裹住，防止资源损耗。



### 6、生命周期和作用域

作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的**并发问题**。

**对象生命周期和依赖注入框架**

依赖注入框架可以创建线程安全的、基于事务的 SqlSession 和映射器，并将它们直接注入到你的 bean 中，因此可以直接忽略它们的生命周期。 如果对如何通过依赖注入框架使用 MyBatis 感兴趣，可以研究一下 MyBatis-Spring 或 MyBatis-Guice 两个子项目。

**1.SqlSessionFactorybuilder**

- 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
- 局部变量（只需要使用一次即可，放到方法内是最好的选择）

**2.SqlSessionFactory**

- 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
-  SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，这样子是浪费资源的
- 可以把 SqlSessionFactory 想成和数据库连接池相类似的存在，一次创建即可，创建多个代表这创建多个对象，这样会造成资源浪费
- 最简单的就是使用单例模式或者静态单例模式。

**3.SqlSession**

- 每个线程都应该有它自己的 SqlSession 实例。
- SqlSession 的实例不是线程安全的，因此是不能被共享的。
- 注意每次使用后都要对SqlSession 进行关闭

这里要使用sql语句有两种方案，一种是创建mapper来间接的使用配置文件内的curd语句，另一种是直接使用它带有的增删改查方法来实现。一般还是使用mapper 的方式来实现会好一些，这样就不需要频繁的修改源代码了。



### 7、属性名和字段不一致的问题

```
Mysql:	name 	id 		pwd
User:	name	id		password
```

这种情况下读取到的password将为null值，如何解决这个问题?

解决方案：

- 暴力解决：起别名 select id,name,pwd as password from mybatis.user where id=#{}
- resultmap

#### 结果集映射：ResultMap

- `resultMap` 元素是 MyBatis 中最重要最强大的元素。

- `ResultMap` 的优秀之处——你完全可以不用显式地配置它们

`column`： 数据库中的字段  `property`:实体类中的属性

```xml
<!--    使用resultMap来代替了取别名的操作，同名的字段可以不用再另起别名-->
    <resultMap id="userMap" type="user">
        <result column="pwd" property="password"></result>
    </resultMap>

    <select id="getUserById"  resultMap="userMap">
        select *
        from mybatis.user
        where id = #{id};
    </select>
```

### 8、日志

#### 日志工厂

思考：我们在测试SQL的时候，要是能够在控制台输出 SQL 的话，是不是就能够有更快的排错效率？

当出现报错的时候，一般的标红比较难查找问题出现在了哪里，使用日志可以快速的定位错误地点。

使用settings，来实现日志的操作和管理

| logImpl | 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。 | SLF4J \| LOG4J(deprecated since 3.5.9) \| LOG4J2 \| JDK_LOGGING \| COMMONS_LOGGING \| STDOUT_LOGGING \| NO_LOGGING | 未设置 |      |
| ------- | ----------------------------------------------------- | ------------------------------------------------------------ | ------ | ---- |

- logImpl
  - SLF4J
  - LOG4J(deprecated since 3.5.9) 【掌握】
  - LOG4J2 
  -  JDK_LOGGING
  - COMMONS_LOGGING
  - STDOUT_LOGGING【掌握】
  - NO_LOGGING

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

```
Logging initialized using 'class org.apache.ibatis.logging.stdout.StdOutImpl' adapter.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
Opening JDBC Connection
Created connection 715378067.
Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@2aa3cd93]
==>  Preparing: select * from mybatis.user where id = ?; 
==> Parameters: 1(Integer)
<==    Columns: id, name, pwd
<==        Row: 1, lucy, 123312
<==      Total: 1
User{name='lucy', id=1, password='123312'}
Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@2aa3cd93]
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@2aa3cd93]
Returned connection 715378067 to pool.

Process finished with exit code 0

```



#### LOG4J

什么是log4j？

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件
- 我们也可以控制每一条日志的输出格式；
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
- 最令人感兴趣的就是，这些可以通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

快速开始：

1. 首先还是setting，对setting设置log4j

```xml
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
```

2. pom中导入相关依赖

```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

3. 在CLASSPATH下建立log4j.properties,建立日志设置的配置文件

```properties
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/fanser.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

4. 相应的修改其中属性，修改之前就必须知道这些都是干什么的

5. 在要输出日志的类中加入相关语句：

   定义属性：static Logger logger = Logger.getLogger(LogDemo.class); //LogDemo为相关的类

   **需要在哪打印出结果就引入哪个类，这里是使用反射来实现的这个操作会更好。**

   在相应的方法中：

   if (logger.isDebugEnabled()){

   logger.debug(“System …..”);

   }

**说明：**

**1、log4j.rootCategory=INFO, stdout , R**

此句为将等级为INFO的日志信息输出到stdout和R这两个目的地，stdout和R的定义在下面的代码，可以任意起名。等级可分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL，如果配置OFF则不打出任何信息，如果配置为INFO这样只显示INFO、WARN、ERROR的log信息，而DEBUG信息不会被显示

**2、log4j.appender.stdout=org.apache.log4j.ConsoleAppender**

此句为定义名为[stdout](https://baike.baidu.com/item/stdout)的输出端是哪种类型，可以是

org.apache.log4j.ConsoleAppender（控制台），

org.apache.log4j.FileAppender（文件），

org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），

org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）

org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）

**3、log4j.appender.stdout.layout=org.apache.log4j.PatternLayout**

此句为定义名为stdout的输出端的layout是哪种类型，可以是

org.apache.log4j.HTMLLayout（以[HTML](https://baike.baidu.com/item/HTML)表格形式布局），

org.apache.log4j.PatternLayout（可以灵活地指定布局模式），

org.apache.log4j.SimpleLayout（包含日志信息的级别和信息[字符串](https://baike.baidu.com/item/字符串)），

org.apache.log4j.TTCCLayout（包含日志产生的时间、[线程](https://baike.baidu.com/item/线程)、类别等等信息）

。。。。 参考百度百科

```
2022-03-05 15:45:31,720-[TS] INFO main com.fanser.dao.Testing - info:进入testLog4j
2022-03-05 15:45:31,722-[TS] ERROR main com.fanser.dao.Testing - error:进入testLog4j
```

这里是打印到了相应的文件中，会产生一个log日志，日志内就是相关的日志内容



### 9、分页

为什么要使用 limit 来进行分页？

- 减少数据的处理量

对于操作系统而言首先分页和分段都是为了更好的管理[内存](https://so.csdn.net/so/search?q=内存&spm=1001.2101.3001.7020)，是内存的管理方式。数据库中也是相似的。

语法：

```sql
SELECT * from user limit startIndes,pageSize; SELECT * from user limit 3; #[0,n]
```

使用Mybatis实现分页，核心SQL

接口

```java
   //分页查询
 List<User>  getUserByLimit(Map<String, Integer> map);
```

Mapper.xml

```xml
 <!--分页查询-->
<select id="getUserByLimit" parameterType="map" resultMap="UserMap">
    select * from mybatis.user limit #{startIndes},#{pageSize}
</select>
```

测试

```java
//分页查询
@Test
public void getUserByLimit(){

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    HashMap<String, Integer> map = new HashMap<String, Integer>();
    map.put("startIndes",0);
    map.put("pageSize",3);
    List<User> userList = mapper.getUserByLimit(map);
    for (User user : userList) {
        System.out.println(user);
    }
    sqlSession.close();

}
```
2、RowBouds分页
不再使用SQL实现分页

接口

```java
//分页查询2
List<User> getUserByRowBounds();
```


mapper.xml

```xml
<!--分页查询2-->
<select id="getUserByRowBounds" resultMap="UserMap">
    select * from mybatis.user
</select>
```
测试

```java
//分页查询2
@Test
public void getUserByRowBounds(){

    SqlSession sqlSession = MybatisUtils.getSqlSession();

    //RowBounds实现
    RowBounds rowBounds = new RowBounds(1,2);
    //通过java代码实现分页
    List<User> list = sqlSession.selectList("com.cfeng.dao.UserMapper.getUserByRowBounds", null, rowBounds);

    for (User user : list) {
        System.out.println(user);
    }
    sqlSession.close();
}
```
### 10、使用注解开发

- 使用注解来映射简单语句会使代码显得更加简洁，但对于稍微复杂一点的语句，Java 注解不仅力不从心，还会让你本就复杂的 SQL 语句更加混乱不堪。
- 如果你需要做一些很复杂的操作，最好用 XML 来映射语句。
- 你可以很轻松的在基于注解和 XML 的语句映射方式间自由移植和切换。

为什么只使用注解开发会力不从心？

sql语句中需要传入的参数的值可能非常多，这样的话，单单使用注解就很难解决复杂的问题了。比如说当需要使用 resultMap 结果集映射的方式给sql的字段取别名来获取其中的数据的时候，那我们使用注解就很难实现了。这样的话不如直接使用xml配置文件的方式简洁而优雅。

那么注解和xml语句可以同时使用吗？

当然可以，这两种方式都可以使用，简单的语句使用注解来开发会更加方便快捷，而复杂的语句交给xml会更加方便。

**CURD**

- @Select：

- @Insert：

- @Update：

- @Delete：

**@param()注解**

- 基本类型的参数或者String类型的参数需要加上
- 引用类型不需要添加
- 如果只有一个基本类型参数，可以不加，但建议还是加上
- 在SQL中引用的就是@param（）中设定的属性名



### 11、Mybatis执行流程

<img src="C:\Users\86159\Desktop\Basic\note\java\Mybatis\未命名文件.png" alt="未命名文件" style="zoom: 80%;" />



---------



### 12、多对一处理

> 环境搭建

#### Lombok的使用

> Project Lombok 是一个 java 库，可自动插入您的编辑器和构建工具，为您的 java 增添趣味。
> 永远不要再编写另一个 getter 或 equals 方法，使用一个注释，您的类就有一个功能齐全的构建器、自动化您的日志记录变量等等。

- java库
- 快速解决创建实体类的问题

```java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
experimental @var
@UtilityClass
```

#### MyBatis 中#{}和${}区别

**#{}** 是预编译处理，像传进来的数据会加个" "（#将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号）

**${}** 就是字符串替换。直接替换掉占位符。$方式一般用于传入数据库对象，例如传入表名.

如果使用了**@Parma**注解，那么注入的属性必须使用#{}，而不能使用${}

使用 ${} 的话会导致 sql 注入。什么是 SQL 注入呢？比如 select * from user where id = ${value}

value 应该是一个数值吧。然后如果对方传过来的是 001  and name = tom。这样不就相当于多加了一个条件嘛？把SQL语句直接写进来了。如果是攻击性的语句呢？001；drop table user，直接把表给删了

所以为了防止 SQL 注入，能用 #{} 的不要去用 ${}

如果非要用 ${} 的话，那要注意防止 SQL 注入问题，可以手动判定传入的变量，进行过滤，一般 SQL 注入会输入很长的一条 SQL 语句

> 示例

**实体类**

```java 
@Data
public class Teacher {
    private int id;
    private String name;
}
```

```java
@Data
public class Student {
    private int id;
    private String name;
    private int tid;
    private Teacher teacher;
}
```

**表结构**

```sql
CREATE TABLE `teacher` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师'); 

CREATE TABLE `student` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  `tid` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktid` (`tid`),
  CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');
```

多对一处理实际就是级联查询，但是在Mybatis中的复杂的查询似乎跟普通查询有所不同。

```xml
<mapper namespace="com.fanser.dao.StudentMapper">
    <select id="getStudent" resultType="Student">
        select * from student,teacher
    </select>
</mapper>
```

结果：

```
[Student(id=1, name=小明, tid=1, teacher=null),
Student(id=2, name=小红, tid=1, teacher=null),
Student(id=3, name=小张, tid=1, teacher=null), 
Student(id=4, name=小李, tid=1, teacher=null),
Student(id=5, name=小王, tid=1, teacher=null)]
```

发现并不能获取到老师的属性，为什么？

因为获取的teacher为一个复杂类型，在Student类中的teacher也是一个类。

复杂的属性： 对象使用 `asoccation`  集合使用 `collection`



如何解决这个问题？

思路：

​	先查询学生，然后根据学生信息中的tid来获取相对应的teacher

实现：

```xml
    <select id="getStudent" resultMap="StudentTeacher">
        select * from student
    </select>
    <resultMap id="StudentTeacher" type="Student">
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>

    <select id="getTeacher" resultType="Teacher">
        select * from teacher where id=#{id};
    </select>
```

这里有个很奇怪的地方，打印出teacher后，就无法打印tid了，除非再另写一行。

- bean中的属性名与数据库的字段名不同，无法省略<resultMap>。
- 不管有多少个属性，都得一一对应过去，所以得加上tid的result部分。

```
[Student{id=1, name='小明', tid=0, teacher=Teacher(id=1, name=秦老师)}
, Student{id=2, name='小红', tid=0, teacher=Teacher(id=1, name=秦老师)}
, Student{id=3, name='小张', tid=0, teacher=Teacher(id=1, name=秦老师)}
, Student{id=4, name='小李', tid=0, teacher=Teacher(id=1, name=秦老师)}
, Student{id=5, name='小王', tid=0, teacher=Teacher(id=1, name=秦老师)}
]
```

第二种实现思路：相关子查询

```xml
<select id="getStudent" resultMap="StudentTeacher">
    select s.name sname, s.id sid, t.name tname,s.tid,t.id
    from student s,teacher t
    where s.tid=t.id;
</select>
<resultMap id="StudentTeacher" type="Student">
    <result column="sname" property="name"/>
    <result column="sid" property="id"/>
    <result column="tid" property="tid"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
        <result property="id" column="id"/>
    </association>
</resultMap>
```

这里还是要特别注意，传出的类型为Student 类型，而在Student 类型中，我们使用了组合的方式来创建了一个teacher实体，所以在输出数据时，同样需要teacher的属性和字段。

```
[Student{id=1, name='小明', tid=1, teacher=Teacher(id=1, name=秦老师)}
, Student{id=2, name='小红', tid=1, teacher=Teacher(id=1, name=秦老师)}
, Student{id=3, name='小张', tid=1, teacher=Teacher(id=1, name=秦老师)}
, Student{id=4, name='小李', tid=1, teacher=Teacher(id=1, name=秦老师)}
, Student{id=5, name='小王', tid=1, teacher=Teacher(id=1, name=秦老师)}
]
```

### 13、一对多处理

**实体类**

```java
@Data
public class Teacher {
    private int id;
    private String name;
    private List<Student> students;
}
```

```java
@Data
public class Student {
    private int id;
    private String name;
    private int tid;
}
```

注意到与上面部分的区别没有，我们的实体属性上有些变化。

多对一，需要的是 “一” 的对象，而一对多的情况下，我们需要的是 “多” 的集合。

回到xml，刚才我们是无法查询到teacher对象，现在我们应该也会遇上查不到学生集合的问题。

所以还是需要使用 ResultMap 来解决查询到的属性为空的问题，这种时候就需要另一个关键字了 **collection**

然后使用 **javaType** 出现问题：

 Could not set property 'student' of 'class com.fanser.pojo.Teacher' with value 'Student{id=1, name='小明', tid=1}

发现集合类型不能使用JavaType来进行属性的导出，所以需要使用一个新的关键字**ofType**。

ofType关键字对应的类型是集合中泛型的类型，而javaType对应的则是java类型，java类型不仅包含类，同样也包括List类型。

```xml
<select id="getTeacher" resultMap="TeacherStudent" parameterType="int">
    select t.name tname,t.id tid,s.name sname,s.id sid
    from student s,teacher t
    where s.tid = t.id and t.id = #{id};
</select>
<resultMap id="TeacherStudent" type="Teacher">
    <result column="tid" property="id"/>
    <result column="tname" property="name"/>
    <collection property="student" column="tid" ofType="Student">
        <result column="sid" property="id"/>
        <result column="sname" property="name"/>
        <result column="tid" property="tid"/>
    </collection>
</resultMap>
```

结果：

```
Teacher(id=1, name=秦老师, student=[Student{id=1, name='小明', tid=1}
                                , Student{id=2, name='小红', tid=1}
                                , Student{id=3, name='小张', tid=1}
                                , Student{id=4, name='小李', tid=1}
                                , Student{id=5, name='小王', tid=1}
		])
```

同样的，我们也可以通过级联查找的方式来查询学生的信息：

```java
public interface TeacherMapper {
    public List<Teacher> getTeacher(int id);
    Teacher getTeacher2(@Param("tid") int id);
}
```

```xml
<select id="getTeacher2" resultMap="getStudentById">
    select * from teacher where id = #{tid}
</select>
<resultMap id="getStudentById" type="Teacher">
    <result property="id" column="tid"/>
    <collection property="student" column="id" javaType="ArrayList" ofType="Student" select="getStudentById"/>
</resultMap>
<select id="getStudentById" resultType="Student">
    select * from student where tid=#{tid}
</select>

```

结果：

```
Teacher(id=0, name=秦老师, student=[Student{id=1, name='小明', tid=1}
, Student{id=2, name='小红', tid=1}
, Student{id=3, name='小张', tid=1}
, Student{id=4, name='小李', tid=1}
, Student{id=5, name='小王', tid=1}
])
```

总结：

- 查询的方式可以是多样的，但是要注意的就是别名。每个属性的内部属性值要与之相匹配。
- 当属性不匹配的时候会出现查找为null的现象
- java类型使用**javaType**，数组类型级联查找的情况下还需要添加上**ofType**
- 集合类型还需要加上集合中的泛型的类型，使用**oftype**

### 14、动态 SQL

- 动态 SQL 是 MyBatis 的强大特性之一。
- 如果你使用过 JDBC 或其它类似的框架，你应该能理解根据不同条件拼接 SQL 语句有多痛苦，例如拼接时要确保不能忘记添加必要的空格，还要注意去掉列表最后一个列名的逗号。

如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

- if
- choose (when, otherwise)
- trim (where, set)
- foreach



**环境搭建：**

```xml
    <settings>
<!--        普通日志-->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
<!--        自动替换驼峰命名-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
```

> 说白了动态sql其实就是方便写sql语句用的，而不是想象中的动态生成sql语句。

#### **if**

```xml
<select id="findActiveBlogWithTitleLike"
     resultType="Blog">
  SELECT * FROM BLOG
  WHERE state = ‘ACTIVE’
  <if test="title != null">
    AND title like #{title}
  </if>
</select>
```

来翻译一下这段，其实就是本来sql中是不太合适去写逻辑结构的语句的，但是我们因为是在xml文件中书写的sql语句，那就有了一些操作空间，可以使用一些标签来完成一些逻辑结构的控制。

#### choose、when、otherwise

有时候，我们不想使用所有的条件，而只是想从多个条件中选择一个使用。针对这种情况，MyBatis 提供了 choose 元素，它有点像 Java 中的 switch 语句。

很多用法上确实很是相似，choose-->switch  、 when -->case 、otherwise -->default

那我们能通过这些标签干嘛？当然是选择结构了！where后的筛选条件的选择，但是要注意的是，这种情况下的选择一般是要有前置的内容的，也就是说  

```sql 
...
select * 
from student 
where xxxx  
<choose>
	<when test="title != null">
	AND title like #{title}
	<when>
</choose>
...
```

#### trim、where、set

前面几个例子已经方便地解决了一个臭名昭著的动态 SQL 问题。现在回到之前的 “if” 示例，这次我们将where后的第一个条件设置成动态条件，看看会发生什么。

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  WHERE
  <if test="state != null">
    state = #{state}
  </if>
  <if test="title != null">
    AND title like #{title}
  </if>
  <if test="author != null and author.name != null">
    AND author_name like #{author.name}
  </if>
</select>
```

如果没有一个匹配项，那就有问题了！ 实际运行的sql语句的效果：

```sql
SELECT * FROM BLOG
WHERE
```

这个sql语句显然有些问题，查询肯定会出错，不仅如此，如果匹配的是第二种情况，那么：

```sql
SELECT * FROM BLOG
WHERE
AND title like ‘someTitle’
```

where 和 and 一起了，显然这个sql语句也是不能正常执行的。

**那么如何解决这个动态sql语句里关键字不匹配的问题呢？**

这种情况下我们就需要新的标签来代替我们完成**检查sql语句并修改**的工作了！

> *where* 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，*where* 元素也会将它们去除。

讲人话：where 元素只会在至少有一个子元素的条件返回 SQL 子句的情况下才去插入“WHERE”子句。而且，若语句的开头为“AND”或“OR”，where 元素也会将它们去除。

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  <where>
    <if test="state != null">
         state = #{state}
    </if>
    <if test="title != null">
        AND title like #{title}
    </if>
    <if test="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
  </where>
</select>
```

看起来仿佛没啥变化，只是添加了一个<where>标签，但实际用处还挺多的，比如说上面存在的问题，当匹配的语句关键字冲突的情况，如果where和and  或者 or 等等冲突了，**where标签可以自动给你加上/删去**，这样的话就可以解决不匹配问题导致的sql语句的语法问题了！



如果 *where* 元素与你期望的不太一样，你也可以通过自定义 trim 元素来定制 *where* 元素的功能。比如，和 *where* 元素等价的自定义 trim 元素为：

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>
```

*prefixOverrides* 属性会忽略通过管道符分隔的文本序列（注意此例中的空格是必要的）。上述例子会移除所有 *prefixOverrides* 属性中指定的内容，并且插入 *prefix* 属性中指定的内容。

？？？ 官方你就给这点文档？真是不让人好好看啊！

说人话：使用 trim 元素可以完成where 的工作，为什么？因为trim 可以是一个私人订制的效果，说白了，它完全可以是你想要的效果的样子，而`prefixOverrides`属性就是拿来替换用的，prefixOverrides 直译不就是替换前置的关键字嘛，替换AND |OR 效果与where一致了。

具体还有些属性：

| 属性 | 描述 |
|:--|:--|
|prefix|	给sql语句拼接的前缀|
|suffix |	给sql语句拼接的后缀|
|prefixOverrides	|去除sql语句前面的关键字或者字符，该关键字或者字符由prefixOverrides属性指定，假设该属性指定为"AND"，当sql语句的开头为"AND"，trim标签将会去除该"AND"|
|suffixOverrides|	去除sql语句后面的关键字或者字符，该关键字或者字符由suffixOverrides属性指定|

suffixOverrides 在哪能用上呢？假设有这样一个sql语句：

```sql
insert into student
(	<if test="id!=null">
		id,
	</if>
	<if test="name!=null">
		name
	</if>)
values
(	<if test="id!=null">
		#{id},
	</if>
	<if test="name!=null">
		#{name}
	</if>)
```

如果name为null，那么实际执行的sql语句会是怎么样的呢？

```sql
insert into student(id,)values(#{id},)
```

看出问题了嘛？这样会导致多余的 `,`存在，这样的话那就无法正常编译了，所以可以通过添加 suffixOverride 属性来消除多余的`,`

```xml
insert into student
(	<trim prifix="(" suffix=")" suffixOverride=",">
        <if test="id!=null">
            id,
        </if>
        <if test="name!=null">
            name
        </if>
    </trim>)
values
(	<trim prifix="(" suffix=")" suffixOverride=",">
    <if test="id!=null">
		#{id},
	</if>
	<if test="name!=null">
		#{name}
	</if>
    </trim>)
```

这样就把最后多余的逗号去除掉了。



用于动态更新语句的类似解决方案叫做 *set*。*set* 元素可以用于动态包含需要更新的列，忽略其它不更新的列。比如：

```xml
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```

这个例子中，*set* 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）。

来看看与 *set* 元素等价的自定义 *trim* 元素吧：

```xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```



对比来看，官方的例子是真的写的不好。。。

#### foreach

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。比如：

```xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  <where>
    <foreach item="item" index="index" collection="list"
        open="ID in (" separator="," close=")" nullable="true">
          #{item}
    </foreach>
  </where>
</select>
```

*foreach* 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符，看它多智能！

看不懂！ 这写的什么？还搁这智能？

``` xml
  <where>
    <foreach item="item" index="index" collection="list"
        open="ID in (" separator="," close=")" nullable="true">
          #{item}
    </foreach>
  </where>
```

来仔细分析一下这段，首先是使用了foreach 元素，内部有几个属性：**item，index，collection，open，nullable** 看看认识的有几个？ 首先看出点东西的是 index，应该是指索引，而 foreach 又是用于遍历用的，结合 collection 属性大胆猜测是用于记录下标用的，而open 属性的值中含有 id ‘ in ’  （***）。是代表遍历list集合，list集合中的元素别名为item，遍历的位置为index，语句在 ”（ “ 处开始，使用 “，”来分割，在 “）” 处结束，nullable 代表是否可以为空，是可以为空的。



来点人家总结的：

foreach 标签主要有以下属性，说明如下。

- item：表示集合中每一个元素进行迭代时的别名。
- index：指定一个名字，表示在迭代过程中每次迭代到的位置。
- open：表示该语句以什么开始（既然是 in 条件语句，所以必然以`(`开始）。
- separator：表示在每次进行迭代之间以什么符号作为分隔符（既然是 in 条件语句，所以必然以`,`作为分隔符）。
- close：表示该语句以什么结束（既然是 in 条件语句，所以必然以`)`开始）。


使用 foreach 标签时，最关键、最容易出错的是 collection 属性，该属性是必选的，但在不同情况下该属性的值是不一样的，主要有以下 3 种情况：

- 如果传入的是单参数且参数类型是一个 List，collection 属性值为 list。
- 如果传入的是单参数且参数类型是一个 array 数组，collection 的属性值为 array。
- 如果传入的参数是多个，需要把它们封装成一个 Map，当然单参数也可以封装成 Map。Map 的 key 是参数名，collection 属性值是传入的 List 或 array 对象在自己封装的 Map 中的 key。

#### SQL

有点像spring中的集合的概念，抽取常用的部分，来保存到xml的某个标签内，需要使用重复的sql子句的时候，调用这部分的内容即可。



### 15、MyBatis缓存

MyBatis 内置了一个强大的事务性查询缓存机制，它可以非常方便地配置和定制。

 为了使它更加强大而且易于配置，我们对 MyBatis 3 中的缓存实现进行了许多改进。

默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：

> <cache/>

基本上就是这样。这个简单语句的效果如下:

- 映射语句文件中的所有 select 语句的结果将会被缓存。
- 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
- 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
- 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
- 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。

**提示** 缓存只作用于 cache 标签所在的映射文件中的语句。如果你混合使用 Java API 和 XML 映射文件，在共用接口中的语句将不会被默认缓存。你需要使用 @CacheNamespaceRef 注解指定缓存作用域。

--------



缓存可以将数据保存在内存中，是互联网系统常常用到的。目前流行的缓存服务器有 MongoDB、Redis、Ehcache 等。缓存是在计算机内存上保存的数据，读取时无需再从磁盘读入，因此具备快速读取和使用的特点。

和大多数持久化框架一样，MyBatis 提供了一级缓存和二级缓存的支持。默认情况下，MyBatis 只开启一级缓存。

#### 一级缓存

一级缓存是基于 PerpetualCache（MyBatis自带）的 HashMap 本地缓存，作用范围为 session 域内。当 session flush（刷新）或者 close（关闭）之后，该 session 中所有的 cache（缓存）就会被清空。

在参数和 SQL 完全一样的情况下，我们使用同一个 SqlSession 对象调用同一个 mapper 的方法，往往只执行一次 SQL。因为使用 SqlSession 第一次查询后，MyBatis 会将其放在缓存中，再次查询时，如果没有刷新，并且缓存没有超时的情况下，SqlSession 会取出当前缓存的数据，而不会再次发送 SQL 到数据库。

由于 SqlSession 是相互隔离的，所以如果你使用不同的 SqlSession 对象，即使调用相同的 Mapper、参数和方法，MyBatis 还是会再次发送 SQL 到数据库执行，返回结果。

SqlSession 实际只发生过一次查询，而第二次查询就从缓存中取出了，也就是 SqlSession 层面的一级缓存。为了克服这个问题，我们往往需要配置**二级缓存**，**使得缓存在 SqlSessionFactory 层面上能够提供给各个 SqlSession 对象共享。**

**缓存失效的情况：**

- 查询不同的语句
- 增删改操作
- 查询不同的mapper.xml
- 手动刷新缓存 .clearcache();

#### 二级缓存

二级缓存是全局缓存，作用域超出 session 范围之外，可以被所有 SqlSession 共享。

一级缓存缓存的是 SQL 语句，二级缓存缓存的是结果对象。

MyBatis 的全局缓存配置需要在 mybatis-config.xml 的 settings 元素中设置，代码如下。

```xml
<settings>    <setting name="cacheEnabled" value="true" /></settings>
```

在 mapper 文件中设置缓存，默认不开启缓存。需要注意的是，二级缓存的作用域是针对 mapper 的 namescape 而言，即只有再次在 namescape 内的查询才能共享这个缓存，代码如下。

```xml
<mapper namescape="net.biancheng.WebsiteMapper">
    <!-- cache配置 -->    
    <cache eviction="FIFO" flushInterval="60000"  size="512" readOnly="true" />
    ...
</mapper>
```

以上属性说明如下。

| 属性          | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| eviction      | 代表的是缓存回收策略，目前 MyBatis 提供以下策略。LRU：使用较少，移除最长时间不用的对象；FIFO：先进先出，按对象进入缓存的顺序来移除它们；SOFT：软引用，移除基于垃圾回收器状态和软引用规则的对象；WEAK：弱引用，更积极地移除基于垃圾收集器状态和弱引用规则的对象。 |
| flushInterval | 刷新间隔时间，单位为毫秒，这里配置的是 100 秒刷新，如果省略该配置，那么只有当 SQL 被执行的时候才会刷新缓存。 |
| size          | 引用数目，正整数，代表缓存最多可以存储多少个对象，不宜设置过大。设置过大会导致内存溢出。这里配置的是 1024 个对象。 |
| readOnly      | 只读，默认值为 false，意味着缓存数据只能读取而不能修改，这样设置的好处是可以快速读取缓存，缺点是没有办法修改缓存。 |

在 mapper 文件配置支持 cache 后，如果需要对个别查询进行调整，可以单独设置 cache，代码如下。

```xml
<select id="getWebsiteList" resultType="net.biancheng.po.Website" usecache="true">    ...</select>
```

对于 MyBatis 缓存仅作了解即可，因为面对一定规模的数据量，内置的 Cache 方式就派不上用场了，并且对查询结果集做缓存并不是 MyBatis 所擅长的，它专心做的应该是 SQL 映射。对于缓存，采用 OSCache、**Memcached** 等专门的缓存服务器来做更为合理。



总结：当读取数据的时候，Mybatis是优先从二级缓存中读取的，当二级缓存中没有的时候再从数据库中查询，然后存放到一级缓存中，如果设置了cache，那么会将一级缓存中的内容保留至二级缓存中，以至于关闭了SQLSession后也能更快的访问之前访问过的sql。

### Mybatis逆向工程

MyBatis Generator (MBG) 是 MyBatis [MyBatis](http://mybatis.org/)的代码生成器。它将为所有版本的 MyBatis 生成代码。它将内省一个数据库表（或许多表），并将生成可用于访问表的工件。这减少了设置对象和配置文件以与数据库表交互的初始麻烦。MBG 试图对大部分简单 CRUD（创建、检索、更新、删除）的数据库操作产生重大影响。您仍然需要为联接查询或存储过程编写 SQL 和对象代码。

其实是一个插件，需要使用mybatis-generator 这个工具来完成这些工作，它可以完成逆向的生成pojo 类和 mapper.xml 文件和 mapper 接口。

完结撒花！
