package com.yu.callofduty.core;

/**
 * 封装文本作业参数
 * @author yl
 *
 */
public class TextTargetSet extends BaseTargetSet{
	
	/**关键字开头*/
	private String word_start;

	/**关键字结尾*/
	private String word_end;

	/**关键字包含*/
	private String word_like;
	
	/**间隔字符*/
	private char[] char_split;

	/**间隔字符串*/
	private String[] word_split;

	/**是否遍历间隔元组，通常情况下为true，除非你十分确定元组的第一个元素能够在短距离内命中，
	否则可能导致长时间运行，并且得不到预想的结果*/
	private boolean splitAll = true;

	/**查找元素标签名*/
	private String xml_tag;

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

	public String getXml_tag() {
		return xml_tag;
	}

	public void setXml_tag(String xml_tag) {
		this.xml_tag = xml_tag;
	} 

	 
}
