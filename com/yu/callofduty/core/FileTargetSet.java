package com.yu.callofduty.core;

/**
 * 封装文件作业参数
 * @author yl
 *
 */
public class FileTargetSet extends BaseTargetSet{

	/**文件名结尾（含后缀）*/
	private String file_end;

	/**文件名开头*/
	private String file_start;

	/**文件名包含*/
	private String file_like;

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
	
}
