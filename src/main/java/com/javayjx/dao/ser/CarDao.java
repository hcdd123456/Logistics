package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.javayjx.entity.ser.Car;

public interface CarDao  extends JpaRepository<Car,Integer>,JpaSpecificationExecutor<Car> {
	
	@Query(value="select * from t_car  where id = ?1",nativeQuery = true)
	public Car findId(Integer id);
	
	
	
}
