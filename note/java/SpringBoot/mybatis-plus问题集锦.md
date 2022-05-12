# 关于@webFilter注解扫描不到，导致整个过滤器失效的问题

我们可以使用webFilter注解来完成指定路径的过滤拦截，但是今天使用的时候却出现了故障：

![image-20220511210022325](https://s2.loli.net/2022/05/11/iCP7MGWHc6RY48t.png)

我明明已经删除**用户已经登陆**这条日志信息，但是控制台却输出了这条日志，然后进行排查：

![image-20220511211039643](https://s2.loli.net/2022/05/11/HgAMjEY7GDenIrt.png)

确认没有写这条日志

然后对maven进行一下清理，怀疑是缓存的问题导致。

 ![image-20220511211216008](https://s2.loli.net/2022/05/11/hVcTrREqmMXSbIA.png)

然后清理过后直接过滤器失效了，登陆验证都没了

![image-20220511211341116](https://s2.loli.net/2022/05/11/ysJ3EvuDnhk29m7.png)

那为什么之前能生效呢？不理解，按道理是能够自动扫描到的。

### 解决方案

两种解决方案：

- 在@webFilter注解下再加上**@component**

```java
@Component
@WebFilter()
```

- 在启动类加上**@ServletComponentScan**

```java
@SpringBootApplication
@ServletComponentScan
```

