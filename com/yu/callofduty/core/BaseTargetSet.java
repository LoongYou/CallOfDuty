package com.yu.callofduty.core;

/**
 * 封装作业基础参数
 * @author yl
 *
 */
public class BaseTargetSet {
	
	/** 搜索匹配长度限制 */
	private int sub_length = 1000;
	
	/** 搜索匹配正则 */
	private String patt_search = "";

	/** 名称正则 */
	private String patt_name = "";

	public int getSub_length() {
		return sub_length;
	}

	public void setSub_length(int sub_length) {
		this.sub_length = sub_length;
	}

	public String getPatt_search() {
		return patt_search;
	}

	public void setPatt_search(String patt_search) {
		this.patt_search = patt_search;
	}

	public String getPatt_name() {
		return patt_name;
	}

	public void setPatt_name(String patt_name) {
		this.patt_name = patt_name;
	}

}
