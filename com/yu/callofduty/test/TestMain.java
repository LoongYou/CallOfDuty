package com.yu.callofduty.test;

import org.junit.Test;

import com.yu.callofduty.core.BaseFileResolver;
import com.yu.callofduty.core.FileTargetSet;
import com.yu.callofduty.core.TextTargetSet;
import com.yu.callofduty.core.XMLBuilder;

public class TestMain {
	static FileTargetSet tar = new FileTargetSet();
	static TextTargetSet tts = new TextTargetSet();
	
	
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
