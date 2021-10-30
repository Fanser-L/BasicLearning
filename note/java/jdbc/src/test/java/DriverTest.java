import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class DriverTest {
    /*
    1. 根据 MYSQL 厂商提供的驱动，获取与 MYSQL 数据库的连接
        将指定厂商提供的驱动复制到lib路径下，点击jar包，然后使用add as library
        利用反射机制获取指定数据库的驱动实例
        通过connect()方法获取指定数据库的连接
    缺点：需要将用户名和密码两个字符串封装到Properties中
    */
    // 避免了一下这几种情况下的冗余和很差的可移植性，连同代码编辑量也会上升
    //        new Driver();

    //        new OracleDriver();

    //        new SQLServerDriver();

    @Test
    public void test1() throws Exception {
        Driver driver = null;
        String url = "jdbc:mysql://192.168.56.1:3306/myemployees";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
//        String user = "root";
//        String password = "root";
        //1.加载驱动
        String driverClassName = "com.mysql.jdbc.Driver";
        Class clazz = Class.forName(driverClassName);//默认会抛出一个 ClassNotFoundException，所以没抛异常会标红
        driver = (Driver) clazz.newInstance();//函数是抛出空构造函数的异常
        //官方推荐写法，可以将构造函数的异常封装了，避免了再次检查异常
        //can be replaced by clazz.getDeclaredConstructor().newInstance()

        //2.连接数据库

        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }


    @Test
        /*
        2.利用DriverManager（驱动管理类）来获取数据库连接

            缺点：手动注册驱动
        */
    public void test02() throws Exception {

//        1.注册驱动

        String driverClassName = "com.mysql.jdbc.Driver";
        Class clazz = Class.forName(driverClassName);
        Driver driver = (Driver) clazz.newInstance();
        DriverManager.registerDriver(driver);
        //2.获取连接
        String url = "jdbc:mysql://192.168.56.1:3306/myemployees";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    @Test
    /*
      3.利用DriverManager（驱动管理类）来获取数据库连接
      注意： JDBC规定，数据库厂商必须为DriverManger注册驱动
      */
    public void test03() throws Exception {
//      利用反射来加载这个driver实例，这样静态代码块内的注册驱动肯定会被执行
//        1.加载驱动
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.56.1:3306/myemployees";
        String user = "root";
        String password = "root";
//        2.获取连接
        Class clazz = Class.forName(driverClassName);
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }
    /*
    4.将四个连接数据库必须的字符串，提取到Properties属性文件中，方便后续的维护与管理

    报错：java.lang.NullPointerException: inStream parameter is null  解决
    **/

    @Test
    public void test04() throws Exception {

        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
        String driverClassName = props.getProperty("driverClassName");
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

//        1.加载驱动
        Class clazz = Class.forName(driverClassName);
        // 2.获取连接
        Connection conn = DriverManager.getConnection(url,user,password);

        System.out.println(conn);
    }
}
