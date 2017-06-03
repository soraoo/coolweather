package com.zxc.coolweather;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zxc.coolweather.gson.Forecast;
import com.zxc.coolweather.gson.Weather;
import com.zxc.coolweather.util.HttpUtil;
import com.zxc.coolweather.util.Utility;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherScroll;

    private TextView titleCityText;

    private TextView titleUpdateTimeText;

    private TextView nowDegreeText;

    private TextView nowInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiAqiText;

    private TextView aqiPm25Text;

    private TextView suggestionComfortText;

    private TextView suggestionCarWashText;

    private TextView suggestionSportText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //初始化各控件
        initUI();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = pref.getString("weather", null);
        if (weatherString != null) {
            //有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        } else {
            //无缓存时去服务器查询天气
            String weatherId = getIntent().getStringExtra("weather_id");
            weatherScroll.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
    }

    /**
     * 根据天气id请求城市天气信息
     *
     * @param weatherId 天气id
     */
    private void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid="
                + weatherId + "&key=6f001e3d8f6e4bf98ed1e3ce8ff409a4";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * 处理并展示Weather实体类中的数据
     *
     * @param weather 天气
     */
    private void showWeatherInfo(Weather weather) {
        String titleCityName = weather.basic.cityName;
        String titleUpdateTime = weather.basic.update.updateTime.split(" ")[1];
        String nowDegree = weather.now.temperature + "℃";
        String nowInfo = weather.now.cond.info;
        titleCityText.setText(titleCityName);
        titleUpdateTimeText.setText(titleUpdateTime);
        nowDegreeText.setText(nowDegree);
        nowInfoText.setText(nowInfo);

        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.weather_forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.text_forecast_item_date);
            TextView infoText = (TextView) view.findViewById(R.id.text_forecast_item_info);
            TextView maxText = (TextView) view.findViewById(R.id.text_forecast_item_max);
            TextView minText = (TextView) view.findViewById(R.id.text_forecast_item_min);
            dateText.setText(forecast.date);
            infoText.setText(forecast.cond.info);
            maxText.setText(forecast.temperature.max);
            minText.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }

        if(weather.aqi!=null){
            aqiAqiText.setText(weather.aqi.city.aqi);
            aqiPm25Text.setText(weather.aqi.city.pm25);
        }

        String comfort = "舒适度" + weather.suggestion.comfort.info;
        String carWash = "洗车指数" + weather.suggestion.carWash.info;
        String sport = "运动建议" + weather.suggestion.sport.info;
        suggestionComfortText.setText(comfort);
        suggestionCarWashText.setText(carWash);
        suggestionSportText.setText(sport);
        weatherScroll.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化控件
     */
    private void initUI() {
        weatherScroll = (ScrollView) findViewById(R.id.scroll_weather);
        titleCityText = (TextView) findViewById(R.id.text_title_city);
        titleUpdateTimeText = (TextView) findViewById(R.id.text_title_update_time);
        nowDegreeText = (TextView) findViewById(R.id.text_now_degree);
        nowInfoText = (TextView) findViewById(R.id.text_now_info);
        forecastLayout = (LinearLayout) findViewById(R.id.layout_forecast);
        aqiAqiText = (TextView) findViewById(R.id.text_aqi_aqi);
        aqiPm25Text = (TextView) findViewById(R.id.text_aqi_pm25);
        suggestionComfortText = (TextView) findViewById(R.id.text_suggestion_comfort);
        suggestionCarWashText = (TextView) findViewById(R.id.text_suggestion_car_wash);
        suggestionSportText = (TextView) findViewById(R.id.text_suggestion_sport);
    }
}
