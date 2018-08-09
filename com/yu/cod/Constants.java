package com.yu.cod;

/**
 * 
 * @author yl
 * @2018年8月9日下午9:52:31
 */
public interface Constants {
	/**处理目录*/
	 String file_path = "C:\\Users\\yl\\Documents\\resource";

	/**文件名结尾（含后缀）*/
	 String file_end = ".txt";

	/**文件名开头*/
	 String file_start = "";

	/**文件名包含*/
	 String file_like = "";
	
	/**关键字开头*/
	 String word_start = "北";

	/**关键字结尾*/
	 String word_end = "市";

	/**关键字包含*/
	 String word_like = "男";
	
	/**间隔字符*/
	 char[] char_split = {'\'',' '};

	/**间隔字符串*/
	 String[] word_split = {};

	/**是否便利间隔元组，通常情况下为true，除非你十分确定元组的第一个元素能够首次命中，
	否则可能导致长时间运行并且得不到预想的结果*/
	 boolean splitAll = true;
	
	/**保存xml的路径*/
	 String xml_path = "";

	/**查找元素标签名*/
	 String xml_tag = "item"; 
	
	 /**搜索匹配正则*/
	 String sub_patt = "";
	 
	 /**文件名正则*/
	 String file_patt = "";
	 
	 /**搜索匹配长度限制*/
	 int sub_length = 100;
}
