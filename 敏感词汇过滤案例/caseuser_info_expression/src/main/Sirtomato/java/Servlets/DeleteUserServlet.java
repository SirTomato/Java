package Servlets;

import Domain.PageBean;
import Domain.User;
import Service.UserService;
import Service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;


@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码，否则当使用post请求时，getParameterMap方法获取中文数据会发生乱码，注意该方法两种请求方式都适用
        request.setCharacterEncoding("utf-8");
        //获取参数
        int id = Integer.parseInt(request.getParameter("id"));
        //调用service
        UserService userService = new UserServiceImpl();
        userService.deductById(id);
        //跳转到UserListServlet，这里没有使用到request的共享数据，所以使用重定向
        String name = request.getParameter("name");
        System.out.println(name);
        String place_of_birth = request.getParameter("place_of_birth");
        String email = request.getParameter("email");
        //转发
        request.getRequestDispatcher("/FindUserByPageServlet?currentPage="+"&rows=5&name="+name+"&place_of_birth="+place_of_birth+"&email="+email).forward(request, response);





        //重定向的URL中带有中文，因此后台接收到的是乱码（猜测，此处编码方式是根据Delete），因此建议使用转发
        //若执意使用get请求方式，可以先将参数以utf-8编码，到目标类中解码，浏览器的编码方式对英文（编码后的参数）不会有影响
//        System.out.println("/FindUserByPageServlet?currentPage="+"&rows=5&name="+name+"&place_of_birth="+place_of_birth+"&email="+email);
        //转码
//        String _name =URLEncoder.encode(name,"utf-8");
//        String test = URLDecoder.decode(_name,"utf-8");
//        System.out.println(test);
//        String _place_of_birth =URLEncoder.encode(place_of_birth);
//        String _email =URLEncoder.encode(email);
//
//        System.out.println("/FindUserByPageServlet?currentPage="+"&rows=5&name="+_name+"&place_of_birth="+_place_of_birth+"&email="+_email);
//        response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet?currentPage="+"&rows=5&name="+_name+"&place_of_birth="+_place_of_birth+"&email="+_email);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
