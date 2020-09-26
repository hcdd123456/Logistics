package com.javayjx.service.base;

import com.javayjx.entity.base.RoleMenu;

public interface RoleMenuService {
	
	public Integer add(RoleMenu roleMenu);
	
	
	public RoleMenu findByRoleIdAndMenuId(Integer roleId,Integer menuId);
	
	
	public Integer deleteByRoleId(Integer roleId);
	
	
}
