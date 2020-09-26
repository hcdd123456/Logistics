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

import com.javayjx.dao.ser.StaffDao;
import com.javayjx.entity.ser.Staff;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

	@Resource
	private StaffDao staffDao   ;
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Staff repalce(Staff curr,Staff origin){
		
		if(curr.getAddress()  ==null){
			curr.setAddress(origin.getAddress());
		}
		
		if(curr.getState()  ==null){
			curr.setState(origin.getState());
		}
		if(curr.getPhone()    ==null){
			curr.setPhone(origin.getPhone());
		}
		if(curr.getAge()    ==null){
			curr.setAge(origin.getAge());
		}
		
		if(curr.getCreateDateTime()    ==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		if(curr.getRemark()==null){
			curr.setRemark(origin.getRemark());
		}
		if(curr.getName()  ==null){
			curr.setName(origin.getName());
		}
		
		if(curr.getSfz()   ==null){
			curr.setSfz(origin.getSfz());
		}
		if(curr.getWages()  ==null){
			curr.setWages(origin.getWages());
		}if(curr.getAmount()    ==null){
			curr.setAmount(origin.getAmount());
		}
		return curr;
	}
	
	
	
	@Override
	public Integer update(Staff staff) {
		Staff origin = staffDao.findId(staff.getId());
		staff = repalce(staff, origin);
		staffDao.save(staff);
		return 1;
	}

	@Override
	public List<Staff> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Staff> pages = staffDao.findAll(new Specification<Staff>() {
			
			@Override
			public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
					
					
					
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("name"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("address"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("phone"),"%"+map.get("q").toString()+"%"), 
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
		Long count=staffDao.count(new Specification<Staff>() {
			@Override
			public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("name"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("address"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("phone"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%")
							));
				}
				
				return predicate;
			}
		});
		return count;
	}

	@Override
	public Staff findid(Integer id) {
		Staff staff = staffDao.findId(id);
		return staff;
	}


}
