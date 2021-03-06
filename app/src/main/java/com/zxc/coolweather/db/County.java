package com.zxc.coolweather.db;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

import org.litepal.crud.DataSupport;

/**
 * 县
 */
public class County extends DataSupport {

    /**
     * id
     */
    private int id;
    /**
     * 县名
     */
    private String name;
    /**
     * 天气id
     */
    private String weatherId;
    /**
     * 所属市的id
     */
    private int cityId;

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

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
