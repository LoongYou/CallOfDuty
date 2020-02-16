package com.yu.callofduty.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import com.yu.callofduty.core.interfaces.Constants;

/**
 * 
 * @author yl
 *
 */
public class FileUtil implements Constants {
	/**
	 * 以行读取文件并返回文本,文本无换行
	 * 
	 * @param file
	 * @return
	 */
	public static final String readByLine(File file) {
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		String line = null;
		try {
			while (null != (line = reader.readLine())) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return sb.toString();
	}

	/**
	 * 以字节读取文本，支持换行符，用于支持逐行扫描,推荐使用
	 * @param file
	 * @return
	 */
	public static final String readByByte(File file) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedInputStream buff = new BufferedInputStream(in, 1024000);
		StringBuilder builder = new StringBuilder();
		int length = 0;
		byte[] b = null;
		try {
			b = new byte[buff.available()];
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//buff.read(arg0, arg1, arg2);
		try {
//			while((length = buff.read(b,0,b.length))!=-1){
//				if(length=='\t')
//					System.out.println("换行");
//			}
			while((length = buff.read())!=-1) {
				if(length==10)
					System.out.println("回车");
				if(length==13)
					System.out.println("换行");
				builder.append((char)length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return new String(b);
		return builder.toString();
	}
}
