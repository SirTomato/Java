package Listeners;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
//@WebListener
public class ContextLoadListener implements ServletContextListener {
    /**
     * 监听ServletContext对象创建
     * ServletContext对象是服务器启动后自动创建。
     * 在服务器启动后自动调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象被创建了");
        //加载资源文件
        //获取ServletContext对象
        ServletContext servletContext = sce.getServletContext();
        //加载资源文件
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        //获取真实路径
        String realPath = servletContext.getRealPath(contextConfigLocation);
        String realPath2 = servletContext.getRealPath("/");
        System.out.println(realPath);//D:\ListenerDemo\target\ListenerDemo\WEB-INF\classes\applicationContext.xml 根目录+资源路径
        System.out.println(realPath2);//D:\ListenerDemo\target\ListenerDemo\   根目录
        servletContext.setAttribute("realPath",realPath);
        //加载进内存
        try{
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);//java.io.FileInputStream@2bf1ebc1
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象被销毁了");
    }
}
