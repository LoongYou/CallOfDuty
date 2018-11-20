package com.yu.callofduty.core;

public class BaseTargetSet {
	 /**搜索匹配长度限制*/
	private int sub_length = 1000;
	 /**搜索匹配正则*/
		private String sub_patt = "";
		 
		 /**文件名正则*/
		private String file_patt = "";
		 
		 /**目录名正则*/
		private String dir_patt = "";

		public int getSub_length() {
			return sub_length;
		}

		public void setSub_length(int sub_length) {
			this.sub_length = sub_length;
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
		
		
}
