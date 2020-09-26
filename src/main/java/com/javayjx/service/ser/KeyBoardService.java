/**
 * 
 */
package com.javayjx.service.ser;


import com.javayjx.entity.ser.KeyBoard;
import com.javayjx.entity.ser.KeyBoardInfo;
import com.javayjx.entity.ser.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author summer
 * @date 2019年10月5日 下午4:23:47
 */
@Service
public interface KeyBoardService {
	void insert(String value, String username, String ip);



    List<KeyBoardInfo> getData();

    List<KeyBoardInfo> getReport();


    public List<KeyBoard> list(Map<String,Object> map, Integer page, Integer pageSize);

    public Long getTotal(Map<String,Object> map);
}
