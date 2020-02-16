package com.cod.util;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 日志扩展接口，仅需实现该接口即可为你的类增加日志打印的便捷方法
 * @author Yulong
 *
 */
public interface Log extends RSC{
	
	Logger logger = Logger.getLogger(Log.class);
	
	int LOG_DEBUG = 0;
	int LOG_INFO = 1;
	int LOG_ERROR = 2;
	
	static void log(int level,String message) {
		if(level==LOG_DEBUG) logger.debug(message);
		if(level==LOG_INFO) logger.info(message);
		if(level==LOG_ERROR) logger.error(message);
	}
	
	/**
	 * 获取上级调用的方法描述
	 * @param tracelevel 层级
	 * @return
	 */
	static StringBuilder getInvokerDesc(int tracelevel) {
		StringBuilder message = new StringBuilder();
		if(tracelevel<1) return message;
		StackTraceElement lastInvoker = Thread.currentThread().getStackTrace()[tracelevel];
		message.append(lastInvoker.getClassName())
		.append(".")
		.append(lastInvoker.getMethodName())
		.append("(")
		.append(lastInvoker.getFileName())
		.append(":")
		.append(lastInvoker.getLineNumber())
		.append(")");
		return message;
	}
	
	/**
	 * 将多个对象入参合并为一条message
	 * @param objs
	 * @return
	 */
	static String concatMessage(Object ...objs) {
		if(objs==null || objs.length==0) return "";
		
		List<Object> list = Arrays.asList(objs);
		StringBuilder message = new StringBuilder();
		list.stream()
		.filter(obj->obj!=null && obj.toString()!=null)
		.forEach(obj->{
			message.append(obj.toString());
			if(!obj.toString().endsWith("=")) {
				message.append(",");
				}
			});
		
//		for(int i = 0;i<objs.length && i<50;i++) {
//			String objectString = objs[i].toString();
//			if(objectString!=null) {
//				message.append(objectString);
//				if(!objectString.endsWith("=")) {
//					message.append(",");
//				}
//			}
//			
//		}
		return message.toString();
	}
	
	
	
	default void Debug(Object ...objs) {
		log(LOG_DEBUG, getInvokerDesc(3).append(concatMessage(objs)).toString());
	}
	
	default void Info(Object ...objs) {
		log(LOG_INFO, getInvokerDesc(3).append(concatMessage(objs)).toString());
	}
	
	default void Error(Object ...objs) {
		log(LOG_ERROR, getInvokerDesc(3).append(concatMessage(objs)).toString());
	}
	
	static void debug(Object ...objs) {
		log(LOG_DEBUG, getInvokerDesc(3).append(concatMessage(objs)).toString());
	}
	
	static void info(Object ...objs) {
		log(LOG_INFO, getInvokerDesc(3).append(concatMessage(objs)).toString());
	}
	
	static void error(Object ...objs) {
		log(LOG_ERROR, getInvokerDesc(3).append(concatMessage(objs)).toString());
	}
}
