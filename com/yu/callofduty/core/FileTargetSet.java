package com.yu.callofduty.core;

public class FileTargetSet extends BaseTargetSet{

	/**文件名结尾（含后缀）*/
	private String file_end = ".java";

	/**文件名开头*/
	private String file_start = "";

	/**文件名包含*/
	private String file_like = "";
	 
	/**目录名结尾（含后缀）*/
	private String dir_end = "";

	/**目录名开头*/
	private String dir_start = "";

	/**目录名包含*/
	private String dir_like = "";
	 
	 /**搜索匹配长度限制*/
	private int sub_length = 1000;
	 
	 /**递归目录最大层数*/
	private int dir_deep = 20;

	public String getFile_end() {
		return file_end;
	}

	public void setFile_end(String file_end) {
		this.file_end = file_end;
	}

	public String getFile_start() {
		return file_start;
	}

	public void setFile_start(String file_start) {
		this.file_start = file_start;
	}

	public String getFile_like() {
		return file_like;
	}

	public void setFile_like(String file_like) {
		this.file_like = file_like;
	}

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

	public int getSub_length() {
		return sub_length;
	}

	public void setSub_length(int sub_length) {
		this.sub_length = sub_length;
	}

	public int getDir_deep() {
		return dir_deep;
	}

	public void setDir_deep(int dir_deep) {
		this.dir_deep = dir_deep;
	}

	
	
}
