package main.java.com.sirtomato.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ResponseDemo04")
public class ResponseDemo04 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取流对象之前，设置流的默认编码，ISO-9959-1设置为GBK
        response.setCharacterEncoding("utf-8");
        //告诉浏览器，服务器发送的消息体（响应体）数据的编码，建议浏览器使用该编码,该行代码可以兼顾设置流和浏览器的编码，因此上面一行代码可以省略
        response.setHeader("content-type","text/html;charset=utf-8");
        //简化形式，该行代码可以兼顾设置流和浏览器的编码，因此上面两行代码可以省略
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write("HELLO RESPONSE");
        pw.write("<h1>HELLO RESPONSE</h1>");
        pw.write("海贼王");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
