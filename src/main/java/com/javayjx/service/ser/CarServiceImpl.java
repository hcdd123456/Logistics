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
import com.javayjx.dao.ser.CarDao;
import com.javayjx.entity.ser.Car;


@Service("carService")
public class CarServiceImpl implements CarService {
	
	@Resource
	private CarDao carDao  ;
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Car repalce(Car curr,Car origin){
		
		if(curr.getColor()   ==null){
			curr.setColor(origin.getColor());
		}
		if(curr.getState()  ==null){
			curr.setState(origin.getState());
		}
		if(curr.getType()   ==null){
			curr.setType(origin.getType());
		}
		
		if(curr.getWeight()    ==null){
			curr.setWeight(origin.getWeight());
		}
		if(curr.getCreateDateTime()    ==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		if(curr.getNumber()   ==null){
			curr.setNumber(origin.getNumber());
		}
		if(curr.getRemark()==null){
			curr.setRemark(origin.getRemark());
		}
		if(curr.getWeixiu()==null){
			curr.setWeixiu(origin.getWeixiu());
		}
		return curr;
	}
	
	
	@Override
	public Integer update(Car car) {
		Car origin = carDao.findId(car.getId());
		car = repalce(car, origin);
		carDao.save(car);
		return 1;
	}

	@Override
	public List<Car> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Car> pages = carDao.findAll(new Specification<Car>() {
			
			@Override
			public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
					
					
					
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("color"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("type"),"%"+map.get("q").toString()+"%"), 
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
		Long count=carDao.count(new Specification<Car>() {
			@Override
			public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("color"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("type"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%")
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
	
	
	
}
