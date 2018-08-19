package com.yu.callofduty.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 文件查找解析器
 * <p>务必确保解析器封装的各种解析方法都是无副作用的纯函数
 * @author yl
 * @2018年8月14日下午10:11:34
 */
public class BaseFileResolver {
	/**
	 * 查找目录下的子目录
	 * @param path
	 * @return
	 */
	public static final List<File> findDirs(String path,TargetSet tar){
		File[] list = null;
		List<File> temp = new ArrayList<File>();
		File file = new File(path);
		list = file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File arg0, String arg1) {
				if(tar.getDir_end().equals(".*")||arg1.endsWith(tar.getDir_end())||(tar.getDir_patt()!=null&&!tar.getDir_patt().equals("")&&Pattern.matches(tar.getDir_patt(),arg1)))
					return true;
				else
					return false;
			}
		});
		if(list!=null){
			for(File item:list){
				if(item.isDirectory()==true)
					temp.add(item);
			}			
		}
		return temp;
	}

	/**
	 * 查找目录下匹配的文件
	 * @param path
	 * @return
	 */
	public final static List<File> findFiles(String path,TargetSet tar){
		File[] list = null;
		List<File> temp = new ArrayList<File>();
		File file = new File(path);
		list = file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File arg0, String arg1) {
				if(tar.getFile_end().equals(".*")||arg1.endsWith(tar.getFile_end())||(tar.getFile_patt()!=null&&!tar.getFile_patt().equals("")&&Pattern.matches(tar.getFile_patt(),arg1)))
					return true;
				else
					return false;
			}
		});
		if(list!=null){
			for(File item:list){
				if(item.isDirectory()==false)
					temp.add(item);
			}			
		}
		return temp;
	}
}
