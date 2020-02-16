package com.yu.callofduty.core;

/**
 * 封装目录作业参数
 * @author yl
 *
 */
public class DirTargetSet extends BaseTargetSet{
	/**目录名结尾（含后缀）*/
	private String dir_end;

	/**目录名开头*/
	private String dir_start;

	/**目录名包含*/
	private String dir_like;
	 
	 /**递归目录最大层数*/
	private int dir_deep = 20;

	public String getDir_end() {
		return dir_end;
	}

	public void setDir_end(String dir_end) {
		this.dir_end = dir_end;
	}

	public String getDir_start() {
		return dir_start;
	}

	public void setDir_start(String dir_start) {
		this.dir_start = dir_start;
	}

	public String getDir_like() {
		return dir_like;
	}

	public void setDir_like(String dir_like) {
		this.dir_like = dir_like;
	}

	public int getDir_deep() {
		return dir_deep;
	}

	public void setDir_deep(int dir_deep) {
		this.dir_deep = dir_deep;
	}
	
	
}
