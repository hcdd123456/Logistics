package com.javayjx.service.ser;

import com.javayjx.dao.ser.WarehouseDao;
import com.javayjx.entity.ser.Storage;
import com.javayjx.entity.ser.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/24 0024 14:57
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;

    public Warehouse repalce(Warehouse curr,Warehouse origin){

        if(curr.getWarehouseName() ==null){
            curr.setWarehouseName(origin.getWarehouseName());
        }
        if(curr.getRemark()  ==null){
            curr.setRemark(origin.getRemark());
        }
        if(curr.getWarehouseCapacity()   ==null){
            curr.setWarehouseCapacity(origin.getWarehouseCapacity());
        }
        if(curr.getWarehouseModel()    ==null){
            curr.setWarehouseModel(origin.getWarehouseModel());
        }
        if(curr.getWarehouseType()    ==null){
            curr.setWarehouseType(origin.getWarehouseType());
        }
        return curr;
    }

    @Override
    public Warehouse selOne(Integer id) {
        return warehouseDao.findId(id);
    }

    @Override
    public List<Warehouse> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Warehouse> pages = warehouseDao.findAll(new Specification<Warehouse>() {
            @Override
            public Predicate toPredicate(Root<Warehouse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                // 模糊查询
                if (map.get("q") != null) {
                    predicate.getExpressions().add(cb.or(
                            cb.like(root.get("warehouseName"),"%"+map.get("q").toString()+"%"),
                            cb.like(root.get("warehouseType"),"%"+map.get("q").toString()+"%"),
                            cb.like(root.get("warehouseModel"),"%"+map.get("q").toString()+"%")
                    ));
                }
                return predicate;
            }
        }, pageable);
        return pages.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        Long count=warehouseDao.count(new Specification<Warehouse>() {
            @Override
            public Predicate toPredicate(Root<Warehouse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate=cb.conjunction();

                // 模糊查询
                if (map.get("q") != null) {
                    predicate.getExpressions().add(cb.or(
                            cb.like(root.get("warehouseName"),"%"+map.get("q").toString()+"%"),
                            cb.like(root.get("warehouseType"),"%"+map.get("q").toString()+"%"),
                            cb.like(root.get("warehouseModel"),"%"+map.get("q").toString()+"%")
                    ));
                }

                return predicate;
            }
        });
        return count;
    }

    @Override
    public void save(Warehouse warehouse) {
        warehouseDao.save(warehouse);
    }

    @Override
    public void deleteById(Integer parseInt) {
        warehouseDao.deleteById(parseInt);
    }

    @Override
    public void update(Warehouse warehouse) {
        Warehouse origin = warehouseDao.findId(warehouse.getId());
        System.out.println("origin......................."+origin.toString());
        warehouse = repalce(warehouse, origin);
        System.out.println("warehouse......................."+warehouse.toString());
        warehouseDao.save(warehouse);
    }
}
