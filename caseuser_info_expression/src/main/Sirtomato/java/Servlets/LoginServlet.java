package Servlets;

import Domain.Administrator;
import Service.impl.AdministratorServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取流对象之前，设置流的默认编码，此处没有作用,下面第一行功能涵盖第二行
        //response.setContentType("text/html;charset=utf-8");
        //response.setCharacterEncoding("utf-8");
        //该命令是为了获取post请求方式的中文参数时，不发生乱码现象
        request.setCharacterEncoding("utf-8");
        //获取数据
        // 获取用户填写的验证码
        String checkCode = request.getParameter("checkCode");
        System.out.println(checkCode);
            //封装Administrator对象，获取除了验证码之外的用户信息
        Map<String, String[]> map = request.getParameterMap();
            //创建对象容器存储用户信息
        Administrator LoginAdministrator = new Administrator();
        try {
            BeanUtils.populate(LoginAdministrator, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(LoginAdministrator);

        //从session取出验证码，从request域对象中取数据和从session域对象中取数据不一样
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //确保验证码一次性使用,不应该删除attribute，否则在成功登录后，后退使用上一次的验证码，会发生500错误,空指针异常的错误，因为后退到该页面，未
        session.setAttribute("CHECKCODE_SERVER"," ");
        //session.removeAttribute("CHECKCODE_SERVER");

        if (checkcode_server.equals(" ")||!checkcode_server.equalsIgnoreCase(checkCode)){
            //验证码不正确
            System.out.println("验证码不正确");
            //存储提示信息到session中
            request.setAttribute("login_msg", "验证码错误");
            //输出提示
            response.getWriter().write("验证码错误");
            //转发
            request.getRequestDispatcher("/login.jsp?time="+new Date().getTime()).forward(request,response);
            //验证码不正确,立即结束该servlet
            return;
        }
        //验证码正确
        System.out.println("验证码正确");

        //调用AdministratorService查询
        AdministratorServiceImpl administratorService = new AdministratorServiceImpl();
        Administrator administrator = administratorService.login(LoginAdministrator);
        System.out.println(administrator);
        if (administrator != null) {
            //将用户对象存入session
            session.setAttribute("user", LoginAdministrator);
            //重定向,绝对路径且须加虚拟目录
            //request.getContextPath()+"/success.html"动态获取虚拟目录并拼接路径
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            //存储提示信息到request中
            request.setAttribute("login_msg", "用户名或密码错误");
            //转发，绝对路径，无需加虚拟目录
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
