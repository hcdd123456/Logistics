package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;

import com.javayjx.entity.ser.Order;

public interface OrderService {
	
	public Integer update(Order order  );
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Order> list(Map<String,Object> map,Integer page,Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);
	
	
}
