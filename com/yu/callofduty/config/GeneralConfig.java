package com.yu.callofduty.config;

public class GeneralConfig {
	/**处理目录*/
	private static String file_path = "C:\\workspace2\\CallOfDuty\\testfile";
	
	/**保存xml的路径*/
	private static String xml_path = "";

	public static String getFile_path() {
		return file_path;
	}

	public static void setFile_path(String file_path) {
		GeneralConfig.file_path = file_path;
	}

	public static String getXml_path() {
		return xml_path;
	}

	public static void setXml_path(String xml_path) {
		GeneralConfig.xml_path = xml_path;
	}	
	
}
