package com.javayjx.service.ser;

import com.javayjx.entity.ser.AutoLog;

import java.util.List;


/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/12 18:58
 * @description： 
 */
public interface AutoLogService {

    void insert(AutoLog autoLog);

    List<String> getLog(String module);
}
