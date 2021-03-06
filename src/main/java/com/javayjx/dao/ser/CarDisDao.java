package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.CarDis;

public interface CarDisDao  extends JpaRepository<CarDis,Integer>,JpaSpecificationExecutor<CarDis> {
	
	@Query(value="select * from t_car_dis  where id = ?1",nativeQuery = true)
	public CarDis findId(Integer id);
	

}
