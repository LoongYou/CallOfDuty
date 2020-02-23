package com.cod.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.cod.desktop.ironman.annotation.Transient;


public class PropertiesUtil implements Log{
	static final String BYTE = "0";
	static final String STRING = "1";
	static String model = BYTE;
	
	@FunctionalInterface
	public interface PropertiesFunction{
		
		void forProperties(Properties properties);
	}
	
	/**
	 * 
	 * 根据文件路径获取properties
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public static Properties getProperties(String path) throws IOException {
		Log.info("path="+path);
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(path);
		if(model==BYTE) {
			properties.load(in);//该方法不会自动关闭流，需显式关闭
			in.close();
		}
		if(model==STRING) {
			InputStreamReader reader = new InputStreamReader(in,"utf-8");
			properties.load(reader);//该方法不会自动关闭流，需显式关闭
			reader.close();
		}
		Log.info("properties="+properties);
		return properties;
	}
	
	/**
	 * 以覆盖模式保存配置文件
	 * @param path
	 * @param function 保存properties之前的操作
	 * @param comments 保存文件时写入的注释
	 * @return
	 * @throws IOException
	 */
	public static Properties savePropertiesToFile(String path, PropertiesFunction function, String comments) throws IOException {
		Log.info("path="+path);
		Properties properties = getProperties(path);
		if(function!=null) {
			function.forProperties(properties);
		}
		FileOutputStream out = new FileOutputStream(new File(path));
		if(model==BYTE) {
			properties.store(out, comments);//该方法不会自动关闭流，需显式关闭
			out.close();
		}
		if(model==STRING) {
			OutputStreamWriter writer = new OutputStreamWriter(out,"utf-8");
			properties.store(writer, comments);//该方法不会自动关闭流，需显式关闭
			writer.close();
		}
		Log.info("properties="+properties);
		return properties;
	}
	
	/**
	 * 以覆盖模式将VO保存到配置文件
	 * @param path
	 * @param vo
	 * @param comments
	 * @return
	 * @throws IOException 
	 */
	public static Properties saveVOtoPropertiesFile(String path, Object vo,String comments) throws IOException {
		Log.info("programConfigVo="+vo);
		return savePropertiesToFile(path,(properties)->{
			VoUtil.forEachField(vo,(field)->{
				if(field.getAnnotation(Transient.class)==null) {
					String value = field.get(vo)==null?"":field.get(vo).toString();
					properties.put(field.getName(),value);
				}
			});
		},comments);
	}
	
	/**
	 * 将map保存到properties文件
	 * @param path
	 * @param map
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	public static Properties saveMapToPropertiesFile(String path, Map<String,String> map,String comments) throws IOException {
		Log.info("map="+map);
		return savePropertiesToFile(path,(properties)->{
			map.forEach((key,value)->{
				properties.put(key,value);
			});
		},comments);
	}
	
	/**
	 * 将properties copy到指定map
	 * @param properties
	 * @param map
	 * @param cover
	 * @return
	 */
	public static Map<String,String> propertiesCopyToMap(Properties properties, final Map<String,String> map,boolean cover){
		
		if(map==null) {
			return map;
		}
		
		properties.forEach((key,value)->{
			if(cover) {
				if(key instanceof String && value instanceof String) {					
					map.put(key.toString(), value.toString());
				}
			}else {
				if(key instanceof String && value instanceof String && !map.containsKey(key.toString())) {
					map.put(key.toString(), value.toString());
				}
			}
		});
		return map;
	}
	
	
	/**
	 * 将配置文件列表转为propertiesMap
	 * @param key 配置文件中可作为唯一键的字段名
	 * @param fileList
	 * @return
	 */
	public static Map<String,Properties> createPropertiesMap(String key,List<File> fileList){
		Map<String,Properties> propertiesMap = new HashMap<String,Properties>();
		for(File file:fileList) {
			if(!file.exists())continue;
			Properties properties;
			try {
				properties = getProperties(file.getPath());
				String keyValue = properties.getProperty(key);
				propertiesMap.put(keyValue, properties);
			} catch (IOException e) {
				Log.error("");//TODO
			}
		}
		Log.info("createPropertiesMap="+propertiesMap);
		return propertiesMap;
	}
	
	/**
	 * 根据后缀扫描配置文件，并创建propertiesMap
	 * @param key 配置文件中可作为唯一键的字段名
	 * @param path
	 * @param fix
	 * @return
	 */
	public static Map<String,Properties> createPropertiesMapByFix(String key,String path,String fix){
		List<File> fileList = FileUtil.searchFile(path,fix);
		Map<String,Properties> propertiesMap = createPropertiesMap(key,fileList);
		Log.info("createPropertiesMapByFix="+propertiesMap);
		return propertiesMap;
	}
	
	/**
	 * 将vo对象保存到配置文件
	 * @param dirPath 目标文件所在目录
	 * @param filePath 目标文件路径
	 * @param vo
	 * @param cover 是否覆盖已有文件
	 * @param comments
	 * @return
	 * @throws IOException 
	 */
	public static Properties createPropertiesFileByVO(String dirPath,String filePath,Object vo,boolean cover,String comments) throws IOException {
		Log.info("createPropertiesFileByVO="+vo+",\ncover="+cover);
		if(FileUtil.create(dirPath,filePath)==Sucess) {
			return saveVOtoPropertiesFile(filePath, vo, comments);
		}else {
			if(cover) return saveVOtoPropertiesFile(filePath, vo, comments);
		}
		return getProperties(filePath);
	}
	
	
	
}
