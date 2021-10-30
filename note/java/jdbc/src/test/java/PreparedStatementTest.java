import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class PreparedStatementTest {

/*
* PrepareStatement和Statement的区别
* Statement发送SQL语句用的是一种拼接字符串的方法来实现SQL语句的发送的
* SQL注入的问题：
*           这样的发送存在很多问题，拼接字符串的问题有  使用双引号和单引号的区别，单双引号合并使用会使在数据库中的查询出现问题
*           PreparedStatement是预编译的方式来执行sql语句，解决了Statement使用拼接字符串的问题（SQL注入）而且效率更高
*
*  */
/*
* 使用 PreparedStatement 来完成增删查改
* 预编译使用了 ？ 作为占位符，只要输入合适的值就能直接转换成相应的sql语句来执行了
* 比如说  insert into students(id,name,sex,age) values(?,?,?,?)  这样的sql语句
* PreparedStatement适用于增 删 改数据           查之后再说
*  */
/*3. 修改数据*/
    @Test
    void test03() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.获取PreparedStatement,发送sql语句
            String sql = "update student set id = 2 where id = ?";
            ps = conn.prepareStatement(sql);
            //3.给占位符赋值
            ps.setInt(1,1);
            //4.执行sql语句
            int row = ps.executeUpdate();
            System.out.println("已影响"+row+"行");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            JDBCUtils.close(conn,ps,null);
        }
    }

    /*2.删除数据*/
    @Test
    void test02() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.获取PreparedStatement,用于发送sql
            String sql = "delete from student where id = ?";
            ps = conn.prepareStatement(sql);
            //3.替换占位符
            ps.setInt(1,1);
            //4.执行sql
            int row = ps.executeUpdate();
            System.out.println("执行了"+row+"行");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //5.关闭连接
            JDBCUtils.close(conn,ps,null);
        }



    }

    /*1.添加数据的操作*/
    @Test
    void test01() throws Exception {
        Connection conn = null;
        PreparedStatement ps =null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();

            //2.通过当前连接，获取PreparedStatement，发送sql语句
            String sql = "insert into student(id,name,sex,age) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);

            //3.填充占位符，发送的语句的具体内容，优化了原有的直接拼接字符串的问题
            ps.setInt(1,01);
            ps.setString(2,"ljr");
            ps.setString(3,"f");
            ps.setInt(4,21);

            //4.执行sql语句
            int row = ps.executeUpdate();

            //显示执行效果
            System.out.println("执行了" + row+"行");
        } catch (Exception e) {
            e.printStackTrace();
        }
            //5.关闭连接
        finally {
            JDBCUtils.close(conn,ps,null);
        }


    }
}
