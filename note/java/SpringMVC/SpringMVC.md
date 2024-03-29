# MVC

什么是**mvc**框架？

了解一下MVC框架的前身：

![](https://s2.loli.net/2022/03/26/Iv5KZ8FclHnGw94.png)

以前是没有model层的，基本上所有的请求都是由Controller层来处理的，几乎所有的功能都聚合到了这一层上，使代码显得非常臃肿可维护性很差，为了解决这个问题，于是乎有了新的办法，那就再分一层！ 成为了我们现在使用的MVC框架。

![image-20220312155749040](https://s2.loli.net/2022/03/26/z3QxwUBr4m71hij.png)

经典MVC模式中，M是指业务模型，V是指用户界面，C则是控制器，使用MVC的目的是将M和V的实现[代码](https://baike.baidu.com/item/代码/86048)分离，从而使同一个程序可以使用不同的表现形式。其中，View的定义比较清晰，就是用户界面。



![img](https://upload-images.jianshu.io/upload_images/1858247-f517d209e937a435.png?imageMogr2/auto-orient/strip|imageView2/2/w/601/format/webp)

在android 学习中了解了另一种框架，叫做MVP框架。

​	![img](https://upload-images.jianshu.io/upload_images/1858247-57db1c3f3fa9380d.png?imageMogr2/auto-orient/strip|imageView2/2/w/822/format/webp)

Spring Web MVC 是基于 Servlet API 构建的原始 Web 框架，从一开始就包含在 Spring Framework 中。

正式名称“Spring Web MVC”来自其源模块的名称（[`spring-webmvc`](https://github.com/spring-projects/spring-framework/tree/main/spring-webmvc)），但更常见的是“Spring MVC”。

与 Spring Web MVC 并行，Spring Framework 5.0 引入了一个响应式堆栈 Web 框架，其名称“Spring WebFlux”也是基于其源模块 ( [`spring-webflux`](https://github.com/spring-projects/spring-framework/tree/main/spring-webflux))。

## Spring MVC Framework的优点

让我们看看Spring MVC Framework的一些优点: -

**分离角色** - Spring MVC分离每个角色，其中可以由专门的对象来实现模型对象，控制器，命令对象，视图解析器，DispatcherServlet，验证器等。

**轻量 **- 它使用轻巧的servlet容器来开发和部署您的应用程序。

**强大的配置 **- 它为框架和应用程序类提供了可靠的配置，其中包括跨上下文的轻松引用，例如从Web控制器到业务对象和验证器。

**快速开发 **- Spring MVC促进了快速并行的开发。

**可重复使用的业务代码 **- 无需创建新对象，它使我们可以使用现有业务对象。

**易于测试 **- 在Spring中，通常我们创建JavaBeans类，使您可以使用setter方法注入测试数据。

**灵活的映射** - 它提供了可轻松重定向页面的特定注释。

----

回顾一下javaweb我们干了些什么？

- 首先学了网页开发的基础技术：HTML、css、JavaScript ，使用这些技术可以书写一些简单的网页。

- 后来学了xml文件的一些基础，基本配置和语法。约束分别有哪些？

  约束有dtd和schema

- 再接下来是http网络协议，为什么要学http网络协议呢？http版本有哪些？

  作为一个开发人员，掌握必要的 HTTP 协议十分重要，下面就通过本文记录自己对 HTTP 协议的理解。本文很长，希望你有耐心看完，会有很多收获的，面试的时候很受用。

- http请求消息是干嘛用的，为什么学？

  超文本传输协议，是互联网应用最为广泛的一种网络协议，所有的 www 文件都必须遵守这个标准。

  HTTP 可以分为两个部分，即请求和响应。

  - HTTP 是无连接无状态的
  - HTTP 一般构建于 TCP/IP 协议之上，默认端口号是 80

- 请求行和请求消息头？响应行和响应消息头？

  请求头提供一些参数比如：Cookie，用户代理信息，主机名等等
  请求正文就放一些发送的数据，一般 GET 请求会将参数放在 URL 中，也就是在请求头中而请求正文一般为空，而 POST 请求将参数放在请求正文中。请求正文可以传一些 json 数据或者字符串等等。

  响应头：1是报文协议及版本，2是状态码及描述。

  响应状态码：2xx  3xx  4xx  5xx

- tomcat

  ![image-20220312000126913](https://s2.loli.net/2022/03/26/GqcASMk9eBtEvFQ.png)

  文件的结构，注意work目录是干嘛用的。

- servlet、HttpServlet类

  Servlet：其主要功能在于交互式地浏览和修改数据
  Servlet的作用：

  ​      1.接收用户发送的请求

  ​      2.调用其他的java程序来处理请求

  ​      3.根据处理结果，返回给用户一个页面

  HttpServlet 指能够处理 HTTP 请求的 servlet，它在原有 Servlet 接口上添加了一些与 HTTP 协议处理方法，它比 Servlet 接口的功能更为强大
  HttpServlet继承了GenericServlet，而GenericServlet实现Servlet接口

- 请求和响应（respect|response）、请求转发
  大部分情况下我们其实使用的都是其中的一个方法，写代码的时候重写doGet或者doPost方法中一个的时候，另一个方法总是会引用重写过的那个方法。

- 会话技术：cookie and session
  说简单点，cookie是在浏览器上的缓存，session是服务器端的缓存

- jsp技术
  几个重要的部分（标签） <%java代码，变量，方法%>  （局部变量）
  声明语句 <%! 定义的变量方法等%>（方法体外的变量）

  隐式对象：在jsp中提供了9 个内置的隐式对象 比如说 out 、request、response、config...等等

  指令
  page 指令,一般而言是生成jsp页面时会自动生成的一段代码

  ```jsp
  <%@ page language="java" ....%>
  ```

  include 指令，在jsp页面内静态的包含一个文件

  ```jsp
  <%@ include file="被包含的文件地址"%>
  ```

- EL表达式和JSTL
  了解EL表达式之前需要先了解一下javaBean ，这里引申一下，使用BeanUtils能够动态的访问Java对象，但是使用需要先下载一些支持，如logging包，需要一个 commons-logging.jar 的jar包，当然在学习Spring的时候其实也使用过这个jar包，有maven的情况下可以直接使用maven来导入。
  EL表达式： ${表达式}
  与jsp一样，也有一些隐式对象 如pageContext，pageScope等等

- servlet高级  filter过滤器  Listen监听器
  过滤器：跟servlet的使用方式很相似，都是继承自某个类，然后同样需要在web.xml中进行配置
  干嘛用的？过滤一些垃圾请求，还有处理一些乱码的问题等等

  Listener：也差不多，配置上更为简单一些，一般而言是于监听session的创建等等。内置了一大堆的监听方法，数都

- jdbc  数据连接池和DButils工具
  这部分的内容在jdbc中有详细的，DButils建议还是自己重新书写一遍，数据连接池常用的C3P0，DBCP，Druid，HikariCP

- jsp开发模型  MVC设计模式

- 文件的上传与下载

**MVC框架要做哪些事情**

1. 将url映射到java类或java类的方法 .
2. 封装用户提交的数据 .
3. 处理请求--调用相关的业务处理--封装响应数据 .
4. 将响应的数据进行渲染 . jsp / html 等表示层数据 .

常见的服务器端MVC框架有：Struts、Spring MVC、ASP.NET MVC、Zend Framework、JSF；常见前端MVC框架：vue、angularjs、react、backbone；由MVC演化出了另外一些模式如：MVP、MVVM 等等....

Spring MVC是Spring Framework的一部分，是基于Java实现MVC的轻量级Web框架。

查看官方文档：https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/web.html#spring-web

**我们为什么要学习SpringMVC呢?**

Spring MVC的特点：

1. 轻量级，简单易学
2. 高效 , 基于请求响应的MVC框架
3. 与Spring兼容性好，无缝结合
4. 约定优于配置
5. 功能强大：RESTful、数据验证、格式化、本地化、主题等
6. 简洁灵活

Spring的web框架围绕**DispatcherServlet** [ 调度Servlet ] 设计。

DispatcherServlet的作用是将请求分发到不同的处理器。从Spring 2.5开始，使用Java 5或者以上版本的用户可以采用基于注解形式进行开发，十分简洁；

正因为SpringMVC好 , 简单 , 便捷 , 易学 , 天生和Spring无缝集成(使用SpringIoC和Aop) , 使用约定优于配置 . 能够进行简单的junit测试 . 支持Restful风格 .异常处理 , 本地化 , 国际化 , 数据验证 , 类型转换 , 拦截器 等等…所以我们要学习 .

最主要的原因：使用的人非常的多

- Spring的web框架围绕DispatcherServlet设计。DispatcherServlet的作用是将请求分发到不同的处理器。从Spring 2.5开始，使用Java 5或者以上版本的用户可以采用基于注解的controller声明方式。

![在这里插入图片描述](https://s2.loli.net/2022/03/26/SPnk2jR1AM65rgu.png)

DispatcherServlet：看起来有点熟悉，是不是很像servlet中的请求转发。实际上就是继承了httpServlet，然后本身也可以完全认为就是一个Servlet。

## SpringMVC的执行原理

![在这里插入图片描述](https://s2.loli.net/2022/03/26/1N4eMGRpAK7Qvsj.png)

实线表示框架帮助我们实现的部分，不需要程序员动手解决的部分。虚线表示需要开发者实现的部分。2、

还是从 DispatcherServlet 说起

1. DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心，用户发出请求 DispatcherServlet 接收并拦截部分请求。

   - 我们假设请求的url为 : http://localhost:8080/SpringMVC/hello

   - **如上url拆分成三部分：**

   - [http://localhost:8080](http://localhost:8080/) ------> 服务器域名

   - SpringMVC ------> 部署在服务器上的web站点

   - hello ------> 表示控制器

   - 通过分析，如上url表示为：请求位于服务器localhost:8080上的SpringMVC站点的hello控制器。

2. HandlerMapping为处理映射器。DispatcherServlet调用HandlerMapping,HandlerMapping根据请求url查找Handler。

3. HandlerExecution表示具体的Handler,其主要作用是根据url查找控制器，如上url被查找控制器为：hello。

4. HandlerExecution将解析后的信息传回给DispatcherServlet,如解析控制器映射等。

5. HandlerAdapter表示处理器适配器，其按照特定的规则去执行Handler。

6. Handler让具体的Controller执行。

7. Controller将具体的执行信息返回给HandlerAdapter,如ModelAndView。

8. HandlerAdapter将视图逻辑名或模型（ModelAndView中返回的参数）传递给DispatcherServlet。

9. DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的逻辑视图名。

10. 视图解析器将解析的逻辑视图名传给DispatcherServlet。

11. DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图。

12. 最终视图呈现给用户。

过程稍微有点点复杂。直接走一遍吧！

## 基于原理的SpringMVC实现

1. 新建一个module

2. 添加web支持

3. 导入相关的lib

4. web.xml配置，第一步 注册 DispatchServlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
   
      <!--1.注册DispatcherServlet-->
      <servlet>
          <servlet-name>springmvc</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <!--关联一个springmvc的配置文件:【servlet-name】-servlet.xml-->
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:springmvc-servlet.xml</param-value>
          </init-param>
          <!--启动级别-1-->
          <load-on-startup>1</load-on-startup>
      </servlet>
   
      <!--/ 匹配所有的请求；（不包括.jsp）-->
      <!--/* 匹配所有的请求；（包括.jsp）-->
      <servlet-mapping>
          <servlet-name>springmvc</servlet-name>
          <url-pattern>/</url-pattern>
      </servlet-mapping>
   
   </web-app>
   ```

   - 注册需要注意哪些内容？ 首先注册DispatcherServlet 需要导入相关的类，类名就是DispatchServlet
   - 然后是初始化参数 init-param
   - 设置启动级别，数字越小，启动级别越高，1代表创建时启动
   - 然后是 servlet-mapping 部分，注意url-pattern 这部分的内容，不能填写为/* 而要填写 / 不然很有可能就给你全都拦截了。

5. 添加springmvc-servlet.xml配置文件

   ```xml
   说明，这里的名称要求是按照官方来的
   
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd">
   
   </beans>
   ```

   这部分的内容其实都是一致的，就像之前学过的Mybatis 中的Mapper.xml 的头部，基本上spring的配置文件的样式都是一致的。我们直接抄官网上的就好了，反正也记不住。

   然后添加以下内容：

   `<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>` 处理器映射器（后期可以省略）

   `<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>`处理器配置器（后期可以省略）

   ```xml
   <!--视图解析器:DispatcherServlet给他的ModelAndView-->
   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="InternalResourceViewResolver">
      <!--前缀-->
      <property name="prefix" value="/WEB-INF/jsp/"/>
      <!--后缀-->
      <property name="suffix" value=".jsp"/>
   </bean>
   ```

   视图解析器是不可以省略的，不管是后续中的注解完成开发，还是根据运行原理的配置文件开发，都需要这部分的内容。

   完整的xml文件：

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
   <!--    <mvc:view-controller path="/hello" view-name="hello"/>-->
   <!--    添加处理映射器-->
       <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
   <!--    添加处理适配器-->
       <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
   <!--    添加视图解析器-->
       <!--视图解析器:DispatcherServlet给他的ModelAndView-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="InternalResourceViewResolver">
           <!--前缀-->
           <property name="prefix" value="/WEB-INF/jsp/"/>
           <!--后缀-->
           <property name="suffix" value=".jsp"/>
       </bean>
       <!--Handler-->
       <bean id="/hello" class="com.fanser.controller.HelloController"/>
   </beans>
   ```

   注意底下的bean，是不是很熟悉，其实就是spring的ioc容器！显然我们还有个 HelloController 类需要实现（总之，缺什么补什么就行，不需要这么死板的记忆这部分，反正后续有注解。。。）

6. 对应的controller
   然后是相应的操作业务 Controller ，两种实现方式：1. 添加注解实现 2. 实现Controller接口。 需要我们返回一个ModelAndView，用于封装数据和处理视图。

   ```java
   package com.fanser.controller;
   
   import org.springframework.web.servlet.ModelAndView;
   import org.springframework.web.servlet.mvc.Controller;
   
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   
   //注意：这里我们先导入Controller接口
   public class HelloController implements Controller {
   
       public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
           //ModelAndView 模型和视图
           ModelAndView mv = new ModelAndView();
   
           //封装对象，放在ModelAndView中。Model
           mv.addObject("msg","HelloSpringMVC!");
           //封装要跳转的视图，放在ModelAndView中
           mv.setViewName("hello"); //: /WEB-INF/jsp/hello.jsp
           return mv;
       }
   }
   ```

   注意我们做了些什么，首先handlerRequest其实是Controller接口中的方法，返回值为 ModelAndView，这部分的内容显然需要返回与视图相关的内容，那我们需要给处理器适配器提供哪些内容呢？ 1. 需要在前端显示的内容部分 2. 要跳转的视图部分。 

   到这就结束了？ 显然不对，光有Controller层还不够，后台是有了，前端咋搞，还有，还没连上呢！ 于是这里我们通过 bean 来与配置文件相关联

   ```xml
       <!--Handler-->
       <bean id="/hello" class="com.fanser.controller.HelloController"/>
   ```

7. 与controller相应的jsp文件
   编写个能显示我们做了哪些事情的前端页面就好了。

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>第一个mvc</title>
   </head>
   <body>
       ${msg}
   </body>
   </html>
   ```

   ![image-20220325154808928](https://s2.loli.net/2022/03/26/qIYwBXOiom9y27Z.png)

项目文件结构

然后就是**404问题**：写web页面多少会出现点404的问题，一般就几个方面：

1. 看看控制台，是不是出了什么毛病，具体情况具体看，百度比我现在记nb（主要是没遇到）
2. maven中导入的jar包存在，但是还是显示404，那很有可能就是没导入jar包，在jsp文件同级的目录下新建一个lib把jar包导入即可
   ![image-20220325155139001](https://s2.loli.net/2022/03/26/VBKhWMXz6y98LA5.png)

![image-20220325155225224](https://s2.loli.net/2022/03/26/Sl1ubOUkP2NVCvc.png)

![image-20220325155253322](https://s2.loli.net/2022/03/26/4Sc7BKrFj8UDPan.png)

大部分问题到这里重启tomcat应该能够解决。

3. 还有就是tomcat的问题：
   ![image-20220325155516239](https://s2.loli.net/2022/03/26/PG6U8zLtmI13JMs.png)

到这里，写完笔记之后突然感觉理解了许多，配置版懂了。那么我们来看看真香版本**（注解实现）**

## 基于注解的SpringMVC实现（重点）

这里还是向上面一样，新开一个module模块，然后添加web支持，添加springmvc配置文件，注意需要添加自动扫描。相关依赖，spring核心库，springmvc，servlet，jstl等等。编写Controller，对应的前端页面。要是出现404了，就按之前的步骤，排查一遍。

### web.xml配置：（跟上面差不多，一致）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--注册servlet-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--初始化Spring配置文件的位置-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!--启动顺序，数字越小，启动越早-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <!--所有的请求都会被SpringMVC拦截-->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
<!--这是使用注解开发时固定的配置-->
```

注意这里的ServletMapping中的内容

**/ 和 /\* 的区别：**

< url-pattern > / </ url-pattern > 不会匹配到.jsp， 只针对我们编写的请求；即：.jsp 不会进入spring的 DispatcherServlet类 。

< url-pattern > /* </ url-pattern > 会匹配 *.jsp，会出现返回 jsp视图时再次进入spring的DispatcherServlet 类，导致找不到对应的controller所以报404错。

- 注意web.xml版本问题，要最新版!4.0
- 注册DispatcherServlet
- 关联SpringMVC的配置文件
- 启动级别为1
- 映射路径为 / 【不要用/*，会404】

### springmvc配置文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="com.th.controller"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping注解生效
        必须向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driven配置帮助我们自动完成上述两个实例的注入。
     -->
    <mvc:annotation-driven />

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
<!--这也是固定配置，需要修改的只有需要注入IOC的包-->
```

在视图解析器中，我们把所有的jsp文件都放在WEB-INF目录下，这样可以保证视图安全，因为这个文件夹下客户端是不能访问的。（一般静态资源文件都可以放在这底下）

总结：

1. 注解支持的需要 context
2. springmvc过滤其中的静态资源
3. 注解驱动
4. 视图解析器

这里发现，我们把之前的一部分内容省略了，我们把 处理器映射器 和 处理器配置器 给省略了。主要原因是springmvc帮我们把这部分的活给默默的干了，不需要过多的配置来解决这个问题。

### Controller部分：

```java
@Controller
@RequestMapping("/AnnotationController")
public class AnnotationController {
    //类上注册了@RequestMapping方法就自动下移，实际地址：项目名/AnnotationController/hello
    @RequestMapping("/hello")
    public String sayHello(Model model){

        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg","hello,SpringMVCAnnotation!");

        //web-inf/jsp/hello.jsp
        return "hello";
    }
}
```

之前提到，实现Controller的方式有两种，一种是实现Controller接口，另一种是注解。这里就是注解方式来完成的部分。

注意到，使用注解方式来实现并没有要求我们重写Controller接口中的方法，但是使用了一个不太认识的注解**@RequestMapping()**,这个注解是干嘛用的？

- @Controller接口在之前学习Spring的时候提到过，就是为了让Spring IOC容器在初始化时自动扫描到，类似的注解还有Service和Repository，分别用于Service层和Dao层。

- @RequestMapping是为了映射请求路径，这里因为类与方法上都有映射所以访问时应该是/AnnotationController/hello；
- 方法中声明的Model是为了把前端页面中Action的数据带到视图中
- 方法返回的结果是视图的名称hello，加上配置文件中的前后缀变成WEB-INF/jsp/**hello**.jsp。

### 视图部分：

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>SpringMVC</title>
</head>
<body>
${msg}
</body>
</html>
```

在WEB-INF/ jsp目录中创建hello.jsp ， 视图可以直接取出并展示从Controller带回的信息( EL表达式)；

配置tomcat，跳转路径为 http://localhost:8080/AnnotationController/hello，注意hello前的路径。为AnnotationController，仔细思考为什么是AnnotationController？

类名外的@RequestMapping 和方法体外的@RequestMapping 之间有什么联系？



使用springMVC必须配置的三大件：

**处理器映射器、处理器适配器、视图解析器**

通常，我们只需要**手动配置视图解析器**，而**处理器映射器**和**处理器适配器**只需要开启**注解驱动**即可，而省去了大段的xml配置



到这里就结束了，仔细观察发现，我们其实压根没写几行代码，之前复杂的配置也大量简化了，快速实现了这些功能。但这还不是最简单的，还能继续简化。

在这之前，来了解一下什么是RestFul风格！

## RestFul风格

![image-20220325205520961](https://s2.loli.net/2022/03/26/LVzFZAMOthg29uJ.png)

![image-20220325205610269](https://s2.loli.net/2022/03/26/FxUou1zHpXMeOyr.png)

这种层层包括的结构层次就叫RestFul风格，它既不是一种协议也不是标准，只是一种风格。优点是能够精确的定位到资源文件夹所在，同样也避免了请求发送时的安全性问题。

### RestFul功能

- `资源`：互联网所有的事物都可以被抽象为资源
- `资源操作`：使用POST、DELETE、PUT、GET，使用不同方法对资源进行操作。
- 分别对应 **添加**、 **删除**、**修改**、**查询**。



localhost:8080/method ? add = 1 原本的风格是这样的

localhost:8080/method ? delete = 1

localhost:8080/method ? add = 1

上面的每种方法都需要一个地址，而restful风格中不需要根据url来定位，是根据请求类型来完成页面的更新的。

分析差异：

- 普通方式请求单一，只有 post 和 get 两种请求方式
- 不安全，普通方式传入的参数和名称都直接暴露在url中
- 如果想实现不同的业务，每次都需要发起不同的请求，对应了不同的处理代码



写到这里遇到了点挫折，tomcat不管怎么样都不能正常的启动项目，后续一系列的错误排查花了不少时间,都写到博客里了 <www.fanser.xyz:8090>



实现：

配置上都是相同的，不同实现方式只需要添加不同的注解即可

### 普通方式实现

```java
@Controller
public class ControllerRestFul {
    @RequestMapping("/add")
    public String add(int a, int b, Model model) {
        int result = a + b;
        model.addAttribute("msg", "结果为" + result);
        return "test";
    }
}
```

RestFul实现：（错误示范）

```java
@Controller
public class ControllerRestFul {
    @PostMapping("/add/{a}/{b}")
    public String test1(int a, int b , Model model){
        int res = a+b;
        model.addAttribute("msg","结果为"+res);
        return "test";
    }
}
```

![image-20220326151658873](https://s2.loli.net/2022/03/26/t9yoS4CmD8vHKYj.png)

失败了，看看哪里出问题了，我们使用的是@PostMapping注解，导致不能使用get请求来完成这个操作，那么就换成@GetMapping试试吧

![image-20220326151853643](https://s2.loli.net/2022/03/26/YRhqvs6TjteVi8K.png)

又出错了！方法不对，不是这样处理的。这里属于是回忆讲的内容混淆了，重新处理一遍。

代码写错了，我们应该还是使用@RequestMapping注解来完成这些操作

### RestFul实现：

```java
@Controller
public class ControllerRestFul {
    @RequestMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果为"+res);
        return "test";
    }
}
```

需要使用@PathVariable注解，让方法参数的值对应绑定到一个URI模板变量上。

![image-20220326153531745](https://s2.loli.net/2022/03/26/EhXMSLot4HFPilK.png)

思考一下，这样做有什么好处？

	- 使路径更加简洁
	- 获得参数更加方便，框架会自动进行类型转换
	- 通过路径变量的类型可以约束访问参数，如果类型不一致，则会出现以下错误，报错是路径访问不正确，而不是参数转换不正确

![image-20220326153810175](https://s2.loli.net/2022/03/26/t5C4X7nhzQDZAO3.png)

如何解决这个错误？ 修改代码的话其实很好处理，把res 和 两个参数的类型更改成String类型就行了

```java
@Controller
public class ControllerRestFul {
    @RequestMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable String b, Model model){
        String res = a+b;
        model.addAttribute("msg","结果为"+res);
        return "test";
    }
}
```

![image-20220326154121600](image-20220326154121600.png)

不过这样子修改其实没啥意义 ，主要还是体现一下这个路径的参数类型是可变的。

来看看这个参数的传入是使用的什么方式了实现的？

![image-20220326154615728](https://s2.loli.net/2022/03/26/iE7HfgdDWRrjGs6.png)

通过控制台工具发现，这其实是一个get方式的请求方法，那我们思考一下，怎么使用post方式来完成这些操作呢？

**使用method属性指定请求类型**

用于约束请求的类型，可以收窄请求范围。指定请求谓词的类型如GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE等

我们来测试一下：

- 增加一个方法

  ```java
  //映射访问路径,必须是POST请求
  @RequestMapping(value = "posttest",method = {RequestMethod.POST})//狂神这里教的时候使用的是name而不是value，然后老是出问题= =，在线翻车
  public String test3(Model model){
      model.addAttribute("msg", "testpost!");
      return "test";
  }
  ```

  ![image-20220326155152116](https://s2.loli.net/2022/03/26/FgdTEz3GUfkW8wS.png)

- 修改其中的方法类型

  ```java
  //映射访问路径,必须是POST请求
  @RequestMapping(value = "posttest",method = {RequestMethod.GET})
  public String test3(Model model){
      model.addAttribute("msg", "testpost!");
      return "test";
  }
  ```

  ![image-20220326160315443](https://s2.loli.net/2022/03/26/QefpqsLAjGyXoZ4.png)

正常运行！

**小结：**

Spring MVC 的 @RequestMapping 注解能够处理 HTTP 请求的方法, 比如 GET, PUT, POST, DELETE 以及 PATCH。我们不使用springMVC方式的话，其实只有两种，只有GET和POST

**所有的地址栏请求默认都会是 HTTP GET 类型的。**

方法级别的注解变体有如下几个：组合注解

```java
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping
```

@GetMapping 是一个组合注解，平时使用的会比较多！

它所扮演的是 @RequestMapping(method =RequestMethod.GET) 的一个快捷方式。

到这里RestFul风格想必你有一定的了解了，那么再来试试如何使用同一个url地址来访问不同的页面，该如何实现？

## 结果跳转方式

### ModelAndView

> 设置ModelAndView对象 , 根据view的名称 , 和视图解析器跳到指定的页面 .

页面 : {视图解析器前缀} + viewName +{视图解析器后缀}

```xml
<!-- 视图解析器 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
     id="internalResourceViewResolver">
   <!-- 前缀 -->
   <property name="prefix" value="/WEB-INF/jsp/" />
   <!-- 后缀 -->
   <property name="suffix" value=".jsp" />
</bean>
```

之前还没具体解释过这个配置的属性的意思，其实也能看出来，之前学Mybatis的时候，我们使用了拼接sql语句的方式来完成复杂的需求，其中就含有前后缀的属性。说到底这里其实也是一样的效果，只是拼接sql换成了拼接url路径。

对应的controller，我们使用的第一个例子：

```java
//注意：这里我们先导入Controller接口
public class HelloController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();

        //封装对象，放在ModelAndView中。Model
        mv.addObject("msg","HelloSpringMVC!");
        //封装要跳转的视图，放在ModelAndView中
        mv.setViewName("hello"); //: /WEB-INF/jsp/hello.jsp
        return mv;
    }
}
```

### ServletAPI

> 通过ServletAPI来完成请求的响应和页面的跳转

通过设置ServletAPI , 不需要视图解析器 .

1、通过HttpServletResponse进行输出

2、通过HttpServletResponse实现重定向

3、通过HttpServletResponse实现转发

```java
@Controller
public class ResultGo {

   @RequestMapping("/result/t1")
   public void test1(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
       rsp.getWriter().println("Hello,Spring BY servlet API");
  }

   @RequestMapping("/result/t2")
   public void test2(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
       rsp.sendRedirect("/index.jsp");
  }

   @RequestMapping("/result/t3")
   public void test3(HttpServletRequest req, HttpServletResponse rsp) throws Exception {
       //转发
       req.setAttribute("msg","/result/t3");
       req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,rsp);
  }
}
```

这种是使用传统的web方式来完成的请求响应，转发和重定向。那我们现在学了SpringMVC，有没有更加快捷的方式来完成这些操作呢？

### SpringMVC

> **通过SpringMVC来实现转发和重定向 - 无需视图解析器；**

转发的方式要根据是否配置了视图解析器来选择转发的内容，如果有视图解析器，那我们只需要直接转发到`test`即可，但是如果没有配置视图解析器，那拼接部分的内容我们也需要给他补全`/WEB-INF/jsp/test.jsp`

没有配置视图解析器的情况下的转发和重定向：

- 默认为forward转发（也可以加上）
- redirect重定向需特别加

```java
@Controller
public class ResultSpringMVC {
   @RequestMapping("/rsm/t1")
   public String test1(){
       //转发
       return "/index.jsp";
  }

   @RequestMapping("/rsm/t2")
   public String test2(){
       //转发二
       return "forward:/index.jsp";
  }

   @RequestMapping("/rsm/t3")
   public String test3(){
       //重定向
       return "redirect:/index.jsp";
  }
}
```

配置了视图解析器的情况下的转发和重定向：

- 默认为forward转发（不可以加上）
- redirect转发需特别加

```java
@Controller
public class ResultSpringMVC2 {
   @RequestMapping("/rsm2/t1")
   public String test1(){
       //转发
       return "test";
  }

   @RequestMapping("/rsm2/t2")
   public String test2(){
       //重定向
       return "redirect:/index.jsp";
       //return "redirect:hello.do"; //hello.do为另一个请求/
  }
}
```

## 数据处理

### 处理前端传回的数据

想想传统方式是如何实现的？

我们一般会使用 HttpRequest req，和HttpResponse resp 属性，然后通过getAttribute()方式来获取需要的属性。现在我们有了框架，完全不需要这么复杂的操作，直接在方法属性中使用【@RequestParam("属性名别名")  String 属性名 】来获取前端接收的参数。

这里要注意的是，如果前端url中的属性名与后台不一致，那么将会传回null值，而使用@RequestParam注解后，那么访问错误的url会出现报错。应该会报一个400的错误，请求错误。

#### **参数名一致**

提交数据 : http://localhost:8080/test?name=fanser

处理方法 :

```java
@RequestMapping("/test")
public String hello(String name){
   System.out.println(name);
   return "test";
}
```

后台输出 : fanser

#### **参数名不一致**

提交的域名称和处理方法的 

提交数据 : http://localhost:8080/test?username=fanser

处理方法 :

```java
@GetMapping("test")
//这个Param注解是不是感觉还是有些熟悉，在Mybatis中其中也使用了这种取别名的方式来查询表中字段与后台中属性名不一致的问题
public String test(@RequestParam("username") String name, Model model){
    //1.接收前端参数
    System.out.println("接收到的前端的参数为"+name);
    //2.将返回的结果传递给前端 ，Model
    model.addAttribute("msg",name);
    //3.视图跳转
    return "test";
}
```

后台输出 : fanser

![image-20220326170753670](https://s2.loli.net/2022/03/26/IiORegDGwtbkvQS.png)

![image-20220326170820361](https://s2.loli.net/2022/03/26/IMeukP6jaBUFOLb.png)

#### 对象传参

这种情况下只能接收一条属性，一般来说，我们的数据会被打包封装成一个对象。那么如何接收这个对象呢？

```java
@Controller
public class UserController {
    @RequestMapping("/user")
    public String userTest(User user){
        System.out.println(user);
        return "test";
    }
}
```

浏览器URL：http://localhost:8080/user?id=1&name=fanser&age=3

![image-20220326202317360](https://s2.loli.net/2022/03/26/SW3tqwMO4aYKXfo.png)

还是老样子，使用的是表单提交的形式来完成的数据传递。不过，这里需要注意，如果url中的属性字段与后台不一致，会出现传入值为null的问题。

### 数据显示到前端

#### **第一种 : 通过ModelAndView**

这方法，刚学就用了，不太快乐。

```java
public class ControllerTest1 implements Controller {

   public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
       //返回一个模型视图对象
       ModelAndView mv = new ModelAndView();
       mv.addObject("msg","ControllerTest1");
       mv.setViewName("test");
       return mv;
  }
}
```

#### **第二种 : 通过ModelMap**

ModelMap

```java
@RequestMapping("/hello")
public String hello(@RequestParam("username") String name, ModelMap model){
   //封装要显示到视图中的数据
   //相当于req.setAttribute("name",name);
   model.addAttribute("name",name);
   System.out.println(name);
   return "hello";
}
```

#### **第三种 : 通过Model**

Model

```java
@RequestMapping("/ct2/hello")
public String hello(@RequestParam("username") String name, Model model){
   //封装要显示到视图中的数据
   //相当于req.setAttribute("name",name);
   model.addAttribute("msg",name);
   System.out.println(name);
   return "test";
}
```

#### 三种方式的对比

```
Model  			只有几个方法，只适用于存储数据，其实就是青春版ModelMap
ModelMap 		继承了LinkedHashMap，拥有LinkedHashMap的全部方法和特性
ModelAndView 	可以在存储数据的同时，可以进行设置返回的视图逻辑，进行控制表示层的跳转
```

## 乱码问题

测试步骤：

1、我们可以在首页编写一个提交的表单

```jsp
<form action="/e/t" method="post">
 <input type="text" name="name">
 <input type="submit">
</form>
```

2、后台编写对应的处理类

```java
@Controller
public class Encoding {
   @RequestMapping("/e/t")
   public String test(Model model,String name){
       model.addAttribute("msg",name); //获取表单提交的值
       return "test"; //跳转到test页面显示输入的值
  }
}
```

3、输入中文测试，发现乱码

不得不说，乱码问题是在我们开发中十分常见的问题，也是让我们程序猿比较头大的问题！

以前乱码问题通过过滤器解决 , 而SpringMVC给我们提供了一个过滤器 , 可以在web.xml中配置 .

修改了xml文件需要重启服务器！

```xml
<filter>
   <filter-name>encoding</filter-name>
   <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
   <init-param>
       <param-name>encoding</param-name>
       <param-value>utf-8</param-value>
   </init-param>
</filter>
<filter-mapping>
   <filter-name>encoding</filter-name>
   <url-pattern>/*</url-pattern>
</filter-mapping>
```

但是我们发现 , 有些极端情况下.这个过滤器对get的支持不好 .

处理方法 :

1、修改tomcat配置文件 ：设置编码！

```xml
<Connector URIEncoding="utf-8" port="8080" protocol="HTTP/1.1"
          connectionTimeout="20000"
          redirectPort="8443" />
```

### 乱码通用过滤

**自定义过滤器**，然后在web.xml中配置一下就好

```java
package com.th.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
* 解决get和post请求 全部乱码的过滤器
*/
public class GenericEncodingFilter implements Filter {

   @Override
   public void destroy() {
  }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       //处理response的字符编码
       HttpServletResponse myResponse=(HttpServletResponse) response;
       myResponse.setContentType("text/html;charset=UTF-8");

       // 转型为与协议相关对象
       HttpServletRequest httpServletRequest = (HttpServletRequest) request;
       // 对request包装增强
       HttpServletRequest myrequest = new MyRequest(httpServletRequest);
       chain.doFilter(myrequest, response);
  }

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
  }

}

//自定义request对象，HttpServletRequest的包装类
class MyRequest extends HttpServletRequestWrapper {

   private HttpServletRequest request;
   //是否编码的标记
   private boolean hasEncode;
   //定义一个可以传入HttpServletRequest对象的构造函数，以便对其进行装饰
   public MyRequest(HttpServletRequest request) {
       super(request);// super必须写
       this.request = request;
  }

   // 对需要增强方法 进行覆盖
   @Override
   public Map getParameterMap() {
       // 先获得请求方式
       String method = request.getMethod();
       if (method.equalsIgnoreCase("post")) {
           // post请求
           try {
               // 处理post乱码
               request.setCharacterEncoding("utf-8");
               return request.getParameterMap();
          } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
          }
      } else if (method.equalsIgnoreCase("get")) {
           // get请求
           Map<String, String[]> parameterMap = request.getParameterMap();
           if (!hasEncode) { // 确保get手动编码逻辑只运行一次
               for (String parameterName : parameterMap.keySet()) {
                   String[] values = parameterMap.get(parameterName);
                   if (values != null) {
                       for (int i = 0; i < values.length; i++) {
                           try {
                               // 处理get乱码
                               values[i] = new String(values[i]
                                      .getBytes("ISO-8859-1"), "utf-8");
                          } catch (UnsupportedEncodingException e) {
                               e.printStackTrace();
                          }
                      }
                  }
              }
               hasEncode = true;
          }
           return parameterMap;
      }
       return super.getParameterMap();
  }

   //取一个值
   @Override
   public String getParameter(String name) {
       Map<String, String[]> parameterMap = getParameterMap();
       String[] values = parameterMap.get(name);
       if (values == null) {
           return null;
      }
       return values[0]; // 取回参数的第一个值
  }

   //取所有值
   @Override
   public String[] getParameterValues(String name) {
       Map<String, String[]> parameterMap = getParameterMap();
       String[] values = parameterMap.get(name);
       return values;
  }
}
```

这个也是我在网上找的一些大神写的，一般情况下，SpringMVC默认的乱码处理就已经能够很好的解决了！

**然后在web.xml中配置这个过滤器即可！**

乱码问题，需要平时多注意，在尽可能能设置编码的地方，都设置为统一编码 UTF-8！

有了这些知识，我们马上就可以进行SSM整合了！

## Json交互处理（Jackson和fastjson）

前后端分离时代：

后端部署后端，提供接口，提供数据：

**json**

前端独立部署，负责渲染后端的数据：

### 什么是JSON？

- JSON(JavaScript Object Notation, JS 对象标记) 是一种轻量级的数据交换格式，目前使用特别广泛。
- 采用完全独立于编程语言的**文本格式**来存储和表示数据。
- 简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。
- 易于人阅读和编写，同时也易于机器解析和生成，并有效地提升网络传输效率。

在 JavaScript 语言中，一切都是对象。因此，任何JavaScript 支持的类型都可以通过 JSON 来表示，例如字符串、数字、对象、数组等。看看他的要求和语法格式：

- 对象表示为键值对，数据由逗号分隔
- 花括号保存对象
- 方括号保存数组

**JSON 键值对**是用来保存 JavaScript 对象的一种方式，和 JavaScript 对象的写法也大同小异，键/值对组合中的键名写在前面并用双引号 "" 包裹，使用冒号 : 分隔，然后紧接着值：

```java
{"name": "QinJiang"}
{"age": "3"}
{"sex": "男"}
```

很多人搞不清楚 JSON 和 JavaScript 对象的关系，甚至连谁是谁都不清楚。其实，可以这么理解：

JSON 是 JavaScript 对象的字符串表示法，它使用文本表示一个 JS 对象的信息，本质是一个字符串。

```java
var obj = {a: 'Hello', b: 'World'}; //这是一个对象，注意键名也是可以使用引号包裹的
var json = '{"a": "Hello", "b": "World"}'; //这是一个 JSON 字符串，本质是一个字符串
```

**JSON 和 JavaScript 对象互转**

要实现从JSON字符串转换为JavaScript 对象，使用 JSON.parse() 方法：

```swift
var obj = JSON.parse('{"a": "Hello", "b": "World"}');
//结果是 {a: 'Hello', b: 'World'}
```

要实现从JavaScript 对象转换为JSON字符串，使用 JSON.stringify() 方法：

```javascript
var json = JSON.stringify({a: 'Hello', b: 'World'});
//结果是 '{"a": "Hello", "b": "World"}'
```



其实这部分的内容在课堂学javaweb的时候有了解过，不过当时别说前端，后端是指什么都不太清楚。现在再看还是挺简单的。

```html
<script type="text/javascript">
    //编写一个js对象
    var user = {
        name:"fanser",
        age:3,
        sex:"男"
    };
    //将js对象转换成json字符串
    var str = JSON.stringify(user);
    console.log(str)
    //将json字符串转换成js对象
    var user2 = JSON.parse(str)
    console.log(user2.name,user2.age,user2.sex);
</script>

```



![image-20220327161441284](https://s2.loli.net/2022/03/27/uRD7BSgJChz4lvZ.png)

> Controller返回JSON数据

Jackson应该是目前比较好的json解析工具了

当然工具不止这一个，比如还有阿里巴巴的 fastjson 等等。

导入依赖：

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
<dependency>
   <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.9.8</version>
</dependency>
```

web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
        version="4.0">

   <!--1.注册servlet-->
   <servlet>
       <servlet-name>SpringMVC</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <!--通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
       <init-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:springmvc-servlet.xml</param-value>
       </init-param>
       <!-- 启动顺序，数字越小，启动越早 -->
       <load-on-startup>1</load-on-startup>
   </servlet>

   <!--所有请求都会被springmvc拦截 -->
   <servlet-mapping>
       <servlet-name>SpringMVC</servlet-name>
       <url-pattern>/</url-pattern>
   </servlet-mapping>

   <filter>
       <filter-name>encoding</filter-name>
       <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
       <init-param>
           <param-name>encoding</param-name>
           <param-value>utf-8</param-value>
       </init-param>
   </filter>
   <filter-mapping>
       <filter-name>encoding</filter-name>
       <url-pattern>/</url-pattern>
   </filter-mapping>
</web-app>

```

编写一个简单的实体类  User { name ,age,sex}

编写对应的controller

```java
@Controller
public class UserController {

   @RequestMapping("/json1")
   @ResponseBody
   public String json1() throws JsonProcessingException {
       //创建一个jackson的对象映射器，用来解析数据
       ObjectMapper mapper = new ObjectMapper();
       //创建一个对象
       User user = new User("秦疆1号", 3, "男");
       //将我们的对象解析成为json格式
       String str = mapper.writeValueAsString(user);
       //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
       return str;
  }
}
```

在tomcat上部署完成后跑一下

![image-20220327171459001](https://s2.loli.net/2022/03/27/jH7WlybCY2IADPu.png)

出现乱码问题

乱码怎么办？ 不要慌，直接添加字符编码即可（关键是在哪加）出现了乱码问题，我们需要设置一下他的编码格式为utf-8，以及它返回的类型；

通过**@RequestMaping的produces属性**来实现，修改下代码

```java
//produces:指定响应体返回类型和编码
@RequestMapping(value = "/json1",produces = "application/json;charset=utf-8")
```

重启tomcat，问题解决。

**返回json字符串统一解决**

这样子解决的了乱码问题，但是每写一个一个方法都要重新配置一遍**@ResponseBody**注解，就很烦。

在类上直接使用 **@RestController** ，这样子，里面所有的方法都只会返回 json 字符串了，不用再每一个都添加@ResponseBody ！我们在前后端分离开发中，一般都使用 @RestController ，十分便捷！

### 输出时间对象

增加一个新的方法

```java
@RequestMapping("/json3")
public String json3() throws JsonProcessingException {

   ObjectMapper mapper = new ObjectMapper();

   //创建时间一个对象，java.util.Date
   Date date = new Date();
   //将我们的对象解析成为json格式
   String str = mapper.writeValueAsString(date);
   return str;
}
```

运行结果 :

[![img](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7Kr2Q3b19mdVPRfiaSSR0OQ2wC0G9p8rYjxUNdyVK8oCUicN5LJ9XVsLJddll3tYr28kVaXoUfO0LaQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7Kr2Q3b19mdVPRfiaSSR0OQ2wC0G9p8rYjxUNdyVK8oCUicN5LJ9XVsLJddll3tYr28kVaXoUfO0LaQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

- 默认日期格式会变成一个数字，是1970年1月1日到当前日期的毫秒数！
- Jackson 默认是会把时间转成timestamps形式

**解决方案：取消timestamps形式 ， 自定义时间格式**

```java
@RequestMapping("/json4")
public String json4() throws JsonProcessingException {

   ObjectMapper mapper = new ObjectMapper();

   //不使用时间戳的方式
   mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
   //自定义日期格式对象
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   //指定日期格式
   mapper.setDateFormat(sdf);

   Date date = new Date();
   String str = mapper.writeValueAsString(date);

   return str;
}
```

### 输出多个对象

增加一个新的方法

```java
@RequestMapping("/json2")
public String json2() throws JsonProcessingException {

   //创建一个jackson的对象映射器，用来解析数据
   ObjectMapper mapper = new ObjectMapper();
   //创建一个对象
   User user1 = new User("秦疆1号", 3, "男");
   User user2 = new User("秦疆2号", 3, "男");
   User user3 = new User("秦疆3号", 3, "男");
   User user4 = new User("秦疆4号", 3, "男");
   List<User> list = new ArrayList<User>();
   list.add(user1);
   list.add(user2);
   list.add(user3);
   list.add(user4);

   //将我们的对象解析成为json格式
   String str = mapper.writeValueAsString(list);
   return str;
}
```

运行结果 : 十分完美，没有任何问题！

[![img](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7Kr2Q3b19mdVPRfiaSSR0OQ28xBricP4KKliaicvQJJlG989xjEUftuqcibuRPWGHjOZfEA3HiaVTq4GDRw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### FastJson（比较好）

fastjson.jar是阿里开发的一款专门用于Java开发的包，可以方便的实现json对象与JavaBean对象的转换，实现JavaBean对象与json字符串的转换，实现json对象与json字符串的转换。实现json的转换方法很多，最后的实现结果都是一样的。

fastjson 的 pom依赖！

```xml
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>fastjson</artifactId>
   <version>1.2.60</version>
</dependency>
```

fastjson 三个主要的类：

**JSONObject 代表 json 对象**

- JSONObject实现了Map接口, 猜想 JSONObject底层操作是由Map实现的。
- JSONObject对应json对象，通过各种形式的get()方法可以获取json对象中的数据，也可利用诸如size()，isEmpty()等方法获取"键：值"对的个数和判断是否为空。其本质是通过实现Map接口并调用接口中的方法完成的。

**JSONArray 代表 json 对象数组**

- 内部是有List接口中的方法来完成操作的。

**JSON代表 JSONObject和JSONArray的转化**

- JSON类源码分析与使用
- 仔细观察这些方法，主要是实现json对象，json对象数组，javabean对象，json字符串之间的相互转化。

```java
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.th.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class FastJsonDemo {
   public static void main(String[] args) {
       //创建一个对象
       User user1 = new User("秦疆1号", 3, "男");
       User user2 = new User("秦疆2号", 3, "男");
       User user3 = new User("秦疆3号", 3, "男");
       User user4 = new User("秦疆4号", 3, "男");
       List<User> list = new ArrayList<User>();
       list.add(user1);
       list.add(user2);
       list.add(user3);
       list.add(user4);

       System.out.println("*******Java对象 转 JSON字符串*******");
       String str1 = JSON.toJSONString(list);
       System.out.println("JSON.toJSONString(list)==>"+str1);
       String str2 = JSON.toJSONString(user1);
       System.out.println("JSON.toJSONString(user1)==>"+str2);

       System.out.println("\n****** JSON字符串 转 Java对象*******");
       User jp_user1=JSON.parseObject(str2,User.class);
       System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

       System.out.println("\n****** Java对象 转 JSON对象 ******");
       JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
       System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

       System.out.println("\n****** JSON对象 转 Java对象 ******");
       User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
       System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);
  }
}
```

使用应该不是大问题，关键是要有思路，可替代的东西很多，关键在于思考业务逻辑和能用上哪些工具包

## Ajax异步编程

### 简介

- **AJAX = Asynchronous JavaScript and XML（异步的 JavaScript 和 XML）。**
- AJAX 是一种在无需重新加载整个网页的情况下，能够更新部分网页的技术。
- **Ajax 不是一种新的编程语言，而是一种用于创建更好更快以及交互性更强的Web应用程序的技术。**
- 在 2005 年，Google 通过其 Google Suggest 使 AJAX 变得流行起来。Google Suggest能够自动帮你完成搜索单词。
- Google Suggest 使用 AJAX 创造出动态性极强的 web 界面：当您在谷歌的搜索框输入关键字时，JavaScript 会把这些字符发送到服务器，然后服务器会返回一个搜索建议的列表。
- 就和国内百度的搜索框一样!
- 传统的网页(即不用ajax技术的网页)，想要更新内容或者提交一个表单，都需要重新加载整个网页。
- 使用ajax技术的网页，通过在后台服务器进行少量的数据交换，就可以实现异步局部更新。
- 使用Ajax，用户可以创建接近本地桌面应用的直接、高可用、更丰富、更动态的Web用户界面。

### 通过iframe来伪造一个ajax的效果

我们可以使用前端的一个标签来伪造一个ajax的样子。iframe标签

1、新建一个module ：sspringmvc-06-ajax ， 导入web支持！

2、编写一个 ajax-frame.html 使用 iframe 测试，感受下效果

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>iFrame测试体验页面无刷新</title>
</head>
    <script>
      function go(){
        var url = document.getElementById("url").value;
        document.getElementById("iframe1").src=url;
      }
    </script>
<body>
  <div>
    <p>请输入地址</p>
    <input type="text" id="url" value="https://cn.bing.com/">
    <input type="button" value="提交" onclick="go()">
  </div>
  <div>
    <iframe style="width: 60%;height: 40%;align-content: center;" id="iframe1"></iframe>
  </div>
</body>
</html>
```

为什么要做成这样子？使用ajax是为了干嘛？可以做哪些操作？

1. ajax是为了在不刷新（跳转）页面的情况下实现页面内容的更新，为了更好的美观性和体验感
2. 为了做成百度搜索那种效果，还有对输入账号密码等等的验证操作，验证用户是否存在，密码是否正确等等
3. 删除数据行时，将行id发送到后台，后台在数据库中删除，数据库中删除后，在页面dom中将数据行也删除。

### jQuery.ajax

纯原生的ajax，目前还没怎么研究过，先不研究了，用人家造好的轮子吧，先用再了解应该会上手快一些。

Ajax的核心是XMLHttpRequest对象（XHR）。XHR为向服务器发送请求和解析服务器响应提供了接口，能够以异步方式从服务器获取新资源。

jQeury提供多个与ajax有关的方法。jQuery实际上就是一堆的js，说到底就类似jar包的存在。

通过 Ajax方法，我们能够使用Http GET 和Http POST从远程服务器上请求文本，HTML，XML或JSON，同时也能直接把这些外部数据直接载入网页的被选元素中。

jQuery Ajax本质就是 XMLHttpRequest，对他进行了封装，方便调用！

```
jQuery.ajax(...)
      部分参数：
            url：请求地址
            type：请求方式，GET、POST（1.9.0之后用method）
        headers：请求头
            data：要发送的数据
    contentType：即将发送信息至服务器的内容编码类型(默认: "application/x-www-form-urlencoded; charset=UTF-8")
          async：是否异步
        timeout：设置请求超时时间（毫秒）
      beforeSend：发送请求前执行的函数(全局)
        complete：完成之后执行的回调函数(全局)
        success：成功之后执行的回调函数(全局)
          error：失败之后执行的回调函数(全局)
        accepts：通过请求头发送给服务器，告诉服务器当前客户端可接受的数据类型
        dataType：将服务器端返回的数据转换成指定类型
          "xml": 将服务器端返回的内容转换成xml格式
          "text": 将服务器端返回的内容转换成普通文本格式
          "html": 将服务器端返回的内容转换成普通文本格式，在插入DOM中时，如果包含JavaScript标签，则会尝试去执行。
        "script": 尝试将返回值当作JavaScript去执行，然后再将服务器端返回的内容转换成普通文本格式
          "json": 将服务器端返回的内容转换成相应的JavaScript对象
        "jsonp": JSONP 格式使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数
```

特别需要注意的几个参数：**url type success error**

**我们来个简单的测试，使用最原始的HttpServletResponse处理 , .最简单 , 最通用**

1、配置web.xml 和 springmvc的配置文件，复制上面案例的即可 【记得静态资源过滤和注解驱动配置上】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xmlns:mvc="http://www.springframework.org/schema/mvc"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

   <!-- 自动扫描指定的包，下面所有注解类交给IOC容器管理 -->
   <context:component-scan base-package="com.th.controller"/>
   <mvc:default-servlet-handler />
   <mvc:annotation-driven />

   <!-- 视图解析器 -->
   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
         id="internalResourceViewResolver">
       <!-- 前缀 -->
       <property name="prefix" value="/WEB-INF/jsp/" />
       <!-- 后缀 -->
       <property name="suffix" value=".jsp" />
   </bean>

</beans>
```

2、编写一个AjaxController

```java
@Controller
public class AjaxController {

   @RequestMapping("/a1")
   public void ajax1(String name , HttpServletResponse response) throws IOException {
       if ("admin".equals(name)){
           response.getWriter().print("true");
      }else{
           response.getWriter().print("false");
      }
  }

}
```

3、导入jquery ， 可以使用在线的CDN ， 也可以下载导入

```js
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
```

4、编写index.jsp测试

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
   <title>$Title$</title>
  <%--<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>--%>
   <script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
   <script>
       function a1(){
           $.post({
               url:"${pageContext.request.contextPath}/a1",
               data:{'name':$("#name").val()},
               success:function (data,status) {
                   alert(data);
                   alert(status);
              }
          });
      }
   </script>
 </head>
 <body>

<%--onblur：失去焦点触发事件--%>
用户名:<input type="text" id="name" onblur="a1()"/>

 </body>
</html>
```

5、启动tomcat测试！打开浏览器的控制台，当我们鼠标离开输入框的时候，可以看到发出了一个ajax的请求！是后台返回给我们的结果！测试成功！

----



### Springmvc实现

实体类user

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

   private String name;
   private int age;
   private String sex;

}
```

我们来获取一个集合对象，展示到前端页面

```java
@RequestMapping("/a2")
public List<User> ajax2(){
   List<User> list = new ArrayList<User>();
   list.add(new User("秦疆1号",3,"男"));
   list.add(new User("秦疆2号",3,"男"));
   list.add(new User("秦疆3号",3,"男"));
   return list; //由于@RestController注解，将list转成json格式返回
}
```

前端页面

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                // console.log('点击事件成功')
                // $.post(url,param,[可以省略],success)
                $.post("${pageContext.request.contextPath}/a2",function (data) {
                    console.log(data[0].name);
                    let html =  "";
                    for (let i = 0; i < data.length; i++) {
                        html += `<tr>
                                    <td>${"${data[i].name}"}</td>
                                    <td>${"${data[i].age}"}</td>
                                    <td>${"${data[i].sex}"}</td>
                                </tr>`
                    }
                    $("#content").html(html)
                    console.log(html)
                })
            })
        })
    </script>
</head>
<body>
<input type="button" value="加载数据" id="btn">
<table>
    <thead>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
            <td>性别</td>
        </tr>
    </thead>
    <tbody id="content">
        <%--数据：后台--%>
    </tbody>
</table>
</body>
</html>
```

**成功实现了数据回显！可以体会一下Ajax的好处！**

### 注册提示效果

我们再测试一个小Demo，思考一下我们平时注册时候，输入框后面的实时提示怎么做到的；如何优化

我们写一个Controller

```java
@RequestMapping("/a3")
public String ajax3(String name,String pwd){
   String msg = "";
   //模拟数据库中存在数据
   if (name!=null){
       if ("admin".equals(name)){
           msg = "OK";
      }else {
           msg = "用户名输入错误";
      }
  }
   if (pwd!=null){
       if ("123456".equals(pwd)){
           msg = "OK";
      }else {
           msg = "密码输入有误";
      }
  }
   return msg; //由于@RestController注解，将msg转成json格式返回
}
```

前端页面 login.jsp

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>ajax</title>
   <script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
   <script>

       function a1(){
           $.post({
               url:"${pageContext.request.contextPath}/a3",
               data:{'name':$("#name").val()},
               success:function (data) {
                   if (data.toString()=='OK'){
                       $("#userInfo").css("color","green");
                  }else {
                       $("#userInfo").css("color","red");
                  }
                   $("#userInfo").html(data);
              }
          });
      }
       function a2(){
           $.post({
               url:"${pageContext.request.contextPath}/a3",
               data:{'pwd':$("#pwd").val()},
               success:function (data) {
                   if (data.toString()=='OK'){
                       $("#pwdInfo").css("color","green");
                  }else {
                       $("#pwdInfo").css("color","red");
                  }
                   $("#pwdInfo").html(data);
              }
          });
      }

   </script>
</head>
<body>
<p>
  用户名:<input type="text" id="name" onblur="a1()"/>
   <span id="userInfo"></span>
</p>
<p>
  密码:<input type="text" id="pwd" onblur="a2()"/>
   <span id="pwdInfo"></span>
</p>
</body>
</html>
```

【记得处理json乱码问题】

```xml
<!--JSON乱码问题配置-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

测试一下效果，动态请求响应，局部刷新，就是如此！

[![img](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KZibKx8mbpgp9uicB6eJRNyNtU7ek8sJ1kpbFia5e5IoMRtGFwBiaylw7iakYBws0icEAqyH22l6Y7FMzQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KZibKx8mbpgp9uicB6eJRNyNtU7ek8sJ1kpbFia5e5IoMRtGFwBiaylw7iakYBws0icEAqyH22l6Y7FMzQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 获取baidu接口Demo

JSONP.html

```xml
<!DOCTYPE HTML>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <title>JSONP百度搜索</title>
   <style>
       #q{
           width: 500px;
           height: 30px;
           border:1px solid #ddd;
           line-height: 30px;
           display: block;
           margin: 0 auto;
           padding: 0 10px;
           font-size: 14px;
      }
       #ul{
           width: 520px;
           list-style: none;
           margin: 0 auto;
           padding: 0;
           border:1px solid #ddd;
           margin-top: -1px;
           display: none;
      }
       #ul li{
           line-height: 30px;
           padding: 0 10px;
      }
       #ul li:hover{
           background-color: #f60;
           color: #fff;
      }
   </style>
   <script>

       // 2.步骤二
       // 定义demo函数 (分析接口、数据)
       function demo(data){
           var Ul = document.getElementById('ul');
           var html = '';
           // 如果搜索数据存在 把内容添加进去
           if (data.s.length) {
               // 隐藏掉的ul显示出来
               Ul.style.display = 'block';
               // 搜索到的数据循环追加到li里
               for(var i = 0;i<data.s.length;i++){
                   html += '<li>'+data.s[i]+'</li>';
              }
               // 循环的li写入ul
               Ul.innerHTML = html;
          }
      }

       // 1.步骤一
       window.onload = function(){
           // 获取输入框和ul
           var Q = document.getElementById('q');
           var Ul = document.getElementById('ul');

           // 事件鼠标抬起时候
           Q.onkeyup = function(){
               // 如果输入框不等于空
               if (this.value != '') {
                   // ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆JSONPz重点☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
                   // 创建标签
                   var script = document.createElement('script');
                   //给定要跨域的地址 赋值给src
                   //这里是要请求的跨域的地址 我写的是百度搜索的跨域地址
                   script.src = 'https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su?wd='+this.value+'&cb=demo';
                   // 将组合好的带src的script标签追加到body里
                   document.body.appendChild(script);
              }
          }
      }
   </script>
</head>

<body>
<input type="text" id="q" />
<ul id="ul">

</ul>
</body>
</html>
```

## 拦截器

### 概述

SpringMVC的处理器拦截器类似于Servlet开发中的过滤器Filter,用于对处理器进行预处理和后处理。开发者可以自己定义一些拦截器来实现特定的功能。

**过滤器与拦截器的区别：**拦截器是AOP思想的具体应用。

**过滤器**

- servlet规范中的一部分，任何java web工程都可以使用
- 在url-pattern中配置了/*之后，可以对所有要访问的资源进行拦截

**拦截器**

- 拦截器是SpringMVC框架自己的，只有使用了SpringMVC框架的工程才能使用
- 拦截器只会拦截访问的控制器方法， 如果访问的是jsp/html/css/image/js是不会进行拦截的

### 自定义拦截器

那如何实现拦截器呢？

想要自定义拦截器，必须实现 HandlerInterceptor 接口。

1、新建一个Moudule ， springmvc-07-Interceptor ， 添加web支持

2、配置web.xml 和 springmvc-servlet.xml 文件

3、编写一个拦截器

```java
package com.th.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

   //在请求处理的方法之前执行
   //如果返回true执行下一个拦截器
   //如果返回false就不执行下一个拦截器
   public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       System.out.println("------------处理前------------");
       return true;
  }

   //在请求处理方法执行之后执行
   public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
       System.out.println("------------处理后------------");
  }

   //在dispatcherServlet处理后执行,做清理工作.
   public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
       System.out.println("------------清理------------");
  }
}
```

4、在springmvc的配置文件中配置拦截器

```xml
<!--关于拦截器的配置-->
<mvc:interceptors>
   <mvc:interceptor>
       <!--/** 包括路径及其子路径-->
       <!--/admin/* 拦截的是/admin/add等等这种 , /admin/add/user不会被拦截-->
       <!--/admin/** 拦截的是/admin/下的所有-->
       <mvc:mapping path="/**"/>
       <!--bean配置的就是拦截器-->
       <bean class="com.th.interceptor.MyInterceptor"/>
   </mvc:interceptor>
</mvc:interceptors>
```

5、编写一个Controller，接收请求

```java
package com.th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//测试拦截器的控制器
@Controller
public class InterceptorController {

   @RequestMapping("/interceptor")
   @ResponseBody
   public String testFunction() {
       System.out.println("控制器中的方法执行了");
       return "hello";
  }
}
```

6、前端 index.jsp

```html
<a href="${pageContext.request.contextPath}/interceptor">拦截器测试</a>
```

7、启动tomcat 测试一下！

[![img](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KshicHhIPa51icXVueiaMfB0HtJH2NsHDlcibyEJuibgomZzDNpHiammcSRt2V87uPMYGC7h0gt5KS2Dcw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KshicHhIPa51icXVueiaMfB0HtJH2NsHDlcibyEJuibgomZzDNpHiammcSRt2V87uPMYGC7h0gt5KS2Dcw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 验证用户是否登录 (认证用户)

**实现思路**

1、有一个登陆页面，需要写一个controller访问页面。

2、登陆页面有一提交表单的动作。需要在controller中处理。判断用户名密码是否正确。如果正确，向session中写入用户信息。*返回登陆成功。*

3、拦截用户请求，判断用户是否登陆。如果用户已经登陆。放行， 如果用户未登陆，跳转到登陆页面

**测试：**

1、编写一个登陆页面 login.jsp

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>Title</title>
</head>

<h1>登录页面</h1>
<hr>

<body>
<form action="${pageContext.request.contextPath}/user/login">
  用户名：<input type="text" name="username"> <br>
  密码：<input type="password" name="pwd"> <br>
   <input type="submit" value="提交">
</form>
</body>
</html>
```

2、编写一个Controller处理请求

```java
package com.th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

   //跳转到登陆页面
   @RequestMapping("/jumplogin")
   public String jumpLogin() throws Exception {
       return "login";
  }

   //跳转到成功页面
   @RequestMapping("/jumpSuccess")
   public String jumpSuccess() throws Exception {
       return "success";
  }

   //登陆提交
   @RequestMapping("/login")
   public String login(HttpSession session, String username, String pwd) throws Exception {
       // 向session记录用户身份信息
       System.out.println("接收前端==="+username);
       session.setAttribute("user", username);
       return "success";
  }

   //退出登陆
   @RequestMapping("logout")
   public String logout(HttpSession session) throws Exception {
       // session 过期
       session.invalidate();
       return "login";
  }
}
```

3、编写一个登陆成功的页面 success.jsp

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>Title</title>
</head>
<body>

<h1>登录成功页面</h1>
<hr>

${user}
<a href="${pageContext.request.contextPath}/user/logout">注销</a>
</body>
</html>
```

4、在 index 页面上测试跳转！启动Tomcat 测试，未登录也可以进入主页！

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
   <title>$Title$</title>
 </head>
 <body>
 <h1>index</h1>
 <hr>
<%--登录--%>
 <a href="${pageContext.request.contextPath}/user/jumplogin">登录</a>
 <a href="${pageContext.request.contextPath}/user/jumpSuccess">成功页面</a>
 </body>
</html>
```

5、编写用户登录拦截器

```java
package com.th.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {

   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
       // 如果是登陆页面则放行
       System.out.println("uri: " + request.getRequestURI());
       if (request.getRequestURI().contains("login")) {
           return true;
      }

       HttpSession session = request.getSession();

       // 如果用户已登陆也放行
       if(session.getAttribute("user") != null) {
           return true;
      }

       // 用户没有登陆跳转到登陆页面
       request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
       return false;
  }

   public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

  }
   
   public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }
}
```

6、在Springmvc的配置文件中注册拦截器

```xml
<!--关于拦截器的配置-->
<mvc:interceptors>
   <mvc:interceptor>
       <mvc:mapping path="/**"/>
       <bean id="loginInterceptor" class="com.th.interceptor.LoginInterceptor"/>
   </mvc:interceptor>
</mvc:interceptors>
```

7、再次重启Tomcat测试！

**OK，测试登录拦截功能无误.**

**OK，测试登录拦截功能无误.**

## 文件上传和下载

### 准备工作

文件上传是项目开发中最常见的功能之一 ,springMVC 可以很好的支持文件上传，但是SpringMVC上下文中默认没有装配MultipartResolver，因此默认情况下其不能处理文件上传工作。如果想使用Spring的文件上传功能，则需要在上下文中配置MultipartResolver。

***前端表单要求：为了能上传文件，必须将表单的method设置为POST，并将enctype设置为multipart/form-data。***只有在这样的情况下，浏览器才会把用户选择的文件以二进制数据发送给服务器；

**对表单中的 enctype 属性做个详细的说明：**

- application/x-www=form-urlencoded：默认方式，只处理表单域中的 value 属性值，采用这种编码方式的表单会将表单域中的值处理成 URL 编码方式。
- multipart/form-data：这种编码方式会以二进制流的方式来处理表单数据，这种编码方式会把文件域指定文件的内容也封装到请求参数中，不会对字符编码。
- text/plain：除了把空格转换为 "+" 号外，其他字符都不做编码处理，这种方式适用直接通过表单发送邮件。

```html
<form action="" enctype="multipart/form-data" method="post">
   <input type="file" name="file"/>
   <input type="submit">
</form>
```

一旦设置了enctype为multipart/form-data，浏览器即会采用二进制流的方式来处理表单数据，而对于文件上传的处理则涉及在服务器端解析原始的HTTP响应。在2003年，Apache Software Foundation发布了开源的Commons FileUpload组件，其很快成为Servlet/JSP程序员上传文件的最佳选择。

- Servlet3.0规范已经提供方法来处理文件上传，但这种上传需要在Servlet中完成。
- 而Spring MVC则提供了更简单的封装。
- Spring MVC为文件上传提供了直接的支持，这种支持是用即插即用的MultipartResolver实现的。
- Spring MVC使用Apache Commons FileUpload技术实现了一个MultipartResolver实现类：
- CommonsMultipartResolver。因此，SpringMVC的文件上传还需要依赖Apache Commons FileUpload的组件。

### 文件上传

1、导入文件上传的jar包，commons-fileupload ， Maven会自动帮我们导入他的依赖包 commons-io包；

```xml
<!--文件上传-->
<dependency>
   <groupId>commons-fileupload</groupId>
   <artifactId>commons-fileupload</artifactId>
   <version>1.3.3</version>
</dependency>
<!--servlet-api导入高版本的-->
<dependency>
   <groupId>javax.servlet</groupId>
   <artifactId>javax.servlet-api</artifactId>
   <version>4.0.1</version>
</dependency>
```

2、配置bean：multipartResolver

【**注意！！！这个bena的id必须为：multipartResolver ， 否则上传文件会报400的错误！在这里栽过坑,教训！**】

```xml
<!--文件上传配置-->
<bean id="multipartResolver"  class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
   <!-- 请求的编码格式，必须和jSP的pageEncoding属性一致，以便正确读取表单的内容，默认为ISO-8859-1 -->
   <property name="defaultEncoding" value="utf-8"/>
   <!-- 上传文件大小上限，单位为字节（10485760=10M） -->
   <property name="maxUploadSize" value="10485760"/>
   <property name="maxInMemorySize" value="40960"/>
</bean>
```

CommonsMultipartFile 的 常用方法：

- **String getOriginalFilename()：获取上传文件的原名**
- **InputStream getInputStream()：获取文件流**
- **void transferTo(File dest)：将上传文件保存到一个目录文件中**

我们去实际测试一下

3、编写前端页面

```html
<form action="/upload" enctype="multipart/form-data" method="post">
 <input type="file" name="file"/>
 <input type="submit" value="upload">
</form>
```

4、**Controller**

#### 法一

```java
package com.th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class FileController {
   //@RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
   //批量上传CommonsMultipartFile则为数组即可
   @RequestMapping("/upload")
   public String fileUpload(@RequestParam("file") CommonsMultipartFile file , HttpServletRequest request) throws IOException {

       //获取文件名 : file.getOriginalFilename();
       String uploadFileName = file.getOriginalFilename();

       //如果文件名为空，直接回到首页！
       if ("".equals(uploadFileName)){
           return "redirect:/index.jsp";
      }
       System.out.println("上传文件名 : "+uploadFileName);

       //上传路径保存设置
       String path = request.getServletContext().getRealPath("/upload");
       //如果路径不存在，创建一个
       File realPath = new File(path);
       if (!realPath.exists()){
           realPath.mkdir();
      }
       System.out.println("上传文件保存地址："+realPath);

       InputStream is = file.getInputStream(); //文件输入流
       OutputStream os = new FileOutputStream(new File(realPath,uploadFileName)); //文件输出流

       //读取写出
       int len=0;
       byte[] buffer = new byte[1024];
       while ((len=is.read(buffer))!=-1){
           os.write(buffer,0,len);
           os.flush();
      }
       os.close();
       is.close();
       return "redirect:/index.jsp";
  }
}
```

5、测试上传文件，OK！

**采用file.Transto 来保存上传的文件**

1、编写Controller

#### 法二

```java
/*
* 采用file.Transto 来保存上传的文件
*/
@RequestMapping("/upload2")
public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

   //上传路径保存设置
   String path = request.getServletContext().getRealPath("/upload");
   File realPath = new File(path);
   if (!realPath.exists()){
       realPath.mkdir();
  }
   //上传文件地址
   System.out.println("上传文件保存地址："+realPath);

   //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
   file.transferTo(new File(realPath +"/"+ file.getOriginalFilename()));

   return "redirect:/index.jsp";
}
```

2、前端表单提交地址修改

3、访问提交测试，OK！

### 文件下载

**文件下载步骤：**

1、设置 response 响应头

2、读取文件 -- InputStream

3、写出文件 -- OutputStream

4、执行操作

5、关闭流 （先开后关）

**代码实现：**

```java
@RequestMapping(value="/download")
public String downloads(HttpServletResponse response ,HttpServletRequest request) throws Exception{
   //要下载的图片地址
   String  path = request.getServletContext().getRealPath("/upload");
   String  fileName = "基础语法.jpg";

   //1、设置response 响应头
   response.reset(); //设置页面不缓存,清空buffer
   response.setCharacterEncoding("UTF-8"); //字符编码
   response.setContentType("multipart/form-data"); //二进制传输数据
   //设置响应头
   response.setHeader("Content-Disposition",
           "attachment;fileName="+URLEncoder.encode(fileName, "UTF-8"));

   File file = new File(path,fileName);
   //2、 读取文件--输入流
   InputStream input=new FileInputStream(file);
   //3、 写出文件--输出流
   OutputStream out = response.getOutputStream();

   byte[] buff =new byte[1024];
   int index=0;
   //4、执行 写出操作
   while((index= input.read(buff))!= -1){
       out.write(buff, 0, index);
       out.flush();
  }
   out.close();
   input.close();
   return null;
}
```

前端

```java
<a href="/download">点击下载</a>
```

测试，文件下载OK，大家可以和我们之前学习的JavaWeb原生的方式对比一下，就可以知道这个便捷多了!

拦截器及文件操作在我们开发中十分重要，一定要学会使用！

















借用一部分内容    <https://www.cnblogs.com/th11/p/15141125.html>,感谢分享笔记内容！同样感谢狂神的笔记。
