package com.zxc.coolweather.util;

/**
 * 作者 zxc.
 * 邮箱 1142716989@qq.com
 * 时间 2017/6/2
 */

import android.text.TextUtils;

import com.zxc.coolweather.db.City;
import com.zxc.coolweather.db.County;
import com.zxc.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析处理数据工具类
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     *
     * @param response 返回的数据
     * @return 是否成功
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray provinces = new JSONArray(response);
                int length = provinces.length();
                for (int i = 0; i < length; i++) {
                    JSONObject provinceObj = provinces.getJSONObject(i);
                    Province province = new Province();
                    province.setName(provinceObj.getString("name"));
                    province.setCode(provinceObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     *
     * @param response     返回的数据
     * @param provinceId 所属省的id
     * @return 是否成功
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray cities = new JSONArray(response);
                int length = cities.length();
                for (int i = 0; i < length; i++) {
                    JSONObject cityObj = cities.getJSONObject(i);
                    City city = new City();
                    city.setName(cityObj.getString("name"));
                    city.setCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     *
     * @param response 返回的数据
     * @param cityId 所属市的id
     * @return 是否成功
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray counties = new JSONArray(response);
                int length = counties.length();
                for (int i = 0; i < length; i++) {
                    JSONObject countyObj = counties.getJSONObject(i);
                    County county = new County();
                    county.setName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
