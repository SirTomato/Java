package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo_7 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo_7来啦");
        chain.doFilter(req, resp);
        System.out.println("FilterDemo_7走啦");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }
}
