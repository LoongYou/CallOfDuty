package com.yu.callofduty.test;

import org.junit.Test;

import com.yu.callofduty.core.BaseTextResolver;
import com.yu.callofduty.core.TargetSet;
import com.yu.callofduty.core.XMLBuilder;

public class TestMain {
	static TargetSet tar = new TargetSet();
	
	@Test
	public void test1(){
		String content = XMLBuilder.toXml(BaseTextResolver.findFiles(tar.getFile_path(),tar),1,tar);
		System.out.println(content);
	}
	
	@Test
	public void test2(){
		String content = XMLBuilder.toXml(BaseTextResolver.findFiles(tar.getFile_path(),tar),2,tar);
		System.out.println(content);
	}
	
	@Test
	public void test3(){
		String content = XMLBuilder.toXml(BaseTextResolver.findFiles(tar.getFile_path(),tar),3,tar);
		System.out.println(content);
	}
}
