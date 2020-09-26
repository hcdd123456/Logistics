package com.javayjx.entity.ser;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/12 18:58
 * @description： 
 */
public class AutoLog {
    private Integer id;
    private String description;
    private String modelType;
    private String operationType;
    private String username;
    private String ip;

    public AutoLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
