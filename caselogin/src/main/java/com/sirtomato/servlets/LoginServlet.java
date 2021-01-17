package com.sirtomato.servlets;

import com.sirtomato.Dao.UserDao;
import com.sirtomato.Domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(loginUser);
        HttpSession session = request.getSession();
        StringBuilder checkCode = (StringBuilder) session.getAttribute("checkCode");
        //避免成功登陆后，退回到登陆页面，可以凭借上一次的验证码正常登陆
        session.removeAttribute("checkCode");
        //equalIgnoreCase忽略大小写
        if (checkCode != null && checkCode.toString().equalsIgnoreCase(loginUser.getCheckCode())) {
            //验证码正确
            User user = new UserDao().login(loginUser);
            System.out.println(user);
            if (user != null) {
                //存储用户信息
                session.setAttribute("userName", user.getUserName());
                //重定向,绝对路径且须加虚拟目录
                //request.getContextPath()+"/success.html"动态获取虚拟目录并拼接路径
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            } else {
                //存储提示信息到session中
                request.setAttribute("msg1", "用户名或密码错误");
                //转发，绝对路径，无需加虚拟目录
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            //验证码不正确
            //存储提示信息到session中
            request.setAttribute("msg2", "验证码错误");
            //转发
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
