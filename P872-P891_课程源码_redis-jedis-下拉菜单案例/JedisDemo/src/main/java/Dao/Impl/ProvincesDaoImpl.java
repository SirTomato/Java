package Dao.Impl;


import Dao.ProvincesDao;
import Domain.Province;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

/**
 * 操作check_provinces数据库provinces表的类
 */
public class ProvincesDaoImpl implements ProvincesDao {
    //1.获取template对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

    /**
     * 查询所有省份
     */
    public List<Province> findAll(){
        List<Province> provinces = null;
        try {
            String sql = "select * from provinces";
            //2.调用对应的方法
            provinces= template.query(sql, new BeanPropertyRowMapper<>(Province.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return  null;
        }
        return  provinces;
    }

}

