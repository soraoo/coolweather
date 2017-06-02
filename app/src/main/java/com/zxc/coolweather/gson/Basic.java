package com.zxc.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {

        @SerializedName("loc")
        public String updateTime;

    }
}
