package com.cod.util;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cod.desktop.ironman.annotation.Transient;
import com.cod.desktop.ironman.configuration.PropertiesConfigVo;

public class VoUtil {
	private static Logger logger = Logger.getLogger(VoUtil.class);
	
	
	/**
	 * 配合VoUtil.forEachField使用，表示对循环迭代的每一个field类型的操作
	 * @author Yulong
	 *
	 */
	@FunctionalInterface
	public interface FieldFunction{
		void forField(Field field) throws IllegalArgumentException,IllegalAccessException;
	}
	
	/**
	 * 对vo中的属性执行给定的操作
	 * @param vo
	 * @param function
	 * @return
	 */
	public static <T> T forEachField(T vo,FieldFunction function) {
		logger.info("vo="+vo.toString());
		Field[] fields = vo.getClass().getDeclaredFields();
		for(Field field:fields) {
			try {
				if(function!=null) {
					field.setAccessible(true);
					function.forField(field);
				}else {
					logger.info("function is null,it will not do anything");
				}
			}catch(IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	/**
	 * 将读取的配置文件转为VO
	 * @param vo
	 * @param properties
	 * @return
	 */
	public static <T extends PropertiesConfigVo> T getPropertiesToVO(T vo,Properties properties) {
		logger.info("properties="+properties);
		forEachField(vo, (field)->{
			properties.forEach((key,value)->{
				if(field.getName().equals(key) && field.getAnnotation(Transient.class)==null) {
					try {
						field.set(vo,value);
					}catch(IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			});
		});
		return vo;
	}	
	
	
	
}
