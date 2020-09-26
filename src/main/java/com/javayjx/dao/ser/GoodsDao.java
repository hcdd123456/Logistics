package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.javayjx.entity.ser.Goods;

public interface GoodsDao extends JpaRepository<Goods,Integer>,JpaSpecificationExecutor<Goods> {
	
	@Query(value="select * from t_goods  where id = ?1",nativeQuery = true)
	public Goods findId(Integer id);

}
