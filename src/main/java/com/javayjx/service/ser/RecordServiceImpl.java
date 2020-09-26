package com.javayjx.service.ser;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.javayjx.dao.ser.RecordDao;
import com.javayjx.entity.base.User;
import com.javayjx.entity.ser.Record;


@Service("recordService")
public class RecordServiceImpl implements RecordService {
	
	@Resource
	private RecordDao recordDao  ;
	
	@Override
	public void add(HttpSession session, Integer type, String content) {
		
		Record record = new Record();
		record.setType(type);
		record.setContent(content);
		record.setCreateDateTime(new Date());
		User user = (User) session.getAttribute("currentUser");
		record.setName(user.getTrueName());
		recordDao.save(record);
		
	}
	
	@Override
	public List<Record> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Record> pages = recordDao.findAll(new Specification<Record>() {
			
			@Override
			public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
					
				// 加入 等于  
				if (map.get("type") != null) {
					predicate.getExpressions().add(cb.equal(root.get("type"), map.get("type")));
				}
				
				 
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=recordDao.count(new Specification<Record>() {
			@Override
			public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 加入 等于 
				if (map.get("type") != null) {
					predicate.getExpressions().add(cb.equal(root.get("type"), map.get("type")));
				}
				
				
				return predicate;
			}
		});
		return count;
	}
	
	
}
