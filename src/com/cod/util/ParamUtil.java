package com.cod.util;

/**
 * 参数校验接口
 * @author Yulong
 *
 */
public interface ParamUtil {
	
	default public boolean paramsIsNull(Object ...params) {
		if(params==null) {
			return true;
		}
		if(params.length==0) {
			return true;
		}
		for(Object param:params) {
			if(param==null) {
				return true;
			}
		}
		return false;
	}
	
}
