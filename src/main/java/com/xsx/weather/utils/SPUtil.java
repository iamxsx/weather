package com.xsx.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xsx.weather.R;
import com.xsx.weather.bean.WeatherInfo;

/**
 * Created by XSX on 2016/3/9.
 */
public class SPUtil {
    Context context;

    private static SPUtil mInstance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SPUtil(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("weather", Context.MODE_PRIVATE);
        editor= sp.edit();
    }

    public static SPUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SPUtil.class) {
                mInstance = new SPUtil(context);
            }
        }
        return mInstance;
    }

    public void saveWeather(String jsonString) {
        editor.putString("jsonString",jsonString);
        editor.commit();
    }

    public String getWeather(){
        return sp.getString("jsonString","");
    }

    /**
     * 将选择的背景保存
     * @param layout_bg_id
     */
    public void putBgId(int layout_bg_id) {
        editor.putInt("layout_bg_id",layout_bg_id);
        editor.commit();
    }

    /**
     * 获得保存的背景id
     */
    public int getBgId() {
        return sp.getInt("layout_bg_id", R.drawable.layout_bg1);
    }
}
