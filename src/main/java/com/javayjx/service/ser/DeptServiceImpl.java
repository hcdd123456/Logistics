package com.javayjx.service.ser;

import com.javayjx.dao.ser.DeptDao;
import com.javayjx.entity.ser.Client;
import com.javayjx.entity.ser.Dept;
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
 * @create 2020/9/26 0026 17:17
 */
@Service(value = "deptService")
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptDao deptDao;


    public Dept repalce(Dept curr,Dept origin){

        if(curr.getName() ==null){
            curr.setName(origin.getName());
        }
        if(curr.getStaff()  ==null){
            curr.setStaff(origin.getStaff());
        }
        if(curr.getNum()   ==null){
            curr.setNum(origin.getNum());
        }
        if(curr.getRemark()    ==null){
            curr.setRemark(origin.getRemark());
        }
        return curr;
    }

    @Override
    public List<Dept> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<Dept> pages = deptDao.findAll(new Specification<Dept>() {

            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

				/*// 新加！！！！
				if (map.get("goods") != null) {
					predicate.getExpressions().add(cb.equal(root.get("goods"), map.get("goods")));
				}*/

                // 模糊查询
                if (map.get("q") != null) {
                    predicate.getExpressions().add(cb.or(
                            cb.like(root.get("name"),"%"+map.get("q").toString()+"%")
                    ));
                }

                return predicate;
            }
        }, pageable);
        return pages.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        Long count=deptDao.count(new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate=cb.conjunction();

                // 模糊查询
                if (map.get("q") != null) {
                    predicate.getExpressions().add(cb.or(
                            cb.like(root.get("name"),"%"+map.get("q").toString()+"%")

                    ));
                }

                return predicate;
            }
        });
        return count;
    }

    @Override
    public void save(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    public void deleteById(int parseInt) {
        deptDao.deleteById(parseInt);
    }

    @Override
    public Dept findId(Integer id) {
        return deptDao.findId(id);
    }

    @Override
    public void update(Dept dept) {
        Dept origin = deptDao.findId( dept.getId());
        dept = repalce(dept, origin);
        deptDao.save(dept);
    }
}
