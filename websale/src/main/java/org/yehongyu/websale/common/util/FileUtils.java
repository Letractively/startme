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
 * ����˵�����ļ������࣬�����ļ���ʵ�÷�����
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:02:27
 */
public class FileUtils {
	
	private String path;

	/**
	 * ���������ܡ������ļ���
	 * @param tem_path ���·��
	 * @param dirname �ļ���
	 * @return true or false
	 */
	public boolean ismakeDir(String tem_path, String dirname) {
		String path_new = "";
		path_new = path + File.separator + tem_path + File.separator + dirname;
		// �����ļ����ļ���ʱ����
		File dirName = new File(path_new);
		// ����ļ�����
		if (dirName.exists()) {
			return false;
		}
		dirName.mkdir();
		return true;
	}

	/**
	 * ���������ܡ�ɾ���ļ���
	 * @param tem_path ���·��
	 * @param dirname �ļ���
	 * @return true or false
	 */
	public boolean isdelDir(String tem_path, String dirname) {
		String path_new = "";
		path_new = path + File.separator + tem_path + File.separator + dirname;
		// �����ļ����ļ���ʱ����
		File dirName = new File(path_new);
		// ����ļ�����
		if (dirName.exists()) {
			dirName.delete();
			return true;
		}
		return false;
	}

	/**
	 * ���������ܡ������ļ�
	 * @param tem_path ���·��
	 * @param filename �ļ���
	 * @return true or false
	 * @throws Exception
	 */
	public boolean ismakeFile(String tem_path, String filename)
			throws Exception {
		String path_new = "";
		System.out.println("******* path: " + path);
		path_new = path + File.separator + tem_path;
		System.out.println("******* path_new: " + path_new);
		// �����ļ����ļ���ʱ����
		File fileName = new File(path_new, filename);
		System.out.println("******* filename: " + fileName);
		// ����ļ�����
		if (fileName.exists()) {
			System.out.println("******* filename exists");
			fileName.delete();
		}
		fileName.createNewFile();
		// return true;
		return true;
	}

	/**
	 * ���������ܡ�ɾ���ļ�
	 * @param tem_path ���·��
	 * @param filename �ļ���
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isdelFile(String tem_path, String filename) throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// �����ļ����ļ���ʱ����
		File fileName = new File(path_new, filename);
		// ����ļ�����
		if (fileName.exists()) {
			fileName.delete();
			return true;
		}
		// fileName.createNewFile();
		return false;
		// return true;
	}

	/**
	 * ���������ܡ��������ļ�
	 * @param tem_path ���·��
	 * @param filename ���ļ���
	 * @param newfilename ���ļ���
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isRenameFile(String tem_path, String filename,
			String newfilename) throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// �����ļ����ļ���ʱ����
		File fileName = new File(path_new, filename);
		// ����ļ�����
		if (fileName.exists()) {
			fileName.renameTo(new File(path_new, newfilename));
			return true;
		}
		return false;
	}

	/**
	 * ���������ܡ������ļ��޸ģ�����ָ���ļ�
	 * @param tem_path ���·��
	 * @param filename �ļ���
	 * @param fileinto д������
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isSaveFile(String tem_path, String filename, String fileinto)
			throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// �����ļ����ļ���ʱ����
		File fileName = new File(path_new, filename);
		// ����ļ�����
		if (fileName.exists()) {
			fileName.delete();
			FileWriter fw = new FileWriter(path_new + File.separator + filename);
			// ����FileWrite����
			// ���ִ�д���ļ�
			fw.write(fileinto);
			// �ر��ļ�
			fw.close();
			return true;
		} else {
			FileWriter fw = new FileWriter(path_new + File.separator + filename);
			// ����FileWrite����
			// ���ִ�д���ļ�
			fw.write(fileinto);
			// �ر��ļ�
			fw.close();
			return true;
		}
	}

	/**
	 * ���������ܡ�׷��д���ļ�����
	 * @param tmp_path ���·��
	 * @param filename �ļ���
	 * @param fileInfo д������
	 * @return true or false
	 */
	public boolean loopWrite(String tmp_path, String filename, String fileInfo) {
		boolean flag = false;
		String path1 = path + File.separator + tmp_path;
		File fileName = new File(path1, filename);
		try {
			if (fileName.exists()) {
				RandomAccessFile rf = new RandomAccessFile(path
						+ File.separator + filename, "rw"); // ����һ����RandomAccessFile�Ķ��󣬲�ʵ����
				rf.seek(rf.length()); // ��ָ���ƶ����ļ�ĩβ
				rf.write(fileInfo.getBytes("GBK"));
				rf.close(); // �ر��ļ���
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
	 * ���������ܡ������ļ�
	 * @param tem_path �����·��
	 * @param filename ���ļ���
	 * @param newpath �����·��
	 * @param temn_path ���ļ���
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
	 * ���������ܡ��õ��ļ��б�type="D"Ϊ�ļ���,type="F"Ϊ�ļ�
	 * @param tem_path ���·��
	 * @param type ��ȡ���ļ�����
	 * @return List �ļ������б�
	 * @throws Exception
	 */
	public List<String> getFilelist(String tem_path, String type)
			throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// �����ļ����ļ���ʱ����
		File d = new File(path_new);
		// �õ��ļ��б�
		File list[] = d.listFiles();
		List<String> al = new ArrayList<String>();
		// �жϵõ��ļ����ļ���
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
	 * ���������ܡ��õ��ļ�����
	 * @param tem_path ���·��
	 * @param filename �ļ���
	 * @return String ��ȡ���ļ�����
	 * @throws Exception
	 */
	public String getFileinfo(String tem_path, String filename)
			throws Exception {
		String path_new = "";
		path_new = path + File.separator + tem_path;
		// �����ļ����ļ���ʱ����
		File fileName = new File(path_new, filename);
		// ����ļ�����
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
	 * ���������ܡ��õ��ļ���С
	 * @param filepath ���·��
	 * @param filename �ļ���
	 * @return String �ļ���С��ʾ
	 */
	public String getFilesize(String filepath, String filename) {
		File fileobj = new File(filepath, filename);
		String renStr = fileobj.length() / 1024 + " K";
		return renStr;
	}

	/**
	 * ���������ܡ��õ�Ĭ��·��
	 * @return String ����·��
	 */
	public String getPath() {
		return path;
	}

	/**
	 * ���������ܡ�����Ĭ��·��
	 * @param path ����·��
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * ���������ܡ����ݴ�����ļ����У�·��ɾ��
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
	 * ���������ܡ����ݴ�����ļ����У�·��ɾ��
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
