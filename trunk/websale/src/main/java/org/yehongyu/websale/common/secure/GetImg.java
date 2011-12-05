/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
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
 * ����˵�����������ͼƬУ������
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����10:57:23
 */
public class GetImg extends HttpServlet {
	
	//��֤���п��Գ��ֵ����弯���ɸ�����Ҫ�޸�
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
//			"��������","����Ҧ��","����_GB2312","Franklin Gothic Medium",
//			"Franklin Gothic Medium Italic","Garamond","Garamond Bold",
//			"Garamond Italic","Gautami","Georgia","Georgia Bold",
//			"Georgia Bold Italic","Georgia Italic","Gulim","GulimChe",
//			"Gungsuh","GungsuhChe","Haettenschweiler","Impact",
//			"����_GB2312","Kartika","Kingsoft Phonetic","Latha",
//			"����","Lucida Bright Demibold","Lucida Bright Demibold Italic",
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
//			"Monotype Corsiva","������","������-18030","PMingLiU",
//			"Palatino Linotype","Palatino Linotype Bold",
//			"Palatino Linotype Bold Italic","Palatino Linotype Italic",
//			"Raavi","���Ĳ���","���ķ���","����ϸ��","�����п�","������κ","��������",
//			"SansSerif.bold","SansSerif.bolditalic","SansSerif.italic",
//			"SansSerif.plain","Serif.bold","Serif.bolditalic","Serif.italic",
//			"Serif.plain","Shruti","����","����","����-18030","����-���������ַ���",
//			"Sylfaen","Symbol","Tahoma","Tahoma Bold","Times New Roman",
//			"Times New Roman Bold","Times New Roman Bold Italic",
//			"Times New Roman Italic","Trebuchet MS","Trebuchet MS Bold",
//			"Trebuchet MS Bold Italic","Trebuchet MS Italic","Tunga","Verdana",
//			"Verdana Bold","Verdana Bold Italic","Verdana Italic","Vrinda",
//			"Webdings","Wingdings","Wingdings 2","Wingdings 3","��Բ","sshlinedraw",
			"Times New Roman"};
	//��֤���п��Գ��ֵ�����
//	Font[] font = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	// ��֤��ͼƬ�п��Գ��ֵ��ַ������ɸ�����Ҫ�޸�
	private char mapTable[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 
			'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };
	//��֤��Ŀ��Գ��ֵ�λ�����ɸ�����Ҫ�޸ģ���picnum����޸�
	private int codenum[] = { 2,3,4 };
	//ͼƬ��ʾ�ַ������ɸ�����Ҫ�޸ģ���codenum����޸�
	private int picnum = 4;
	//ͼƬ��ÿ���ַ��Ŀ��
	private int codewidth = 15;
	//���λ�ã�����λ����ȷ��
	private int[] codepos;
	
	/**
	 * ���������ܡ������ȡ���λ������֤�룬���趨�����ʾλ��
	 * @return����֤��
	 */
	private String getCode(){
		String code = "";
		// 4����4λ��֤��,���Ҫ���ɸ���λ����֤��,��Ӵ���ֵ
		int num = codenum[(int)(codenum.length * Math.random())];
		for (int i = 0; i < num; ++i) {
			code += mapTable[(int) (mapTable.length * Math.random())];
		}
		//��ʹ�õ�λ��
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
		for(int i=0;i<num;i++){
			ordpos[i] = (Integer)objs[i];
		}
		//����λ��
		sortInt(ordpos);
		//����λ��
		codepos = new int[num];
		for(int i=0;i<ordpos.length;i++){
			codepos[i] = ordpos[i]*codewidth;
		}
		return code;
	}
	
	/**
	 * ���������ܡ�������������С��������
	 * @param ordpos
	 */
	private void sortInt(int[] ordpos){
		for(int j=0;j<ordpos.length;j++){
			for(int k=0;k<ordpos.length-1;k++){
				if(ordpos[k+1]<ordpos[k]){
					int temp = ordpos[k+1];
					ordpos[k+1]=ordpos[k];
					ordpos[k] = temp;
				}
			}
		}
	}
	
	/**
	 * ���������ܡ�������Χ��ȡ�������
	 * @param min
	 * @param max
	 * @return
	 */
	private int getRandNum(int min,int max){
		return min + new Random().nextInt(max-min);
	}
	
	/**
	 * ���������ܡ���ȡ�������
	 * @return
	 */
	private Font getRandFont(){
		String fontn = "Times New Roman";
		fontn = fontName[(int) (fontName.length * Math.random())];
		return new Font(fontn, getRandNum(0,3), getRandNum(15,20));
//		return font[(int)(font.length*Math.random())];
	}

	/**
	 * ���������ܡ�������Χ��������ɫ
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
	 * ����:���ɲ�ɫ��֤��ͼƬ ����widthΪ����ͼƬ�Ŀ��,����heightΪ����ͼƬ�ĸ߶�,����osΪҳ��������
	 */
	public String getCertPic(OutputStream os) {
		// ȡ�����������֤��
		String strEnsure = getCode();
		char[] codes = strEnsure.toCharArray();
		//��֤��ͼƬ�Ŀ�Ⱥ͸߶�
		int width = codewidth*picnum;
		int height = 20;
		
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// ��ȡͼ��������
		Graphics g = image.getGraphics();
		// �趨����ɫ
		g.setColor(getRandColor(160, 200));
		g.fillRect(0, 0, width, height);
		// ���߿�
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		// �������10�����ŵ�
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			g.setColor(getRandColor(160, 200));
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(12);
			int yl = rand.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// �������3��(������ɫ)�����ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(10, 50));
		for (int i = 0; i < 3; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(60);
			int yl = rand.nextInt(20);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// ����֤����ʾ��ͼ����,���Ҫ���ɸ���λ����֤��,����drawString���
		for(int c = 0;c<codes.length;c++){
			g.setColor(getRandColor(10, 50));
			g.setFont(getRandFont());
			String str = String.valueOf(codes[c]);
			g.drawString(str, codepos[c]+2, getRandNum(13,18));
		}
		// �ͷ�ͼ��������
		g.dispose();
		try {
			// ���ͼ��ҳ��
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			return "";
		}
		return strEnsure;
	}

	/**
	 * ����Post����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * ����Get����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(AppConstants.VERIFYCODE);
		// ����ҳ�治����
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		//����֤��д��ͼƬ
		String verifycode = getCertPic(response.getOutputStream());
		// ����֤�����SESSION
		session.setAttribute(AppConstants.VERIFYCODE, verifycode);
	}
	
	/**
	 * ���������ܡ���ȡ����֧������
	 * @return ��������
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