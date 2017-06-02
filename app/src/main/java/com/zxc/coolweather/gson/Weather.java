package com.zxc.coolweather.gson;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 天气类
 */
public class Weather {

    /**
     * 请求状态
     */
    public String status;
    /**
     * 城市基本信息
     */
    public Basic basic;
    /**
     * 空气质量
     */
    public AQI aqi;
    /**
     * 当天天气信息
     */
    public Now now;
    /**
     * 建议
     */
    public Suggestion suggestion;
    /**
     * 未来数日的天气预报
     */
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
