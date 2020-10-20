package cn.huiounet.utils.checkCode;

import cn.huiounet.utils.redis.RedisUtil;

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

@WebServlet(name = "CheckCodeServlet", urlPatterns = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //创建一个随机类
    private Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String phone = request.getParameter("phone");
        /*
         * 目标:动态输出一张内存图片
         * */

        //1.创建一张内存图片
        //new BufferedImage(width,height,图片模式)
        //    width 图片的宽度
        //    height 图片的高度
        //    图片模式 rgb
        BufferedImage image = new BufferedImage(150,50,BufferedImage.TYPE_INT_RGB);

        //2.获取图片绘制对象
        Graphics g = image.getGraphics();

        //3.绘制矩形区域
        //g.fillRect(x,y,width,height);
        //  x,y 设置绘制起始点
        //  width 绘制的宽度
        //  height 绘制的高度
        g.fillRect(0,0,150,50);

        //4.设置绘制颜色
        g.setColor(Color.BLACK);

        //5.绘制边框
        g.drawRect(1,1,147,47);

        //6.画3条干扰线
        //g.drawLine(x1,y1,x2,y2);  通过设置2个点画一条线
        for (int i = 0; i < 3; i++) {

            //设置绘制随机颜色
            g.setColor(getRandomColor());

            int x1 = random.nextInt(147);
            int y1 = random.nextInt(47);
            int x2 = random.nextInt(147);
            int y2 = random.nextInt(47);
            g.drawLine(x1,y1,x2,y2);
        }

        //7.绘制验证码
        //定义一个随机生成验证码的范围
        String scope = "asdfghjkqwertyupzxcvbnmSADFGHJKQWERTYUPZXCVBNM";
        //定义一个拼接的字符串,用于接收4个验证码
        StringBuilder stringBuilder = new StringBuilder();
        //循环4次,每次循环随机获取一个验证码,
        g.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            //生成随机位置
            int index = random.nextInt(scope.length());
            //根据位置获取字符
            char c = scope.charAt(index);
            //拼接到验证码字符串中
            stringBuilder.append(c);

            //设置字体
            //new Font(字体类型,粗体/斜体/正常,字体字号大小);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));

            //将字符绘制到图片上
            //g.drawString(字符串,x,y); 将指定字符串绘制到指定的点位置
            g.drawString(c+"",25+i*30,30);
        }

        //stringBuilder验证码字符串写入session中
//        request.getSession().setAttribute("SERVER_CODE",stringBuilder.toString());

        RedisUtil.redisSetString(phone+"SERVER_CODE",stringBuilder.toString(),false,0);


        //输出内存图片到浏览器上
        //ImageIO.write(内存图片对象,图片格式,输出给浏览器的输出流)
        ImageIO.write(image,"png",response.getOutputStream());
    }

    //获取随机颜色对象
    private Color getRandomColor(){
        //new Color(r,g,b);
        int r = random.nextInt(256);//颜色值的范围是0~255
        int g = random.nextInt(256);//颜色值的范围是0~255
        int b = random.nextInt(256);//颜色值的范围是0~255
        return new Color(r,g,b);
    }
}

