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
import com.javayjx.dao.ser.OrderDao;
import com.javayjx.entity.ser.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
		
	@Resource
	private OrderDao orderDao   ;
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Order repalce(Order curr,Order origin){
		
		if(curr.getAddress1() ==null){
			curr.setAddress1(origin.getAddress1());
		}
		if(curr.getAddress2() ==null){
			curr.setAddress2(origin.getAddress2());
		}
		if(curr.getName1() ==null){
			curr.setName1(origin.getName1());
		}
		if(curr.getName2()  ==null){
			curr.setName2(origin.getName2());
		}
		if(curr.getDetail2() ==null){
			curr.setDetail2(origin.getDetail2());
		}
		if(curr.getDetail1() ==null){
			curr.setDetail1(origin.getDetail1());
		}
		if(curr.getPhone1()  ==null){
			curr.setPhone1(origin.getPhone1());
		}
		if(curr.getPhone2()  ==null){
			curr.setPhone2(origin.getPhone2());
		}
		if(curr.getNumber() ==null){
			curr.setNumber(origin.getNumber());
		}
		if(curr.getPrice()  ==null){
			curr.setPrice(origin.getPrice());
		}
		
		
		if(curr.getReceiver()  ==null){
			curr.setReceiver(origin.getReceiver());
		}
		if(curr.getState()  ==null){
			curr.setState(origin.getState());
		}
		if(curr.getWeight() ==null){
			curr.setWeight(origin.getWeight());
		}
		
		if(curr.getRemark()==null){
			curr.setRemark(origin.getRemark());
		}
		if(curr.getCreateDateTime()==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		
		return curr;
	}
	
	
	@Override
	public Integer update(Order order) {
		Order origin = orderDao.findId(order.getId());
		order = repalce(order, origin);
		orderDao.save(order);
		return 1;
	}
	
	@Override
	public List<Order> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Order> pages = orderDao.findAll(new Specification<Order>() {
			
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				 
				 
					
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("name1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("name2"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("address1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("address2"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("detail1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("detail2"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("phone1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("phone2"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("receiver"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%")
							
							
							));
				}
				
				
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=orderDao.count(new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("name1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("name2"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("address1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("address2"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("detail1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("detail2"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("phone1"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("phone2"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("receiver"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%")
							
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
}
