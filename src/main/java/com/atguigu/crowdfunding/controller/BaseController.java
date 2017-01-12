package com.atguigu.crowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	
	private ThreadLocal<Map<String, Object>> results = new ThreadLocal<Map<String, Object>>();
	protected void start() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		results.set(resultMap);
	}
	
	protected Object end() {
		return results.get();
	}
	
	protected void success(boolean flg) {
		Map<String, Object> resultMap = results.get();
		resultMap.put("success", flg);
	}
	
	protected void error(String msg) {
		Map<String, Object> resultMap = results.get();
		resultMap.put("error", msg);
	}
	
	protected void param(String key, Object obj) {
		Map<String, Object> resultMap = results.get();
		resultMap.put(key, obj);
	}
}
