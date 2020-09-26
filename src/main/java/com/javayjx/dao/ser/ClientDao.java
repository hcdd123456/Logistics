package com.javayjx.dao.ser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.javayjx.entity.ser.Client;


public interface ClientDao  extends JpaRepository<Client,Integer>,JpaSpecificationExecutor<Client> {
	
	@Query(value="select * from t_client  where id = ?1",nativeQuery = true)
	public Client findId(Integer id);

}
