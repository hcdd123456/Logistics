package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.CarApp;

public interface CarAppDao extends JpaRepository<CarApp,Integer>,JpaSpecificationExecutor<CarApp> {
	
	@Query(value="select * from t_car_app  where id = ?1",nativeQuery = true)
	public CarApp findId(Integer id);
	
}
