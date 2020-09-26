package com.javayjx.service.ser;

import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.StaffSalary;

import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/26 0026 22:25
 */
public interface StaffSalaryService {
    List<StaffSalary> list(Map<String,Object> map, Integer page, Integer pageSize);

    Long getTotal(Map<String, Object> map);

    void save(StaffSalary staffSalary);

    void deleteById(int parseInt);

    StaffSalary findId(Integer id);

    void update(StaffSalary staffSalary);
}
