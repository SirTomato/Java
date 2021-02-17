package main.java.com.sirtomato.servlet;

import main.java.com.sirtomato.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/ServletDownload")
public class ServletDownload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取获取请求参数，文件名称
        String filename = request.getParameter("filename");
        //2、使用字节输入流加载文件进内存
        //2.1 找到文件在服务器中的绝对路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/resource/" + filename);
        //2.2 用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        //3 设置response响应头
        //3.1 设置响应头类型，content-type
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        //解决中文文件名问题
        //获取user-agent请求头
        String agent = request.getHeader("user-agent");
        //使用工具类方法编码文件夹名即可
        filename = DownLoadUtils.getFileName(agent, filename);
        //3.2 设置响应头打开方式，content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);
        //4 将输入的数据写出到response的输出流中
        response.setContentType("text/html;charset=utf-8");
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = fis.read(buff))!=-1){
            sos.write(buff,0,len);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
