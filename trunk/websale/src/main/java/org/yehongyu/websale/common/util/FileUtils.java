/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 【类说明】文件操作类，操作文件的实用方法。
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:02:27
 */
public class FileUtils {
	
	private String path;

	/**
	 * 【函数功能】创建文件夹
	 * @param tem_path 相对路径
	 * @param dirname 文件名
	 * @return true or false
	 */
	public boolean ismakeDir(String tem_path, String dirname) {
		String path_new = "";
		path_new = path + File.separator + tem_path + File.separator + dirname;
		// 建立文件夹文件临时变量
		File dirName = new File(path_new);
		// 检查文件存在
		if (dirName.exists()) {
			return false;
		}
		dirName.mkdir();
		return true;
	}

	/**
	 * 【函数功能】删除文件夹
	 * @param tem_path 相对路径
	 * @param dirname 文件名
	 * @return true or false
	 */
	public boolean isdelDir(String tem_path, String dirname) {
		String path_new = "";
		path_new = path + File.separator + tem_path + File.separator + dirname;
		// 建立文件夹文件临时变量
		File dirName = new File(path_new);
		// 检查文件存在
		if (dirName.exists()) {
			dirName.delete();
			return true;
		}
		return false;
	}

	/**
	 * 【函数功能】创建文件
	 * @param tem_path 相对路径
	 * @param filename 文件名
	 * @return true or false
	 * @throws Exception
	 */
	public boolean ismakeFile(String tem_path, String filename)
			throws Exception {
		String path_new = "";
		System.out.println("******* path: " + path);
		path_new = path + File.separator + tem_path;
		System.out.println("******* path_new: " + path_new);
		// 建立文件夹文件临时变量
		File fileName = new File(path_new, filename);
		System.out.println("******* filename: " + fileName);
		// 检查文件存在
		if (fileName.exists()) {
			System.out.println("******* filename exists");
			fileName.delete();
		}
		fileName.createNewFile();
		// return true;
		return true;
	}

	/**
	 * 【函数功能】删除文件
	 * @param tem_path 相对路径
	 * @param filename 文件名
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isdelFile(String tem_path, String filename) throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// 建立文件夹文件临时变量
		File fileName = new File(path_new, filename);
		// 检查文件存在
		if (fileName.exists()) {
			fileName.delete();
			return true;
		}
		// fileName.createNewFile();
		return false;
		// return true;
	}

	/**
	 * 【函数功能】重命名文件
	 * @param tem_path 相对路径
	 * @param filename 旧文件名
	 * @param newfilename 新文件名
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isRenameFile(String tem_path, String filename,
			String newfilename) throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// 建立文件夹文件临时变量
		File fileName = new File(path_new, filename);
		// 检查文件存在
		if (fileName.exists()) {
			fileName.renameTo(new File(path_new, newfilename));
			return true;
		}
		return false;
	}

	/**
	 * 【函数功能】保存文件修改，覆盖指定文件
	 * @param tem_path 相对路径
	 * @param filename 文件名
	 * @param fileinto 写入内容
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isSaveFile(String tem_path, String filename, String fileinto)
			throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// 建立文件夹文件临时变量
		File fileName = new File(path_new, filename);
		// 检查文件存在
		if (fileName.exists()) {
			fileName.delete();
			FileWriter fw = new FileWriter(path_new + File.separator + filename);
			// 建立FileWrite对象
			// 将字串写入文件
			fw.write(fileinto);
			// 关闭文件
			fw.close();
			return true;
		} else {
			FileWriter fw = new FileWriter(path_new + File.separator + filename);
			// 建立FileWrite对象
			// 将字串写入文件
			fw.write(fileinto);
			// 关闭文件
			fw.close();
			return true;
		}
	}

	/**
	 * 【函数功能】追加写入文件内容
	 * @param tmp_path 相对路径
	 * @param filename 文件名
	 * @param fileInfo 写入内容
	 * @return true or false
	 */
	public boolean loopWrite(String tmp_path, String filename, String fileInfo) {
		boolean flag = false;
		String path1 = path + File.separator + tmp_path;
		File fileName = new File(path1, filename);
		try {
			if (fileName.exists()) {
				RandomAccessFile rf = new RandomAccessFile(path
						+ File.separator + filename, "rw"); // 定义一个类RandomAccessFile的对象，并实例化
				rf.seek(rf.length()); // 将指针移动到文件末尾
				rf.write(fileInfo.getBytes("GBK"));
				rf.close(); // 关闭文件流
				flag = true;
			} else {
				flag = false;
			}
		} catch (IOException e) {
			flag = false;
		}
		return flag;
	}	

	/**
	 * 【函数功能】拷贝文件
	 * @param tem_path 旧相对路径
	 * @param filename 旧文件名
	 * @param newpath 新相对路径
	 * @param temn_path 新文件名
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isCopyFile(String tem_path, String filename, String newpath,
			String temn_path) throws Exception {
		String fileinto = getFileinfo(tem_path, filename);
		setPath(newpath);
		if (ismakeFile(temn_path, filename)
				&& isSaveFile(temn_path, filename, fileinto)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 【函数功能】得到文件列表，type="D"为文件夹,type="F"为文件
	 * @param tem_path 相对路径
	 * @param type 获取的文件类型
	 * @return List 文件名称列表
	 * @throws Exception
	 */
	public List<String> getFilelist(String tem_path, String type)
			throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// 建立文件夹文件临时变量
		File d = new File(path_new);
		// 得到文件列表
		File list[] = d.listFiles();
		List<String> al = new ArrayList<String>();
		// 判断得到文件或文件夹
		if (type.equals("D")) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					al.add(list[i].getName());
				} else {
				}
			}
		} else if (type.equals("F")) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
				} else {
					al.add(list[i].getName());
				}
			}
		}
		return al;
	}
	
	/**
	 * 【函数功能】得到文件内容
	 * @param tem_path 相对路径
	 * @param filename 文件名
	 * @return String 获取的文件内容
	 * @throws Exception
	 */
	public String getFileinfo(String tem_path, String filename)
			throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// 建立文件夹文件临时变量
		File fileName = new File(path_new, filename);
		// 检查文件存在
		if (fileName.exists()) {
			FileReader fr = new FileReader(path_new + File.separator + filename);
			int c = fr.read();
			String flieinfo = "";
			while (c != -1) {
				flieinfo = flieinfo + (char) c + "";
				c = fr.read();
				if (c == 13) {
					flieinfo = flieinfo + "\n";
					fr.skip(1);
					c = fr.read();
				}
			}
			fr.close();
			return flieinfo;
		}
		return null;
	}
	
	/**
	 * 【函数功能】得到文件大小
	 * @param filepath 相对路径
	 * @param filename 文件名
	 * @return String 文件大小显示
	 */
	public String getFilesize(String filepath, String filename) {
		File fileobj = new File(filepath, filename);
		String renStr = fileobj.length() / 1024 + " K";
		return renStr;
	}

	/**
	 * 【函数功能】得到默认路径
	 * @return String 绝对路径
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 【函数功能】设置默认路径
	 * @param path 绝对路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * 【函数功能】根据传入的文件（夹）路径删除
	 * @param tempath
	 */
	public static void delDirAndFile(String tempath){
		File f = new File(tempath);
		if(f.isDirectory()){
			File[] l = f.listFiles();
			for(int i = 0;i<l.length;i++){
				delDirAndFile(l[i].getAbsolutePath());
			}
		}
		f.delete();
	}
	
	/**
	 * 【函数功能】根据传入的文件（夹）路径删除
	 * @param tempath
	 */
	public static void renDirAndFile(String tempath){
		File f = new File(tempath);
		if(f.isDirectory()){
			File[] l = f.listFiles();
			for(int i = 0;i<l.length;i++){
				renDirAndFile(l[i].getAbsolutePath());
			}
		}
		f.renameTo(new File(f.getAbsolutePath().toLowerCase()));
	}
	
	public static void main(String[] args) throws Exception{
		FileUtils.renDirAndFile("D:/yehy/workspace/websale/web/UserFiles/Image");
		
	}

}
