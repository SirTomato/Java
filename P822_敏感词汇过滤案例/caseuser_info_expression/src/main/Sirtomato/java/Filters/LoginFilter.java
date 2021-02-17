package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("拦截器执行啦");
        //强制转换
        HttpServletRequest request=(HttpServletRequest)req;
        //获取资源请求路径
        String uri = request.getRequestURI();
        //判断是否包含登录相关的资源路径，要注意排除掉css/js/图片/验证码等资源
        if(uri.contains("/login.jsp")||uri.contains("/LoginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/CheckCodeServlet")) {
            //包含，用户就是想登录，放行
            chain.doFilter(req, resp);
        }else {
            //不包含，需要验证用户是否登录
            //从session中获取user
            Object user = request.getSession().getAttribute("user");
            if (user != null){
                //登录，放行
                chain.doFilter(req, resp);
            }else{
                //没有登录，跳转到登录页面
                request.setAttribute("login_msg","您尚未登录，请登录");
                //此处参数可以使用ServletResponse resp
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }
}
