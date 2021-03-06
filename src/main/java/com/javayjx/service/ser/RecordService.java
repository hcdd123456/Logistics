package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.javayjx.entity.ser.Record;

public interface RecordService {
	
	void add(HttpSession session,Integer type,String content);
	

	/**
	 * @param map
	 * @param page     从0开始
	 * @param pageSize
	 */
	public List<Record> list(Map<String, Object> map, Integer page, Integer pageSize);

	public Long getTotal(Map<String, Object> map);
	
	
}
