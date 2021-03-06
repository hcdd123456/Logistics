package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;
import com.javayjx.entity.ser.CarApp;

public interface CarAppService {
	
	public Integer update(CarApp carApp );
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<CarApp> list(Map<String,Object> map,Integer page,Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);
}
