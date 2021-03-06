package com.zxc.coolweather.db;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

import org.litepal.crud.DataSupport;

/**
 * 市
 */
public class City extends DataSupport {

    /**
     * id
     */
    private int id;
    /**
     * 市名
     */
    private String name;
    /**
     * 市代码
     */
    private int code;
    /**
     * 所属省份的id
     */
    private int provinceId;

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

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
