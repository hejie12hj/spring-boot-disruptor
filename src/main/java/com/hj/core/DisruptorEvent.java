package com.hj.core;

import java.io.Serializable;

/**
 * 消息实体
 *
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:23 AM
 */
public class DisruptorEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    String type;

    String jsonData;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
