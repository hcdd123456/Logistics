package com.javayjx.dao.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.javayjx.entity.base.Menu;


public interface MenuDao extends JpaRepository<Menu,Integer>,JpaSpecificationExecutor<Menu> {
	
	@Query(value="select * from t_menu where id = ?1",nativeQuery = true)
	public Menu findId(Integer id);
	
	public  List<Menu>  findByPId(Integer pId);
	
}
