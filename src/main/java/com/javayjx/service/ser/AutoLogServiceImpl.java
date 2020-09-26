package com.javayjx.service.ser;

import com.javayjx.dao.ser.AutoLogMapper;
import com.javayjx.entity.ser.AutoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AutoLogServiceImpl implements AutoLogService {


    @Autowired
    private AutoLogMapper autoLogMapper;

    @Override
    public void insert(AutoLog autoLog) {
        autoLogMapper.insert(autoLog);
    }

    @Override
    public List<String> getLog(String module) {
        String selectData = autoLogMapper.getSelectData(module);
        String addData = autoLogMapper.getAddData(module);
        String editData = autoLogMapper.getEditData(module);
        String delData = autoLogMapper.getDelData(module);
        return Arrays.asList(addData,delData,editData,selectData);
    }
}