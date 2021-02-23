package Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class JedisPoolUtils {
    /**
     * JedisPool工具类
     * 加载配置文件，配置连接池的参数
     * 提供获取连接的方法
     */
    private static JedisPool jedisPool;

    static{

        /**
         * getResourceAsStream("Jedis.properties")
         */
/*        //读取配置文件
        InputStream is = JedisPool.class.getClassLoader().getResourceAsStream("Jedis.properties");
        //创建Properties对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

/**
 * getResource("Jedis.properties")获取URL
 */

        Properties pro = new Properties();
        ClassLoader classLoader = JedisPoolUtils.class.getClassLoader();
        URL res = classLoader.getResource("Jedis.properties");
        System.out.println(res);//file:/D:/JedisDemo/target/classes/jedis.properties
        String path = res.getPath();
        System.out.println(path);//  /D:/JedisDemo/target/classes/jedis.properties
        //加载文件
        try {
            pro.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));

    }

    /**
     * 获取连接方法
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
