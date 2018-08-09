package com.yu.cod;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class Builder extends Resolver{
	/**
	 * 生成xml文本
	 * @param list
	 * @param model 工作模式：1 根据开头查找  2 结尾  3 包含
	 * @return
	 */
	public static final String toXml(List<File> list,int model){
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
			if(result!=null){
				for(String item:result){
					if(item!=null&&!item.equals("")||(sub_patt!=null&&!sub_patt.equals("")&&Pattern.matches(sub_patt,item)))
						xml.append("\t<"+xml_tag+">").append(item).append("</"+xml_tag+">\n");
				}				
			}
			xml.append("</file>\n");
		}
		
		return xml.toString();
	}
}
