package servlet;

import dao.UserDao;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码格式
        request.setCharacterEncoding("UTF-8");
/*        //2.获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.将请求的参数封装到user对象中
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/
        //获取所有请求参数
        Map<String, String[]> map = request.getParameterMap();
        //创建loginUser对象
        User loginUser = new User();
        //使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用 UserDao 中的 login方法
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        //5.判断是否登录成功
        String path = null;
        if (user == null){
            //登录失败 请求转发
            path = "/failServlet";
        }else{
            //登录成功 存储数据  转发
            request.setAttribute("user",user);
            path = "/successServlet";
        }
        //转到对应的servlet
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

