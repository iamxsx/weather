package com.xsx.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XSX on 2016/3/6.
 */
public class WeatherInfo implements Parcelable{

    /**
     * 城市
     */
    private String city;
    /**
     * 国家
     */
    private String cnty;
    /**
     * 更新时间
     */
    private String loc;
    /**
     * 天气描述
     */
    private String txt;
    /**
     * 体感温度
     */
    private String fl;
    /**
     * 相对湿度
     */
    private String hum;
    /**
     * 降水量
     */
    private String pcpn;
    /**
     * 降水概率
     */
    private String pop;
    /**
     * 气压
     */
    private String pres;
    /**
     * 温度
     */
    private String tmp;
    /**
     * 能见度
     */
    private String vis;
    /**
     * 风向
     */
    private String wind;
    /**
     * 空气质量
     */
    private String aqi;
    /**
     * /PM10 1小时平均值(ug/m³)
     */
    private String pm10;
    /**
     * /PM2.5 1小时平均值(ug/m³)
     */
    private String pm25;
    /**
     * 空气质量类别
     */
    private String qlty;

    /**
     * 7天的天气预报
     */
    List<DailyForecast> dailyForecasts = new ArrayList<>(7);
    /**
     * 推荐
     */
    private Suggestion suggestion;

    public WeatherInfo(String city, String cnty, String loc, String txt, String fl, String hum, String pcpn, String pres, String tmp, String vis, String wind) {
        this.city = city;
        this.cnty = cnty;
        this.loc = loc;
        this.txt = txt;
        this.fl = fl;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pres = pres;
        this.tmp = tmp;
        this.vis = vis;
        this.wind = wind;

    }

    public WeatherInfo(String city, String cnty, String loc, String txt, String hum, String pcpn,  String tmp,  String wind) {
        this.city = city;
        this.cnty = cnty;
        this.loc = loc;
        this.txt = txt;
        this.hum = hum;
        this.pcpn = pcpn;
        this.tmp = tmp;
        this.wind = wind;

    }

    public WeatherInfo(String city, String cnty, String loc, String txt, String hum, String pcpn, String tmp, String wind, String aqi, String pm10, String pm25, String qlty) {
        this.city = city;
        this.cnty = cnty;
        this.loc = loc;
        this.txt = txt;
        this.hum = hum;
        this.pcpn = pcpn;
        this.tmp = tmp;
        this.wind = wind;
        this.aqi = aqi;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.qlty = qlty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }


    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.city);
            dest.writeString(this.cnty);
            dest.writeString(this.loc);
            dest.writeString(this.txt);
            dest.writeString(this.fl);
            dest.writeString(this.hum);
            dest.writeString(this.pcpn);
            dest.writeString(this.pop);
            dest.writeString(this.pres);
            dest.writeString(this.tmp);
            dest.writeString(this.vis);
            dest.writeString(this.wind);
            dest.writeString(this.aqi);
            dest.writeString(this.pm10);
            dest.writeString(this.pm25);
            dest.writeString(this.qlty);
            dest.writeTypedList(dailyForecasts);
            dest.writeParcelable(this.suggestion, flags);
        }

        protected WeatherInfo(Parcel in) {
            this.city = in.readString();
            this.cnty = in.readString();
            this.loc = in.readString();
            this.txt = in.readString();
            this.fl = in.readString();
            this.hum = in.readString();
            this.pcpn = in.readString();
            this.pop = in.readString();
            this.pres = in.readString();
            this.tmp = in.readString();
            this.vis = in.readString();
            this.wind = in.readString();
            this.aqi = in.readString();
            this.pm10 = in.readString();
            this.pm25 = in.readString();
            this.qlty = in.readString();
            this.dailyForecasts = in.createTypedArrayList(DailyForecast.CREATOR);
            this.suggestion = in.readParcelable(Suggestion.class.getClassLoader());
        }

        public static final Parcelable.Creator<WeatherInfo> CREATOR = new Parcelable.Creator<WeatherInfo>() {
            public WeatherInfo createFromParcel(Parcel source) {
                return new WeatherInfo(source);
            }

            public WeatherInfo[] newArray(int size) {
                return new WeatherInfo[size];
            }
        };
}
