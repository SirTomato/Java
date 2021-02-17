package Dao.impl;

import Dao.UserDao;
import Domain.User;
import Utils.JDBCUtils;
import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.*;

/**
 * 操作数据库user表的类
 */
public class UserDaoImpl implements UserDao {
    //1.获取template对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());
    //查
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        //定义sql
        String sql = "select * from user_info";
        User[] objects = new User[]{};
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    //查byid
    public User findById(int id) {
        User user = null;
        try {
            String sql = "select * from user_info where id = ?";
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class), id);
        } catch (DataAccessException e) {
//            e.printStackTrace();
            System.out.println("输入的id不存在");
            return null;
        }
        return user;
    }


    //删
    @Override
    public void deleteById(int id) {

        if (findById(id) != null) {
            try {
                String sql = "delete from user_info where id = ?";
                template.update(sql, id);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
            System.out.println("操作成功！");
        }
    }

    //增
    @Override
    public void insert(User newUser) {
        try {
            //定义sql,不插入的填null
            String sql = "insert into user_info VALUES(NULL,?,?,?,?,?,?)";//双引号内没有分号
            //执行sql
            template.update(sql, newUser.getName(), newUser.getSex(), newUser.getAge(),
                    newUser.getPlace_of_birth(), newUser.getQq_number(), newUser.getEmail());
        } catch (DataAccessException e) {
//            e.printStackTrace();
            System.out.println("操作失败");
            return;
        }
        System.out.println("操作成功！");
    }

    //改
    @Override
    public void updateById(User user) {
        try {
            String sql = "UPDATE user_info set name=?,sex=?,age=?,place_of_birth=?,qq_number=?,email=? WHERE id=?";
            template.update(sql, user.getName(), user.getSex(), user.getAge(), user.getPlace_of_birth(), user.getQq_number(), user.getEmail(), user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("操作失败");
            return;
        }
        System.out.println("操作成功！");
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //使用JDBC操作数据库
        //定义模板sql，注意空格
        String sql = "select count(*) from user_info where 1=1 ";
        StringBuffer stringBuffer = new StringBuffer(sql);
        //创建list对象，用于存放value,为什么泛型为Object
        List<Object> params = new ArrayList<>();
        //遍历map
        Set<String> keySet = condition.keySet();
        for (String s : keySet) {
            //排除分页参数
            //排除id，删除操作带入参数id，批量删除带入参数uid，查询总数中并不需要该参数
            if ("currentPage".equals(s) || "rows".equals(s)||"id".equals(s)||"uid".equals(s)){
                continue;
            }
            //获取value，注意value的数据类型，Map<String, String[]> condition,
            String value = condition.get(s)[0];
            //判断value是否有值，注意判断条件，空字符串和null;
            if (value != null && !"".equals(value)){
                stringBuffer.append("and ").append(s).append(" like ? ");
                //SQL语句中？对应的值
                params.add("%"+value+"%");
            }
        }
        //用于测试
        System.out.println("findTotalCount-拼接后的sql语句："+stringBuffer.toString());
        System.out.println("findTotalCount-参数集合："+params);
        //queryForObject只能查询一条记录并封装为对象，多用于聚合函数的查询
        // 本方法中的第二个形参表示按哪种数据类型返回,第三个形参可以是数组类型，能够自动对应sql中的?
//        List<Integer> list = template.query(sql, new BeanPropertyRowMapper<Integer>(Integer.class));
        return template.queryForObject(stringBuffer.toString(), Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
//    public List<User> findByPage(int start, int rows) { 重构
        List<User> usersList = null;
        try {
            //使用JDBC操作数据库
            //定义模板sql，注意空格
            String sql = "select * from user_info where 1=1 ";
            StringBuffer stringBuffer = new StringBuffer(sql);
            //创建list对象，用于存放value,为什么泛型为Object
            List<Object> params = new ArrayList<>();
            //遍历map
            Set<String> keySet = condition.keySet();
            for (String s : keySet) {
                //排除分页参数
                //排除id，删除操作带入参数id，分页查询中并不需要该参数
                if ("currentPage".equals(s) || "rows".equals(s)||"id".equals(s)||"uid".equals(s)){
                    continue;
                }
                //获取value，注意value的数据类型，Map<String, String[]> condition,
                String value = condition.get(s)[0];
                //重定向方法中，utf-8解码
//                String _value = URLDecoder.decode(value,"utf-8");
                //判断value是否有值，注意判断条件，空字符串和null;
                if (value != null && !"".equals(value)){

                    stringBuffer.append("and ").append(s).append(" like ? ");
                    //SQL语句中？对应的值 %代表任意多个字符
                    //重定向方法中，添加解码后的value
                    //params.add("%"+_value+"%");
                    params.add("%"+value+"%");
                }
            }
            //添加分页查询
            stringBuffer.append("limit ?,?");
            //添加分页查询参数值
            params.add(start);
            params.add(rows);
            //用于测试
            System.out.println("findByPage-拼接后的sql语句："+stringBuffer.toString());
            System.out.println("findByPage-参数集合："+params);

            usersList = template.query(stringBuffer.toString(), new BeanPropertyRowMapper<User>(User.class),params.toArray());
//            usersList = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
        } catch (DataAccessException e /*| UnsupportedEncodingException e*/) {
//            e.printStackTrace();
            System.out.println("输入的数据有误");
            return null;
        }
        return usersList;
    }


}