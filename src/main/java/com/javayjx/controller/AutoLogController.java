package com.javayjx.controller;

import com.javayjx.service.ser.AutoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/1/1 14:19
 * @description：
 */

@RestController
@RequestMapping("log")
public class AutoLogController {

    @Autowired
    private AutoLogService autoLogService;

    @RequestMapping("commonData")
    public Object getData(String module){
        List<String> data = autoLogService.getLog(module);
        List<Integer> data1 = new ArrayList<>();
        data.forEach((item) -> {
            data1.add(Integer.parseInt(item));
        });
        return data1;
    }
}
