package com.zxc.coolweather.gson;

import android.os.ParcelUuid;

import com.google.gson.annotations.SerializedName;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    public Cond cond;

    public class Temperature{

        public String max;

        public String min;

    }

    public class Cond{

        @SerializedName("txt_d")
        public String info;

    }

}
