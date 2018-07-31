package com.yu.cod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	
	//处理目录
	static String file_path = "C:\\Users\\yl\\Documents\\resource";
	//文件名结尾（含后缀）
	static String file_end = ".txt";
	//文件名开头
	static String file_start = "";
	//文件名包含
	static String file_like = "";
	
	//关键字开头
	static String word_start = "北";
	//关键字结尾
	static String word_end = "市";
	//关键字包含
	static String word_like = "男";
	
	//间隔字符
	static char[] char_split = {' ','\'',','};
	//间隔字符串
	static String[] word_split = {};
	
	//保存xml的路径
	static String xml_path = "";
	//查找元素标签名
	static String xml_tag = "item"; 
	
	
	/**
	 * 查找目录下匹配的文件
	 * @param c
	 * @param s
	 * @return
	 */
	public static boolean matchSplit(char c,char[] cs,int index){
		for(char item:char_split){
			if(item==c)
				return true;
		}
		for(String item:word_split){
			if(index+item.length()>=cs.length)
				continue;
			String str = String.copyValueOf(cs, index, item.length());
			if(item.equals(str))
				return true;
		}
		return false;
	}
	
	/**
	 * 查找目录下匹配的文件
	 * @param path
	 * @return
	 */
	public List<File> findFiles(String path){
		File[] list = null;
		List<File> temp = new ArrayList<File>();
		File file = new File(path);
		list = file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File arg0, String arg1) {
				if(file_end.equals(".*")||arg1.endsWith(file_end))
					return true;
				else
					return false;
			}
		});
		for(File item:list){
			if(item.isDirectory()==false)
				temp.add(item);
		}
		return temp;
	}
	
	/**
	 * 读取文件并返回文本
	 * @param file
	 * @return
	 */
	public String read(File file){
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
		String line = null;
			while(null != (line = reader.readLine())){
				sb.append(line);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
	 * 查找文件中匹配的字符串,根据开头查找
	 * @param content
	 * @return
	 */
	public List<String> searchTextByStart(String content){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		int start = 0;//关键字起始下标
		int ender = 0;//连续字符串尾部
		while(start!=-1){
			start = content.indexOf(word_start, start);
			ender = start;
			if((ender = start)>-1){
				char c = 1;
				while(!matchSplit(c,chars,ender)&&ender+1<chars.length){
					c = chars[ender+1];
					ender++;
				}
				if(start>-1)
					sub = new String(content.substring(start,ender));
				list.add(sub);
				if(start!=-1)
					start++;
				//System.out.println(sub+",");				
			}
		}
		return list;
	}

	/**
	 * 查找文件中匹配的字符串,根据结尾查找
	 * @param content
	 * @return
	 */
	public List<String> searchTextByEnd(String content){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		
		int start = 0;//关键字起始下标
		int hearder = 0;//连续字符串头部
		while(start!=-1){
			start = content.indexOf(word_end, start);
			if((hearder = start)>-1){
				char c = 1;
				while(!matchSplit(c,chars,hearder)&&hearder>0){
					c = chars[hearder-1];
					hearder--;
				}
				sub = new String(content.substring(hearder+1,start+word_end.length()));
				list.add(sub);
				if(start!=-1)
					start++;
			}
			//System.out.println(sub+",");
		}
		
		return list;	
			
	}
	
	/**
	 * 查找文件中匹配的字符串,根据包含关键字查找
	 * @param content
	 * @return
	 */
	public List<String> searchTextByKey(String content){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		int start = 0;//关键字起始下标
		int hearder = 0;//连续字符串头部
		int ender = 0;//连续字符串尾部
		while(start!=-1){
			start = content.indexOf(word_like, start);
			if((hearder = start)>-1){
				ender = start;
				char c = 1;
				while(!matchSplit(c,chars,hearder)&&hearder>0){
					c = chars[hearder-1];
					hearder--;
				}
				c = 1;
				while(!matchSplit(c,chars,ender)&&ender+1<chars.length){
					c = chars[ender+1];
					ender++;
				}
				sub = new String(content.substring(hearder+1,ender));
				//System.out.println(sub+hearder+" "+ender);
				list.add(sub);								
				if(start!=-1)
					start++;
				//System.out.println(sub+",");				
			}
		}
		return list;
	}
	
	
	/**
	 * 生成xml文本
	 * @param list
	 * @return
	 */
	public String toXml(List<File> list,int model){
		if(list==null)
			return null;
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\"> encoding=\"UTF-8\"?>\n");
		
		for(File file:list){
			xml.append("file name=\""+file.getName()+"\">\n");
			String content = read(file);
			List<String> result = null;
			switch(model){
			case 1:result = searchTextByStart(content);
				break;
			case 2:result = searchTextByEnd(content);
				break;
			case 3:result = searchTextByKey(content);
			}
			for(String item:result){
				if(item!=null&&!item.toString().equals(""))
					xml.append("\t<"+xml_tag+">").append(item).append("</"+xml_tag+">\n");
			}
			xml.append("</file>\n");
		}
		
		return xml.toString();
	}
	
}
