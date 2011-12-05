<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<img src="/getimg">
<%=session.getAttribute("yehyVerifyCode")%>

<pre>
package org.yehongyu.websale.common.secure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.yehongyu.websale.AppConstants;

/**
 * 【类说明】随机生成图片校验码类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午10:57:23
 */
public class GetImg extends HttpServlet {
	
	//验证码中可以出现的字体集，可根据需要修改
	private String[] fontName = { "Atlantic Inline",
			"Arial","Arial Black","Arial Bold","Arial Bold Italic",
			"Arial Italic","Arial Narrow","Arial Narrow Bold",
			"Arial Narrow Bold Italic","Arial Narrow Italic",
//			"Basemic","Basemic Symbol","Basemic Times","Batang",
//			"BatangChe","Book Antiqua","Book Antiqua Bold",
//			"Book Antiqua Bold Italic","Book Antiqua Italic",
//			"Bookman Old Style","Bookman Old Style Bold",
//			"Bookman Old Style Bold Italic","Bookman Old Style Italic",
//			"Bookshelf Symbol 7","Century Gothic","Century Gothic Bold",
//			"Century Gothic Bold Italic","Century Gothic Italic",
//			"Comic Sans MS","Comic Sans MS Bold","Courier New",
//			"Courier New Bold","Courier New Bold Italic",
//			"Courier New Italic","Dialog.bold","Dialog.bolditalic",
//			"Dialog.italic","Dialog.plain","DialogInput.bold",
//			"DialogInput.bolditalic","DialogInput.italic",
//			"DialogInput.plain","Dotum","DotumChe","Estrangelo Edessa",
//			"方正舒体","方正姚体","仿宋_GB2312","Franklin Gothic Medium",
//			"Franklin Gothic Medium Italic","Garamond","Garamond Bold",
//			"Garamond Italic","Gautami","Georgia","Georgia Bold",
//			"Georgia Bold Italic","Georgia Italic","Gulim","GulimChe",
//			"Gungsuh","GungsuhChe","Haettenschweiler","Impact",
//			"楷体_GB2312","Kartika","Kingsoft Phonetic","Latha",
//			"隶书","Lucida Bright Demibold","Lucida Bright Demibold Italic",
//			"Lucida Bright Italic","Lucida Bright Regular","Lucida Console",
//			"Lucida Sans Demibold","Lucida Sans Demibold Italic",
//			"Lucida Sans Demibold Roman","Lucida Sans Italic",
//			"Lucida Sans Regular","Lucida Sans Typewriter Bold",
//			"Lucida Sans Typewriter Regular","Lucida Sans Unicode",
//			"MS Gothic","MS Mincho","MS PGothic","MS PMincho",
//			"MS Reference Sans Serif","MS Reference Specialty",
//			"MS UI Gothic","MV Boli","Mangal","Marlett",
//			"Microsoft Sans Serif","MingLiU","Monospaced.bold",
//			"Monospaced.bolditalic","Monospaced.italic","Monospaced.plain",
//			"Monotype Corsiva","新宋体","新宋体-18030","PMingLiU",
//			"Palatino Linotype","Palatino Linotype Bold",
//			"Palatino Linotype Bold Italic","Palatino Linotype Italic",
//			"Raavi","华文彩云","华文仿宋","华文细黑","华文行楷","华文新魏","华文中宋",
//			"SansSerif.bold","SansSerif.bolditalic","SansSerif.italic",
//			"SansSerif.plain","Serif.bold","Serif.bolditalic","Serif.italic",
//			"Serif.plain","Shruti","黑体","宋体","宋体-18030","宋体-方正超大字符集",
//			"Sylfaen","Symbol","Tahoma","Tahoma Bold","Times New Roman",
//			"Times New Roman Bold","Times New Roman Bold Italic",
//			"Times New Roman Italic","Trebuchet MS","Trebuchet MS Bold",
//			"Trebuchet MS Bold Italic","Trebuchet MS Italic","Tunga","Verdana",
//			"Verdana Bold","Verdana Bold Italic","Verdana Italic","Vrinda",
//			"Webdings","Wingdings","Wingdings 2","Wingdings 3","幼圆","sshlinedraw",
			"Times New Roman"};
	//验证码中可以出现的字体
//	Font[] font = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	// 验证码图片中可以出现的字符集，可根据需要修改
	private char mapTable[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 
			'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };
	//验证码的可以出现的位数，可根据需要修改，与picnum配合修改
	private int codenum[] = { 2,3,4 };
	//图片显示字符数，可根据需要修改，与codenum配合修改
	private int picnum = 4;
	//图片中每个字符的宽度
	private int codewidth = 15;
	//随机位置，根据位数来确定
	private int[] codepos;
	
	/**
	 * 【函数功能】随机获取随机位数的验证码，并设定码的显示位置
	 * @return　验证码
	 */
	private String getCode(){
		String code = "";
		// 4代表4位验证码,如果要生成更多位的认证码,则加大数值
		int num = codenum[(int)(codenum.length * Math.random())];
		for (int i = 0; i < num; ++i) {
			code += mapTable[(int) (mapTable.length * Math.random())];
		}
		//可使用的位置
		List<Integer> pos = new ArrayList<Integer>();
		int p;
		for(int i = 0; i < num; i++){
			do{
				p = (int)(picnum*Math.random());
			}while(pos.contains(p));
			pos.add(p);
		}
		int[] ordpos = new int[num];
		Object[] objs = pos.toArray();
		for(int i=0;i < num;i++){
			ordpos[i] = (Integer)objs[i];
		}
		//排序位置
		sortInt(ordpos);
		//计算位置
		codepos = new int[num];
		for(int i=0;i < ordpos.length;i++){
			codepos[i] = ordpos[i]*codewidth;
		}
		return code;
	}
	
	/**
	 * 【函数功能】将传入的数组从小到大排序
	 * @param ordpos
	 */
	private void sortInt(int[] ordpos){
		for(int j=0;j < ordpos.length;j++){
			for(int k=0;k < ordpos.length-1;k++){
				if(ordpos[k+1] < ordpos[k]){
					int temp = ordpos[k+1];
					ordpos[k+1]=ordpos[k];
					ordpos[k] = temp;
				}
			}
		}
	}
	
	/**
	 * 【函数功能】给定范围获取随机数字
	 * @param min
	 * @param max
	 * @return
	 */
	private int getRandNum(int min,int max){
		return min + new Random().nextInt(max-min);
	}
	
	/**
	 * 【函数功能】获取随机字体
	 * @return
	 */
	private Font getRandFont(){
		String fontn = "Times New Roman";
		fontn = fontName[(int) (fontName.length * Math.random())];
		return new Font(fontn, getRandNum(0,3), getRandNum(15,20));
//		return font[(int)(font.length*Math.random())];
	}

	/**
	 * 【函数功能】给定范围获得随机颜色
	 * @param fc
	 * @param bc
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 功能:生成彩色验证码图片 参数width为生成图片的宽度,参数height为生成图片的高度,参数os为页面的输出流
	 */
	public String getCertPic(OutputStream os) {
		// 取随机产生的认证码
		String strEnsure = getCode();
		char[] codes = strEnsure.toCharArray();
		//验证码图片的宽度和高度
		int width = codewidth*picnum;
		int height = 20;
		
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(getRandColor(160, 200));
		g.fillRect(0, 0, width, height);
		// 画边框
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生10个干扰点
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			g.setColor(getRandColor(160, 200));
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(12);
			int yl = rand.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 随机产生3条(少量深色)干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(10, 50));
		for (int i = 0; i < 3; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(60);
			int yl = rand.nextInt(20);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 将认证码显示到图像中,如果要生成更多位的认证码,增加drawString语句
		for(int c = 0;c < codes.length;c++){
			g.setColor(getRandColor(10, 50));
			g.setFont(getRandFont());
			String str = String.valueOf(codes[c]);
			g.drawString(str, codepos[c]+2, getRandNum(13,18));
		}
		// 释放图形上下文
		g.dispose();
		try {
			// 输出图像到页面
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			return "";
		}
		return strEnsure;
	}

	/**
	 * 处理Post请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 处理Get请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(AppConstants.VERIFYCODE);
		// 设置页面不缓存
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		//将验证码写入图片
		String verifycode = getCertPic(response.getOutputStream());
		// 将认证码存入SESSION
		session.setAttribute(AppConstants.VERIFYCODE, verifycode);
	}
	
	/**
	 * 【函数功能】获取所有支持字体
	 * @return 字体名称
	 */
	public String getFont(){
		String s = "";
		Font[] f = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for(Font font:f){
			s += "\""+font.getFontName()+"\",";
		}
		return s;
	}
}
</pre>