# ssmpbuild

基于springboot搭建的图书管理系统，包含增删改查和分页等功能



## 图书管理系统



### 1、CRUD

### 2、接口数据绑定

### 3、异步请求

需要的技术：

- springboot
- 一点点SpringMVC知识
- mybatis-plus的一点知识
- sql语句的拼接
- vue 入门级 和axios入门级
- maven 的使用
- lombok插件
- druid数据连接池
- 打包工具

流程：

​	跟之前写ssm的商城管理系统的流程不太一样。之前我们是从功能上直接思考每个组件的内容怎么来完成，现在是根据一个结构化的方式来完成这些项目的开发。如果说之前是自顶向下，那么这次就是自底向上。

1. 新建数据库
2. 配置yml文件，完成dao层的书写和测试  包含增删改查和分页在内的所有查询
3. service层的编码测试（没什么要干的，基本上就是复制粘贴）
4. 前端页面的编写
5. controller层的编写
6. 前后端数据的联调



## 实现

### 1、配置文件

```yml
#配置相关信息
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/ssmbuild
      username: root
      password: root
  mvc:
    view:
      prefix: spring.mvc.view.suffix
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: false
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      id-type: auto
```



首先是pom.xml的配置，maven依赖的管理，如果是一个大的项目，那就更应该第一步就思考好哪些技术的可以多次复用的，但是就这项目，属实是没什么内容，毕竟教程加起来还没一小时。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!--创建springboot项目的时候自动生成部分依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.6</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>ssmpbuild</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ssmpbuild</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <!--创建web项目自动生成的spring-boot-web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--导入mybatis-plus的依赖，注意版本不要超过3.5 版本，后续的版本有很大的差别-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.2</version>
        </dependency>
        <!--光有数据处理工具还不够，还需要一个连接数据库的工具，这里使用druid-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>
		<!--勾选的mysql连接使用的connector-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--lombok插件-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--springboot自动配置的测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

基本的配置就这些，有了这些jar包就能进行开发了！

自底向上开发

### 2、数据库的编写

数据库：

![image-20220410205849939](https://s2.loli.net/2022/04/10/zs32XBPG67gmkRn.png)

表的编写：

```sql
CREATE DATABASE ssmbuild;
USE ssmbuild;
CREATE TABLE `books`(
`bookID` INT NOT NULL AUTO_INCREMENT COMMENT '书id',
`bookName` VARCHAR(100) NOT NULL COMMENT '书名',
`bookCounts` INT NOT NULL COMMENT '数量',
`detail` VARCHAR(200) NOT NULL COMMENT '描述',
KEY `bookID`(`bookID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `books`(`bookID`,`bookName`,`bookCounts`,`detail`)VALUES
(1,'Java',1,'从入门到放弃'),
(2,'MySQL',10,'从删库到跑路'),
(3,'Linux',5,'从进门到进牢')
```

这里有点大坑，黑马的使用的表的字段里除了id没有int类型的值，都是用的char类型的，之后的前端传回数据会有许多差别，int类型的值是不能够进行模糊查询的，而且主键自增与mybatis-plus的一些设置还是有一些出入的。 

到这里需要确定我们的需求了，图书管理系统，首先肯定得要有一些书的信息（查询书的信息，包含书名，一些简单的描述等等，有几本？），存入一些书，删除一些书，修改书的信息等等。 这些还不够，还得注意些细节上的问题，一次不能全查了，所以还需要有分页功能，如果可以的话，还能增加一些排序功能，按条件查找书籍等等。

于是乎，接下来就是编写能达成这些效果的sql语句，直白点说，Dao层要干的工作就是这些。

### Dao层

使用Mybatis-plus 非常强大的框架，看代码就知道有多强大了

```java
package com.fanser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanser.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
```

这就是Dao层！ 没错，就一个继承，没了！增删改查都不用写了，包括分页功能的编写。

这里注意到，继承的BaseMapper接口需要传入一个泛型，这个泛型的内容也很好理解，毕竟我们什么都没写，那肯定这些数据来源就是表的对象了！

因此，还需要创建一个pojo类，内部放入我们的实体。

### pojo

```java
package com.fanser.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("books")
public class Book {
    @TableId("bookID")
    private Integer bookID;
    private String bookName;
    private Integer bookCounts;
    private String detail;
}
```

也很简单，基本的字段，加上lombok，还有mybatis-plus的独有的注解，注意一下这些注解是干嘛用的。

- TableName 显然指的是表名，为什么需要加上这个，显然是定位表，不然谁知道你连了这个数据库里的哪个表？默认只能让编译器自己猜，猜就是跟你的类名一致。
- TabId  同样也很好猜，他这个太符合中国人的思维了，毕竟是国人写的

这些写完了，那就这样了？ 你安心？ 你不觉得你什么都没写有点慌张？ 反正我觉得是要先试试能不能跑起来，不然改都改不了。

于是乎我们现在的任务就是------> 对这些自带的方法进行测试，至少得能访问到数据库的一些数据，要不然就是白扯。下一层才敢好好调用这层的内容。

```java
package com.fanser.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanser.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void testGetById() {
        System.out.println(bookMapper.selectById(1));
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setBookCounts(10);
        book.setBookName("springBoot");
        book.setDetail("从学习到失业");
        bookMapper.insert(book);
        System.out.println(bookMapper.selectById(6));
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setBookID(6);
        book.setBookCounts(10);
        book.setBookName("springBoot");
        book.setDetail("从学习到失业,如何被程序替代的过程");
        bookMapper.updateById(book);
        System.out.println(bookMapper.selectById(6));
    }

    @Test
    void testDeleteById() {
        int i = bookMapper.deleteById(7);
        System.out.println(i);
    }

    @Test
    void testGetAll() {
        List<Book> books = bookMapper.selectList(null);
        System.out.println(books);
    }
    @Test
    void testGetBy() {
//        QueryWrapper<Book> qw = new QueryWrapper<>();
//        qw.like("bookName","Spring");
        LambdaQueryWrapper lqw = new LambdaQueryWrapper();
        lqw.like("bookName", "spring");
//        lqw.like(Book::getBookName,"spring");//默认标红，函数式接口失效？
        bookMapper.selectList(lqw);
    }
    @Test
    void testGetPage() {
        Page page = new Page(1, 5);
        Page page1 = bookMapper.selectPage(page, null);
        System.out.println(page1.getCountId());
        System.out.println(page1.getCurrent());
        System.out.println(page1.getPages());
        System.out.println(page1.getRecords());
        System.out.println(page1.getSize());
    }
}
```

挨个测试一下这些方法，每个都需要能用的通才行。

验证没问题了再使用，然后进行下一阶段的内容的书写。

这里已经验证过了，那么就写下一层的内容，

### Service层

**普通写法：**

```java
package com.fanser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fanser.pojo.Book;

import java.util.List;

public interface BookService {
    public boolean save(Book book);
    public boolean deleteById(int id);
    public boolean update(Book book);
    public Book selectById(int id);
    public IPage<Book> getPage(int current, int size);
    public List<Book> selectAll();
    public boolean selectBy();

}
```

```java
package com.fanser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanser.dao.BookMapper;
import com.fanser.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;
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
        return bookMapper.selectById(id);
    }

    @Override
    public IPage<Book> getPage(int current, int size) {
        IPage page = new Page<>(current,size);
        bookMapper.selectPage(page,null);
        return page;
    }

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public boolean selectBy() {
        return false;
    }
    //这部分的内容没有写全
}
```

然后发现，其实还是挺麻烦的，我们写dao层的时候啥方法都没写，这里居然还得自己写。有没有跟dao层一样的写法，就一行代码解决的那种呢？

按道理来说肯定有,为啥，因为dao都有，service层跟dao基本上毫无差距，也就书写实现类的时候不太一样。所以，我觉得应该是有的。不过也确实有

**使用框架写法**：

```java
package com.fanser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fanser.dao.BookMapper;
import com.fanser.pojo.Book;

public interface BookService2 extends IService<Book> {
    public IPage<Book> getPagePro(int current, int size);

    IPage<Book> getPagePro(int current, int size, Book book);
}
```

```java
package com.fanser.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanser.dao.BookMapper;
import com.fanser.pojo.Book;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl2 extends ServiceImpl<BookMapper, Book> implements BookService2 {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public IPage<Book> getPagePro(int current, int size) {
        IPage page = new Page(current,size);
        bookMapper.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Book> getPagePro(int current, int size, Book book) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(Strings.isNotEmpty(book.getBookName()),Book::getBookName,book.getBookName());
        lqw.like(!book.getBookCounts().equals(null),Book::getBookCounts,book.getBookCounts());
        lqw.like(Strings.isNotEmpty(book.getDetail()),Book::getDetail,book.getDetail());
        IPage page = new Page(current,size);
        bookMapper.selectPage(page,lqw);
        return page;
    }
}
```

也好简单，实现类都好简单，要不是它自带的不能完全满足需求，甚至都只需要一行代码就够了。（java程序员直呼失业）

这里也是一样的，写完还得试试，确保初步使用没有问题，有bug才好改。

MVC 三层架构，M层算是写完了，那么这个项目的其实已经完成了一半了就，接下来就是根据前端的穿回来的数据然后对这些数据处理，然后再返回给前端的这些工作了，所以还是根据前端页面来写Controller层的内容会好写一些。 所以先导入静态的前端资源。

### 前端页面书写

```html
<!DOCTYPE html>

<html>

<head>

    <!-- 页面meta -->

    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>基于SpringBoot整合SSM案例</title>

    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

    <!-- 引入样式 -->

    <link rel="stylesheet" href="../plugins/elementui/index.css">

    <link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">

    <link rel="stylesheet" href="../css/style.css">

</head>

<body class="hold-transition">

<div id="app">

    <div class="content-header">

        <h1>图书管理</h1>

    </div>

    <div class="app-container">

        <div class="box">

            <div class="filter-container">
                <el-input placeholder="图书名称" v-model="pagination.bookName" style="width: 200px;" class="filter-item"></el-input>
                <el-input placeholder="图书数量" v-model="pagination.bookCounts" style="width: 200px;" class="filter-item"></el-input>
                <el-input placeholder="图书描述" v-model="pagination.detail" style="width: 200px;" class="filter-item"></el-input>
                <el-button @click="getAll()" class="dalfBut">查询</el-button>
                <el-button type="primary" class="butT" @click="handleCreate()">新建</el-button>
            </div>

            <el-table size="small" current-row-key="id" :data="dataList" stripe highlight-current-row>

                <el-table-column type="index" align="center" label="序号"></el-table-column>

                <el-table-column prop="bookName" label="图书名称" align="center"></el-table-column>

                <el-table-column prop="bookCounts" label="图书数量" align="center"></el-table-column>

                <el-table-column prop="detail" label="描述" align="center"></el-table-column>

                <el-table-column label="操作" align="center">

                    <template slot-scope="scope">

                        <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>

                        <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>

                    </template>

                </el-table-column>

            </el-table>

            <!--分页组件-->
            <div class="pagination-container">

                <el-pagination
                        class="pagiantion"

                        @current-change="handleCurrentChange"

                        :current-page="pagination.currentPage"

                        :page-size="pagination.pageSize"

                        layout="total, prev, pager, next, jumper"

                        :total="pagination.total">

                </el-pagination>

            </div>

            <!-- 新增标签弹层 -->

            <div class="add-form">

                <el-dialog title="新增图书" :visible.sync="dialogFormVisible">

                    <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right" label-width="100px">

                        <el-row>


                            <el-col :span="12">

                                <el-form-item label="图书名称" prop="bookName">

                                    <el-input v-model="formData.bookName"/>

                                </el-form-item>

                            </el-col>

                            <el-col :span="12">

                                <el-form-item label="图书数量" prop="bookCounts">

                                    <el-input v-model="formData.bookCounts"/>

                                </el-form-item>

                            </el-col>

                        </el-row>


                        <el-row>

                            <el-col :span="24">

                                <el-form-item label="描述">

                                    <el-input v-model="formData.detail" type="textarea"></el-input>

                                </el-form-item>

                            </el-col>

                        </el-row>

                    </el-form>

                    <div slot="footer" class="dialog-footer">

                        <el-button @click="cancel()">取消</el-button>

                        <el-button type="primary" @click="handleAdd()">确定</el-button>

                    </div>

                </el-dialog>

            </div>

            <!-- 编辑标签弹层 -->

            <div class="add-form">

                <el-dialog title="编辑检查项" :visible.sync="dialogFormVisible4Edit">

                    <el-form ref="dataEditForm" :model="formData" :rules="rules" label-position="right" label-width="100px">

                        <el-row>

                            <el-col :span="12">

                                <el-form-item label="图书数量" prop="bookCounts">

                                    <el-input v-model="formData.bookCounts"/>

                                </el-form-item>

                            </el-col>

                            <el-col :span="12">

                                <el-form-item label="图书名称" prop="bookName">

                                    <el-input v-model="formData.bookName"/>

                                </el-form-item>

                            </el-col>

                        </el-row>

                        <el-row>

                            <el-col :span="24">

                                <el-form-item label="描述">

                                    <el-input v-model="formData.detail" type="textarea"></el-input>

                                </el-form-item>

                            </el-col>

                        </el-row>

                    </el-form>

                    <div slot="footer" class="dialog-footer">

                        <el-button @click="cancel()">取消</el-button>

                        <el-button type="primary" @click="handleEdit()">确定</el-button>

                    </div>

                </el-dialog>

            </div>

        </div>

    </div>

</div>

</body>

<!-- 引入组件库 -->

<script src="../js/vue.js"></script>

<script src="../plugins/elementui/index.js"></script>

<script type="text/javascript" src="../js/jquery.min.js"></script>

<script src="../js/axios-0.18.0.js"></script>

<script>
    var vue = new Vue({
        el: '#app',
        data:{
            dataList: [],//当前页要展示的列表数据
            dialogFormVisible: false,//添加表单是否可见
            dialogFormVisible4Edit:false,//编辑表单是否可见
            formData: {
                bookName:"",
                bookCounts:0,
                detail:""
            },//表单数据
            rules: {//校验规则
                bookName: [{ required: true, message: '图书名称为必填项', trigger: 'blur' }],
                bookCounts: [{ required: true, message: '图书数量为必填项', trigger: 'blur' }]
            },
            pagination: {//分页相关模型数据
                currentPage: 1,//当前页码
                pageSize:10,//每页显示的记录数
                total:0,//总记录数
                bookCounts: "",
                bookName: "",
                detail: ""
            }
        },

        //钩子函数，VUE对象初始化完成后自动执行
        created() {
            //调用查询全部数据的操作
            this.getAll();
        },

        methods: {
            //列表
            // getAll() {
            //     //发送异步请求
            //     axios.get("/book").then((res)=>{
            //         // console.log(res.data);
            //         this.dataList = res.data.data;
            //     });
            // },

            // 分页查询
            getAll() {
                //组织参数，拼接url请求地址
                console.log(this.pagination.bookName);
                param ="?bookName="+this.pagination.bookName;
                param +="&detail="+this.pagination.detail;
                param +="&bookCounts="+parseInt(this.pagination.bookCounts,10);
                // console.log(param);

                //发送异步请求
                axios.get("/book/"+this.pagination.currentPage+"/"+this.pagination.pageSize+param).then((res)=>{
                    console.log(res.data.data)
                    this.pagination.currentPage = res.data.data.current;
                    this.pagination.pageSize = res.data.data.size;
                    this.pagination.total = res.data.data.total;

                    this.dataList = res.data.data.records;
                });
            },

            //切换页码
            handleCurrentChange(currentPage) {
                //修改页码值为当前选中的页码值
                this.pagination.currentPage = currentPage;
                //执行查询
                this.getAll();
            },

            //弹出添加窗口
            handleCreate() {
                this.dialogFormVisible = true;
                this.resetForm();
            },

            //重置表单
            resetForm() {
                this.formData = {};
            },

            //添加
            handleAdd() {
                axios.post("/book", {bookName:this.formData.bookName,bookCounts:parseInt(this.formData.bookCounts),detail:this.formData.detail }).then((res)=>{
                    //判断当前操作是否成功
                    console.log(this.formData)
                    if(res.data.flag){
                        //1.关闭弹层
                        this.dialogFormVisible = false;
                        this.$message.success(res.data.msg);
                    }else{
                        this.$message.error(res.data.msg);
                    }
                }).finally(()=>{
                    //2.重新加载数据
                    this.getAll();
                });
            },

            //取消
            cancel(){
                this.dialogFormVisible = false;
                this.dialogFormVisible4Edit = false;
                this.$message.info("当前操作取消");
            },

            // 删除
            handleDelete(row) {
                console.log(row);
                this.$confirm("此操作永久删除当前信息，是否继续？","提示",{type:"info"}).then(()=>{
                    axios.delete("/book/"+row.bookID).then((res)=>{
                        if(res.data.flag){
                            this.$message.success("删除成功");
                        }else{
                            this.$message.error("数据同步失败，自动刷新");
                        }
                    }).finally(()=>{
                        //2.重新加载数据
                        this.getAll();
                    });
                }).catch(()=>{
                    this.$message.info("取消操作");
                });
            },

            //弹出编辑窗口
            handleUpdate(row) {
                axios.get("/book/"+row.id).then((res)=>{
                    if(res.data.flag && res.data.data != null ){
                        this.dialogFormVisible4Edit = true;
                        this.formData = res.data.data;
                    }else{
                        this.$message.error("数据同步失败，自动刷新");
                    }
                }).finally(()=>{
                    //2.重新加载数据
                    this.getAll();
                });
            },

            //修改
            handleEdit() {
                axios.put("/book",this.formData).then((res)=>{
                    //判断当前操作是否成功
                    if(res.data.flag){
                        //1.关闭弹层
                        this.dialogFormVisible4Edit = false;
                        this.$message.success("修改成功");
                    }else{
                        this.$message.error("修改失败");
                    }
                }).finally(()=>{
                    //2.重新加载数据
                    this.getAll();
                });
            },

            //条件查询
        }
    })

</script>

</html>
```

经典单页面应用，hhh 反正我觉得写这个一点都不快乐，特别是这页面不是一个人写的情况下。一些属性真就靠找，不会前端的后端不是一个好后端，最好还是得学学前端。

这里使用的是一个响应式的页面，使用了axios来传递这些异步请求，完成一系列的操作，反正把数据显示出来，还有把前端接收的用户数据传到后台就好了。  不得不说，很麻烦！花的时间比写前面全部代码都多

### Controller层 

```java
package com.fanser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fanser.config.util.R;
import com.fanser.pojo.Book;
import com.fanser.service.BookServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl2 bookService;
    @GetMapping
    public R getAll(){
        return new R(true,bookService.list());
    }
    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));
    }
    @PutMapping
    public R update(@RequestBody Book book){
        return new R(bookService.updateById(book));
    }
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.removeById(id));
    }
    @GetMapping("{id}")
    public R getBookById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }
    //分页
//    @GetMapping("{current}/{size}")
//    public R getBookPage(@PathVariable int current, @PathVariable int size){
//        IPage<Book> page = bookService.getPagePro(current, size);
//        //如果当前的页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前的页码值
//        if (current>page.getPages()){
//            bookService.getPagePro((int) page.getPages(),size);
//        }
//        return new R(true,page);
//    }
    //分页+条件查询
    @GetMapping("{current}/{size}")
    public R getBookPage(@PathVariable int current, @PathVariable int size,Book book){
        IPage<Book> page = bookService.getPagePro(current, size,book);
        //如果当前的页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前的页码值
        if (current>page.getPages()){
            bookService.getPagePro((int) page.getPages(),size);
        }
        return new R(true,page);
    }
}
```

到这里其实已经初步完成了，剩余的就是一些优化的内容了，比如说参数的问题，传给前端的一大堆数据应该是什么样的，要使用json数据格式嘛？ 前端传回来的应该是怎么样的数据等等。

使用一些工具类来改善我们的开发效率和复杂度。

MPconfig：

```java
package com.fanser.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 创建拦截器
* */
@Configuration
public class MpConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor =  new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
```

异常处理

```java
package com.fanser.config.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public R doException(Exception ex){
        //记录日志
        //通知运维
        //通知开发
        ex.printStackTrace();
        return new R("服务器故障，请稍后再试！");
    }
}
```

数据类型统一

```java
package com.fanser.config.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class R {
    private Boolean flag;
    //为了兼容所有输入的数据类型，这里使用Object类对象来存储数据
    private Object data;
    private String msg;


    //判断是错误导致的页面信息不在还是网页信息找不到
    public R(Boolean flag){
        this.flag = flag;
    }
    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }

    public R(String msg) {
        this.msg = msg;
    }
}
```

没了，就这点内容，相比之前使用ssm框架开发，属实是舒服了太多，配置配置配置，太恶心了属于是。springboot  真香！