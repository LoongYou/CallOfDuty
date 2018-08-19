package com.yu.callofduty.core;

public class TargetSet {
	/**处理目录*/
	private String file_path = "D:\\workspace2\\CallOfDuty\\testfile";

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
	
	/**关键字开头*/
	private String word_start = "import";

	/**关键字结尾*/
	private String word_end = "}";

	/**关键字包含*/
	private String word_like = "new";
	
	/**间隔字符*/
	private char[] char_split = {};

	/**间隔字符串*/
	private String[] word_split = {";"};

	/**是否遍历间隔元组，通常情况下为true，除非你十分确定元组的第一个元素能够在短距离内命中，
	否则可能导致长时间运行，并且得不到预想的结果*/
	private boolean splitAll = true;
	
	/**保存xml的路径*/
	private String xml_path = "";

	/**查找元素标签名*/
	private String xml_tag = "item"; 
	
	 /**搜索匹配正则*/
	private String sub_patt = "";
	 
	 /**文件名正则*/
	private String file_patt = "";
	 
	 /**目录名正则*/
	private String dir_patt = "";
	 
	 /**搜索匹配长度限制*/
	private int sub_length = 1000;
	 
	 /**递归目录最大层数*/
	private int dir_deep = 20;

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

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

	public String getWord_start() {
		return word_start;
	}

	public void setWord_start(String word_start) {
		this.word_start = word_start;
	}

	public String getWord_end() {
		return word_end;
	}

	public void setWord_end(String word_end) {
		this.word_end = word_end;
	}

	public String getWord_like() {
		return word_like;
	}

	public void setWord_like(String word_like) {
		this.word_like = word_like;
	}

	public char[] getChar_split() {
		return char_split;
	}

	public void setChar_split(char[] char_split) {
		this.char_split = char_split;
	}

	public String[] getWord_split() {
		return word_split;
	}

	public void setWord_split(String[] word_split) {
		this.word_split = word_split;
	}

	public boolean isSplitAll() {
		return splitAll;
	}

	public void setSplitAll(boolean splitAll) {
		this.splitAll = splitAll;
	}

	public String getXml_path() {
		return xml_path;
	}

	public void setXml_path(String xml_path) {
		this.xml_path = xml_path;
	}

	public String getXml_tag() {
		return xml_tag;
	}

	public void setXml_tag(String xml_tag) {
		this.xml_tag = xml_tag;
	}

	public String getSub_patt() {
		return sub_patt;
	}

	public void setSub_patt(String sub_patt) {
		this.sub_patt = sub_patt;
	}

	public String getFile_patt() {
		return file_patt;
	}

	public void setFile_patt(String file_patt) {
		this.file_patt = file_patt;
	}

	public String getDir_patt() {
		return dir_patt;
	}

	public void setDir_patt(String dir_patt) {
		this.dir_patt = dir_patt;
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
