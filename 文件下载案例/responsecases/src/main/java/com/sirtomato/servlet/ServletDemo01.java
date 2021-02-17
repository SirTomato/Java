package main.java.com.sirtomato.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletDemo01")
public class ServletDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDemo01执行啦！！！");
        //访问ServletDemo01，自动跳转到ServletDemo02
//        response.setStatus(302);
//        response.setHeader("location","/responsecases_war_exploded/ServletDemo02");
        //采用简单的重定向方法
//        response.sendRedirect("/responsecases_war_exploded/ServletDemo02");
        response.sendRedirect("https://www.baidu.com/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
