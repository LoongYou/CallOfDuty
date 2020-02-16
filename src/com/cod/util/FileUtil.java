package com.cod.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil implements Log{
	
	public static void main(String[] args) {		
		List<File> fileList = searchFile("D:\\log","log");
		for(File file:fileList) {
			System.out.println(file.getName());
		}
	}
	
	
	/**
	 * 获取目录下所有文件对象
	 * @param path
	 * @param endsWith
	 * @return
	 */
	public static List<File> searchFile(String path,String endsWith){
		Log.info("path=",path,"endsWith=",endsWith);
		File[] list = null;
		List<File> temp = new ArrayList<File>();
		File file = new File(path);
		if(!file.isDirectory())return temp;
		list = file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(endsWith)) {
					return true;
				}else {
					return false;
				}
			}
		});
		for(File item:list) {
			if(item.isDirectory()==false) {
				temp.add(item);
			}
		}
		return temp;
	}
	
	/**
	 * 创建目录/文件，如果已存在，则不创建
	 * @param path
	 * @param isFile true=文件
	 * @return 创建成功返回true，文件已存在返回false
	 * @throws IOException
	 */
	public static int create(String path,boolean isFile) throws IOException {
		Log.info("path=",path,"isDoc=",isFile);
		if(path==null) {
			return Error;
		}
		File file = new File(path);
		if(!file.exists()) {
			if(isFile && file.createNewFile()) {
				logger.info("create file="+path);
				return Sucess;
			}
			if(!isFile && file.mkdirs()) {
				logger.info("create dir="+path);
				return Sucess;
			}
		}else {
			Log.info("Can not create "+path+",because "+path+" is already exists!");
		}
		return Failed;
	}
	
	/**
	 * 创建目录
	 * @param dirPath
	 * @return
	 * @throws IOException
	 */
	public static int createDir(String dirPath) throws IOException {
		Log.info("dirPath=",dirPath);
		return create(dirPath, false);
	}
	
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static int createFile(String filePath) throws IOException {
		Log.info("filePath=",filePath);
		return create(filePath,true);
	}
	
	
	/**
	 * 先创建目录，再创建文件
	 * @param dirPath
	 * @param filePath
	 * @return 创建成功返回true，文件已存在返回false
	 * @throws IOException
	 */
	public static int create(String dirPath,String filePath) throws IOException {
		Log.info("dirPath=",dirPath,"filePath=",filePath);
		if(createDir(dirPath)==Error) {
			return Error;
		}
		return createFile(filePath);
	}
	
}
