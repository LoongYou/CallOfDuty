package com.yu.callofduty.test;

import org.junit.Test;

import com.yu.callofduty.core.BaseFileResolver;
import com.yu.callofduty.core.FileTargetSet;
import com.yu.callofduty.core.TextTargetSet;
import com.yu.callofduty.core.XMLBuilder;

public class TestMain {
	static FileTargetSet tar = new FileTargetSet();
	static TextTargetSet tts = new TextTargetSet();
	static{
		tar.setFile_end(".java");
		//tar.setFile_like(file_like);
		tts.setWord_start("import");
		tts.setWord_end("}");
		tts.setWord_like("new");
		//tts.setChar_split();
		tts.setWord_split(new String[]{";"});
		tts.setXml_tag("item");
	}
	
	
	@Test
	public void test1(){
		String content = XMLBuilder.toXml(BaseFileResolver.findFiles(tar),1,tar,tts);
		System.out.println(content);
	}
	
	@Test
	public void test2(){
		String content = XMLBuilder.toXml(BaseFileResolver.findFiles(tar),2,tar,tts);
		System.out.println(content);
	}
	
	@Test
	public void test3(){
		String content = XMLBuilder.toXml(BaseFileResolver.findFiles(tar),3,tar,tts);
		System.out.println(content);
	}
	
	
	
	
}
