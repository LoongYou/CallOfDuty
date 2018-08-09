package com.yu.cod;

import org.junit.Test;

public class TestMain {
	
	@Test
	public void test1(){
		String content = Builder.toXml(Builder.findFiles(Builder.file_path),1);
		System.out.println(content);
	}
	
	@Test
	public void test2(){
		String content = Builder.toXml(Resolver.findFiles(Builder.file_path),2);
		System.out.println(content);
	}
	
	@Test
	public void test3(){
		String content = Builder.toXml(Resolver.findFiles(Builder.file_path),3);
		System.out.println(content);
	}
}
