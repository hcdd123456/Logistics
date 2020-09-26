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

import com.javayjx.dao.ser.ClientDao;
import com.javayjx.entity.ser.Client;


@Service("clientService")
public class ClientServiceImpl implements ClientService {
	@Resource
	private ClientDao clientDao     ;
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Client repalce(Client curr,Client origin){
		
		if(curr.getTrueName()  ==null){
			curr.setTrueName(origin.getTrueName());
		}
		if(curr.getPhone() ==null){
			curr.setPhone(origin.getPhone());
		}
		if(curr.getSex()  ==null){
			curr.setSex(origin.getSex());
		}
		if(curr.getNumber()   ==null){
			curr.setNumber(origin.getNumber());
		}
		if(curr.getRemark()==null){
			curr.setRemark(origin.getRemark());
		}
		
		if(curr.getAddress() ==null){
			curr.setAddress(origin.getAddress());
		}
		if(curr.getAge() ==null){
			curr.setAge(origin.getAge());
		}
		
		
		if(curr.getTransaction() ==null){
			curr.setTransaction(origin.getTransaction());
		}
		if(curr.getType() ==null){
			curr.setType(origin.getType());
		}
		return curr;
	}
	
	@Override
	public Integer update(Client client) {
		Client origin = clientDao.findId(client.getId());
		client = repalce(client, origin);
		clientDao.save(client);
		return 1;
	}

	@Override
	public List<Client> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Client> pages = clientDao.findAll(new Specification<Client>() {
			
			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				 
					
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("trueName"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("phone"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("address"),"%"+map.get("q").toString()+"%")
							));
				}
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=clientDao.count(new Specification<Client>() {
			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("trueName"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("phone"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%"),
							cb.like(root.get("address"),"%"+map.get("q").toString()+"%")
							
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
}
