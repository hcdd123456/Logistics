package com.javayjx.dao.ser;

import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.Staff;
import com.javayjx.entity.ser.StaffSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author hc
 * @create 2020/9/26 0026 22:26
 */
public interface StaffSalaryDao extends JpaRepository<StaffSalary,Integer>, JpaSpecificationExecutor<StaffSalary> {

    @Query(value="select * from t_staff_salary  where id = ?1",nativeQuery = true)
    public StaffSalary findId(Integer id);

}
