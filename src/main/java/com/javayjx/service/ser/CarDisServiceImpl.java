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
import com.javayjx.dao.ser.CarDisDao;
import com.javayjx.entity.ser.CarDis;

@Service("carDisService")
public class CarDisServiceImpl implements CarDisService {
	
	@Resource
	private CarDisDao carDisDao   ;
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public CarDis repalce(CarDis curr,CarDis origin){
		
		if(curr.getCar()  ==null){
			curr.setCar(origin.getCar());
		}
		if(curr.getStaff()   ==null){
			curr.setStaff(origin.getStaff());
		}
		if(curr.getRemark()  ==null){
			curr.setRemark(origin.getRemark());
		}
		if(curr.getCreateDateTime()    ==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		return curr;
	}
	
	
	
	
	@Override
	public Integer update(CarDis carDis) {
		CarDis origin = carDisDao.findId(carDis.getId());
		carDis = repalce(carDis, origin);
		carDisDao.save(carDis);
		return 1;
	}

	@Override
	public List<CarDis> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<CarDis> pages = carDisDao.findAll(new Specification<CarDis>() {
			
			@Override
			public Predicate toPredicate(Root<CarDis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
							cb.like(root.get("siji"),"%"+map.get("q").toString()+"%"), 
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
		Long count=carDisDao.count(new Specification<CarDis>() {
			@Override
			public Predicate toPredicate(Root<CarDis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
							cb.like(root.get("siji"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%") 
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
		
	
	
	
	
	
}
