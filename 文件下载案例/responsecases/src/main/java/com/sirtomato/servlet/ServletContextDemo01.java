package main.java.com.sirtomato.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletContextDemo01")
public class ServletContextDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        ServletContext servletContext1 = this.getServletContext();
        System.out.println(servletContext);//org.apache.catalina.core.ApplicationContextFacade@a64c85e
        System.out.println(servletContext1);//org.apache.catalina.core.ApplicationContextFacade@a64c85e

        String filename = "a.jpg";
        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);//image/jpeg

        String msg = (String)servletContext.getAttribute("msg");
        System.out.println(msg);

        System.out.println(servletContext.getRealPath("/a.txt"));//D:\responsecases\out\artifacts\responsecases_war_exploded\a.txt
        System.out.println(servletContext.getRealPath("/web/a.txt"));//D:\responsecases\out\artifacts\responsecases_war_exploded\web\a.txt
        System.out.println(servletContext.getRealPath("/web/WEB-INF/a.txt"));//D:\responsecases\out\artifacts\responsecases_war_exploded\web\WEB-INF\a.txt

        System.out.println(servletContext.getResource("a.txt"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
