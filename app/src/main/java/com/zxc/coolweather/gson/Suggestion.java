package com.zxc.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort {

        @SerializedName("txt")
        public String info;

    }

    public class CarWash{

        @SerializedName("txt")
        public String info;

    }

    public class Sport{

        @SerializedName("txt")
        public String info;

    }
}
