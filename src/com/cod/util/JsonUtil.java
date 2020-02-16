package com.cod.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	public static<T> T jsonToObject(String jsonString,Class<T> clazz) {
		return JSON.parseObject(jsonString, clazz);
	}
}
