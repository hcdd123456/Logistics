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
import com.javayjx.dao.ser.CarUseDao;
import com.javayjx.entity.ser.CarUse;



@Service("carUseService")
public class CarUseServiceImpl implements CarUseService {
	
	@Resource
	private CarUseDao  carUseDao  ;
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public CarUse repalce(CarUse curr,CarUse origin){
		
		if(curr.getCar()  ==null){
			curr.setCar(origin.getCar());
		}
		if(curr.getRemark()   ==null){
			curr.setRemark(origin.getRemark());
		}
		if(curr.getCreateDateTime()    ==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		
		if(curr.getStaff() ==null){
			curr.setStaff(origin.getStaff());
		}
		
		return curr;
	}
	
	
	@Override
	public Integer update(CarUse carUse) {
		CarUse origin = carUseDao.findId(carUse.getId());
		carUse = repalce(carUse, origin);
		carUseDao.save(carUse);
		return 1;
	}

	@Override
	public List<CarUse> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<CarUse> pages = carUseDao.findAll(new Specification<CarUse>() {
			
			@Override
			public Predicate toPredicate(Root<CarUse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				
				
				// 加入 等于 
				if (map.get("car") != null) {
					predicate.getExpressions().add(cb.equal(root.get("car"), map.get("car")));
				}
				
				// 加入 等于 
				if (map.get("staff") != null) {
					predicate.getExpressions().add(cb.equal(root.get("staff"), map.get("staff")));
				}
				
				
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%") 
							));
				}
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=carUseDao.count(new Specification<CarUse>() {
			@Override
			public Predicate toPredicate(Root<CarUse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 加入 等于 
				if (map.get("car") != null) {
					predicate.getExpressions().add(cb.equal(root.get("car"), map.get("car")));
				}
				
				// 加入 等于 
				if (map.get("staff") != null) {
					predicate.getExpressions().add(cb.equal(root.get("staff"), map.get("staff")));
				}
				
				
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%") 
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
	
	
}
