package com.yu.callofduty.project.general;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DirManager {
	private ConcurrentHashMap<String,Map> dirSet;

	
	private static DirManager dm;
	private DirManager() {
		dm.dirSet = new ConcurrentHashMap<String,Map>(100);
	}
	
	public static DirManager getInstance() {
		if(dm==null) {
			dm = new DirManager();			
		}
		return dm;
	}
	
	public void destroy() {
		if(dirSet!=null) {
			dirSet.clear();			
		}
		dirSet=null;
		dm = null;
	}
	
	public void reset() {
		if(dirSet!=null) {
			dirSet.clear();			
		}
		dm.dirSet = new ConcurrentHashMap<String,Map>(100);
	}
	
	public void addDir(String dir,Map subDir) {
		dm.dirSet.put(dir, subDir);
	}
	
	public void delDir(String dir) {
		dm.dirSet.remove(dir);
	}
}
