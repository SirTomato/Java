package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类 使用Druid数据库连接池
 */
public class JDBCUtils {

    private static DataSource ds;

    //在静态代码块中获取ds对象
    static {
        try {
            //加载配置文件
            Properties properties = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties");
//            获取数据库连接池对象  工厂类获取对象
            properties.load(is);
//            properties.setProperty("driverClassName","com.mysql.jdbc.Driver");
//            properties.setProperty("url","jdbc:mysql://localhost:3306/test");
//            properties.setProperty("username","root");
//            properties.setProperty("password","524yalways");

            ds = DruidDataSourceFactory.createDataSource(properties);
            //获取连接：getConnection()
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取连接池对象
     */
    public static  DataSource getDs(){
        return  ds;
    }

    /**
     * 获取Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }

    /**
     * 释放资源(两个参数)  通过方法重载实现功能的全面性
     */
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    /**
     * 释放资源(三个参数)  通过方法重载实现功能的全面性
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {

        if (rs != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
