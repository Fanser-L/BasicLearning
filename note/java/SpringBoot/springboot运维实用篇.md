# 运维实用篇

主要讲了一些服务端和如何将你写的项目架设到服务器上的一些操作。

## 1、工程打包与运行

推荐是使用maven插件来完成相关的打包操作

- 使用maven插件来完成相应的打包操作
- 不是杨maven插件而直接使用maven来完成打包

两种方式的区别在于，使用插件来进行打包能够将工程中使用的jar包也一并打包，而不适用插件打包就只有源代码和配置文件的部分

```xml
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
</plugins>
```

### 插件打包的jar包

![image-20220414185902865](https://s2.loli.net/2022/04/14/1Gqls8vbOogWZdS.png)

### 不使用插件打包的jar包

![image-20220414190338348](https://s2.loli.net/2022/04/14/V5wOxWcJiL2Ipgt.png)

很明显，两个jar包的不管是文件大小上，还是说文件内容上，都有些区别，具体区别就是一些内部依赖的类和jar包了

为什么要打包？为什么这里打的是jar包而不是war包，我们之前学习的项目打包使用的包一般为war包，然后架设到服务器上。而jar包就很有趣了，直接使用`java -jar xxxx.jar`命令，把服务跑起来就好了，毕竟springboot是内置tomcat的，直接就能把项目跑起来了。

![image-20220414195402931](https://s2.loli.net/2022/04/14/BbiKDMdXreoO965.png)

## 2、配置文件的读取优先级问题

上面我们把jar包打好了，然后可以通过cmd窗口或者说命令行的方式来修改其中的一些配置属性`java -jar xxx.jar --server.port=8081`这种方式来临时的修改其中的端口配置和一些其他配置属性。

![image-20220414195639016](https://s2.loli.net/2022/04/14/VPUxT85isfzEduh.png)

这种使用方式一般很少使用，但是这里同样也引出一个问题，那我们内部的配置文件的属性的优先级是怎么回事呢？

正常情况下，我们的配置文件都是在resource文件夹目录下，使用的是properties文件或者yml文件来记录我们的一些基本配置，那要是有多个配置文件的情况下，我们会优先读取哪个文件呢？

**SpringBoot提供了3种配置文件的格式**

- properties（传统格式/默认格式）
- **yml**（主流格式）
- yaml

**思考**

​	现在我们已经知道使用三种格式都可以做配置了，好奇宝宝们就有新的灵魂拷问了，万一我三个都写了，他们三个谁说了算呢？打一架吗？那么这三种配置文件的优先级又是怎么样的呢？

其实三个文件如果共存的话，谁生效说的就是配置文件加载的优先级别。先说一点，虽然以后这种情况很少出现，但是这个知识还是可以学习一下的。我们就让三个配置文件书写同样的信息，比如都配置端口，然后我们让每个文件配置的端口号都不一样，最后启动程序后看启动端口是多少就知道谁的加载优先级比较高了。

- application.properties（properties格式）

```properties
server.port=80
```

- application.yml（yml格式）

```YML
server:
  port: 81
```

- application.yaml（yaml格式）

```yaml
server:
  port: 82
```

​	启动后发现目前的启动端口为80，把80对应的文件删除掉，然后再启动，现在端口又改成了81。现在我们就已经知道了3个文件的加载优先顺序是什么

```XML
application.properties  >  application.yml  >  application.yaml
```

​	虽然得到了一个知识结论，但是我们实际开发的时候还是要看最终的效果为准。也就是你要的最终效果是什么自己是明确的，上述结论只能帮助你分析结论产生的原因。这个知识了解一下就行了，因为以后同时写多种配置文件格式的情况实在是较少。

**现在我宣布，配置文件的内容到此结束！（做什么梦呢！这也就最基本的知识）**

知道了优先级有什么用？（好吧，还是有点用的）实际情况不就写一种配置文件，我们想知道的是，多个同种的配置文件应该怎么处理，全部配置的属性都覆盖？那要是冲突了怎么办？

### 临时属性配置

首先是先解释一下为什么使用命令行的方式也能修改配置属性，我们启动springboot项目的时候实际上还是启动的application.class文件，而其中有一部分的内容我们是可以直接进行配置的，实际的效果就跟命令行一致。

![image-20220414201208602](https://s2.loli.net/2022/04/14/RlUpmKPxH6GCOW4.png)

从`program arguments`属性中添加我们刚才在命令行中结尾加的属性配置`--server.port=8081`也能达到不修改配置文件的情况下完成配置的修改。

当然，这样子是存在一定的风险的，毕竟到时候找错都找不到，如何让这些配置不生效呢？

```java
package com.fanser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsmpbuildApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmpbuildApplication.class, args);
    }

}
```

注意到这个了没有，后面有个args属性，实际上就是可变参数，我们写入的属性就会在这个参数里，为了使我们写的属性不生效，可以直接把这个args给去掉他，那就不会存在传入可变个参数的问题了。实际体验一下：![image-20220414202202995](https://s2.loli.net/2022/04/14/Z1TzB3i2NXjmnHE.png)

当然，我们为了一些固定的配置生效，也可以用些邪门的方法来给他注入一些属性，比如说写个数组来代替这个args来传入我们想要的属性配置什么的，当然，只要不是脑壳有包一般也不会这么写就是了。

好了临时属性解释完了，那么就是到了配置文件的问题了。

### 配置文件的分级处理

- 项目类路径配置文件：服务于开发人员本机开发和测试
- 项目类路径config目录中配置文件：服务于项目经理整体调控
- 工程路径配置文件：服务于运维人员配置涉密的线上环境
- 工程路径config目录中的配置文件：服务于运维经理整体调控

**多层级的配置文件间的属性采用的叠加并覆盖的形式来作用于程序的**

总结：

-  文件路径下，越上层的配置文件优先级越高，而统一层的配置文件优先级低于同层的config目录下的配置文件的优先级。（一般配置文件和jar包同级别说明是到了项目线上配置的环节了，但是有些东西就不是工作人员能看的，银行数据库密码什么的，能给你看？所以再加一层给有资格的人看这些配置文件）
-  四级配置文件：
  - 一级：file：config/application.yml   最高优先级
  - 二级：file：application.yml   
  - 一级：classpath：config/application.yml   
  - 一级：classpath：application.yml   最低优先级

## 3、自定义配置文件

配置文件少的情况下，一个application.yml肯定是够的了。但是在配置文件多的情况下，那就不好说了，总不能一直加个xxx1,xxx2这样吧。而且读取配置属性也是个大问题。

### 1.配置文件名的指定

![image-20220414204603906](https://s2.loli.net/2022/04/14/WJrKgYAXZhdp1Nc.png)

可以通过这种方式来指定相对应的文件:`--server.config.name=xxx`

同样也可以通过指定路径名的方式来完成文件的指定:`--server.config.location=xxx`注意这里需要使用全路径名来定位到指定的路径中去。或者使用`--server.config.location=classpath:/xxx.yml`也可以达到想要的效果。并且可以使用多个配置文件，但是优先级是在后面的配置文件优先级比前面写的配置文件的高。

## 4、多环境的配置文件处理

在开发过程中难免有多种情况，开发和生产使用的配置肯定是不一样的。

![image-20220414205752344](https://s2.loli.net/2022/04/14/icJhSvwygA3mOCE.png)

![image-20220414213859301](https://s2.loli.net/2022/04/14/7JMqe6WOSsUAuy9.png)

同一个配置文件里配置三种环境，注意底下这个document，发现编译器其实已经检测到了这三种环境。

应用部分的环境：

![image-20220414214118294](https://s2.loli.net/2022/04/14/yObNnweL1QVr79z.png)

新的格式，其实用之前的过时的格式也没啥大问题

![image-20220414214553443](https://s2.loli.net/2022/04/14/UWu4y9JtrpBj5ND.png)

一般情况下，我们只需要在不同的环境下配置冲突的配置文件就好了，一些重复的配置只需要在主文件中进行配置就好了

单一环境多个文件一起配置：

``` yml
spring:
  profiles:
    active: dev
      include: devDB,debMVC
```

这里我们使用了多个配置文件，优先级是后加载的会覆盖前面的配置文件冲突的属性。

多个环境多个配置文件的配置：

```yml
spring:
  profiles:
    active: dev
    #给配置文件设置分组
    group:
      "dev": devDB,devRedis,devMVC
      "pro": proDB,proRedis,proMVC
      "test": testDB,testRedis,testMVC
```

### Maven与Springboot的环境配置出现冲突的情况

![image-20220414220051660](https://s2.loli.net/2022/04/14/JAf7GPwgrlDFyQR.png)

修改配置文件中属性：

```yml
spring:
  profiles:
    active: @dev@
    group:
      "dev": devDB,devRedis,devMVC
      "pro": proDB,proRedis,proMVC
      "test": testDB,testRedis,testMVC
```

Maven的优先级比springboot优先级更高

## 5、日志

日志的作用：

- 日志用于记录开发调试和运维过程的消息
- 日志的级别总共6种，通常使用4种即可，分别是DEBUG，INFO，WARN，ERROR
- 可以通过日志组和代码包的形式进行日志显示级别的控制

### 日志的使用

![image-20220414220555147](https://s2.loli.net/2022/04/14/V3hStOM1dknT7og.png)

![image-20220414220607636](https://s2.loli.net/2022/04/14/28GQ7F3hbmDioWH.png)

这里也可以设置某个包的日志级别：

```yml
logging:
  level:
    root: info
    #设置某个包的日志级别
    com.fanser.controller: debug
```

缺点很明显：万一工程比较庞大情况下，我们需要设置一大堆的的属性，这可太致命了！

当然，办法肯定是有的，像我们之前的配置文件那样，设置好分组就好了

```yml
logging:
  group:
  	ebank: com.fanser.controller,com.fanser.dao
  	iservice: com.alibaba
  level:
    root: info
    #设置某个包的日志级别
    com.fanser.controller: debug
    ebank: warn
```

这样就能设置多个包的日志级别了，当然，直接选择某个大包其实也能完成就是了。

### 使用lombok的注解快捷添加日志对象

这里还是有些麻烦的，想个办法来解决声明日志的这些操作，很好的一点是，我们使用的lombok现在还挺牛逼的，可以直接声明日志，都省的你自己想声明日志要哪个类了。

使用lombok中的 **@Slf4j** 注解来为类快速添加日志对象，像使用@Data 注解那样使用就好了。

### 日志输出格式控制

![image-20220414222315276](https://s2.loli.net/2022/04/14/2HCPTjn9G54OJBv.png)

![image-20220414222844057](https://s2.loli.net/2022/04/14/QJthmCGDXURFIPw.png)

![image-20220414222859708](https://s2.loli.net/2022/04/14/LomIVHQh72rzCae.png)

### 日志文件

![image-20220414223322268](https://s2.loli.net/2022/04/14/mXovjzMnD2wGJi4.png)