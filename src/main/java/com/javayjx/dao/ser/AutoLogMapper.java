package com.javayjx.dao.ser;


import com.javayjx.entity.ser.AutoLog;

public interface AutoLogMapper {
    void insert(AutoLog log);

    String getSelectData(String module);
    String getAddData(String module);
    String getEditData(String module);
    String getDelData(String module);
}
