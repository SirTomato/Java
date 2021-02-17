package Servlets;

import Domain.User;
import Service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用UserService完成查询
        UserServiceImpl service = new UserServiceImpl();
        List<User> users = service.findAll();
        //将list存入request对象
        request.setAttribute("users",users);
        //转发到list.jsp
        request.getRequestDispatcher("/UserInfoList.jsp").forward(request,response);//注意此处和EL表达式中href的区别
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
