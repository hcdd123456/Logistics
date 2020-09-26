package com.javayjx.service.ser;

import java.util.List;
import java.util.Map;

import com.javayjx.entity.ser.Client;

public interface ClientService {
	
	
	public Integer update(Client client     );
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Client> list(Map<String,Object> map,Integer page,Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);
	
	
	
}
