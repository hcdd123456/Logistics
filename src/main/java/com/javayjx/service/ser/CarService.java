package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;
import com.javayjx.entity.ser.Car;

public interface CarService {
	
	
	
	public Integer update(Car car        );
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Car> list(Map<String,Object> map,Integer page,Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);
	
	
	
	
}
