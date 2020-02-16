package com.cod.desktop.ironman.framework.base;

/**
 * 通常一个task里面包含一个GUI和WORK，即界面和后台程序分离模式。
 * 实现该模式有利于设计出界面和后台清晰的交互结构，若开发的插件不需要界面，
 * 或者仅有非常简单的界面，可以不遵循该模式
 * @author Yulong
 *
 */
public interface Assignment {

	GUI getGUI();
	
	Work getWork();
}
