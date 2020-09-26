package com.javayjx.dao.ser;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.Staff;

public interface StaffDao  extends JpaRepository<Staff,Integer>,JpaSpecificationExecutor<Staff> {
	
	@Query(value="select * from t_staff  where id = ?1 order by ?#{#amount} ", nativeQuery = true)
	public Staff findId(Integer id);

//	@Query(value="select * from t_staff  where id = ?1 order by ?#{#amount} ", nativeQuery = true)
//	public Staff findid(Integer id);

}
