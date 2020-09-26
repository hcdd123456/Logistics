package com.javayjx.entity.ser;

import java.io.Serializable;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/1/1 20:50
 * @description：
 */


public class KeyBoardInfo implements Serializable {

    private String description;
    private String count;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
