package com.yu.callofduty.core;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import com.yu.callofduty.config.GeneralConfig;
import com.yu.callofduty.core.interfaces.Constants;
import com.yu.callofduty.core.util.FileUtil;

public class XMLBuilder implements Constants{
	/**
	 * 生成xml文本
	 * @param list
	 * @param model 工作模式：1 根据开头查找  2 结尾  3 包含
	 * @return
	 */
	public static final String toXml(List<File> list,int model,FileTargetSet tar,TextTargetSet tts){
		if(list==null)
			return null;
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		
		for(File file:list){
			xml.append("<file name=\""+file.getName()+"\">\n");
			String content = FileUtil.readByByte(file);
			List<String> result = null;
			switch(model){
			case 1:result = BaseTextResolver.searchTextByStart(content,tts);
				break;
			case 2:result = BaseTextResolver.searchTextByEnd(content,tts);
				break;
			case 3:result = BaseTextResolver.searchTextByKey(content,tts);
			}
			if(result!=null){
				for(String item:result){
					//System.out.println(item);
					if(item!=null&&!item.equals("")||(tar.getSub_patt()!=null&&!tar.getSub_patt().equals("")&&Pattern.matches(tar.getSub_patt(),item)))
						xml.append("\t<"+GeneralConfig.getXml_path()+">").append(item).append("</"+GeneralConfig.getXml_path()+">\n");
				}				
			}
			xml.append("</file>\n");
		}
		
		return xml.toString();
	}
}
