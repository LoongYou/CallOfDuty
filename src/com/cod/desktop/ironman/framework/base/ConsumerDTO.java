package com.cod.desktop.ironman.framework.base;

import java.util.function.Consumer;

/**
 * 任务消费对象
 * @author Yulong
 *
 * @param <T>
 */
public class ConsumerDTO<T extends Object> {

	/**消费方法*/
	private Consumer<T> consumer;
	
	/**被消费对象*/
	private Object param;

	public Consumer<T> getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsumerDTO [consumer=");
		builder.append(consumer);
		builder.append(", param=");
		builder.append(param);
		builder.append("]");
		return builder.toString();
	}
}
