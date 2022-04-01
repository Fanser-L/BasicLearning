import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/*
* 查找数据库中的一行数据
* 1. 需要获取连接
* 2. 发送sql语句
* 3. 执行sql语句，获取rs结果集
* 4. 查询结果集,行可以用next()方法来查询，然后使用一个ResultSetMetaData 来获取元数据
*       列有多少列可以使用元数据的查询结果使用一个getColumnCount来查询   列索引？
*       然后列有了，行也有了，现在就差查询对应列的名字了，使用getColumnLabel来获取不知道的列名
*       现在有的内容有 ， 知道查的第几行，哪一列，所以现在完全可以查询对应行列的值，使用getObject来获取不知道类型的值
*       值也有了，所以需要把这些值封装进我们开始的时候声明的类中去，但是又因为类的种类不是固定的，所以需要使用反射来给不确定的类赋值
*       使用getField(列名 //实际上就是我们需要的属性或者说数据)，最后使用set来将数据装入类中。
*
* */
public class PreparedStatementUtils2 {
    @Test
    void test05() {
        String sql = "select * from student where id = ? or id = ?";
//        Student student = query(sql, Student.class, 3);
        List<Student> studentList = null;
        MyQueryRunner myQueryRunner = new MyQueryRunner();
        studentList = myQueryRunner.queryList(sql,Student.class,3);
        for (Student student: studentList) {
            System.out.println(student);
        }
        System.out.println();
    }

    @Test
    void test04() {
        String sql = "select * from student where id = ?";
        Student student = query(sql, Student.class, 1);
        System.out.println(student);
    }

    public <T> T query(String sql ,Class<T> clazz, Object ... args){

        Connection conn = null;
        T t = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1. 获取连接
            conn = JDBCUtils.getConnection();
            //2. 获取PreparedStatement,发送sql语句
            ps = conn.prepareStatement(sql);
            //3. 给占位符赋值,注意mysql中索引是从1开始的，所以i+1
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //4. 执行sql语句，获取ResultSet结果集  获取到了有几行，指针是在行数据的前一行，所以需要next()
            rs = ps.executeQuery();
            //5. 获取结果集的元数据 ResultSetMetaData()  为了下一步获取有几列
            ResultSetMetaData rsmd = rs.getMetaData();
            //6. 获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //7. 已经有列数了，所以对每一列进行获取数据
            if (rs.next()){  //看似是个if条件选择，实际上是个循环语句
                //需要将这些散列的数据封装进对象里头，更方便管理，这里是只封装进单个对象,
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //8. 获取结果集列名，列别名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //9. 获取结果集中对应列值，由于不知道列值是什么类型的  所以这里使用Object代替

                    Object columnValue = rs.getObject(columnName);

                    //10. 由于使用的类未知，所以需要利用反射来进行赋值
                    Field field = clazz.getDeclaredField(columnName);// 注意：结果集的列名（别名）必须和属性名保持一致
                    field.setAccessible(true);//忽略访问权限
                    field.set(t,columnValue);  //这里每次只装一个数据，但是在两层循环下能将全部数据都装进去
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5. 关闭连接
            JDBCUtils.close(conn,ps,rs);
        }
        return t;
    }


}
