## Redis为什么单线程也这么快

1. 高性能的服务器不一定是多线程的
2. 多线程（CPU上下文切换的时间消耗）并不一定比单线程快

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