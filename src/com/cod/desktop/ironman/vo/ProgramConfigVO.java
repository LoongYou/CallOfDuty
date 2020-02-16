package com.cod.desktop.ironman.vo;

import com.cod.desktop.ironman.configuration.PropertiesConfigVo;

/**
 * 程序配置VO
 * @author Yulong
 *
 */
public class ProgramConfigVO implements PropertiesConfigVo{
	
	/**工作路径*/
	private String workPath;
	/**最后启动时间*/
	private String lastStartTime;
	/**最后关闭时间*/
	private String lastCloseTime;
	/**运行时长*/
	private String lastRunTime;
	/**默认启动任务*/
	private String defaultStartUpTask;
	/**日志路径*/
	private String logPath;
	/**插件启动超时时间*/
	private String pluginStartTimeout;
	/**运行时数据大小*/
	private String memoryStoreSize;
	public String getWorkPath() {
		return workPath;
	}
	public void setWorkPath(String workPath) {
		this.workPath = workPath;
	}
	public String getLastStartTime() {
		return lastStartTime;
	}
	public void setLastStartTime(String lastStartTime) {
		this.lastStartTime = lastStartTime;
	}
	public String getLastCloseTime() {
		return lastCloseTime;
	}
	public void setLastCloseTime(String lastCloseTime) {
		this.lastCloseTime = lastCloseTime;
	}
	public String getLastRunTime() {
		return lastRunTime;
	}
	public void setLastRunTime(String lastRunTime) {
		this.lastRunTime = lastRunTime;
	}
	public String getDefaultStartUpTask() {
		return defaultStartUpTask;
	}
	public void setDefaultStartUpTask(String defaultStartUpTask) {
		this.defaultStartUpTask = defaultStartUpTask;
	}
	public String getLogPath() {
		return logPath;
	}
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	public String getPluginStartTimeout() {
		return pluginStartTimeout;
	}
	public void setPluginStartTimeout(String pluginStartTimeout) {
		this.pluginStartTimeout = pluginStartTimeout;
	}
	public String getMemoryStoreSize() {
		return memoryStoreSize;
	}
	public void setMemoryStoreSize(String memoryStoreSize) {
		this.memoryStoreSize = memoryStoreSize;
	}
	
	
	
}
