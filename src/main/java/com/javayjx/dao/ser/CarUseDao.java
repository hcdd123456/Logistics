package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.CarUse;

public interface CarUseDao extends JpaRepository<CarUse,Integer>,JpaSpecificationExecutor<CarUse> {
	
	@Query(value="select * from t_car_use  where id = ?1",nativeQuery = true)
	public CarUse findId(Integer id);

}
