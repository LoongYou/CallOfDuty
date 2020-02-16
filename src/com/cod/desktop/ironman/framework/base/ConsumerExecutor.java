package com.cod.desktop.ironman.framework.base;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import com.cod.desktop.ironman.framework.task.Shutdownable;
import com.cod.util.Log;

/**
 * 消费执行器ConsumerExecutor以睡眠——唤醒交替的模式运行，用于替换swing侦听器所在的EDT线程，完成对GUI的操作，
 * 避免因后台线程hold主导致UI卡顿，从而保证UI响应，增强体验。由于该线程有睡眠状态，在用于执行事实任务或定时任将无法得到保证。
 * @author Yulong
 *
 */
public class ConsumerExecutor<T> implements Runnable,Shutdownable,Log {
	private ConcurrentLinkedQueue<ConsumerDTO<T>> consumerQueue = new ConcurrentLinkedQueue<ConsumerDTO<T>>();
	
	/**终止信号*/
	private int shutdown;
	
	
	@Override
	public void run() {
		Info("ConsumerExecutor start");
		while(shutdown==0) {
			ConsumerDTO<?> dto = consumerQueue.poll();
			Info("poll dto="+dto);
			if(dto!=null) {
				Consumer<Object> consumer = (Consumer<Object>) dto.getConsumer();
				Object param = dto.getParam();
				Info("consumerQueue poll="+consumer+",param="+param);
				if(consumer!=null) {
					consumer.accept(param);
				}
			}else {
				Info("wait",Thread.currentThread().getId());
				synchronized (this) {
					try {
						this.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void addConsumer(ConsumerDTO<T> dto) {
		Info("ConsumerDTO="+dto);
		this.consumerQueue.offer(dto);
	}
	
	/**
	 * 添加一个消费类型方法到消费队列，ConsumerExecutor线程被唤醒的时候将会执行它
	 * @param consumer
	 * @param param
	 */
	public void addConsumer(Consumer<T> consumer,Object param) {
		Info("consumer=",consumer,"param="+param);
		ConsumerDTO<T> dto = new ConsumerDTO<T>();
		dto.setConsumer(consumer);
		dto.setParam(param);
		addConsumer(dto);
	}
	
	public void addConsumer(Consumer<T> consumer) {
		Info("consumer=",consumer);
		ConsumerDTO<T> dto = new ConsumerDTO<T>();
		dto.setConsumer(consumer);
		dto.setParam(null);
		addConsumer(dto);
	}
	
	
	@Override
	public void shutdown() {
		shutdown = 1;
		synchronized(this) {
			Info("ConsumerExecutor will shutdown");
			this.notify();
		}
	}
	
	
	
	
	
	
	
	
}
