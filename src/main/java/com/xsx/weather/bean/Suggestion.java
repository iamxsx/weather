package com.xsx.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XSX on 2016/3/6.
 */
public class Suggestion implements Parcelable {

    /**
     * 生活指数简介
     */
    private String comf_brf;
    /**
     * 洗车指数简介
     */
    private String cw_brf;
    /**
     * 穿衣指数简介
     */
    private String drsg_brf;
    /**
     * 感冒指数简介
     */
    private String flu_brf;
    /**
     * 运动指数简介
     */
    private String sport_brf;
    /**
     * 旅游指数简介
     */
    private String trav_brf;
    /**
     * 紫外线指数简介
     */
    private String uv_brf;

    /**
     * 生活指数详情
     */
    private String comf_txt;
    /**
     * 洗车指数详情
     */
    private String cw_txt;
    /**
     * 穿衣指数详情
     */
    private String drsg_txt;
    /**
     * 感冒指数详情
     */
    private String flu_txt;
    /**
     * 运动指数详情
     */
    private String sport_txt;
    /**
     * 旅游指数详情
     */
    private String trav_txt;
    /**
     * 紫外线指数详情
     */
    private String uv_txt;

    public Suggestion() {
    }

    public Suggestion(String comf_brf, String cw_brf, String drsg_brf, String flu_brf, String sport_brf, String trav_brf, String uv_brf, String comf_txt, String cw_txt, String drsg_txt, String flu_txt, String sport_txt, String trav_txt, String uv_txt) {
        this.comf_brf = comf_brf;
        this.cw_brf = cw_brf;
        this.drsg_brf = drsg_brf;
        this.flu_brf = flu_brf;
        this.sport_brf = sport_brf;
        this.trav_brf = trav_brf;
        this.uv_brf = uv_brf;
        this.comf_txt = comf_txt;
        this.cw_txt = cw_txt;
        this.drsg_txt = drsg_txt;
        this.flu_txt = flu_txt;
        this.sport_txt = sport_txt;
        this.trav_txt = trav_txt;
        this.uv_txt = uv_txt;
    }

    public String getComf_brf() {
        return comf_brf;
    }

    public void setComf_brf(String comf_brf) {
        this.comf_brf = comf_brf;
    }

    public String getComf_txt() {
        return comf_txt;
    }

    public void setComf_txt(String comf_txt) {
        this.comf_txt = comf_txt;
    }

    public String getCw_brf() {
        return cw_brf;
    }

    public void setCw_brf(String cw_brf) {
        this.cw_brf = cw_brf;
    }

    public String getDrsg_brf() {
        return drsg_brf;
    }

    public void setDrsg_brf(String drsg_brf) {
        this.drsg_brf = drsg_brf;
    }

    public String getFlu_brf() {
        return flu_brf;
    }

    public void setFlu_brf(String flu_brf) {
        this.flu_brf = flu_brf;
    }

    public String getSport_brf() {
        return sport_brf;
    }

    public void setSport_brf(String sport_brf) {
        this.sport_brf = sport_brf;
    }

    public String getTrav_brf() {
        return trav_brf;
    }

    public void setTrav_brf(String trav_brf) {
        this.trav_brf = trav_brf;
    }

    public String getUv_brf() {
        return uv_brf;
    }

    public void setUv_brf(String uv_brf) {
        this.uv_brf = uv_brf;
    }



    public String getCw_txt() {
        return cw_txt;
    }

    public void setCw_txt(String cw_txt) {
        this.cw_txt = cw_txt;
    }

    public String getDrsg_txt() {
        return drsg_txt;
    }

    public void setDrsg_txt(String drsg_txt) {
        this.drsg_txt = drsg_txt;
    }

    public String getFlu_txt() {
        return flu_txt;
    }

    public void setFlu_txt(String flu_txt) {
        this.flu_txt = flu_txt;
    }

    public String getSport_txt() {
        return sport_txt;
    }

    public void setSport_txt(String sport_txt) {
        this.sport_txt = sport_txt;
    }

    public String getTrav_txt() {
        return trav_txt;
    }

    public void setTrav_txt(String trav_txt) {
        this.trav_txt = trav_txt;
    }

    public String getUv_txt() {
        return uv_txt;
    }

    public void setUv_txt(String uv_txt) {
        this.uv_txt = uv_txt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.comf_brf);
        dest.writeString(this.cw_brf);
        dest.writeString(this.drsg_brf);
        dest.writeString(this.flu_brf);
        dest.writeString(this.sport_brf);
        dest.writeString(this.trav_brf);
        dest.writeString(this.uv_brf);
        dest.writeString(this.comf_txt);
        dest.writeString(this.cw_txt);
        dest.writeString(this.drsg_txt);
        dest.writeString(this.flu_txt);
        dest.writeString(this.sport_txt);
        dest.writeString(this.trav_txt);
        dest.writeString(this.uv_txt);
    }

    protected Suggestion(Parcel in) {
        this.comf_brf = in.readString();
        this.cw_brf = in.readString();
        this.drsg_brf = in.readString();
        this.flu_brf = in.readString();
        this.sport_brf = in.readString();
        this.trav_brf = in.readString();
        this.uv_brf = in.readString();
        this.comf_txt = in.readString();
        this.cw_txt = in.readString();
        this.drsg_txt = in.readString();
        this.flu_txt = in.readString();
        this.sport_txt = in.readString();
        this.trav_txt = in.readString();
        this.uv_txt = in.readString();
    }

    public static final Parcelable.Creator<Suggestion> CREATOR = new Parcelable.Creator<Suggestion>() {
        public Suggestion createFromParcel(Parcel source) {
            return new Suggestion(source);
        }

        public Suggestion[] newArray(int size) {
            return new Suggestion[size];
        }
    };
}
