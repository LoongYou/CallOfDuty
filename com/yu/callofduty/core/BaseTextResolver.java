package com.yu.callofduty.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.yu.callofduty.core.interfaces.Constants;

/**
 * 文本解析器，封装各种解析手段
 * <p>务必确保解析器封装的各种解析方法都是无副作用的纯函数
 * @author yulong
 * @2018年8月1日下午11:01:10
 */
public class BaseTextResolver implements Constants{
	
	
	/**
	 * 匹配间隔符/间隔字符，优先匹配字符元组再到字符串元组
	 * @param c 匹配的字符
	 * @param cs 匹配的字符串
	 * @param index 当前的c在cs中的下标
	 * @return
	 */
	public final static boolean matchSplit(char c,char[] cs,int index,TargetSet tar){
		for(char item:tar.getChar_split()){
			if(item==c)
				return true;
			if(!tar.isSplitAll())
				break;
		}
		for(String item:tar.getWord_split()){
			if(index+item.length()>=cs.length)
				continue;
			String str = String.copyValueOf(cs, index, item.length());
			if(item.equals(str))
				return true;
			if(!tar.isSplitAll())
				break;
		}
		return false;
	}
	
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
	
	/**
	 * 查找文件中匹配的字符串,根据开头查找
	 * @param content
	 * @return
	 */
	public static final List<String> searchTextByStart(String content,TargetSet tar){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		int start = 0;//关键字起始下标
		int ender = 0;//连续字符串尾部
		while(start!=-1){
			start = content.indexOf(tar.getWord_start(), start);
			ender = start;
			if((ender = start)>-1){
				char c = 1;
				while(!matchSplit(c,chars,ender,tar)&&ender+1<chars.length){
					c = chars[ender+1];
					ender++;
				}
				if(start>-1)
					sub = subString(start,ender,content,tar);
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
	public static final List<String> searchTextByEnd(String content,TargetSet tar){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		
		int start = 0;//关键字起始下标
		int hearder = 0;//连续字符串头部
		while(start!=-1){
			start = content.indexOf(tar.getWord_end(), start);
			if((hearder = start)>-1){
				char c = 1;
				while(!matchSplit(c,chars,hearder,tar)&&hearder>0){
					c = chars[hearder-1];
					hearder--;
				}
				sub = subString(hearder+1,start+tar.getWord_end().length(),content,tar);
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
	public static final List<String> searchTextByKey(String content,TargetSet tar){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		int start = 0;//关键字起始下标
		int hearder = 0;//连续字符串头部
		int ender = 0;//连续字符串尾部
		while(start!=-1){
			start = content.indexOf(tar.getWord_like(), start);
			if((hearder = start)>-1){
				ender = start;
				char c = 1;
				while(!matchSplit(c,chars,hearder,tar)&&hearder>0){
					c = chars[hearder-1];
					hearder--;
				}
				c = 1;
				while(!matchSplit(c,chars,ender,tar)&&ender+1<chars.length){
					c = chars[ender+1];
					ender++;
				}
				sub = subString(hearder+1,ender,content,tar);
				list.add(sub);								
				if(start!=-1)
					start++;
				//System.out.println(sub+",");				
			}
		}
		return list;
	}
	
	/**
	 * 根据前后下标截取字符串
	 * @param start
	 * @param end
	 * @param content
	 * @return
	 */
	private static final String subString(int start,int end,String content,TargetSet tar){
		if(end-start<1||end-start>=tar.getSub_length())
			return "匹配内容太长或没有";
		return new String(content.substring(start,end));
	}
	
	
}
