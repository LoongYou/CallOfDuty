package com.cod.test.util;

import com.cod.util.Log;

public class TestLogUitl implements Log{
	
	public static void main(String[] args) {
		
		String s1 = "111";
		String s2 = "112";
		String s3 = "113";
		TestLogUitl aa = new TestLogUitl();
		new TestLogUitl().Info("s1=",s1,"s2 is this",s2,"and s3 is ok",s3);
		Log.info("s1=",s1,"s2 is this",s2,"and s3 is ok",s3);
		aa.get();
		aa.get();
	}
	
	
	
	public void get() {
		Info("helloworld");
	}
	
	
}
