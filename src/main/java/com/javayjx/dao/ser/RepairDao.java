package com.javayjx.dao.ser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.Repair;


public interface RepairDao extends JpaRepository<Repair,Integer>,JpaSpecificationExecutor<Repair> {
	
	
	@Query(value="select * from t_repair  where id = ?1",nativeQuery = true)
	public Repair findId(Integer id);
	
	
}