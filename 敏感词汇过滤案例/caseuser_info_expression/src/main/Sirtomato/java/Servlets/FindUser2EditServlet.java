package Servlets;

import Domain.User;
import Service.UserService;
import Service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/FindUser2EditServlet")
public class FindUser2EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码，否则当使用post请求时，getParameterMap方法获取中文数据会发生乱码，注意该方法两种请求方式都适用
        request.setCharacterEncoding("utf-8");
        //获取参数
        int id = Integer.parseInt(request.getParameter("id"));
        //调用service，根据id查询用户信息
        UserService userService = new UserServiceImpl();
        User user = userService.find2editById(id);
        //将User对象存到request
        request.setAttribute("user",user);
        //转发到editUser页面,绝对路径，无需加虚拟目录
        request.getRequestDispatcher("/editUser.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
