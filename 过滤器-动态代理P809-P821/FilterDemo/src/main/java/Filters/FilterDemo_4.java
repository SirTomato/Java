package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/index.jsp")//具体资源路径
//@WebFilter("/user/*")//拦截目录
//@WebFilter("*.jsp")//后缀名拦截
public class FilterDemo_4 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filterDemo_4被执行了");
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }
}
