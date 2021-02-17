package Servlets;

import Service.UserService;
import Service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelSeletedUserServlet")
public class DelSeletedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取存有id的数组
        String[] users = request.getParameterValues("uid");
        //调用service删除
        UserService service = new UserServiceImpl();
        service.deductByIds(users);
        //跳转到UserListServlet，这里没有使用到request的共享数据，所以使用重定向
        String name = request.getParameter("name");
        String place_of_birth = request.getParameter("place_of_birth");
        String email = request.getParameter("email");
        //（重点，每次都会忘记这一步）跳转到UserListServlet，这里没有使用到request的共享数据，所以使用重定向
//        response.sendRedirect(request.getContextPath() + "/UserListServlet");不用这个类啦
//        response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet");
        // 为了完成查询之后，批量删除后，查询结果不消失，使用转发，无需二次编码
        request.getRequestDispatcher("/FindUserByPageServlet?currentPage="+"&rows=5&name="+name+"&place_of_birth="+place_of_birth+"&email="+email).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
