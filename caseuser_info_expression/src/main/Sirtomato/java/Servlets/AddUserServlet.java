package Servlets;

import Domain.User;
import Service.UserService;
import Service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码，否则当使用post请求时，getParameterMap方法获取中文数据会发生乱码，注意该方法两种请求方式都适用
        request.setCharacterEncoding("utf-8");
        //获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();
/*      用于定位BUG
        Set<String> keySet = parameterMap.keySet();
        for (String s : keySet) {
            System.out.println(s+"="+ Arrays.toString(parameterMap.get(s)));
        }*/
        //封装对象
        User addUser = new User();
        try {
            BeanUtils.populate(addUser, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(addUser.toString()); 用于定位BUG
        //调用service，注意使用接口作为数据类型
        UserService userService = new UserServiceImpl();
        userService.addUser(addUser);

        //跳转到UserListServlet，这里没有使用到request的共享数据，所以使用重定向
//        response.sendRedirect(request.getContextPath() + "/UserInfoList.jsp");
        response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
