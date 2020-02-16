package com.cod.desktop.ironman.configuration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cod.desktop.ironman.main.handler.MainTask;
import com.cod.desktop.ironman.vo.PluginConfigVO;
import com.cod.desktop.ironman.vo.ProgramConfigVO;
import com.cod.util.FileUtil;
import com.cod.util.PropertiesUtil;
import com.cod.util.VoUtil;

public final class Configurations {
	private static Logger logger = Logger.getLogger(Configurations.class);
	
	/*----------------------------------目录常量----------------------------------*/
	/**程序的配置文件名*/
	public static final String PRO_PROPERTIES_NAME = "cod.properties";
	
	/**程序的日志文件名称*/
	public static final String PRO_LOG_NAME = "cod.log";
	
	/**日志的根目录文件夹名称*/
	public static final String LOG_ROOT_NAME = "codLogs";
	
	/**程序根文件夹名称*/
	public static final String PRO_ROOT_NAME = "CallOfDuty";
	
	/**插件配置文件夹名称*/
	public static final String PLUGIN_ROOT_NAME = "plugins";
	
	/**插件配置文件后缀*/
	public static final String PLUGIN_CONFIG_FIX = "_plugin.properties";
	
	/**用户主文件夹*/
	public static final String SYSTEM_USERHOME_PATH = System.getProperties().getProperty("user.home");
	
	/**默认程序配置路径*/
	public static final String PRO_ROOT_PATH = SYSTEM_USERHOME_PATH + "\\" + PRO_ROOT_NAME;
	
	/**备用程序配置路径*/
	public static final String PRO_SYSTEM_ROOT_PATH = "D:\\"+PRO_ROOT_NAME;
	
	/**实际工作根文件夹*/
	static String WORK_ROOT_PATH;
	
	
	/*-----------------------------------------------配置常量项----------------------------------*/
	/**初始化状态*/
	static int initStatus = 0;
	
	/**插件配置集合*/
	static Map<String,Properties> pluginMap;
	
	/**
	 * 获取当前程序配置
	 * @param propramPropertiesName
	 * @return
	 */
	public static String getProgramPropertiesPath(String propramPropertiesName) {
		return WORK_ROOT_PATH+"\\"+propramPropertiesName;
	}
	
	/**
	 * 获取当前插件配置路径
	 * @param defaultLoadTask 如果为空，则返回插件根目录
	 * @return
	 */
	public static String getPluginPropertiesPath(String defaultLoadTask) {
		if(defaultLoadTask!=null) {
			return WORK_ROOT_PATH+"\\"+PLUGIN_ROOT_NAME+"\\"+defaultLoadTask+PLUGIN_CONFIG_FIX;
		}else {
			return WORK_ROOT_PATH+"\\"+PLUGIN_ROOT_NAME+"\\";
		}
	}
	
	/***
	 * 获取日志配置路径
	 * @param programLogsName 如果为空，则返回日志根目录
	 * @return
	 */
	public static String getProgramLogsPath(String programLogsName) {
		return WORK_ROOT_PATH+"\\"+LOG_ROOT_NAME+"\\"+programLogsName;
	}
	
	
	/**
	 * 获取系统配置，默认在user.homg中的程序根文件夹下获取cod.properties文件，如果没有权限，则在D：\\程序根文件夹下创建并获取，
	 * 获取不到则创建新的配置文件，如果是首次创建，则写入默认配置项，否则不覆盖并返回对应的配置对象
	 * @throws IOException 
	 */
	static void initProgramProperties() throws IOException {
		//TODO 改为系统磁盘编号，以兼容linux系统
		String comments = "The program will create properties at the root of user dir when time of first,"
				+ "if create failed(maybe the haven't authority),it will be create at D:\\\\";
		WORK_ROOT_PATH = PRO_ROOT_PATH;
		logger.info("PRO_PROPERTIES_PATH="+WORK_ROOT_PATH);
		try {
			setProgramProperties(null,false,comments);
		}catch(IOException e1) {
			logger.error("create program file at "+WORK_ROOT_PATH+" is failed:",e1);
			WORK_ROOT_PATH = PRO_SYSTEM_ROOT_PATH;
			logger.error("change default program properties path to "+WORK_ROOT_PATH);
			try {
				setProgramProperties(null,false,comments);
			}catch(IOException e) {
				logger.error("create program file at "+WORK_ROOT_PATH+" is failed:",e);
				throw e;
			}
		}
	}
	
	/**
	 * 创建程序配置文件
	 * @param programConfigVO 为null则创建配置文件
	 * @param cover 配置文件已存在的情况下，是否覆盖，若文件不存在，VO不可为空
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	static Properties setProgramProperties(ProgramConfigVO programConfigVO, boolean cover, String comments) throws IOException {
		logger.info("programConfigVO="+programConfigVO+",\ncover="+cover);
		String programPropertiesPath = getProgramPropertiesPath(PRO_PROPERTIES_NAME);
		if(programConfigVO==null) {
			programConfigVO = new ProgramConfigVO();
			programConfigVO.setWorkPath(WORK_ROOT_PATH);
			programConfigVO.setLastStartTime(null);
			programConfigVO.setLogPath(getProgramLogsPath(PRO_LOG_NAME));
			programConfigVO.setDefaultStartUpTask("");
			logger.info("create new programConfigVO="+programConfigVO);
		}
		return PropertiesUtil.createPropertiesFileByVO(WORK_ROOT_PATH, programPropertiesPath, programConfigVO, cover, comments);
	}
	
	/**
	 * 获取程序配置文件VO
	 * @return
	 */
	public static ProgramConfigVO getProgramConfigVO() {
		Properties properties = PropertiesUtil.getProperties(getProgramPropertiesPath(PRO_PROPERTIES_NAME));
		logger.info("properties="+properties);
		return VoUtil.getPropertiesToVO(new ProgramConfigVO(), properties);
	}
	
	/**
	 * 设置插件配置
	 * @param pluginConfigVO
	 * @param cover
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	public static Properties setPluginProperties(PluginConfigVO pluginConfigVO,boolean cover,String comments) throws IOException {
		logger.info("pluginConfigVO="+pluginConfigVO+",\ncover="+cover);
		String pluginRootPath = getPluginPropertiesPath(null);
		String pluginPropertiesPath = getPluginPropertiesPath(pluginConfigVO.getDefaultLoadTask());
		return PropertiesUtil.createPropertiesFileByVO(pluginRootPath, pluginPropertiesPath, pluginConfigVO, cover, comments);
	}
	
	/**
	 * 创建插件自身目录
	 * @param pluginName
	 * @return
	 * @throws IOException 
	 */
	public static boolean createPluginSelfDir(String pluginName) throws IOException {
		String pluginSelfPath = getPluginPropertiesPath(null)+pluginName;
		logger.info("pluginSelfPath="+pluginSelfPath);
		if(FileUtil.create(pluginSelfPath, false)==1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 获取插件自身目录
	 * @param pluginName
	 * @return 如果目录不存在则返回null
	 */
	public static String getPluginSelfDir(String pluginName) {
		String pluginSelfPath = getPluginPropertiesPath(null)+pluginName;
		logger.info("pluginSelfPath="+pluginSelfPath);
		File file = new File(pluginSelfPath);
		if(file.exists()) {
			return pluginSelfPath;
		}else {
			return null;
		}
	}
	
	/**
	 * 获取插件配置文件VO
	 * @param defaultLoadTask
	 * @return
	 */
	public static PluginConfigVO getPluginConfigVO(String defaultLoadTask) {
		Properties properties;
		properties = PropertiesUtil.getProperties(getPluginPropertiesPath(defaultLoadTask));
		logger.info("properties="+properties);
		return VoUtil.getPropertiesToVO(new PluginConfigVO(), properties);
	}
	
	/**
	 * 
	 * @param properties
	 * @return
	 */
	public static PluginConfigVO getPluginConfigVO(Properties properties) {
			logger.info("properties="+properties);
			return VoUtil.getPropertiesToVO(new PluginConfigVO(), properties);
	}
	
	/**
	 * 扫描插件配置
	 */
	static void setPluginMap() {
		pluginMap = PropertiesUtil.createPropertiesMapByFix("defaultLoadTask", getPluginPropertiesPath(null), PLUGIN_CONFIG_FIX);
		logger.info("setPluginMap="+pluginMap);
	}
	
	
	/**
	 * 用于提供Main类获取插件清单
	 * @param main
	 * @return
	 */
	public static Map<String,Properties> getPluginPropertiesMap(MainTask main){
		if(main!=null) {
			return pluginMap;
		}else {
			return null;
		}
	}
	
	/**
	 * 初始化配置，应当确保插件的配置类初始化前执行该方法初始化
	 * @throws IOException
	 */
	public static void initConfigurations() throws IOException {
		if(initStatus==0) {
			initProgramProperties();
			setPluginMap();
			initStatus++;
			logger.info("system init finish");
		}else {
			logger.info("system has been init before");
		}
	}
}
