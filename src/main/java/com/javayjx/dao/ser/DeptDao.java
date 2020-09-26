package com.javayjx.dao.ser;

import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author hc
 * @create 2020/9/26 0026 17:16
 */
@Mapper
public interface DeptDao extends JpaRepository<Dept,Integer>, JpaSpecificationExecutor<Dept> {

    @Query(value="select * from t_dept  where id = ?1",nativeQuery = true)
    public Dept findId(Integer id);

}
