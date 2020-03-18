package com.cod.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil implements Log{
	
	public static final int IsFile = 1;
	public static final int IsDir = 2;
	
	public static void main(String[] args) {		
		List<File> fileList = searchFile("D:\\log","log");
		for(File file:fileList) {
			System.out.println(file.getName());
		}
	}
	
	
	/**
	 * 获取目录下所有文件对象
	 * @param path
	 * @param endsWith 匹配的后缀，为""则匹配所有
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
	 * @param type true=文件
	 * @return 创建成功返回true，文件已存在返回false
	 * @throws IOException
	 */
	public static int create(String path,int type) throws IOException {
		Log.info("path=",path,"isDoc=",type);
		if(path==null) {
			return Error;
		}
		File file = new File(path);
		if(!file.exists()) {
			if(type==IsFile && file.createNewFile()) {
				logger.info("create file="+path);
				return Sucess;
			}
			if(type==IsDir && file.mkdirs()) {
//				if(!path.endsWith("\\")||!path.endsWith("\\\\")) {
//					path = path+"\\";
//				}
//				file = new File(path);
//				if(file.mkdirs()) {					
					logger.info("create dir="+path);
					return Sucess;
//				}
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
		return create(dirPath, IsDir);
	}
	
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static int createFile(String filePath) throws IOException {
		Log.info("filePath=",filePath);
		return create(filePath,IsFile);
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
	
	/**
	 * 使用缓冲流读取文件，以逐行的方式读取为字符串
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readBufferByLine(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		BufferedInputStream bs = new BufferedInputStream(in, 4096);//固态硬盘4K对齐读取优化
		InputStreamReader reader = new InputStreamReader(bs,"UTF-8");
		
		
		
		BufferedReader br = new BufferedReader(reader);
		try {
			StringBuilder sb = new StringBuilder();		
			String line = null;
			while(null!=(line=br.readLine())) {
				sb.append(line).append("\n");
			}
			return sb.toString();
		}finally {
			br.close();
		}
	};
	
	/**
	 * 将文本写入文件(通过OutputStreamWriter一次性写入)
	 * @param content
	 * @throws IOException 
	 */
	public static void write(File file,String content) throws IOException {
		OutputStreamWriter wr = null;
		try{
			FileOutputStream out = new FileOutputStream(file);
			wr = new OutputStreamWriter(out,"UTF8");
			wr.write(content);			
		}finally {
			if(wr !=null ) {				
				wr.close();
			}
		}
	}
	
}
