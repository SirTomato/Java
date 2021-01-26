package Dao.impl;

import Dao.AdministratorDao;
import Domain.Administrator;
import Utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdministratorDaoImpl implements AdministratorDao {
    //获取template对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());
    @Override
    public Administrator findUserByUsernameAndPasword(Administrator LoginAdministrator) {

        Administrator administrator = null;
        try {
            String sql = "select * from administrator where username = ? and password = ?";
            //2.调用对应的方法
            administrator = (Administrator) template.queryForObject(sql,
                    new BeanPropertyRowMapper<Administrator>(Administrator.class),
                    LoginAdministrator.getUserName(), LoginAdministrator.getPassWord());
        } catch (DataAccessException e) {
            //e.printStackTrace();
            return null;
            //queryForObject方法如果查询不到对应的值会出异常，为了让它查询不到返回null，我们进行手动try catch
        }
        return administrator;
    }

}
