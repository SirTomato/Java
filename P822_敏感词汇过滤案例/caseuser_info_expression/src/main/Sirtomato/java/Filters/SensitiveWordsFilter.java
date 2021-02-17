package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    public void doFilter(final ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("SensitiveWordsFilter执行啦");
     //创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if (method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value =(String)method.invoke(req, args);
                    if (value != null){
                        for (String s : list) {
                            if (value.contains(s)){
                                value = value.replaceAll(s, "***");
                            }
                        }
                    }
                    return value;
                }

                //后续还要
                //判断方法名是否是getParameterMap
                //判断方法名是否是getParameterValue

                return method.invoke(req,args);
            }
        });
        //放行，注意要传递proxy_req
        chain.doFilter(proxy_req, resp);
    }
    private List<String> list = new ArrayList<String>();//敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init...");
        try{
            //获取文件真实路径
            ServletContext servletContext = config.getServletContext();
//            URL url = servletContext.getClassLoader().getResource("敏感词汇.txt");
//            System.out.println(url);//file:/D:/caseuser_info_expression/target/case-user_info_expression/WEB-INF/classes/敏感词汇.txt
            String path = servletContext.getClassLoader().getResource("敏感词汇.txt").getPath();
//            String realPath = servletContext.getRealPath("敏感词汇.txt");
            System.out.println(path);// D:/caseuser_info_expression/target/case-user_info_expression/WEB-INF/classes/敏感词汇.txt 系统能够找到该文件
//            System.out.println(realPath);//D:\caseuser_info_expression\target\case-user_info_expression\敏感词汇.txt 系统找不到文件
            //读取文件,字符流默认创建的是GBK格式的，需要确保被读取的文件也是GBK，否则会出现乱码现象
            BufferedReader br = new BufferedReader(new FileReader(path));
//            BufferedReader br = new BufferedReader(new FileReader(realPath));

            //将文件的每一行数据添加到list中
            String line =null;
            while ((line = br.readLine())!=null){
                System.out.println(line);
                list.add(line);
            }
            System.out.println("list");
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void destroy() {
    }
}
