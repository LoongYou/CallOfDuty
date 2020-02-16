package com.cod.desktop.ironman.vo;

import com.cod.desktop.ironman.configuration.PropertiesConfigVo;

/**
 * 通用插件配置VO
 * @author yl
 *
 */
public class PluginConfigVO implements PropertiesConfigVo{
	
	/**插件名（类名，包括路径）*/
	private String pluginName;
	/**描述*/
	private String description;
	/**默认启动类*/
	private String defaultLoadTask;
	/**是否随主程序启动*/
	private String loadOnProgramStartup;
	/**是否启用*/
	private String enable;
	/**最后启动时间*/
	private String lastStartTime;
	/**最后关闭时间*/
	private String lastCloseTime;
	/**运行时长*/
	private String lastRunTime;
	/**允许多开*/
	private String allowMultiRuning;
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDefaultLoadTask() {
		return defaultLoadTask;
	}
	public void setDefaultLoadTask(String defaultLoadTask) {
		this.defaultLoadTask = defaultLoadTask;
	}
	public String getLoadOnProgramStartup() {
		return loadOnProgramStartup;
	}
	public void setLoadOnProgramStartup(String loadOnProgramStartup) {
		this.loadOnProgramStartup = loadOnProgramStartup;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
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
	public String getAllowMultiRuning() {
		return allowMultiRuning;
	}
	public void setAllowMultiRuning(String allowMultiRuning) {
		this.allowMultiRuning = allowMultiRuning;
	}
	
	
}
