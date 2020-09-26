package com.javayjx.service.ser;

import com.javayjx.dao.ser.StaffSalaryDao;
import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.StaffSalary;
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
 * @create 2020/9/26 0026 22:25
 */
@Service
public class StaffSalaryServiceImpl implements StaffSalaryService {

    @Autowired
    private StaffSalaryDao staffSalaryDao;

    public StaffSalary repalce(StaffSalary curr,StaffSalary origin){

        if(curr.getStaff() ==null){
            curr.setStaff(origin.getStaff());
        }
        if(curr.getSalary() == null ){
            curr.setSalary(origin.getSalary());
        }
        if(curr.getRemark()   ==null){
            curr.setRemark(origin.getRemark());
        }
        return curr;
    }

    @Override
    public List<StaffSalary> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<StaffSalary> pages = staffSalaryDao.findAll(new Specification<StaffSalary>() {

            @Override
            public Predicate toPredicate(Root<StaffSalary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        Long count=staffSalaryDao.count(new Specification<StaffSalary>() {
            @Override
            public Predicate toPredicate(Root<StaffSalary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public void save(StaffSalary staffSalary) {
        staffSalaryDao.save(staffSalary);
    }

    @Override
    public void deleteById(int parseInt) {
        staffSalaryDao.deleteById(parseInt);
    }

    @Override
    public StaffSalary findId(Integer id) {
        return staffSalaryDao.findId(id);
    }

    @Override
    public void update(StaffSalary staffSalary) {
        StaffSalary origin = staffSalaryDao.findId( staffSalary.getId());
        staffSalary = repalce(staffSalary, origin);
        staffSalaryDao.save(staffSalary);
    }
}
