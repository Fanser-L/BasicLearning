# 开发实用篇



## 热部署

> 热部署，就是在应用正在运行的时候升级软件，却不需要重新启动应用。

![image-20220414224136838](https://s2.loli.net/2022/04/14/EOnAXcgaSstQ1ed.png)

**启动热部署**

关于热部署：

- 重启（Restart）：自定义开发代码，包含类，页面，配置文件等等，加载位置restart类加载器
- 重载（Reload）：jar包，加载位置base类加载器

![image-20220414234330329](https://s2.loli.net/2022/04/14/fduPjq58r3MtaY2.png)

**所有代码内容都会参与热部署嘛？**

![image-20220415105836900](https://s2.loli.net/2022/04/15/hNA2UZ76oRGDJIq.png)

默认配置里自动忽略了一部分的文件夹，这些文件夹下的文件不会自动热部署。 既然是配置，那就肯定有办法对配置进行修改，自定义的方式就如上，这样就可以指定部分文件夹不进行热部署了！

**如何禁用热部署呢？**

```yml
devtools:
  restart: 
    exclude: static/**,public/**
    enabled: false
```

当然这只是其中一种禁用方式，通过maven导入的工具肯定还有从其他地方禁用的方式，比如说从application中来编写java代码来禁用，或者说通过maven配置来禁用（没尝试过，不知道可不可行）

## 第三方bean属性的绑定

什么叫第三方的bean属性的绑定，bean属性的绑定应该怎么搞，在spring里还有个xml文件，然后通过创建bean 的方式来注入属性，现在连写了bean的配置文件都没了，怎么来给bean注入属性呢？

其实学习spring的时候也学过使用注解开发，而现在就是全注解开发的典型。

创建一个自定义的类，然后给他注入相应的属性：

```java
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
//需要添加前缀：前缀就是配置文件的上级目录
/*
* servers:
* address: 192.168.0.1
* port: 8081
* timeout: -1
* */
@ConfigurationProperties(prefix = "servers")
public class ServerConfig {
    private String ip;
    private int port;
    private long timeout;
}
```

这里我们学习一个新的注解**@ConfigurationProperties**，感觉跟之前学过的**@Configuration**有些相似之处。

具体的用法： @ConfigurationProperties 注解需要一个前缀来表示属性的层级，高级的属性需要通过类似包的路径的方式来给附上相应的值。

但是使用的时候会有点小问题：

![image-20220415123854953](https://s2.loli.net/2022/04/15/tYidPupyj92b8k7.png)

----

这里我们就已经初步达成了目标，我们想要的是直接给外部的第三方的类库注入相关的属性，那么，同样的，给第三方的bean同样的以这种方式来注入相应的属性。

```java
    @Bean
    //参考刚才自定义的类的配置，外部引入的类应该也能够通过导入配置文件的方式来完成bean的创建，那么 同样的，我们使用@Configuration注解
    @ConfigurationProperties(prefix = "datasource")
    public DruidDataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        //因为druid连接池使用的是懒加载的方式，所以不会直接实例化数据库驱动，所以不用担心导包的问题
        //既然这也是加载配置，那么有没有办法能够通过配置来解决这些问题呢？ 毕竟是bean对象
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
        return  ds;
    }
```

```yml
servers:
  ip: 192.168.0.1
  port: 8081
  timeout: -1

datasource:
  driverClassName: com.mysql.jdbc.Driver
```

这样就完成对应属性的注入了！

然后另外科普另一个注解 **@EnableConfigurationProperties()**，默认要求是需要注入需要的对象，也就是需要注入属性的bean



![image-20220415123837178](https://s2.loli.net/2022/04/15/6L3ldDyne8WQxNG.png)

## 松散绑定

![image-20220421144131188](https://s2.loli.net/2022/04/21/ohdfG9l83vwP4K6.png)

简单来说，就是springboot很智能，你配置文件里属性随便写，只要英文字母对得上，就能识别出来。注意的是，前缀（prefix）的属性名需要使用小写或者使用下划线来分割，不能使用驼峰命名方式来定义。

## 数值计量校验

![image-20220421150239149](https://s2.loli.net/2022/04/21/fITqxAzwDjJSXu8.png)

![image-20220421175557609](https://s2.loli.net/2022/04/21/YdtfOgM31bEAXhI.png)

![image-20220421175606003](https://s2.loli.net/2022/04/21/YrjIfCBszeGx5nA.png)

![image-20220421175647072](https://s2.loli.net/2022/04/21/9xZyLYlX1ICg6dP.png)

## 进制数据转换规则

配置文件中可能出现的问题，格式转换导致的密码错误。 字符串的传递最好还是加上引号，以免导致进制错误，然后带来一系列的问题，如： 0127 因为符合8进制的格式，可能会导致这部分的字符转换为8 进制数值，然后导致数据对不上的问题。

所以，如果是字符串，建议还是加上双引号来处理这些数据。

## 测试

### 加载测试专用属性

> 为了可以给单个测试来定义特定的环境属性

![image-20220421180934300](https://s2.loli.net/2022/04/21/uTzwHI1E4PNg7Wf.png)

### 加载测试专用配置

> 给一套测试环境导入专用的配置，如外部bean等等

![image-20220421181632364](https://s2.loli.net/2022/04/21/zH2xQoUyn7SEWaT.png)

<img src="https://s2.loli.net/2022/04/21/ZF5xTX6NGjRKs3H.png" alt="image-20220421183355055" style="zoom:150%;" />

![image-20220421183752219](https://s2.loli.net/2022/04/21/zAJ4y9tR7h3EKlC.png)

![image-20220421184321966](https://s2.loli.net/2022/04/21/cVuaLfktFCv8EKo.png)

![image-20220421192643357](https://s2.loli.net/2022/04/21/OaJdj9sNfCEZcrn.png)

![image-20220421192941874](https://s2.loli.net/2022/04/21/6GkClMXoZnSwxcV.png)

![image-20220421193245620](https://s2.loli.net/2022/04/21/IjxZqmC2oSdWKJ7.png)

![image-20220421193927751](https://s2.loli.net/2022/04/21/KZsN1ViJbM4ngoC.png)

![image-20220421194433338](https://s2.loli.net/2022/04/21/8kjFdVNLP41zq9E.png)

## 数据层解决方案

### 数据源

两种配置Druid的方式的区别

![image-20220422123844675](https://s2.loli.net/2022/04/22/2MvifWreq3DSGgx.png)

这种方式是已经配置了数据源的情况下，另一种情况就是，导入druid-spring-starter 依赖，springboot会自动的帮你配置druid数据源，自动装配这部分的内容。

如果连stater也没配置，那么就会使用Hikari数据源，这是springboot内置的数据源

![image-20220422123658458](https://s2.loli.net/2022/04/22/T7zr5QaEsKiPCkv.png)

![image-20220422124214439](https://s2.loli.net/2022/04/22/dPewJjNHIFtKkuA.png)

### 数据层

![image-20220422124348268](https://s2.loli.net/2022/04/22/a52I1z6lgZcKOus.png)

经典的JdbcTemplate

对应的关系： 在Mybatis-Plus中内置了 JdbcTemplate，所以说不需要额外导入jdbcTemplate

### 内嵌数据库

![image-20220422125515119](https://s2.loli.net/2022/04/22/LSdjK6xb3mycpUN.png)

配置H2数据源：

![image-20220422130429327](https://s2.loli.net/2022/04/22/PWSrEkAxnKghQbY.png)

![image-20220422130130429](https://s2.loli.net/2022/04/22/tALzIaUloiO4YCZ.png)

## Redis整合

首先下载Redis，注意：Redis官方是没有准备windows 版本的Redis的，需要自行的去github上下载 https://github.com/MicrosoftArchive/redis/releases

下载完之后就把服务端开启，之后就能完成一系列的工作了，然后是将redis整合到springboot上。

![image-20220422193610819](https://s2.loli.net/2022/04/22/rbLWBAnGskzK7FJ.png)

![image-20220422193623037](https://s2.loli.net/2022/04/22/O3joXq7e9CzhMUr.png)

![image-20220422193633390](https://s2.loli.net/2022/04/22/qMk9Vz75lPhdaLr.png)

![image-20220422193654278](https://s2.loli.net/2022/04/22/pXYbkcqPmrCU75s.png)

![image-20220422193719348](https://s2.loli.net/2022/04/22/B2OtGSZH7xfpIor.png)

思考，控制台和程序中操作的不同步问题：

![image-20220422193937779](https://s2.loli.net/2022/04/22/At8dgp1EYDWoeNB.png)

在代码层使用了另一种方式来写入内容的。

![image-20220422194056181](https://s2.loli.net/2022/04/22/fqDgEa7OA9ulFTQ.png)

```java
@Autowired
private StringRedisTemplate stringRedisTemplate;
@Test
void setRedis2( ) {
    stringRedisTemplate.opsForValue().set("testKey","testValue");
}
@Test
void getRedis2( ){
    ValueOperations ops = stringRedisTemplate.opsForValue();
    Object testKey = ops.get("testKey");
    System.out.println(testKey);
}
```

记得得对对象进行自动装配



redis中的 jedis 和 lettuce

![image-20220422194427604](https://s2.loli.net/2022/04/22/iY9RmPqjI5dU1bk.png)

## MongoDB

![image-20220422194524492](https://s2.loli.net/2022/04/22/H5vyAfnehz2D8VB.png)

应用场景：

![image-20220422194931266](https://s2.loli.net/2022/04/22/qKL5jVH9rpTfSxk.png)

整合上和Redis相似，1. 先导入pom依赖 2. 对MongoDB进行配置 3. MongoTemplate 

## ES

Elasticsearch 分布式全文搜索引擎

启动报错：received plaintext http traffic on an https channel, closing connection Netty4HttpChannel{localAddress=/[0:0:0:0:0:0:0:1]:9200, remoteAddress=/[0:0:0:0:0:0:0:1]:62432}

**原因**：是因为ES8默认开启了 ssl 认证。

修改elasticsearch.yml配置文件

将**xpack.security.enabled**设置为false

![image-20220422221821709](https://s2.loli.net/2022/04/22/oXga1MBrFIxJVR6.png)

```yml
xpack.security.enabled: false
```

修改完成，关闭，重启，问题解决！

![image-20220422222001502](https://s2.loli.net/2022/04/22/nK6tJp4Aqis7kuR.png)

创建索引直接使用put请求，风格就是RestFul风格的来完成对应的索引的添加，添加完成后再使用get来获取。 具体的内容得通过从body中给其赋值。附到mapping上就好了。

注意，需要添加插件，ik分词器。下载地址： https://github.com/medcl/elasticsearch-analysis-ik

![image-20220423164707090](https://s2.loli.net/2022/04/23/9OJUxzs7uGNlr2Q.png)

![image-20220423164723008](https://s2.loli.net/2022/04/23/G6FmKrgSxyfuUX1.png)

![image-20220423164806777](https://s2.loli.net/2022/04/23/39t8qIUQX6f4Eca.png)

安装可能出现的问题： 添加ik分词器之后出现闪退的情况

1. 没有在添加插件后把插件放入 `ik`文件夹中
2. 文件路径中存在空格
3. es版本与ik分词器版本不一致，在 分词器的配置文件中更新es版本即可`plugin-descriptor.properties`
   - 然后就是不知道es版本的情况，不知道es版本是啥，点击lib目录，然后找es的jar包即可，有写是哪个版本

请求中添加属性：

注意 类型上不是java的int 等等类型，文本就是可以被分词查询的内容。添加文档

```json
{
    "mappings":{
        "properties":{
            "id":{
                "type":"keyword"
            },
            "type":{
                "type":"keyword"
            },
            "name":{
                "type":"text",
                "analyzer":"ik_max_word",
                "copy_to":"all"
            },
            "description":{
                "type":"text",
                "analyzer":"ik_max_word",
                "copy_to":"all"
            },
            "all":{
                "type":"text",
                "analyzer":"ik_max_word"
            }
        }
    }
}
```

## 缓存

> 缓存是一种介于数据永久存储介质与数据应用之间的数据临时存储介质

- 使用换成可以有效的减少低速数据读取的次数，提高系统性能

  

### 模拟缓存实现：

```java
package com.fanser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanser.dao.BookMapper;
import com.fanser.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;

    private HashMap<Integer,Book> cache = new HashMap<Integer,Book>();
    @Override
    public boolean save(Book book) {
        return bookMapper.insert(book)>0;
    }

    @Override
    public boolean deleteById(int id) {
        return bookMapper.deleteById(id)>0;
    }

    @Override
    public boolean update(Book book) {
        return bookMapper.updateById(book)>0;
    }

    @Override
    public Book selectById(int id) {
        //如果当前缓存中没有本次查询查找的id，则进行查询，否则直接从缓存中返回
        Book book = cache.get(id);
        if (book==null){
            Book queryBook = bookMapper.selectById(id);
            cache.put(id,queryBook);
            return queryBook;
        }
        return book;
    }

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectList(null);
    }
}
```

这里我们使用了hashmap来模拟了一个缓存用的容器，把已经查询过的结果放入到其中，只要不是查询没有查找过的数据，那么就可以直接从hashmap中取出来了。

模拟手机验证码生成：

- 创建一个service层和Controller层就好了，记得加上对应层的注解
- 模拟手机验证码生成需要的参数 1. 手机号 2. 验证码

service层：

```java
package com.fanser.service;

public interface MsgService {
    public String get(String tele);
    public boolean check(String tele,String code);
}
```

serviceImpl：

```java
package com.fanser.service.impl;

import com.fanser.service.MsgService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class MsgServiceImpl implements MsgService {
    private HashMap<String,String> cache = new HashMap<String,String>();

    @Override
    public String get(String tele) {
        String code = tele.substring(tele.length() - 6);
        cache.put(tele,code);
        return code;
    }

    @Override
    public boolean check(String tele, String code) {
        boolean b = cache.get(tele).equals(code);
        return b;
    }
}
```

Controller：

```java
package com.fanser.controller;

import com.fanser.config.util.R;
import com.fanser.pojo.Book;
import com.fanser.service.impl.BookServiceImpl;
import com.fanser.service.impl.MsgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgServiceImpl msgService;
    @GetMapping("{tele}")
    public String get(@PathVariable String tele){
       return msgService.get(tele);
    }
    @PostMapping
    public boolean check(String tele,String code){
        return msgService.check(tele,code);
    }

}
```

![image-20220425160501615](https://s2.loli.net/2022/04/25/Kk5y1VgWNT4Bn7z.png)

![image-20220425160522905](https://s2.loli.net/2022/04/25/IBsHAVK9JQpOrFL.png)

![image-20220425142719417](https://s2.loli.net/2022/04/25/GyQOjpusi5naKCA.png)

![image-20220425142725704](https://s2.loli.net/2022/04/25/aWzKwnyht18Zo2C.png)

1. 手机验证码案例基础代码准备

   - 存储手机号和验证码的实体类`SMSCode.java`

     ```java
     @Data
     public class SMSCode {
         private String phone;
         private String code;
     }
     ```

   - 生成验证码的工具类`CodeUtils.java`

     ```java
     //这种随机产生验证码的方式多少有点大病
     
     @Component
     public class CodeUtils {
         private final String padding = "000000";
         // 生成验证码（位数少于6位左边填充0，填充方法1）
         public String generateCode(String phone) {
             int hash = phone.hashCode();
             int encryption = 20228888;
             long result = hash ^ encryption;
             long nowTime = System.nanoTime();
             result = (result ^ nowTime) % 1000000;
             String code = result + "";
             // code = phone;
             // padding.substring(code.length())   code.length()
             //                                         6
             //             0                           5
             //             00                          4
             //             000                         3
             //             000                         3
             //             0000                        2
             //             00000                       1
             //             000000                      0
             code = padding.substring(code.length()) + code;
             // System.out.println(code);
             return code;
         }
     
         private final String[] patch = {"000000", "00000", "0000", "000", "00", "0", ""};
         // 生成验证码（位数少于6位左边填充0，填充方法2）
         public String generateCode1(String phone) {
             int hash = phone.hashCode();
             int encryption = 20228888;
             long result = hash ^ encryption;
             long nowTime = System.nanoTime();
             result = (result ^ nowTime) % 1000000;
             String code = result + "";
             // code = phone;
             // patch[code.length]    code.length()
             //   000000                    0
             //   00000                     1
             //   0000                      2
             //   000                       3
             //   00                        4
             //   0                         5
             //                             6
             code = patch[code.length()] + code;
             // System.out.println(code);
             return code;
         }
     
         // 根据手机号从缓存中获取验证码，缓存中有的话返回缓存中的值，没有的话就返回null
         @Cacheable(value = "smsCode", key = "#phone")
         public String getCodeByPhoneFromCache(String phone) {
             return null;
         }
     }
     ```

     ```java
     package com.fanser.config.util;
     
     import org.springframework.cache.annotation.Cacheable;
     import org.springframework.stereotype.Component;
     
     @Component
     public class SimCodeGetter {
         /**
          * 生成？位的数字类型的短信验证码
          *
          * @return
          */
         public static String generatedcode() {
             String code = (Math.random() + "").substring(2, 2 + 6);
             return code;
         }
     
         //    public static void main(String[] args) {
     //        System.out.println(generatedcode());
     //    }
     // 根据手机号从缓存中获取验证码，缓存中有的话返回缓存中的值，没有的话就返回null
         @Cacheable(value = "cacheSpace", key = "#tele")
         public String getCodeByPhoneFromCache(String tele) {
             return null;
         }
     }
     ```

   - 业务层接口`SMSCodeService.java`

     ```java
     public interface SMSCodeService {
         String sendCodeToSMS(String phone);
     
         boolean checkCode(SMSCode smsCode);
     }
     ```

     业务层接口实现类`SMSCodeServiceImpl.java`

     ```java
     @Service
     public class SMSCodeServiceImpl implements SMSCodeService {
         @Autowired
         private CodeUtils codeUtils;
     
         @Override
         public String sendCodeToSMS(String phone) {
             return codeUtils.generateCode(phone);
         }
     
         @Override
         public boolean checkCode(SMSCode smsCode) {
             return false;
         }
     }
     ```

2. 加入`spring`默认的缓存功能

   - 在`pom.xml`中添加缓存依赖

     ```xml
     <!--cache-->
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-cache</artifactId>
     </dependency>
     ```

   - 在`SMSCodeServiceImpl`的`sendCodeToSMS()`方法上添加`@CachePut(value = "smsCode", key = "#phone")`，如下所示

     ```java
     @Override
     // @Cacheable(value = "smsCode", key = "#phone")
     // 这里@Cacheable注解不适用，因为@Cacheable注解的功能是：如果缓存中没有值就去执行一次方法，然后将值存到缓存中，
     // 如果有值就直接从缓存中取值并返回，并不会执行方法，因而缓存中值不会发生改变。
     // 而用户点击界面发送一次验证码就调用了一次该方法，需要获取到新的验证码。
     // 使用新的缓存注解@CachePut可以解决这个问题，每次调用都会执行方法，向缓存中存新的值并返回
     @CachePut(value = "smsCode", key = "#phone")
     public String sendCodeToSMS(String phone) {
         return codeUtils.generateCode(phone);
     }
     ```

   - 编写`checkCode()`方法：校验验证码是否正确

     错误的写法：

     ```java
     @Override
     public boolean checkCode(SMSCode smsCode) {
         // 取出缓存中的验证码并与传递过来的验证码比对，如果相同，返回true，否则，返回false
         // 用户填写的验证码
         String code = smsCode.getCode();
         // 缓存中存的验证码
         String cacheCode = getCodeByPhoneFromCache(smsCode.getPhone());
         return cacheCode.equals(code);
     }
     
     // 根据手机号从缓存中获取验证码，缓存中有的话返回缓存中的值，没有的话就返回null
     @Cacheable(value = "cacheSpace", key = "#phone")
     public String getCodeByPhoneFromCache(String phone) {
         return null;
     }
     ```

     在`getCodeByPhoneFromCache()`方法上加了`@Cacheable(value = "cacheSpace", key = "#phone")`，然后在`checkCode()`方法中调用`getCodeByPhoneFromCache()`方法，这种方式看似是正确的，实际上`@Cacheable`注解不会生效，导致`getCodeByPhoneFromCache()`的返回值始终是`null`。这是由于被`spring`管理的类内的方法互调注解不会被解析。

     由此可以想到解决办法，将`getCodeByPhoneFromCache()`放到另外一个类（这里为了方便起见，直接放到`CodeUtils`类中），并将这个类交由`spring`管理（在类上面加`@Component`注解），然后再用`codeUtils.getCodeByPhoneFromCache(smsCode.getPhone())`即可正常从缓存中拿到值。代码如下：

     ```java
     @Override
     public boolean checkCode(SMSCode smsCode) {
         // 取出缓存中的验证码并与传递过来的验证码比对，如果相同，返回true，否则，返回false
         // 用户填写的验证码
         String code = smsCode.getCode();
         // 缓存中存的验证码
         String cacheCode = codeUtils.getCodeByPhoneFromCache(smsCode.getPhone());
         return cacheCode.equals(code);
     }
     ```
