package com.cod.desktop.ironman.constants;

/**
 * Framework常量接口
 * @author Yulong
 *
 */
public interface FrameworkContants {

	/**任务启动超时（毫秒）*/
	int task_startup_timeout = 5000;
	
	/**任务启动等待重试次数*/
	int task_startup_retryRound = 5;
	
	/**任务启动每轮等待时间（毫秒）*/
	int task_startup_waitTime = 1000;
}
