# 1、NoSQL数据库简介



技术的分类

1、解决功能性的问题：Java，JSP，RDBMS、Tomcat、HTML、Linux、JDBC、SVN

2、解决拓展性问题：Struts、Spring、SpingMVC、Hibernate、Mybatis

3、解决性能问题：  NoSQL、Java线程、Hadoop、Nginx、MQ、ElasticSearch



为什么需要NoSQL数据库？

时代的发展导致客户端的访问量越来越大，服务器不堪重负（session对象的存储导致的问题），对于后端的关系型数据库来说，存取这些数据也造成了巨大的IO压力，CPU压力很大。于是乎引入NoSQL数据库的使用，因为NoSQL型数据库不需要进行IO，能很好的缓解来自CPU和内存的压力。

同样，因为业务的增长，数据库中的数据量也不断增加，导致了性能的下降，于是乎通过分库，分表等操作来实现业务逻辑的拆分，以提高一定的性能，但同样的，这导致了业务逻辑的破坏。而NoSQl数据库就能很好的解决这个问题。

## 1.1、NoSQL数据库

> Not Only SQL,不仅仅是SQL，泛指==非关系型的数据库==

NoSQL不依赖业务逻辑方式存储，而是以简单的key-value 模式储存，因此大大增加的数据库的扩展能力。

- 不遵循SQL标准
- 不支持ACID
- 远超于SQL的性能

## 1.2、NoSQL的适用场景

- 对数据高并发的读写
- 海量数据的读写
- 对数据的高可拓展性

## 1.3、NoSQL不适用场景

- 需要事务支持
- 基于sql的结构化查询存储
- 用不着sql和用了sql也不行的情况，考虑使用NoSQL

## 1.4、常见的NoSQL数据库

### 1. Memcache

- 很早出现的NoSQL数据库
- 数据都存储在内存中，==一般不持久化==
- 支持简单的key-value模式，==支持类型单一==
- 一般是作为==缓存数据库==来辅助持久化的数据库

### 2. Redis

- 几乎覆盖了Memcache的绝大部分功能
- 数据都在内存中，==支持持久化==，主要用于备份恢复
- 除了支持简单的key-value模式，还支持多种数据结构的存储，如==list，set，hash，zset==等等
- 一般是作为==缓存数据库==辅助持久化的数据库
- Redis为什么单线程也这么快
  1. 高性能的服务器不一定是多线程的
  2. 多线程（CPU上下文切换的时间消耗）并不一定比单线程快

### 3. MongoDB

- 高性能，开源，模式自由的==文档型数据库==
- 数据都在内存中，如果内存不足，把不常用的数据保存到硬盘中去
- 虽然是key-value模式，但是对于value（尤其是json）提供了丰富的查询功能
- 支持二进制数据以及大型对象
- 开源根据数据的特点代替RDBMS，成为独立的数据库，或者配合RDBMS，存储特定的数据 

## 1.5、行式存储数据库（大数据时代）

### 1.行式数据库

把每行当做一部分数据来存储，对于查询关系会有些困难，比如查询平均值等等。

### 2.列式数据库

把每列当做一部分数据来存储，同样的查询关系会有些困难

# 2、Redis概述与安装

> Redis（Remote Dictionary Server )，即远程字典服务，是一个开源的使用ANSI C 语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value 数据库，并提供多种语言的API。

- Redis 是**开源的**Key-value型数据库
- 和Memcache 类似，它支持存储的key-value 类型相对角度，包括**String 字符串， list 链表， set 集合， zset sort set --有序集合和hash**
- 这些数据类型都支持push/pop ，add/remove 及取交集并集和差集更丰富的操作，而且这些操作都是**原子性**的。
- 在此基础上，Redis支持不同方式的**排序**
- 与Memcache一样，为了保障效率，**数据都是缓存在内存中**的。
- 区别是，Redis会**周期性**的把更新的**数据写入到磁盘**中，或者吧修改操作写入追加的记录文件
- 并且在此基础上实现了master - slave (主从)同步

## 2.1、Linux系统上redis的安装：

1. 首先redis是使用c语言编写的，所以需要有c语言环境，环境要求在4.8.5以上的gcc版本 使用
   ```shell
   yum install centos-release-scl scl-utils-build
   #安装c环境
   yum install gcc-c++
   #查看当前环境版本
   gcc --version
   ```

2. 解压下载好的放在/opt目录下的redis压缩包
   ```shell
   tar -xvf redis-6.2.6.tar.gz
   #进入解压后的文件，然后进行编译
   make
   #编译完成后安装redis
   make install
   ```

3. 安装完成，安装完成后一般会在 /usr/local/bin 目录下，那么我们直接进入到该目录下

   ![image-20220429212929591](https://s2.loli.net/2022/04/29/vPeYfu9BA4p5azi.png)

   直接执行redis-server 然后就能直接启动redis了，当然这样启动是通过前台来启动的，工作中要是关闭了当前命令窗就直接关闭了，这样不行，所以还需要设置后台启动。

4. 先找到redis安装目录下的redis.conf文件，然后修改其中的守护线程 daemon  no  改为 yes 保存退出即可
   ![image-20220429213343894](https://s2.loli.net/2022/04/29/LEQojGCRJkpWIxn.png)

5. 开启服务器，配置文件使用复制到etc 文件目录下的修改后的 redis.conf 配置文件

6. 查看后台开启的服务 ps -ef    可以配合grep命令来完成指定字段内容的查找  `ps -ef |grep redis`
   ![image-20220429213708397](https://s2.loli.net/2022/04/29/vOEZpPBuYsa659N.png)

7. 客户端访问 `redis-cli` 
   ![image-20220429213729708](https://s2.loli.net/2022/04/29/k2NZBesgR5nuXIl.png)

8. 关闭后台进程的方式

   1. 直接通过kill命令来把对应的进程号下的进程杀死
   2. shutdown 关闭服务器

## 2.2、windows系统的Redis安装

首先下载Redis，注意：Redis官方是没有准备windows 版本的Redis的，需要自行的去github上下载 https://github.com/MicrosoftArchive/redis/releases 下载完之后就把服务端开启，之后就能完成一系列的工作了，如果出现了bug，那就先开启客户端，然后关闭然后再开服务端就好了。

![image-20220429201806161](https://s2.loli.net/2022/04/29/2LJINjmB5uDqEna.png)

先从windows版本的来解释一下这些exe文件：

- redis-benchmark：性能测试工具，可以在自己本地运行，看看自己本地性能如何
- redis-check-aof：修复有问题的 AOF文件
- redis-check-dump：修复有问题的dump.rdb 文件
- redis-sentinel：redis集群的使用
- redis-server：redis服务器启动命令
- redis-cli：redis客户端操作入口

## 2.3、Redis介绍相关知识

- 端口号6379的来源：Alessia ==Merz== merz在九键上就是对应的6379 这四个数字。
- 默认有16个数据库，类似数组的下标从0开始，初始默认使用的是0号库
  使用命令`select <dbid>`来切换数据库，如 `select 5`
- 统一的密码管理，所有库含有同样的密码
- **dbsize** 查看当前数据库的key的数量
- **flushdb 清空当前库**
- **flushall 通杀全部库**

redis是单线程+多路IO复用技术

多路复用：是指使用一个线程来检查多个文件描述符（Socket）的就绪状态，比如调用select 和 poll 函数，传入多个文件描述符，如果有一个文件描述符就绪，则返回，否则阻塞直到超时。得到就绪状态后进行真正的操作可以在同一个线程里执行，也可以启动线程执行（比如使用线程池）

突然想起来了计算机网络中的多路复用，在计算机网络中的解释：

![image-20220429220722872](https://s2.loli.net/2022/04/30/LnHPWdq2m4hp6vQ.png)

==串行  VS  多线程 + 锁（Memcached） VS  单线程  +  多路IO复用==

与Memcache三点不同：支持多数据类型，支持持久化，单线程+多路IO复用

# 3、常用五大数据类型

redis常见数据类型操作命令 <http://www.redis.cn/commands.html>/

## 3.1、Redis键（key）

- `keys *` 查看当前库所有key（匹配 ：keys *1）

- `exists key` 判断某个key 是否存在（返回1代表为true 0 为false）
  ![image-20220430140659168](https://s2.loli.net/2022/04/30/71Qa2tgjVoUpC6M.png)

- `type <key>` 查看key 的类型

- `del <key>`  删除指定的key 数据
- **`unlike <key>` 根据value选择非阻塞删除** 仅将keyspace 元数据中删除，真正的删除会在后续异步操作
- `expire <key> time`  10秒钟：为给定的key 设置过期时间
  ttl key 查看还有多少秒时间过期，-1 表示永不过期，-2 表示已经过期
  ![image-20220430141334055](https://s2.loli.net/2022/04/30/t2LQ9GwDaWivxrj.png)
- `select` 命令切换数据库
- `dbsize` 查看当前数据库的key的数量
- `flushdb` 清空当前库(删库危险，不要瞎搞)
- `flushall` 清空所有库

 ![image-20220430141938071](https://s2.loli.net/2022/04/30/PdzaNl8xTsDpuA7.png)

## 3.2、Redis字符串（String）

> 简介

- String是 Redis 最基本的类型，可以理解成和Memcache 一模一样的数据类型，一个key 对应 一个 value。
- String 类型是二进制安全的，意味着Redis 的 String 可以包含任何数据，比如jpg 图片或者序列化对象
- String 类型是Redis 最基本的数据类型，一个Redis 中 字符串 **value**最多可以是512M

### 常用命令

- `set <key> <value>` 添加键值对 
  其实在上面的例子中我们已经使用过了这个语句 `set k1 lucy` 注意，设置同一个key的值时，会进行覆盖操作
- `get  <key>`   获取对应键的值
- `append <key> <value>` 将给定的value 追加到原值的末尾
- `strlen  <key>`   获取值长
- `setnx <key> <value>`  当key 不存在时，设置key的值
   ![image-20220430144713532](https://s2.loli.net/2022/04/30/i9lcWZa6yk3JtTb.png)
- `incr <key>`  让 key 中存储的数字 +1 ，只能对数字操作，如果没有值，则设置新值为1
- `decr <key>`  让 key 中存储的数字 -1 ，只能对数字操作，如果没有值，则设置新值为1
- `incrby <key> <len>`  上面的两种只能进行一步的增减，这种方式可以设置步长
- `decr by  <key>  <len>`  ~~~
   ![image-20220430150126700](https://s2.loli.net/2022/04/30/xf7dhBlL5MYtIay.png)

值得一提的是，incr 是==原子性==的，意味着不会出现多线程下的数据不准确的问题

- `mset <key1 value1> <key2 value2> <key3 value3 ....>` 能够一次性存储多个值
- `mget <key1 key2 key3...>`  能够一次获取多个值
- `msetnx <key1 value1 key2 value2 key...>`  能够一次设置多个value，但是操作是**原子性**的，有任意一个存在导致插入失败会全部都插入失败

 ![image-20220430153657790](https://s2.loli.net/2022/04/30/9yzMRhrvKClaS8k.png)

- `getrange <key> <起始位置> <结束位置>` 获取值的范围
- `setrange <key> <起始位置> <插入的值>` 在指定的位置插入对应的值

 ![image-20220430153735731](https://s2.loli.net/2022/04/30/s2NCzHSwq3jYoBn.png)

- `setex <key> <过期时间> <value>`  设置键值的同时，设置过期时间，单位 s
- `getset <key> <value>` 以新换旧，设置了新值的同时获取旧值

 ![image-20220430153809390](https://s2.loli.net/2022/04/30/FJvArShDBE7oHOc.png)

### String 的底层数据结构

String的数据结构为简单动态字符串 (simple data string，简称SDS)。是可以修改的字符串，内部结构实现上类似于Java的ArrayList，采用预分配冗余空间的方式来减少内存的频繁分配。

![image-20220430154552256](https://s2.loli.net/2022/04/30/OrD8PBqwWxYkdbz.png)

如图，String首先会预分配空间为capacity，一般要高于实际字符串长度，当字符串长度小于1m时，扩容都是**加倍**（java中ArrayList的扩容是1.5倍扩容）当前的空间，如果超过1m，扩容时只会多扩1m的空间。注意之前也提到过的，字符串的最大长度为512m。

## 3.3、Redis列表（List）

> 简介

单键多值

Redis 列表是简单的字符串列表，按照插入顺序排序，你可以添加一个元素到列表的头部(左边) 或者尾部 (右边).

它的底层实际上是个 ==双向链表== ,对于两端的操作性能很高，但是对于通过索引下标查找中间值的性能会比较差。

### 常用命令

- `lpush/rpush <key> <value1 value2 value3...>` 从左边或者从右边放入值，注意push 就类似栈的结构
- `lpop/rpop <key> ` 这里是取值操作，还是一样的 左边出栈或者右边出值，列表空就清除key



- `rpoplpush <key1> <key2>` 从key1列表右边吐出一个值，然后插入到key2列表左边

 ![image-20220430161614315](https://s2.loli.net/2022/04/30/DaocMG1tyZ9LEbw.png)

 ![image-20220430161626565](https://s2.loli.net/2022/04/30/evOSTrDqboY2dRA.png)

 ![image-20220430161644622](https://s2.loli.net/2022/04/30/CvQyUEonSfc8MW6.png)

- `lrange <key> start stop`  按照索引下标获得元素，从左到右
- `lrange <key> 0 -1`  获取列表中的所有值

 ![image-20220430161425678](https://s2.loli.net/2022/04/30/R4ZAXHeGaIbN3tv.png)

- `lindex <key> <index>` 按照索引下标获取元素，从左到右

- `llen <key>` 获取列表长度

- `linsert <key> <before/after> <value> <newvalue>` 在value后面/前面插入newvalue的值
  ![image-20220430163619767](https://s2.loli.net/2022/04/30/1mEn6WO9iXHZfVw.png)

- `lrem <key> <n> <value>` 从左边删除n个 value  （删除指定个数个同样的value）![image-20220430163653870](https://s2.loli.net/2022/04/30/Hpu6rz7ovwNbDj8.png)

- `lset <key> <index> <value>` 将列表下标为index 的值更新为value

### 数据结构

List的数据结构为快速列表 quickList

首先在元素较少的情况下，会使用一块连续的的内存存储，这个结构是ziplist，也就是压缩列表。

它将所有的元素挨着一起存储，分配的是一块连续的内存，当数据量较多的时候才会改成quicklist

因为普通的链表需要附加的指针空间太大，会比较浪费空间，比如这个列表里存的只是int类型的数据，结构上还需要两个额外的指针prev，和next‘。

![image-20220430162943147](https://s2.loli.net/2022/04/30/sLiRKUVeWHYEuAG.png)

Redis将链表和ziplist结合起来成为了quicklist，也就是将多个ziplist使用双向指针串起来使用，这样既满足了快速的插入删除功能，又不会出现太大的空间冗余。

## 3.4、Redis集合（set）

Redis set 对外提供的功能与list类似是一个列表的功能，特殊之处在于set是可以**自动排重**的，当你需要存储一个列表数据，又不希望出现重复数据时，set就是一个很好的选择（怎么感觉全跟java一个样），并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。

Redis的set是string类型的无序集合，它的**底层就是一个value为null的hash表**，所以添加，删除，查找的时间 	复杂度为O（1）。

### 常用命令

`sadd <key><value1><value2> ..... `：将一个或多个 ***member*** 元素加入到集合 ***key*** 中，已经存在的 ***member*** 元素将被忽略

`smembers <key>`：取出该集合的所有值。

`sismember <key><value>`：判断集合 ***<key>*** 是否为含有该 ***<value>*** 值，有返回 1，没有返回 0

`scard<key>`：返回该集合的元素个数。

`srem <key><value1><value2> ....`：删除集合中的某个元素

`spop <key>`：随机从该集合中吐出一个值

`srandmember <key><n>`：随机从该集合中取出 ***n*** 个值，不会从集合中删除

`smove <source><destination>value`：把集合中一个值从一个集合移动到另一个集合

`sinter <key1><key2>`：返回两个集合的交集元素

`sunion <key1><key2>`：返回两个集合的并集元素

`sdiff <key1><key2>`：返回两个集合的差集元素（***key1*** 中的，不包含 ***key2*** 中的）

### 数据结构

***Set*** 数据结构是字典，字典是用哈希表实现的。

## 3.5、Redis Hash

Redis hash 是一个键值对集合

Redis hash 是一个String类型的field 和 value的映射表，hash非常适合用于存储对象

![image-20220430170415602](https://s2.loli.net/2022/04/30/UxqOzpSe4ZLDhE7.png)

### 常用方法

 `hset <key><field><value>`：给 ***<key>*** 集合中的 ***<field>*** 键赋值 ***<value>***

 `hget <key1><field>`：从 ***<key1>*** 集合 ***<field>*** 取出 ***value***

 `hmset <key1><field1><value1><field2><value2>...`： 批量设置 ***hash*** 的值

 `hexists <key1><field>`：查看哈希表 ***key*** 中，给定域 ***field*** 是否存在

 `hkeys <key>`：列出该 ***hash*** 集合的所有 ***field***

 `hvals <key>`：列出该 ***hash*** 集合的所有 ***value***

 `hincrby <key><field><increment>`：为哈希表 ***key*** 中的域 ***field*** 的值加上增量 1 -1

 `hsetnx <key><field><value>`：将哈希表 ***key*** 中的域 ***field*** 的值设置为 ***value*** ，当且仅当域 ***field*** 不存在

### 数据结构

Hash类型对应数据结构是两种 ziplist （压缩列表），hashtable（哈希表） 。当field - value 长度较短且个数较少是，使用ziplist，否则使用hashtable。

## 3.6、Redis有序集合（Zset）

***Redis*** 有序集合 ***zset*** 与普通集合 ***set*** 非常相似，是一个没有重复元素的字符串集合。

不同之处是有序集合的每个成员都关联了一个评分（***score***）,这个评分（***score***）被用来按照从最低分到最高分的方式排序集合中的成员。集合的成员是唯一的，但是评分可以是重复的。

因为元素是有序的，所以可以很快的根据评分（***score***）或者次序（***position***）来获取一个范围的元素。

访问有序集合的中间元素也是非常快的，因此能够使用有序集合作为一个没有重复成员的智能列表。

### 常用方法

 `zadd <key><score1><value1><score2><value2>…`：将一个或多个 ***member*** 元素及其 ***score*** 值加入到有序集 ***key*** 当中

 `zrange <key><start><stop> [WITHSCORES] `：返回有序集 ***key*** 中，下标在 ***<start><stop>*** 之间的元素

 当带 ***WITHSCORES***，可以让分数一起和值返回到结果集

 `zrangebyscore key min max [withscores] [limit offset count]`：返回有序集 ***key*** 中，所有 ***score*** 值介于 ***min*** 和 ***max*** 之间（包括等于 ***min*** 或 ***max*** ）的成员。有序集成员按 ***score*** 值递增（从小到大）次序排列。

 `zrevrangebyscore key max min [withscores] [limit offset count] `：同上，改为从大到小排列

 `zincrby <key><increment><value>`：为元素的 ***score*** 加上增量

 `zrem <key><value>`：删除该集合下，指定值的元素

 `zcount <key><min><max>`：统计该集合，分数区间内的元素个数

 `zrank <key><value>`：返回该值在集合中的排名，从 0 开始。

### 数据结构

***SortedSet（zset）***是 ***Redis*** 提供的一个非常特别的数据结构，一方面它等价于 ***Java*** 的数据结构 ***Map<String, Double>***，可以给每一个元素 ***value*** 赋予一个权重 ***score***，另一方面它又类似于 ***TreeSet***，内部的元素会按照权重 ***score*** 进行排序，可以得到每个元素的名次，还可以通过 ***score*** 的范围来获取元素的列表。

***zset*** 底层使用了**两个数据结构**

- ***hash***，***hash*** 的作用就是关联元素 ***value*** 和权重 ***score***，保障元素 ***value*** 的唯一性，可以通过元素 ***value*** 找到相应的 ***score*** 值
- 跳跃表，跳跃表的目的在于给元素 ***value*** 排序，根据 ***score*** 的范围获取元素列表

![image-20220430173026828](https://s2.loli.net/2022/04/30/siJa3PC58rKAD46.png)

# 4、配置文件

 #####################404 NOT Found############################

# 5、Redis发布和订阅

> 什么是发布和订阅

Redis发布订阅（sub/pub）是一种消息通信模式：发送者（pub）发送消息，订阅者（sub）接收消息

Redis客户端可以订阅任意数量的频道

![image-20220430215605882](https://s2.loli.net/2022/04/30/SmrfIseWPviJtKa.png)

就两条命令：

`PUBLISH channel message ` 发布信息到channal频道中

`SUBSCRIBE channel [channel ...]` 接收来自订阅频道的message

# 6、Redis新数据类型

## 三种特殊数据类型

### geospatial（地理空间）

- [GEOADD](http://www.redis.cn/commands/geoadd.html)
- [GEODIST](http://www.redis.cn/commands/geodist.html)
- [GEOHASH](http://www.redis.cn/commands/geohash.html)
- [GEOPOS](http://www.redis.cn/commands/geopos.html)
- [GEORADIUS](http://www.redis.cn/commands/georadius.html)
- [GEORADIUSBYMEMBER](http://www.redis.cn/commands/georadiusbymember.html)

底层是基于zset实现的，没有移除位置操作，可以通过 zrem来实现移除指定元素。

### hyperloglog

**允许容错的情况下使用。**

相比于set ，内存占用是固定的，只需要2^64个空间，也就是12kb，节省内存空间。

用于统计数量，统计不同元素的个数，如访问量等等，个人访问重复只记录为1次。

PFADD  添加一组元素

PFCOUNT 计算一组元素中的非重复元素

PFMERGE 合并两组元素，去除重复项

### Bitmap

状态比较简单的，表示是否这种的，就可以使用bitmaps

更加接近一种数据结构。

setbit key  1 0

setbit key  1 1

setbit key  1 1

setbit key  1 0



# 7、Jedis操作Redis6

首先是修改配置文件redis.conf，首先是关闭只能本地访问

将 bind 127.0.0.1：：1  注释掉 

然后是 network 下 关闭保护模式 protected-mode  yes 改为 no

//折腾半天。还不如直接连接本地的，windows版本 凑合用吧

## jedis的基本使用

```java
package com.fanser;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo01 {
    public static void main(String[] args) {
        //创建Jedis对象
        Jedis jedis = new Jedis("localhost",6379);
        //测试
        String value = jedis.ping();
        System.out.println(value);
    }
    //set
    @Test
    public void demo3(){
        Jedis jedis = new Jedis("localhost",6379);
        //清空之前测试添加的数据，防止命名冲突
        jedis.flushDB();

        jedis.sadd("k1","lucy");
        jedis.sadd("k1","mary");
        Set<String> k1 = jedis.smembers("k1");
        System.out.println(k1);

    }
    //list
    @Test
    public void demo2(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.flushDB();

        jedis.lpush("k2","lucy","mary","jack");

        List<String> value = jedis.lrange("k2", 0, -1);
        System.out.println(value);

    }
    //set 和 get
    @Test
    public void demo1(){
        Jedis jedis = new Jedis("localhost",6379);

        jedis.set("name", "lucy");

        String name = jedis.get("name");
        System.out.println(name);

        jedis.mset("k1","v1","k2","v2");

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

    }
}
```

hash

```java
jedis.hset("hash1","userName","lisi");
System.out.println(jedis.hget("hash1","userName"));

Map<String,String> map = new HashMap<String,String>();
map.put("telphone","13810169999");
map.put("address","atguigu");
map.put("email","abc@163.com");

jedis.hmset("hash2",map);

List<String> result = jedis.hmget("hash2", "telphone","email");

for (String element : result) {
	System.out.println(element);
}
```

zset

```java
jedis.zadd("zset01", 100d, "z3");
jedis.zadd("zset01", 90d, "l4");
jedis.zadd("zset01", 80d, "w5");
jedis.zadd("zset01", 70d, "z6");
 
Set<String> zrange = jedis.zrange("zset01", 0, -1);

for (String e : zrange) {
	System.out.println(e);
}
```

## 手机验证码功能实现

> 1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
> 2、输入验证码，点击验证，返回成功或失败
> 3、每个手机号每天只能输入3次

# 8、事务和锁机制

> Redis 事务是一个单独的隔离操作：事务中的所有命令都会序列化，按顺序的执行。事务在执行的过程中，不会被其他客户端发来的命令请求所打断。
>
> Redis 事务的主要作用是 串联多个命令 防止别的命令插队。

==Redis单条命令是保证原子性的，但是事务不保证原子性==

==事务没有隔离级别的概念== 

所有的命令并没有直接被执行，只有在发起执行命令的时候才会执行

## Multi、Exec、discard

从输入Multi 命令开始，输入的命令都会依次进入命令队列中 ，但不会执行，直到输入Exec 后，Redis 会将之前的命令队列中的命令依次执行。

组队的过程中可以通过discard 来放弃组队。

![image-20220502202759683](https://s2.loli.net/2022/05/02/ZLOVFu6yxT1QPAm.png)

![image-20220502203023535](https://s2.loli.net/2022/05/02/ozMKIevUQEZyHSJ.png)

![image-20220502203433477](https://s2.loli.net/2022/05/02/A3zrwpWsojGnu4R.png)

![image-20220502203446813](https://s2.loli.net/2022/05/02/Bc8jwZYPV9igtQo.png)

组队时出现错误，全部不执行，执行时出错，错误的不执行。

组队时一荣共荣，组队后互不相干。

## 事务冲突的问题

![image-20220502203644646](https://s2.loli.net/2022/05/02/84ZvJPk9Utesfia.png)

![image-20220502204454587](../../../../AppData/Roaming/Typora/typora-user-images/image-20220502204454587.png)

## 悲观锁和乐观锁

#### 悲观锁：

- 很悲观，认为什么时候都会出现问题，无论做什么都会进行加锁

#### 乐观锁：

- 很乐观，认为什么时候都不会出现问题，所以不会加锁，更新数据的时候判断一下是否有人修改过这个数据。
- 获取version
- 更新的时候比较version

> redis的监视测试

使用watch 来充当redis的乐观锁

如果事务执行前，另外一个线程对事务监视的值进行了修改，会导致事务执行失败。

如果出现了事务执行失败的情况，那就先放弃watch  使用unwatch，然后获取最新的值在watch

# 9、事务 秒杀案例

![image-20220502210103316](https://s2.loli.net/2022/05/02/PFSwBlgCXExhfU3.png)

![image-20220504140413143](../../../../AppData/Roaming/Typora/typora-user-images/image-20220504140413143.png)

# 10、持久化 RDB（Redis DataBase）

Redis是内存数据库，如果不进行持久化，内存会断电即失。所以持久化是必要的。

优点：

- 适合大规模的数据恢复
- 对数据的完整性要求不高

缺点：

- 需要一定的时间间隔进行操作，如果redis意外宕机了，那么最后一次修改的数据就丢失了
- fork 进程的时候，会占用一定的内存空间

RDB缺点是如果最后一次进行保存时发生了断电，那么会出现数据丢失的情况。

在指定的**时间间隔**内将内存中的==数据集快照==写入内存

备份是如何执行的：

![image-20220504145414042](https://s2.loli.net/2022/05/04/Z5bqGONoM6mKIUT.png)

# 11、持久化AOF

默认是不开启的，需要手动的从配置文件中进行开启，需要将`appendonly` 改为 yes

将我们所有的命令都记录下来（读操作是不进行记录的），如果这个aof文件出错了，那么启动服务器时会出现错误，这样是无法启动redis的。

 ![image-20220504214543789](https://s2.loli.net/2022/05/04/1swCTycl9Fkdj5p.png)

如果aof文件大于64m，就会fork一个新的进程来将文件进行重写

优点：

- 每一次修改都进行同步，文件的完整性会更好
- 默认开启是每秒同步一次，可能会出现丢失1s的数据
- 从不同步效率最高

缺点：

- 相对于数据文件来说，aof远远大于rdb，修复的速度也比rdb满
- aof运行效率也比rdb慢，所以redis默认配置就是rdb

![image-20220504213325051](https://s2.loli.net/2022/05/04/yYHmgTJ2u5KAEox.png)

# 12、主从复制

