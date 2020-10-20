package cn.huiounet.utils.qrcode;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.commons.lang.StringUtils;
import sun.font.FontDesignMetrics;

public class QRCodeUtilEx {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "png";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 40;
    // LOGO高度
    private static final int HEIGHT = 40;
    // 字体大小
    private static final int FONT_SIZE = 18;


    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static BufferedImage createImage(String content, String bottomDes, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int tempHeight = height;
        boolean needDescription = (null != bottomDes && !"".equals(bottomDes));
        if (needDescription) {
            tempHeight += 30;
        }
        BufferedImage image = new BufferedImage(width, tempHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        // 插入图片
        if (imgPath != null && !"".equals(imgPath)) {
            QRCodeUtilEx.insertImage(image, imgPath, needCompress);
        }
        //添加底部文字
        if (needDescription) {
            QRCodeUtilEx.addFontImage(image, bottomDes);
        }
        return image;
    }

    /**
     * 添加 底部图片文字
     *
     * @param source      图片源
     * @param declareText 文字本文
     */
    private static void addFontImage(BufferedImage source, String declareText) {
        BufferedImage textImage = strToImage(declareText, QRCODE_SIZE, 50);
        Graphics2D graph = source.createGraphics();
        //开启文字抗锯齿
        graph.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = textImage.getWidth(null);
        int height = textImage.getHeight(null);

        Image src = textImage;
        graph.drawImage(src, 0, QRCODE_SIZE - 20, width, height, null);
        graph.dispose();
    }

    @SuppressWarnings("restriction")
    private static BufferedImage strToImage(String str, int width, int height) {
        BufferedImage textImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)textImage.getGraphics();
        //开启文字抗锯齿
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.BLACK);
        FontRenderContext context = g2.getFontRenderContext();
        Font font = new Font("微软雅黑", Font.BOLD, FONT_SIZE);
        g2.setFont(font);
        LineMetrics lineMetrics = font.getLineMetrics(str, context);
        FontMetrics fontMetrics = FontDesignMetrics.getMetrics(font);
        float offset = (width - fontMetrics.stringWidth(str)) / 2;
        float y = (height + lineMetrics.getAscent() - lineMetrics.getDescent() - lineMetrics.getLeading()) / 2;

        g2.drawString(str, (int)offset, (int)y);

        return textImage;
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * @param content 二维码的内容
     * @param bottomDes	底部的文字
     * @param imgPath  	Logo 图片地址
     * @param destPath	保存二维码 地址 (没有该目录会自动创建)
     * @param needCompress	是否压缩Logo大小
     * @param fileName	文件名称
     * @param fileEnd	文件后缀
     * @throws Exception
     */
    public static void encode(String content, String bottomDes, String imgPath, String destPath, boolean needCompress, String fileName, String fileEnd) throws Exception {
        BufferedImage image = QRCodeUtilEx.createImage(content, bottomDes, imgPath, needCompress);

        mkdirs(destPath);

        String file = (StringUtils.isEmpty(fileName) ? UUID.randomUUID().toString() : fileName) +
                "." + (StringUtils.isEmpty(fileEnd) ? FORMAT_NAME : fileEnd);
        ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
    }
    public static void main(String[] args) throws Exception {
        String text = "https://www.baidu.com/";
        QRCodeUtilEx.encode(text, "这", "E:/test/DSC_2315.png", "D://", false, "aa", ".jpg");
    }
    /**
     * @param content 二维码的内容
     * @param bottomDes	底部的文字
     * @param imgPath  	Logo 图片地址
     * @param needCompress	是否压缩Logo大小
     * @return
     * @throws Exception
     */
    public static BufferedImage encode(String content, String bottomDes, String imgPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtilEx.createImage(content, bottomDes, imgPath, needCompress);
        return image;
    }

    /**
     * 创建文件夹
     * @param destPath
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * @param content 二维码的内容
     * @param bottomDes	底部的文字
     * @param imgPath  	Logo 图片地址
     * @param destPath	保存二维码 地址 (没有该目录会自动创建)
     * @throws Exception
     */
    public static void encode(String content, String bottomDes, String imgPath, String destPath) throws Exception {
        QRCodeUtilEx.encode(content, bottomDes, imgPath, destPath, false, null, null);
    }

    /**
     * @param content 二维码的内容
     * @param bottomDes	底部的文字
     * @param destPath	保存二维码 地址 (没有该目录会自动创建)
     * @throws Exception
     */
    public static void encode(String content, String bottomDes, String destPath) throws Exception {
        QRCodeUtilEx.encode(content, bottomDes, null, destPath, true, null, null);
    }


    /**
     * @param content 二维码的内容
     * @param bottomDes	底部的文字
     * @param destPath	保存二维码 地址 (没有该目录会自动创建)
     * @param fileName	文件名称
     * @param fileEnd	文件后缀
     * @throws Exception
     */
    public static void encode(String content, String bottomDes, String destPath, String fileName, String fileEnd) throws Exception {
        QRCodeUtilEx.encode(content, bottomDes, null, destPath, true, fileName, fileEnd);
    }





    public static void encode(String content, String bottomDes, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtilEx.createImage(content, bottomDes, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    public static void encode(String content, String bottomDes, OutputStream output) throws Exception {
        QRCodeUtilEx.encode(content, bottomDes, null, output, false);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtilEx.decode(new File(path));
    }

}


