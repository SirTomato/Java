package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo_2 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //对request对象的请求消息增强
        System.out.println("filterDemo_2执行啦");
        //放行
        chain.doFilter(req, resp);
        //对response对象的响应消息增强
        System.out.println("filterDemo_2回来了");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }
}
/*
filterDemo_2执行啦
index.jsp......
filterDemo_2回来了*/
