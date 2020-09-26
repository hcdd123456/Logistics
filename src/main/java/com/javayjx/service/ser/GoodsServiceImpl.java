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
import com.javayjx.dao.ser.GoodsDao;
import com.javayjx.entity.ser.Goods;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	
	@Resource
	private GoodsDao goodsDao    ;



	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public  Goods repalce( Goods curr, Goods origin){
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
		if(curr.getPrice1()  ==null){
			curr.setPrice1(origin.getPrice1());
		}
		if(curr.getPrice2()  ==null){
			curr.setPrice2(origin.getPrice2());
		}
		return curr;
	}
	
	
	@Override
	public Integer update(Goods goods) {
		Goods origin = goodsDao.findId(goods.getId());
		goods = repalce(goods, origin);
		goodsDao.save(goods);
		return 1;
	}

	@Override
	public List<Goods> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Goods> pages = goodsDao.findAll(new Specification<Goods>() {
			
			@Override
			public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
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
		Long count=goodsDao.count(new Specification<Goods>() {
			@Override
			public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
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
