package com.yu.cod;

import org.junit.Test;

public class TestMain {
	
	@Test
	public void test1(){
		Main m = new Main();
		String content = m.toXml(m.findFiles(Main.file_path),1);
		System.out.println(content);
	}
	
	@Test
	public void test2(){
		Main m = new Main();
		String content = m.toXml(m.findFiles(Main.file_path),2);
		System.out.println(content);
	}
	
	@Test
	public void test3(){
		Main m = new Main();
		String content = m.toXml(m.findFiles(Main.file_path),3);
		System.out.println(content);
	}
}
