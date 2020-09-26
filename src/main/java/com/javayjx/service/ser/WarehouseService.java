package com.javayjx.service.ser;

import com.javayjx.entity.ser.Storage;
import com.javayjx.entity.ser.Warehouse;

import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/24 0024 14:56
 */
public interface WarehouseService {

    Warehouse selOne(Integer id);

    List<Warehouse> list(Map<String,Object> map, Integer page, Integer pageSize);

    Long getTotal(Map<String, Object> map);

    void save(Warehouse warehouse);

    void deleteById(Integer parseInt);

    void update(Warehouse warehouse);

}
