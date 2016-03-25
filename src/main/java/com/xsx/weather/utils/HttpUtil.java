package com.xsx.weather.utils;

import android.content.Context;
import android.util.Log;

import com.xsx.weather.bean.WeatherInfo;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by XSX on 2016/3/7.
 */
public class HttpUtil {

    private static HttpUtil mInstance;
    private Context context;

    private HttpUtil(Context context){
        this.context=context;
    }

    public static HttpUtil getInstance(Context context){
        if (mInstance==null){
            synchronized (HttpUtil.class){
                mInstance=new HttpUtil(context);
            }
        }
        return mInstance;
    }

    private static final String APIKEY = "056a80ed5835306b07fab17f9a9a21d0";

    public WeatherInfo getWeatherInfo(String city){
        String httpUrl = "http://apis.baidu.com/heweather/weather/free";
        try {
            httpUrl=httpUrl+"?city="+ URLEncoder.encode(city,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.i("xsx","httpUrl="+httpUrl);
        HttpURLConnection conn= null;
        BufferedReader br=null;
        StringBuilder sb=new StringBuilder();
        WeatherInfo weatherInfo = null;
        try {
            URL url=new URL(httpUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("apikey",APIKEY);
            conn.connect();
            br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line="";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            Log.i("xsx",sb.toString());
            br.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (br!=null){
                    br.close();
                }
                if (conn!=null){
                    conn.disconnect();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            weatherInfo=ParseJsonUtil.parseJsonString(sb.toString());
            //将信息存入sp中，方便下次读取
            SPUtil.getInstance(context).saveWeather(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherInfo;
    }
}
