package com.xsx.weather.utils;

import com.xsx.weather.bean.DailyForecast;
import com.xsx.weather.bean.Suggestion;
import com.xsx.weather.bean.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析查询天气返回的json字符串
 * Created by XSX on 2016/3/6.
 */
public class ParseJsonUtil {

    public static WeatherInfo parseJsonString(String jsonString) throws JSONException {
        WeatherInfo weatherInfo = null;

        JSONObject jsonObject = new JSONObject(jsonString);
        //得到基本信息
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
        jsonObject = jsonArray.getJSONObject(0);
        String status = jsonObject.getString("status");
        if (status != null && status.equals("ok")) {


            JSONObject basic = jsonObject.getJSONObject("basic");
            //得到城市
            String city = basic.getString("city");
            //得到国家
            String cnty = basic.getString("cnty");
            JSONObject update = basic.getJSONObject("update");
            //得到更新时间
            String loc = update.getString("loc");

            //得到实况信息
            JSONObject now = jsonObject.getJSONObject("now");
            //得到天气状况描述
            String txt = now.getJSONObject("cond").getString("txt");
            //体感湿度
            //String fl = now.getString("fl");
            //相对湿度
            String hum = now.getString("hum");
            //降水量
            String pcpn = now.getString("pcpn");
            //气压
            //String pres = now.getString("pres");
            //温度
            String tmp = now.getString("tmp");
            //可见度
            //String vis = now.getString("vis");
            //风向
            JSONObject windObject = now.getJSONObject("wind");
            StringBuilder sb = new StringBuilder();
//            sb.append("风向:" + windObject.getString("dir") + "\n");
//            sb.append("风力:" + windObject.getString("sc") + "\n");
//            sb.append("风速:" + windObject.getString("spd") + "\n");
            String wind = windObject.getString("dir");

            if (!jsonObject.isNull("aqi")) {
                JSONObject aqi = jsonObject.getJSONObject("aqi");
                JSONObject aqi_city1 = aqi.getJSONObject("city");
                //空气质量指数
                String aqi_ = aqi_city1.getString("aqi");
                //PM10 1小时平均值(ug/m³)
                String pm10 = aqi_city1.getString("pm10");
                //PM2.5 1小时平均值(ug/m³)
                String pm25 = aqi_city1.getString("pm25");
                //空气质量类别
                String qlty = aqi_city1.getString("qlty");
                weatherInfo = new WeatherInfo(city, cnty, loc, txt, hum, pcpn, tmp, wind, aqi_, pm10, pm25, qlty);
            } else {
                weatherInfo = new WeatherInfo(city, cnty, loc, txt,  hum, pcpn,  tmp,  wind);
            }


            List<DailyForecast> dailyForecasts = new ArrayList<>();
            //获得七天的天气预报
            JSONArray forecasts = jsonObject.getJSONArray("daily_forecast");
            for (int i = 0; i < forecasts.length(); i++) {
                JSONObject forecast = forecasts.getJSONObject(i);
                String date = forecast.getString("date");
                String sr = forecast.getJSONObject("astro").getString("sr");
                String ss = forecast.getJSONObject("astro").getString("ss");
                String txt_d = forecast.getJSONObject("cond").getString("txt_d");
                String txt_n = forecast.getJSONObject("cond").getString("txt_n");
                //相对湿度
                String hum_i = forecast.getString("hum");
                //降水量
                String pcpn_i = forecast.getString("pcpn");
                //气压
                String pres_i = forecast.getString("pres");
                //降水概率
                String pop_i = forecast.getString("pop");
                //可见度
                String vis_i = forecast.getString("vis");
                //温度
                String tmp_max = forecast.getJSONObject("tmp").getString("max");
                String tmp_min = forecast.getJSONObject("tmp").getString("min");

                JSONObject wind_o = forecast.getJSONObject("wind");
                StringBuilder sb_i = new StringBuilder();
                sb_i.append("风向:" + wind_o.getString("dir") + "\n");
                sb_i.append("风力:" + wind_o.getString("sc") + "\n");
                sb_i.append("风速:" + wind_o.getString("spd") + "\n");
                String wind_i = sb.toString();

                DailyForecast dailyForecast = new DailyForecast(date, sr, ss, txt_d, txt_n, hum_i, pcpn_i, pop_i, pres_i, tmp_max, tmp_min, vis_i, wind_i);
                dailyForecasts.add(dailyForecast);
            }
            weatherInfo.setDailyForecasts(dailyForecasts);

            //获得天气建议
            JSONObject suggestion = jsonObject.getJSONObject("suggestion");
            //舒适度指数
            JSONObject comf = suggestion.getJSONObject("comf");
            //简介
            String comf_brf = comf.getString("brf");
            //详细描述
            String comf_txt = comf.getString("txt");

            //洗车指数
            JSONObject cw = suggestion.getJSONObject("cw");
            //简介
            String cw_brf = cw.getString("brf");
            //详细描述
            String cw_txt = cw.getString("txt");

            //穿衣指数
            JSONObject drsg = suggestion.getJSONObject("drsg");
            //简介
            String drsg_brf = drsg.getString("brf");
            //详细描述
            String drsg_txt = drsg.getString("txt");

            //感冒指数
            JSONObject flu = suggestion.getJSONObject("flu");
            //简介
            String flu_brf = flu.getString("brf");
            //详细描述
            String flu_txt = flu.getString("txt");

            //运动指数
            JSONObject sport = suggestion.getJSONObject("sport");
            //简介
            String sport_brf = sport.getString("brf");
            //详细描述
            String sport_txt = sport.getString("txt");

            //旅游指数
            JSONObject trav = suggestion.getJSONObject("trav");
            //简介
            String trav_brf = trav.getString("brf");
            //详细描述
            String trav_txt = trav.getString("txt");

            //旅游指数
            JSONObject uv = suggestion.getJSONObject("uv");
            //简介
            String uv_brf = uv.getString("brf");
            //详细描述
            String uv_txt = uv.getString("txt");
            Suggestion suggestionBean = new Suggestion(comf_brf, cw_brf, drsg_brf, flu_brf, sport_brf, trav_brf, uv_brf, comf_txt,
                    cw_txt, drsg_txt, flu_txt, sport_txt,
                    trav_txt, uv_txt);
            weatherInfo.setSuggestion(suggestionBean);
        }
        return weatherInfo;
    }

}
