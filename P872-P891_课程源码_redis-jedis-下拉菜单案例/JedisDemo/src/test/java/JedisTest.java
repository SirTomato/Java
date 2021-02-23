import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import Utils.JedisPoolUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis测试类
 */

public class JedisTest {
    @Test
    //注意@Test注解只能写在方法上
    public void test1(){
        //获取连接
        //6379是redis服务器端上显示的端口
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        jedis.set("username","SORO");
        //关闭连接
        jedis.close();
    }
    /**
     * String数据结构操作
     */

    @Test
    public void test2(){
        //获取连接
        //6379是redis服务器端上显示的端口
        Jedis jedis = new Jedis();//如果使用空参构造函数，则默认"localhost", 6379端口
        //操作
        jedis.set("username","SANJI");
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法存储指定过期时间的key value
        jedis.setex("activeCode",20,"ALLBLUE");//20秒后自动删除该键值对
        //关闭连接
        jedis.close();
    }


    /**
     * Hash数据结构操作
     */

    @Test
    public void test3(){
        //获取连接
        //6379是redis服务器端上显示的端口
        Jedis jedis = new Jedis();//如果使用空参构造函数，则默认"localhost", 6379端口
        //存储hash
        jedis.hset("weapon","LUFY","PUNCH");
        jedis.hset("weapon","SORO","SWORD");
        jedis.hset("weapon","SANJI","FOOTS");
        //获取hash
        String LUFY = jedis.hget("weapon", "LUFY");
        System.out.println(LUFY);
        //获取hash中的所有数据
        Map<String, String> weapon = jedis.hgetAll("weapon");
        Set<String> keySet = weapon.keySet();
        for (String s : keySet) {
            String w = weapon.get(s);
            System.out.println(s+":"+w);
        }
        //关闭连接
        jedis.close();
    }

/*
        PUNCH
        LUFY:PUNCH
        SORO:SWORD
        SANJI:FOOT
*/
    /**
     * List数据结构操作
     */

    @Test
    public void test4(){
        //获取连接
        //6379是redis服务器端上显示的端口
        Jedis jedis = new Jedis();//如果使用空参构造函数，则默认"localhost", 6379端口
        //存储list
        jedis.lpush("myList","a","b","c");
        jedis.rpush("myList","a","b","c");
        //获取list
        List<String> myList = jedis.lrange("myList", 0, -1);
        System.out.println(myList);//[c, b, a, a, b, c]
        //list 弹出
        String ele1 = jedis.lpop("myList");
        System.out.println(ele1);//c
        String ele2 = jedis.rpop("myList");
        System.out.println(ele2);//c
        //list 范围获取
        List<String> myList1 = jedis.lrange("myList", 0, -1);
        System.out.println(myList1);//[b, a, a, b]
        //关闭连接
        jedis.close();
    }

    /**
     * Set数据结构操作
     */

    @Test
    public void test5(){
        //获取连接
        //6379是redis服务器端上显示的端口
        Jedis jedis = new Jedis();//如果使用空参构造函数，则默认"localhost", 6379端口
        //存储set
        jedis.sadd("mySet","java","php","c++");
        //set 获取
        Set<String> mySet = jedis.smembers("mySet");
        System.out.println(mySet);
        //关闭连接
        jedis.close();
    }

    /**
     * SortedSet数据结构操作
     */

    @Test
    public void test6(){
        //获取连接
        //6379是redis服务器端上显示的端口
        Jedis jedis = new Jedis();//如果使用空参构造函数，则默认"localhost", 6379端口
        //存储sortedset
        //score参数是double类型
        jedis.zadd("mySortedSet",1.2,"甚平");
        jedis.zadd("mySortedSet",2.3,"娜美");
        jedis.zadd("mySortedSet",3.4,"罗宾");
        jedis.zadd("mySortedSet",0.1,"乔巴");
        //SortedSet 获取
        Set<String> mySortedSet = jedis.zrange("mySortedSet", 0, -1);
        System.out.println(mySortedSet);//[乔巴, 甚平, 娜美, 罗宾]
        //关闭连接
        jedis.close();
    }

    /**
     * Jedis连接池
     */

    @Test
    public void test7(){
        //创建配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);

        //创建Jedis连接池对象
//        JedisPool jedisPool = new JedisPool();
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
        //获取连接
        Jedis jedis = jedisPool.getResource();
        jedis.set("HELLO","JedisPool!");
        String hello = jedis.get("HELLO");
        System.out.println(hello);
        //将资源归还到连接池中
        jedis.close();
    }

    /**
     * Jedis连接池工具类的使用
     */

    @Test
    public void test8(){
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //使用
        String s = jedis.set("HELLO", "JedisPoolUtils");
        String s1 = jedis.get("HELLO");
        System.out.println(s1);
        //关闭，归还到连接池
        jedis.close();
    }

}
