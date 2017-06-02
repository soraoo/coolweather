package com.zxc.coolweather.db;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

import org.litepal.crud.DataSupport;

/**
 * 省份
 */
public class Province extends DataSupport {

    /**
     * id
     */
    private int id;
    /**
     * 省份名称
     */
    private String name;
    /**
     * 省份代码
     */
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
