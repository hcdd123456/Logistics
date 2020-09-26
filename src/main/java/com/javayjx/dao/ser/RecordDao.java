package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.Record;

public interface RecordDao extends JpaRepository<Record,Integer>,JpaSpecificationExecutor<Record> {
	
	
	@Query(value="select * from t_record  where id = ?1",nativeQuery = true)
	public Record findId(Integer id);
	
	
}
