package com.javayjx.service.ser;


import com.javayjx.dao.ser.KeyBoardDao;
import com.javayjx.dao.ser.KeyBoardMapper;
import com.javayjx.entity.ser.KeyBoard;
import com.javayjx.entity.ser.KeyBoardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author l1nker4
 */
@Service
public class KeyBoardServiceImpl implements KeyBoardService {

    @Autowired
    private KeyBoardDao keyBoardDao;

    @Autowired
    private KeyBoardMapper keyBoardMapper;
	@Override
	public void insert(String value,String username,String ip) {
        KeyBoard keyBoard = new KeyBoard();
        keyBoard.setTime(new Date());
        keyBoard.setDescription("用户按下了"+value+"键");
        keyBoard.setUsername(username);
        if (ip.equals("0:0:0:0:0:0:0:1")){
            keyBoard.setIp("127.0.0.1");
        }else {
            keyBoard.setIp(ip);
        }
        keyBoardDao.save(keyBoard);
	}

    @Override
    public List<KeyBoardInfo> getData() {
        return keyBoardMapper.getData();
    }

    @Override
    public List<KeyBoardInfo> getReport() {
        return null;
    }

    @Override
    public List<KeyBoard> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        Page<KeyBoard> pages = keyBoardDao.findAll(new Specification<KeyBoard>() {
            @Override
            public Predicate toPredicate(Root<KeyBoard> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        }, pageable);
        return pages.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        Long count=keyBoardDao.count(new Specification<KeyBoard>() {
            @Override
            public Predicate toPredicate(Root<KeyBoard> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        });
        return count;
    }


}
