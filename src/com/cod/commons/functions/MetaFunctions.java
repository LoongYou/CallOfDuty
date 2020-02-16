package com.cod.commons.functions;

public interface MetaFunctions {
	static <T> boolean getIsMatchClass(Class<T>[] classList,Object object) {
		if(classList!=null && object!=null) {
			for(Class<T> Class:classList) {
				if(Class!=null && object.getClass()==Class) {
					return true;
				}
			}
		}
		return false;
	}
}
