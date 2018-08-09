package com.yu.cod;

import org.junit.Test;

public class TestMain {
	static TargetSet tar = new TargetSet();
	
	@Test
	public void test1(){
		String content = Builder.toXml(BaseResolver.findFiles(tar.getFile_path(),tar),1,tar);
		System.out.println(content);
	}
	
	@Test
	public void test2(){
		String content = Builder.toXml(BaseResolver.findFiles(tar.getFile_path(),tar),2,tar);
		System.out.println(content);
	}
	
	@Test
	public void test3(){
		String content = Builder.toXml(BaseResolver.findFiles(tar.getFile_path(),tar),3,tar);
		System.out.println(content);
	}
}
