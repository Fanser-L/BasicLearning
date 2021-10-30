
## java连接数据库的几种方法

1. 跨过jdbc直接连接相应的数据库
   使用反射来编译不确定的类（用泛型来编译不确定的类型）
2. 利用驱动管理类DriverManger来获取数据库连接
   1.DriverManger获取数据库连接必须手动获取驱动
   2.DriverManger规定了数据库厂商必须为DriverManger注册驱动 静态代码块中会加载驱动 
   建议不管有没有默认加载驱动  都加上 Class clazz = Class.forName(DriverClassName); 以防没有加载驱动出现错误


## 数据库数据的增删改

只需要修改sql语句的内容即可

 1. 获取连接
      conn = JDBCUtils.getConnection();  
 2. 获取PreparedStatement，发送sql语句
      String sql = "insert into student(id,name,sex,age) values(?,?,?,?)";
      ps = conn.prepareStatement(sql)；   
 3. 给sql语句中的 ？ 占位填充符赋值
      ps.setInt(1,01);
      ps.setString(2,"ljr");
      ps.setString(3,"f");
      ps.setInt(4,21);
 4. 执行sql语句
   int row = ps.executeUpdate(); 
   //显示执行效果
   System.out.println("执行了" + row+"行");
 5. 关闭连接
   JDBCUtils.close(conn,ps);

   值得注意的是，关闭连接是必须执行的操作，所以必须放在finally中，确保代码必定执行。


## 数据库的数据的查询

步骤与增删改相似

1. 获取连接
Connection conn = JDBCUtils.getConnection();
2. 获取PreparedStatement，发送sql
String sql = "查询语句";
PreparedStatement ps = conn.prepareStatement(sql);
3. 给占位符赋值
ps.setObject();
4. 执行sql语句
**ResultSet rs = ps.executeQuery();**
5. 获取结果集ResultSet
遍历其中的数据  使用while 等遍历
6. 关闭连接
JDBCUtils.close(conn,ps,rs);

进一步改进，将查询获得的数据封装进入对象内

5. 获取结果集ResultSet
遍历其中的数据  使用while 等遍历
然后创建个实体类，再将遍历的数据填入其中

再进一步改进，获取多个对象的数据，这样就能获得一个类似表的数据

5. 获取结果集ResultSet
遍历其中的数据  使用while 等遍历
然后创建个实体类，再将遍历的数据填入其中
创建一个集合 list ，将封装好的对象再装入集合中去


## 批量处理

简单的说，每次建立连接只传输一次数据，大部分时间都花费在了传输过程中，而传输的内容其实可以通过打包传输的方式来传输，这样就会更快一些，当然，打包的数据多少得视情况而定，有时候太多的数据，数据库一下处理不过来。

1. 这里需要较高版本的mysql-connector，低版本的mysql驱动里没有执行批量处理的这个操作。
2. 新一点的版本的驱动默认情况下批量处理也是处于关闭状态的，需要在**url**后添加 **?rewriteBatchedStatements=true** 来开启批量处理的功能。
3. 需要一个**addBatch()** 方法来打包sql语句，然后进行执行，之后执行完之后需要清空之前打包的sql语句，使用 **claarBatch**来清空其中的sql语句。