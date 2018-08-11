package com.yu.callofduty.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.yu.callofduty.core.interfaces.Constants;

public class FileUtil implements Constants{
	/**
	 * 读取文件并返回文本
	 * @param file
	 * @return
	 */
	public static final String read(File file){
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
		String line = null;
			while(null != (line = reader.readLine())){
				sb.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null)reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
}
