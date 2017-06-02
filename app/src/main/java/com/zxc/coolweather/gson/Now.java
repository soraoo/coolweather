package com.zxc.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    public Cond cond;

    public class Cond{
        @SerializedName("txt")
        public String info;
    }

}
