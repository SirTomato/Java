package dao;


import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

/**
 * 操作数据库user表的类
 */
public class UserDao {

    //1.获取template对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

    /**
     * 登录方法  代码编写完成后 最好去测试下
     * @param loginUser:传递给我的user对象 只有用户名和密码属性有值
     * @return 返回一个user对象 如果数据库中有对应的用户名和密码则user不为null  如果没有  user为null
     * 输入/**自动形成该注释格式
     */
    public User login(User loginUser){

        User user = null;
        try {
            String sql = "select * from user where username = ? and password = ?";
            //2.调用对应的方法
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
        } catch (DataAccessException e) {
//            e.printStackTrace();
            return  null;
            //queryForObject方法如果查询不到对应的值会出异常  为了让它查询不到返回null 我们进行手动try catch
        }
        return  user;
    }

}
