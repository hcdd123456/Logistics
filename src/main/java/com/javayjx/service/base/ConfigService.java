package com.javayjx.service.base;

import com.javayjx.entity.base.Config;

public interface ConfigService {
	
	public Integer update(Config config);
	
	/**
	 * init 方法用这个
	 */
	public Config findById(Integer id);
	
	
}
