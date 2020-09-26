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

import com.javayjx.dao.ser.StorageDao;
import com.javayjx.entity.ser.Storage;

@Service("storageService")
public class StorageServiceImpl implements StorageService {
	
	@Resource
	private StorageDao storageDao     ;
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Storage repalce(Storage curr,Storage origin){
		
		if(curr.getMan() ==null){
			curr.setMan(origin.getMan());
		}
		if(curr.getName()  ==null){
			curr.setName(origin.getName());
		}
		if(curr.getType()   ==null){
			curr.setType(origin.getType());
		}
		if(curr.getWeight()    ==null){
			curr.setWeight(origin.getWeight());
		}
		if(curr.getNum()    ==null){
			curr.setNum(origin.getNum());
		}
		if(curr.getNumber()   ==null){
			curr.setNumber(origin.getNumber());
		}
		if(curr.getRemark()==null){
			curr.setRemark(origin.getRemark());
		}
		return curr;
	}
	
	
	@Override
	public Integer update(Storage storage) {
		Storage origin = storageDao.findId(storage.getId());
		storage = repalce(storage, origin);
		storageDao.save(storage);
		return 1;
	}

	@Override
	public List<Storage> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Storage> pages = storageDao.findAll(new Specification<Storage>() {
			
			@Override
			public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();

				/*// 新加！！！！
				if (map.get("goods") != null) {
					predicate.getExpressions().add(cb.equal(root.get("goods"), map.get("goods")));
				}*/
					
				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("name"),"%"+map.get("q").toString()+"%"), 
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
		Long count=storageDao.count(new Specification<Storage>() {
			@Override
			public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();


				/*// 新加！！！
				if (map.get("goods") != null) {
					predicate.getExpressions().add(cb.equal(root.get("goods"), map.get("goods")));
				}*/

				// 模糊查询
				if (map.get("q") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("number"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("name"),"%"+map.get("q").toString()+"%"), 
							cb.like(root.get("remark"),"%"+map.get("q").toString()+"%")
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
	
	
}
