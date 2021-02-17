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
import java.util.Arrays;
import java.util.Map;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此处用到post请求，需要设置字符集
        request.setCharacterEncoding("utf-8");
        //接受请求参数 currentPage，rows,注意Servlet不进行数据类型转换，应该交给service来做！
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //知识点，避免currentPage或rows为空字符串或null
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }
        //获取条件查询的参数,这一步对编码类型有要求，由于 request.setCharacterEncoding("utf-8")
        // 因此URL地址栏中的中文参数一定要以utf-8来编码，否则读取出的是乱码"??"
        Map<String, String[]> condition = request.getParameterMap();
        //用于测试
//        for (String s : condition.keySet()) {
//            System.out.println(s+"="+ Arrays.toString(condition.get(s)));
//        }
        //调用service查询
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findUserByPage(currentPage, rows, condition);
//        //调用service查询PageBean,为了适应条件查询，重构了下面的方法，后续代码无需改动
//        UserService userService = new UserServiceImpl();
//        PageBean<User> pageBean = userService.findUserByPage(currentPage, rows, condition);



        //用于测试
        System.out.println(pageBean);
        //将PageBean存入request
        request.setAttribute("pageBean", pageBean);
        //将查询条件存入request
        request.setAttribute("condition",condition);
        //转发到list.jsp
        request.getRequestDispatcher("/UserInfoList.jsp").forward(request, response);//注意此处和EL表达式中的区别
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
