import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PreparedStatementTest2 {



    @Test
    void test03() {

        List<Student> list = getList();
        Iterator<Student> iterator = list.listIterator();
        while (iterator.hasNext()){
            Student student = iterator.next();
            System.out.println(student);

        }


    }

    public List<Student> getList(){
        List<Student> list = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            list = new ArrayList<>();

            conn = JDBCUtils.getConnection();
            String sql = "select * from student where id >= ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1,0);

            rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");

                Student student = new Student(id,name,sex,age);
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }

        return list;
    }



    @Test
    void test02() {
        //获取封装后的单个对象
        Student student = get();
        if (student ==  null){
            System.out.println("找不到指定学生");
        }else {
            System.out.println(student);
        }
    }

    /*
* 使用PreparedStatement进行查询
*
* */
    //使用对象来封装查询到的数据
    public Student get() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student stu = null;
        try {
            stu = null;
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.获取PreparedStatement,发送sql
            String sql = "select * from student where id = ?";
            ps = conn.prepareStatement(sql);
            //3.给占用符赋值
            ps.setInt(1, 2);
            //4.执行sql
            rs = ps.executeQuery();
            //5.获取resultSet结果集
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                //6.将结果集封装进入对象中
                stu = new Student(id, name, sex, age);
            }
            //7.关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        return stu;
    }

    @Test
    void test01() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.获取PreparedStatement,发送sql
            String sql = "select * from student where id = ?";
            ps = conn.prepareStatement(sql);

            //3.给占位符赋值
            ps.setInt(1,2);

            //4.执行sql，得到ResultSet（结果集，其实就是查询得到的新表）
            rs = ps.executeQuery();
            //5.获取结果集中的数据
            while (rs.next()){
                int id = rs.getInt(1);
                String sex = rs.getString(2);
                String name = rs.getString(3);
                int age = rs.getInt(4);

                System.out.println(id +","+sex +","+ name +","+age+" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭所有连接
            JDBCUtils.close(conn,ps,rs);
        }

    }
}
