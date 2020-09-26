package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;

import com.javayjx.entity.ser.CarDis;

public interface CarDisService {

	public Integer update(CarDis carDis);

	/**
	 * @param map
	 * @param page     从0开始
	 * @param pageSize
	 */
	public List<CarDis> list(Map<String, Object> map, Integer page, Integer pageSize);

	public Long getTotal(Map<String, Object> map);

}
