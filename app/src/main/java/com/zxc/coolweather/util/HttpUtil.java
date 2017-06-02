package com.zxc.coolweather.util;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Http工具类
 */
public class HttpUtil {

    /**
     * 发送http请求
     * @param address 请求地址
     * @param callback 回调函数
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

}
