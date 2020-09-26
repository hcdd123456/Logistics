package com.javayjx.service.ser;


import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.javayjx.dao.ser.RepairDao;
import com.javayjx.entity.ser.Repair;


@Service("repairService")
public class RepairServiceImpl implements RepairService {
	
	@Resource
	private RepairDao repairDao    ;
	
	@Override
	public List<Repair> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Repair> pages = repairDao.findAll(new Specification<Repair>() {
			
			@Override
			public Predicate toPredicate(Root<Repair> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
					
				// 加入 等于 
				if (map.get("car") != null) {
					predicate.getExpressions().add(cb.equal(root.get("car"), map.get("car")));
				}
				
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=repairDao.count(new Specification<Repair>() {
			@Override
			public Predicate toPredicate(Root<Repair> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 加入 等于 
				if (map.get("car") != null) {
					predicate.getExpressions().add(cb.equal(root.get("car"), map.get("car")));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
}
