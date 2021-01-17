package com.sirtomato.servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height= 50;
        StringBuilder s = new StringBuilder();
        //创建一个对象，在内存中绘制验证码图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //美化图片
        //2.1 填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.PINK);
        g.fillRect(0,0,width,height);
        //2.2 画边框
        g.setColor(Color.blue);
        g.drawRect(0,0,width-1,height-1);
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        for (int i = 1; i < 5; i++) {
            //2.3 写验证码
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            g.setColor(Color.black);
            g.drawString(ch+"",width/5*i,25);
            s.append(ch);
        }
        for(int j=0; j<10;j++) {
            //2.4 画干扰线
            g.setColor(Color.white);
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
        HttpSession session1 = request.getSession();
        session1.setAttribute("checkCode",s);
        //原理待明确
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
