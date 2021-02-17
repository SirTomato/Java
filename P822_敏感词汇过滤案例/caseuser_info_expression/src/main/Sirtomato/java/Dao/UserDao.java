package Dao;

import Domain.User;

import java.util.List;
import java.util.Map;


public interface UserDao {

    /**
     * 查询所有用户的数据
     */
    public List<User> findAll();

    /**
     * 保存新用户的数据
      * @param newUser
     */
    public void insert(User newUser);

    /**
     * 根据数据库中的id删除用户数据
     * @param id
     */
    public void deleteById(int id);
    /**
     * 根据数据库中的id查询用户数据
     * @param id
     */
    public User findById(int id);
    /**
     * 根据数据库中的id更新用户数据
     * @param user
     */
    void updateById(User user);
    /**
     * 查找数据库中的用户总数
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);
    /**
     * 分页条件查询list
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

}
