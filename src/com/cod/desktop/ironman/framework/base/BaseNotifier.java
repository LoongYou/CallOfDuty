package com.cod.desktop.ironman.framework.base;

import com.cod.desktop.ironman.framework.task.TaskRule;
import com.cod.util.LogExtenral;

/**
 * 基础的Notifier接口实现类。如果后台任务耗时较长，建议都采用异步的方式通知，以保证良好的响应速度。
 * 固定线程数情况下（线程数无自增量）建议使用SwingWork提供的方法实现同时
 * @author Yulong
 *
 */
public interface BaseNotifier extends Notifier,LogExtenral{
	
	default void SyncTodo(Class<?> handler) {
		Info("handler=",handler.getName());
	}
	
	default void SyncTodo(Class<?>[] handlers) {
		for(Class<?> name:handlers) {
			SyncTodo(name);
		}
	}
	
	default void AsyncTodo(Class<?> handler) {
		TaskRule.putThread((匆匆那年我们见过太少世面只爱看同一张脸)->{
			new SwingWorker<String,String>(){
				
				protected String doInBackground() {
					TaskRule.putId();
					Info("Async invoke handler="+handler.getName());
					return null;
				}
			}.execute();
		});
	}
	
	
	default void AsyncToDo(Class<?>[] handlers) {
		for(Class<?> name:handlers) {
			AsyncTodo(name);
		}
	}
	
	/**
	 * 根据给定的模式（异步、同步），通知handler
	 * @param handler
	 * @param mode
	 */
	default TODO(Class<?>handler,int mode) {
		switch(mode) {
		case ASYNC:AsyncTodo(handler);break;
		case SYNC:SyncTodo(handler);break;
		}
		
	}
	
	
}
