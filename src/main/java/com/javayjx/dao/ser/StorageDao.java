package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.Storage;



public interface StorageDao  extends JpaRepository<Storage,Integer>,JpaSpecificationExecutor<Storage> {
	
	@Query(value="select * from t_storage  where id = ?1",nativeQuery = true)
	public Storage findId(Integer id);
}
