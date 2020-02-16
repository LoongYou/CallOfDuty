package com.cod.desktop.ironman.framework.base;

import java.util.function.Supplier;

import org.apache.log4j.Logger;

import com.cod.desktop.ironman.configuration.PropertiesConfigVo;
import com.cod.desktop.ironman.framework.task.Task;
import com.cod.util.Log;

public abstract class BaseGUITask<P extends PropertiesConfigVo,T> extends ConsumerExecutor<T> implements GUI,Log {
	
	/**主界面*/
	private T frame;
	
	private Supplier<T> frameSupplier;
	
	private boolean ready = false;
	
	protected P propertiesConfigVo;
	
	public BaseGUITask() {}
	
	@Override
	public void run() {
		frame = (T)Task.create(frameSupplier,(PropertiesConfigVo)propertiesConfigVo);
		Info("create frame=",frame);
		ready = true;
		super.run();
		
	}
	
	public T getFrame() {
		return frame;
	}
	
	protected final void setFrame(Supplier<T> supplier) {
		this.frameSupplier = supplier;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	@SuppressWarnings("unchecked")
	public void setPropertiesConfigVo(PropertiesConfigVo propertiesConfigVo) {
		this.propertiesConfigVo = (P) propertiesConfigVo;
	}
	
	/**
	 * 打开UI界面
	 * 当主程序侦听到插件选项被双击启动，默认会掉用此方法以显示插件的UI，
	 * 形式包括但不限于如显示菜单，显示工具栏，显示设置界面，显示tab窗口等等
	 */
	public abstract void openUI();
	
	/**
	 * 关闭UI界面
	 * 当主程序侦听到插件选项被选择关闭，默认会调用此方法以关闭插件的UI，
	 * 你可以决定需要关闭的插件的UI和形式
	 */
	public abstract void closeUI();
	
	
	
	
	
	
}
