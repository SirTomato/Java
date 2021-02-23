package Service.Impl;

import Dao.Impl.ProvincesDaoImpl;
import Domain.Province;
import Service.ProvincesService;
import Utils.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvincesServiceImpl implements ProvincesService {

    @Override
    public List<Province> findAll() {
        ProvincesDaoImpl dao = new ProvincesDaoImpl();
        return dao.findAll();
    }

    @Override
    public String findAllJson() {
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //在缓存中查找province
        String province_json = jedis.get("province");//将数据库的数据以String的数据格式存入缓存
        //判断province_json是否为空
        if(province_json == null || province_json.length() == 0){
            System.out.println("redis中没有数据，查询数据库");
            //从数据库中查询
            ProvincesDaoImpl dao = new ProvincesDaoImpl();
            List<Province> provinces = dao.findAll();
            //将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(provinces);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将字符串类型的json存入redis
            jedis.set("province",province_json);
            //归还连接
            jedis.close();
            return province_json;
        }else {
            System.out.println("redis中有数据，查询缓存");
            return province_json;
        }
    }


}
