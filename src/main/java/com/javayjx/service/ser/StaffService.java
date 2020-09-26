package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;

import com.javayjx.entity.ser.Staff;

public interface StaffService {
	
	public Integer update(Staff staff);
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Staff> list(Map<String,Object> map,Integer page,Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);

     public Staff findid(Integer id);
}
