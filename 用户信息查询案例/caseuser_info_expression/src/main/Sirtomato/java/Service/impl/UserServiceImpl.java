package Service.impl;

import Dao.UserDao;
import Dao.impl.UserDaoImpl;
import Domain.PageBean;
import Domain.User;
import Service.UserService;


import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {
    //注意利用接口作为数据类型
//    UserDaoImpl dao = new UserDaoImpl(); 错误
    UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void addUser(User newUser) {
        dao.insert(newUser);
    }

    @Override
    public void deductById(int id) {
        dao.deleteById(id);
    }

    //建议在service中就将int id 转为 String id
    @Override
    public User find2editById(int id) {
        return dao.findById(id);
    }

    @Override
    public void editById(User user) {
        dao.updateById(user);
    }

    @Override
    public void deductByIds(String[] users) {
        for (int i = 0; i < users.length; i++) {
            String user = users[i];
            int j = Integer.parseInt(user);
            dao.deleteById(j);
        }
        System.out.println("批量删除成功！");
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        //转换参数的数据类型
        int currentPage = Integer.parseInt(_currentPage);
        //避免当前页数小于0或，大于totalPage
        if (currentPage<=0){
            currentPage=1;
        }
        int rows = Integer.parseInt(_rows);
        //创建空的PageBean对象
        PageBean<User> pageBean = new PageBean<>();

        //调用dao查询totalCount总记录数，重构方法
        int totalCount = dao.findTotalCount(condition);
        //int totalCount = dao.findTotalCount();
        //计算start
        int start = (currentPage-1)*rows;
        //调用dao查询list集合
        List<User> usersList = dao.findByPage(start, rows,condition);
        //计算总页码
        int totalPage = totalCount%rows == 0 ? totalCount/rows:totalCount/rows+1;
        if (currentPage>totalPage){
            currentPage--;
        }
        //设置当前页面属性和rows属性
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
//        System.out.println("findUserByPage-currentPage"+currentPage);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(usersList);
        pageBean.setTotalPage(totalPage);
        //返回PageBean对象
        return pageBean;
    }


}

