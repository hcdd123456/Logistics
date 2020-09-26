package com.javayjx.service.ser;

import com.javayjx.entity.ser.Client;
import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.Warehouse;

import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/26 0026 17:17
 */
public interface DeptService {
    List<Dept> list(Map<String,Object> map, Integer page, Integer pageSize);

    Long getTotal(Map<String, Object> map);

    void save(Dept dept);

    void deleteById(int parseInt);
    
    Dept findId(Integer id);

    void update(Dept dept);
}
