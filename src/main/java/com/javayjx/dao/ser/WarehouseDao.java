package com.javayjx.dao.ser;

import com.javayjx.entity.ser.Storage;
import com.javayjx.entity.ser.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author hc
 * @create 2020/9/24 0024 14:53
 */
public interface WarehouseDao  extends JpaRepository<Warehouse,Integer>, JpaSpecificationExecutor<Warehouse> {

    @Query(value="select * from t_warehouse  where id = ?1",nativeQuery = true)
    public Warehouse findId(Integer id);

}
