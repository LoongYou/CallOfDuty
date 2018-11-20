package com.yu.callofduty.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.yu.callofduty.config.GeneralConfig;

/**
 * 文件查找解析器
 * <p>
 * 务必确保解析器封装的各种解析方法都是无副作用的纯函数
 * 
 * @author yl
 * @2018年8月14日下午10:11:34
 */
public class BaseFileResolver {
	/**
	 * 查找目录下的子目录
	 * 
	 * @param path
	 * @return
	 */
	public static final List<File> findDirs(DirTargetSet dts) {
		File[] list = null;
		List<File> temp = new ArrayList<File>();
		File file = new File(GeneralConfig.getFile_path());
		list = file.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				if (dts.getDir_end().equals(".*") || arg1.endsWith(dts.getDir_end()) || 
						(dts.getPatt_name() != null && !dts.getPatt_name().equals("") && Pattern.matches(dts.getPatt_name(), arg1)))
					return true;
				else
					return false;
			}
		});
		if (list != null) {
			for (File item : list) {
				if (item.isDirectory() == true)
					temp.add(item);
			}
		}
		return temp;
	}

	/**
	 * 查找目录下匹配的文件
	 * 
	 * @param path
	 * @return
	 */
	public final static List<File> findFiles(FileTargetSet fts) {
		File[] list = null;
		List<File> temp = new ArrayList<File>();
		File file = new File(GeneralConfig.getFile_path());
		list = file.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				if (fts.getFile_end().equals(".*") || arg1.endsWith(fts.getFile_end()) || 
						(fts.getPatt_name() != null && !fts.getPatt_name().equals("") && Pattern.matches(fts.getPatt_name(), arg1)))
					return true;
				else
					return false;
			}
		});
		if (list != null) {
			for (File item : list) {
				if (item.isDirectory() == false)
					temp.add(item);
			}
		}
		return temp;
	}
}
