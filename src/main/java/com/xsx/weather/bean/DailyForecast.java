package com.xsx.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XSX on 2016/3/6.
 */
public class DailyForecast implements Parcelable {
    /**
     * 日期
     */
    private String date;
    /**
     * 日出时间
     */
    private String sr;
    /**
     * 日落时间
     */
    private String ss;
    /**
     * 白天天气状况描述
     */
    private String txt_d;
    /**
     * 夜间天气状况描述
     */
    private String txt_n;
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
     * 最高温度
     */
    private String tmp_max;
    /**
     * 最低温度
     */
    private String tmp_min;
    /**
     * 能见度
     */
    private String vis;
    /**
     * 风向
     */
    private String wind;

    public DailyForecast() {
    }

    public DailyForecast(String date, String sr, String ss, String txt_d,String txt_n, String hum, String pcpn, String pop, String pres, String tmp_max,String tmp_min, String vis, String wind) {
        this.date = date;
        this.sr = sr;
        this.ss = ss;
        this.txt_d = txt_d;
        this.txt_n = txt_n;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pop = pop;
        this.pres = pres;
        this.tmp_max = tmp_max;
        this.tmp_min = tmp_min;
        this.vis = vis;
        this.wind = wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTxt_d() {
        return txt_d;
    }

    public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
    }

    public String getTxt_n() {
        return txt_n;
    }

    public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
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

    public String getTmp_max() {
        return tmp_max;
    }

    public void setTmp_max(String tmp_max) {
        this.tmp_max = tmp_max;
    }

    public String getTmp_min() {
        return tmp_min;
    }

    public void setTmp_min(String tmp_min) {
        this.tmp_min = tmp_min;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.sr);
        dest.writeString(this.ss);
        dest.writeString(this.txt_d);
        dest.writeString(this.txt_n);
        dest.writeString(this.hum);
        dest.writeString(this.pcpn);
        dest.writeString(this.pop);
        dest.writeString(this.pres);
        dest.writeString(this.tmp_max);
        dest.writeString(this.tmp_min);
        dest.writeString(this.vis);
        dest.writeString(this.wind);
    }

    protected DailyForecast(Parcel in) {
        this.date = in.readString();
        this.sr = in.readString();
        this.ss = in.readString();
        this.txt_d = in.readString();
        this.txt_n = in.readString();
        this.hum = in.readString();
        this.pcpn = in.readString();
        this.pop = in.readString();
        this.pres = in.readString();
        this.tmp_max = in.readString();
        this.tmp_min = in.readString();
        this.vis = in.readString();
        this.wind = in.readString();
    }

    public static final Parcelable.Creator<DailyForecast> CREATOR = new Parcelable.Creator<DailyForecast>() {
        public DailyForecast createFromParcel(Parcel source) {
            return new DailyForecast(source);
        }

        public DailyForecast[] newArray(int size) {
            return new DailyForecast[size];
        }
    };
}
