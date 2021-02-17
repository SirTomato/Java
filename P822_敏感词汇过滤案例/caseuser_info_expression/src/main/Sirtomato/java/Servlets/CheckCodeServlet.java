package Servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        int width = 80;
        int height= 30;
        //创建一个对象，在内存中绘制验证码图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //美化图片
        //获取画笔对象
        Graphics g = image.getGraphics();//画笔对象
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0,width,height);
        //画边框
        g.setColor(Color.GRAY);
        g.drawRect(0,0,width-1,height-1);
        //产生4位随机验证码
        String checkCode=getCheckCode();
        //将验证码放入HttpSession中,便于LoginServlet类拿到该值与页面提交的数据进行比较
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的大小
        g.setFont(new Font("黑体",Font.BOLD,24));
        //在图片上写验证码
        g.drawString(checkCode,15,25);
        //画干扰线,设置画笔颜色
        for(int j=0; j<10;j++) {
            Random random = new Random();
            g.setColor(Color.white);
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
            }
        //将图片输出到页面展示
        //参数一，图片对象
        //参数二，图片的格式，如PNG,JPG,GIF
        //参数三，图片输出哪里去
        ImageIO.write(image,"jpg",response.getOutputStream());
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 产生4位随机字符串
     * @return
     */
    private String getCheckCode(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            s.append(ch);
        }
        return s.toString();
    }

}
