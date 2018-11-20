package com.yu.callofduty.core;

import java.util.ArrayList;
import java.util.List;

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
	public final static boolean matchSplitByOneWorld(char c,char[] cs,int index,TextTargetSet tar){
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
	 * 匹配间隔符/间隔字符，优先匹配字符元组再到字符串元组
	 * @param c 匹配的字符
	 * @param cs 匹配的字符串
	 * @param index 当前的c在cs中的下标
	 * @return
	 */
	public final static boolean matchSplitByStartAndEnd(char c,char[] cs,int index,TextTargetSet tar){
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
	 * 查找文件中匹配的全量字符串,根据开头查找
	 * @param content
	 * @return
	 */
	public static final List<String> searchTextByStart(String content,TextTargetSet tar){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		int start = 0;//关键字起始下标
		int ender = 0;//连续字符串尾部
		int lastIndex = 0;//上一次匹配到字符串的下标
		while(start!=-1){
			start = content.indexOf(tar.getWord_start(), start);
			ender = start;
			if((ender = start)>-1){
				char c = 1;
				while(!matchSplitByOneWorld(c,chars,ender,tar)&&ender+1<chars.length&&ender>lastIndex){
					c = chars[ender+1];
					ender++;
				}
				if(start>-1)
					sub = subString(start,ender,content,tar);
				lastIndex = start;//从上次匹配的下标继续匹配
				list.add(sub);
				if(start!=-1)
					start++;
				//System.out.println(sub+",");				
			}
		}
		return list;
	}

	/**
	 * 查找文件中匹配的全量字符串,根据结尾查找
	 * @param content
	 * @return
	 */
	public static final List<String> searchTextByEnd(String content,TextTargetSet tar){
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		
		int start = 0;//关键字起始下标
		int hearder = 0;//连续字符串头部
		int lastIndex = 0;//上一次匹配到字符串的下标
		while(start!=-1){
			start = content.indexOf(tar.getWord_end(), start);
			if((hearder = start)>-1){
				char c = 1;
				while(!matchSplitByOneWorld(c,chars,hearder,tar)&&hearder>0&&hearder>lastIndex){
					c = chars[hearder-1];
					hearder--;
				}
				sub = subString(hearder+1,start+tar.getWord_end().length(),content,tar);
				lastIndex = start;//从上次匹配的下标继续匹配
				list.add(sub);
				if(start!=-1)
					start++;
			}
			//System.out.println(sub+",");
		}
		
		return list;	
			
	}
	
	/**
	 * 查找文件中匹配的全量字符串,根据包含的关键字查找
	 * @param content
	 * @return
	 */
	public static final List<String> searchTextByKey(String content,TextTargetSet tar){
		//System.out.println(content);
		List<String> list = new ArrayList<String>();
		String sub = null;
		char[] chars = content.toCharArray();
		int start = 0;//关键字起始下标
		int hearder = 0;//连续字符串头部
		int ender = 0;//连续字符串尾部
		int lastIndex = 0;//上一次匹配到字符串的下标
		while(start!=-1){
			start = content.indexOf(tar.getWord_like(), start);
			if((hearder = start)>-1){
				ender = start;
				char c = 1;
				while(!matchSplitByOneWorld(c,chars,hearder,tar)&&hearder>0&&hearder>lastIndex){
					c = chars[hearder-1];
					hearder--;
				}
				c = 1;
				while(!matchSplitByOneWorld(c,chars,ender,tar)&&ender+1<chars.length&&ender>lastIndex){
					c = chars[ender+1];
					ender++;
				}
				sub = subString(hearder+1,ender,content,tar);
				lastIndex = start;//从上次匹配的下标继续匹配
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
	private static final String subString(int start,int end,String content,TextTargetSet tar){
		if(end-start<1||end-start>=tar.getSub_length())
			return "匹配内容太长或没有";
		return new String(content.substring(start,end));
	}
	
	
}
