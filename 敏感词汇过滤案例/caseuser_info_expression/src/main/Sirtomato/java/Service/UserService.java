package Service;

import Domain.PageBean;
import Domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */

public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 保存用户信息
     */
    public void addUser(User newUser);
    /**
     * 根据id删除用户信息
     */
    void deductById(int id);
    /**
     * 根据id找到需要编辑的用户信息
     */
    User find2editById(int id);
    /**
     * 根据id编辑用户信息
     */
    void editById(User user);
    /**
     * 根据数组内的id，批量删除用户信息
     */
    void deductByIds(String[] users);

    /**
     * 根据分页条件查询用户列表
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
