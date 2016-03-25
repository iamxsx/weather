package com.xsx.weather.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xsx.weather.MyTimer;
import com.xsx.weather.R;
import com.xsx.weather.Temp;
import com.xsx.weather.bean.DailyForecast;
import com.xsx.weather.bean.Suggestion;
import com.xsx.weather.bean.WeatherInfo;
import com.xsx.weather.impl.WeatherTxt;
import com.xsx.weather.utils.HttpUtil;
import com.xsx.weather.utils.ParseJsonUtil;
import com.xsx.weather.utils.SPUtil;
import com.xsx.weather.widget.BezierEvaluator;
import com.xsx.weather.widget.ChartView;
import com.xsx.weather.widget.RainView;
import com.xsx.weather.widget.SnowView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, WeatherTxt {

    public static final int TO_LOCATION_ACTIVITY = 200;
    List<Temp> temps;
    ChartView chart_view;
    private ImageView iv_thermometer;
    private ScrollView sv;
    MyTimer myTimer;
    int temp = 0;
    int currentTemp = 0;

    private TextView tv_temp;

    private TextView tv_fl;
    private TextView tv_hum;
    private TextView tv_pcpn;
    private TextView tv_pres;
    private TextView tv_vis;
    private TextView tv_wind;

    private TextView tv_comf_brf;
    private TextView tv_comf_txt;
    private TextView tv_cw_brf;
    private TextView tv_cw_txt;
    private TextView tv_drsg_brf;
    private TextView tv_drsg_txt;
    private TextView tv_flu_brf;
    private TextView tv_flu_txt;
    private TextView tv_sport_brf;
    private TextView tv_sport_txt;
    private TextView tv_trav_brf;
    private TextView tv_trav_txt;
    private TextView tv_uv_brf;
    private TextView tv_uv_txt;

    private TextView tv_txt;
    private TextView tv_update_time;

    //记录日期的TextView
    private TextView tv_date1;
    private TextView tv_date2;
    private TextView tv_date3;
    private TextView tv_date4;

    private TextView tv_tem1;
    private TextView tv_tem2;
    private TextView tv_tem3;
    private TextView tv_tem4;
    private TextView tv_tem5;
    private TextView tv_tem6;
    private TextView tv_tem7;

    private List<TextView> tvs;

    private ImageView iv_add;
    private ImageView iv_location;
    private ImageView iv_weather;
    private ImageView iv_refresh;

    private ImageView iv_day1;
    private ImageView iv_day2;
    private ImageView iv_day3;
    private ImageView iv_day4;
    private ImageView iv_day5;
    private ImageView iv_day6;
    private ImageView iv_day7;


    private TextView tv_date_;
    private TextView tv_sr_;
    private TextView tv_ss_;
    private TextView tv_txt_d_;
    private TextView tv_txt_n_;
    private TextView tv_hum_;
    private TextView tv_pop_;
    private TextView tv_pcpn_;
    private TextView tv_pres_;
    private TextView tv_vis_;
    private TextView tv_wind_;

    private ImageView iv_leaf1;
    private ImageView iv_leaf2;

    private TextView tv_city;

    private LinearLayout main_layout;

    List<DailyForecast> forecasts;

    private List<ImageView> ivs;

    /**
     * 当前天气
     */
    private String txt;
    /**
     * 当前城市
     */
    private String city;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MyTimer.REFERSH_UI) {
                //从0增加到当前温度
                if (currentTemp <= temp) {
                    tv_temp.setText(currentTemp + "");
                    currentTemp++;
                } else {
                    myTimer.stopTimer();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initViews();
        String jsonString = SPUtil.getInstance(this).getWeather();
        if (jsonString != null && !jsonString.equals("")) {
            try {
                WeatherInfo weatherInfo = ParseJsonUtil.parseJsonString(jsonString);
                txt = weatherInfo.getTxt();
                city = weatherInfo.getCity();
                initDatas(weatherInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //判断天气提供适当的图案和动画
        setupIcon();

    }

    RainView rainView;
    SnowView snowView;

    private void setupIcon() {
        if (txt == null) {
            return;
        }
        rainView = (RainView) findViewById(R.id.rainview);
        snowView = (SnowView) findViewById(R.id.snowview);

        if (txt.contains(SUNNY)) {  //晴
            iv_weather.setImageResource(R.drawable.ic_sun);
            startSunRotate();
            startLeafAnimation();

        } else if (txt.contains(CLOUD)) {   //云
            iv_weather.setImageResource(R.drawable.ic_cloud);
            startCloudAnimation();
            startLeafAnimation();

        } else if (txt.contains(WIND)) {    //风
            iv_weather.setImageResource(R.drawable.ic_wind1);
            startLeafAnimation();

        } else if (txt.contains(THUNDER)) { //雷
            iv_weather.setImageResource(R.drawable.ic_thunder_rain);

        } else if (txt.contains(SNOW)) {    //雪
            iv_weather.setImageResource(R.drawable.ic_snow);
            snowView.setVisibility(View.VISIBLE);

        } else if (txt.contains(RAIN) ||txt.contains(RANINNY) ) {    //雨
            iv_weather.setImageResource(R.drawable.ic_rain);
            rainView.setVisibility(View.VISIBLE);
        }
    }

    private void startCloudAnimation() {

    }

    private void initViews() {
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        tv_city = (TextView) findViewById(R.id.tv_city);
        chart_view = (ChartView) findViewById(R.id.chart_view);
        tv_temp = (TextView) findViewById(R.id.tv_temp);

        tv_fl = (TextView) findViewById(R.id.tv_fl);
        tv_hum = (TextView) findViewById(R.id.tv_hum);
        tv_pcpn = (TextView) findViewById(R.id.tv_pcpn);
        tv_pres = (TextView) findViewById(R.id.tv_pres);
        tv_vis = (TextView) findViewById(R.id.tv_vis);
        tv_wind = (TextView) findViewById(R.id.tv_wind);

        tv_comf_brf = (TextView) findViewById(R.id.tv_comf_brf);
        tv_comf_txt = (TextView) findViewById(R.id.tv_comf_txt);
        tv_cw_brf = (TextView) findViewById(R.id.tv_cw_brf);
        tv_cw_txt = (TextView) findViewById(R.id.tv_cw_txt);
        tv_drsg_brf = (TextView) findViewById(R.id.tv_drsg_brf);
        tv_drsg_txt = (TextView) findViewById(R.id.tv_drsg_txt);
        tv_flu_brf = (TextView) findViewById(R.id.tv_flu_brf);
        tv_flu_txt = (TextView) findViewById(R.id.tv_flu_txt);
        tv_sport_brf = (TextView) findViewById(R.id.tv_sport_brf);
        tv_sport_txt = (TextView) findViewById(R.id.tv_sport_txt);
        tv_trav_brf = (TextView) findViewById(R.id.tv_trav_brf);
        tv_trav_txt = (TextView) findViewById(R.id.tv_trav_txt);
        tv_uv_brf = (TextView) findViewById(R.id.tv_uv_brf);
        tv_uv_txt = (TextView) findViewById(R.id.tv_uv_txt);

        tv_txt = (TextView) findViewById(R.id.tv_txt);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);

        tv_date1 = (TextView) findViewById(R.id.tv_date1);
        tv_date2 = (TextView) findViewById(R.id.tv_date2);
        tv_date3 = (TextView) findViewById(R.id.tv_date3);
        tv_date4 = (TextView) findViewById(R.id.tv_date4);

        iv_leaf1 = (ImageView) findViewById(R.id.iv_leaf1);
        iv_leaf2 = (ImageView) findViewById(R.id.iv_leaf2);

        tv_tem1 = (TextView) findViewById(R.id.tv_tem1);
        tv_tem2 = (TextView) findViewById(R.id.tv_tem2);
        tv_tem3 = (TextView) findViewById(R.id.tv_tem3);
        tv_tem4 = (TextView) findViewById(R.id.tv_tem4);
        tv_tem5 = (TextView) findViewById(R.id.tv_tem5);
        tv_tem6 = (TextView) findViewById(R.id.tv_tem6);
        tv_tem7 = (TextView) findViewById(R.id.tv_tem7);

        tvs = new ArrayList<>();
        tvs.add(tv_tem1);
        tvs.add(tv_tem2);
        tvs.add(tv_tem3);
        tvs.add(tv_tem4);
        tvs.add(tv_tem5);
        tvs.add(tv_tem6);
        tvs.add(tv_tem7);

        ivs = new ArrayList<>();
        iv_day1 = (ImageView) findViewById(R.id.iv_day1);
        iv_day2 = (ImageView) findViewById(R.id.iv_day2);
        iv_day3 = (ImageView) findViewById(R.id.iv_day3);
        iv_day4 = (ImageView) findViewById(R.id.iv_day4);
        iv_day5 = (ImageView) findViewById(R.id.iv_day5);
        iv_day6 = (ImageView) findViewById(R.id.iv_day6);
        iv_day7 = (ImageView) findViewById(R.id.iv_day7);

        ivs.add(iv_day1);
        ivs.add(iv_day2);
        ivs.add(iv_day3);
        ivs.add(iv_day4);
        ivs.add(iv_day5);
        ivs.add(iv_day6);
        ivs.add(iv_day7);

        iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        iv_location = (ImageView) findViewById(R.id.iv_location);
        iv_location.setOnClickListener(this);
        iv_weather = (ImageView) findViewById(R.id.iv_weather);
        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(this);
        initPopupWindow();

        setupBg();
    }

    private void setupBg() {
        int bgID = SPUtil.getInstance(this).getBgId();
        main_layout.setBackgroundResource(bgID);
    }

    Random random;

    /**
     * 叶子飘落动画
     */
    private void startLeafAnimation() {
        int maxX = 800;
        int maxY = 1300;
        random = new Random();
        PointF p0 = new PointF(0, random.nextInt(maxY));
        PointF p1 = new PointF(random.nextInt(maxX), random.nextInt(maxY));
        PointF p2 = new PointF(random.nextInt(maxX), random.nextInt(maxY));
        PointF p3 = new PointF(maxX, random.nextInt(maxY));

        PointF p4 = new PointF(0, random.nextInt(maxY));
        PointF p5 = new PointF(random.nextInt(maxX), random.nextInt(maxY));
        PointF p6 = new PointF(random.nextInt(maxX), random.nextInt(maxY));
        PointF p7 = new PointF(random.nextInt(maxX), maxY);


        AnimatorSet animatorSet = new AnimatorSet();

        BezierEvaluator bezierEvaluator1 = new BezierEvaluator(
                p0, p1, p2, p3);
        ValueAnimator valueAnimator1 = ValueAnimator.ofObject(bezierEvaluator1, p0, p3);
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                iv_leaf1.setX(pointF.x);
                iv_leaf1.setY(pointF.y);
            }
        });
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);

        BezierEvaluator bezierEvaluator2 = new BezierEvaluator(
                p4, p5, p6, p7);
        ValueAnimator valueAnimator2 = ValueAnimator.ofObject(bezierEvaluator2, p4, p7);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                iv_leaf2.setX(pointF.x);
                iv_leaf2.setY(pointF.y);
            }
        });
        valueAnimator2.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(iv_leaf1, "rotation", 0, 720);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv_leaf2, "rotation", 0, 660);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator2.setRepeatCount(ValueAnimator.INFINITE);

        animatorSet.playTogether(
                valueAnimator1,
                valueAnimator2,
                objectAnimator1,
                objectAnimator2

        );
        animatorSet.setDuration(3000).start();
    }


    /**
     * 太阳旋转
     */
    private void startSunRotate() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_weather, "rotation", 0, 360);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void initDatas(WeatherInfo weatherInfo) {
        if (weatherInfo == null) {
            log("weatherInfo为空");
        }
        String tmp = weatherInfo.getTmp();
        temp = Integer.parseInt(tmp);
        tv_temp.setText(tmp);

        tv_txt.setText(weatherInfo.getTxt());
        tv_update_time.setText("更新：" + weatherInfo.getLoc());

        tv_city.setText(weatherInfo.getCity());
        tv_fl.setText(weatherInfo.getTmp());
        tv_hum.setText(weatherInfo.getHum());
        tv_pcpn.setText(weatherInfo.getPcpn());
        tv_pres.setText(weatherInfo.getPres() == null ? "--" : weatherInfo.getPres());
        tv_vis.setText(weatherInfo.getVis() == null ? "--" : weatherInfo.getVis());
        tv_wind.setText(weatherInfo.getWind());

        Suggestion suggestion = weatherInfo.getSuggestion();
        tv_comf_brf.setText(suggestion.getComf_brf());
        tv_comf_txt.setText(suggestion.getComf_txt());
        tv_cw_brf.setText(suggestion.getCw_brf());
        tv_cw_txt.setText(suggestion.getCw_txt());
        tv_drsg_brf.setText(suggestion.getDrsg_brf());
        tv_drsg_txt.setText(suggestion.getDrsg_txt());
        tv_flu_brf.setText(suggestion.getFlu_brf());
        tv_flu_txt.setText(suggestion.getFlu_txt());
        tv_sport_brf.setText(suggestion.getSport_brf());
        tv_sport_txt.setText(suggestion.getSport_txt());
        tv_trav_brf.setText(suggestion.getTrav_brf());
        tv_trav_txt.setText(suggestion.getTrav_txt());
        tv_uv_brf.setText(suggestion.getUv_brf());
        tv_uv_txt.setText(suggestion.getUv_txt());

        forecasts = weatherInfo.getDailyForecasts();
        //记录温度的点
        List<Temp> temps = new ArrayList<>();
        for (int i = 0; i < forecasts.size(); i++) {
            DailyForecast forecast = forecasts.get(i);
            if (i == 3) {
                tv_date1.setText(forecast.getDate());
            } else if (i == 4) {
                tv_date2.setText(forecast.getDate());
            } else if (i == 5) {
                tv_date3.setText(forecast.getDate());
            } else if (i == 6) {
                tv_date4.setText(forecast.getDate());
            }
            ImageView iv = ivs.get(i);
            setupIconResource(iv,forecast.getTxt_d());
            TextView tvi = tvs.get(i);
            tvi.setText(forecast.getTmp_max() + "°| " + forecast.getTmp_min() + "°");
            Temp temp = new Temp(Integer.parseInt(forecast.getTmp_max()), Integer.parseInt(forecast.getTmp_min()));
            temps.add(temp);
        }

        chart_view.setTemps(temps);
        chart_view.postInvalidate();
    }

    private void setupIconResource(ImageView iv, String txt_d) {
        if (txt_d.contains(SUNNY)) {  //晴
            iv.setImageResource(R.drawable.ic_sun);

        } else if (txt_d.contains(CLOUD)) {   //云
            iv.setImageResource(R.drawable.ic_cloud);

        } else if (txt_d.contains(WIND)) {    //风
            iv.setImageResource(R.drawable.ic_wind1);

        } else if (txt_d.contains(THUNDER)) { //雷
            iv.setImageResource(R.drawable.ic_thunder_rain);

        } else if (txt_d.contains(SNOW)) {    //雪
            iv.setImageResource(R.drawable.ic_snow);

        } else if (txt_d.contains(RAIN)||txt_d.contains(RANINNY)) {    //雨
            iv.setImageResource(R.drawable.ic_rain);
        }
    }

    PopupWindow popupWindow;

    private void initPopupWindow() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        tv_date_ = (TextView) view1.findViewById(R.id.tv_date_);
        tv_sr_ = (TextView) view1.findViewById(R.id.tv_sr_);
        tv_ss_ = (TextView) view1.findViewById(R.id.tv_ss_);
        tv_txt_d_ = (TextView) view1.findViewById(R.id.tv_txt_d_);
        tv_txt_n_ = (TextView) view1.findViewById(R.id.tv_txt_n_);
        tv_hum_ = (TextView) view1.findViewById(R.id.tv_hum_);
        tv_pop_ = (TextView) view1.findViewById(R.id.tv_pop_);
        tv_pcpn_ = (TextView) view1.findViewById(R.id.tv_pcpn_);
        tv_pres_ = (TextView) view1.findViewById(R.id.tv_pres_);
        tv_vis_ = (TextView) view1.findViewById(R.id.tv_vis_);
        tv_wind_ = (TextView) view1.findViewById(R.id.tv_wind_);
        ImageView iv_close = (ImageView) view1.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(view1, 600, 650);
    }


    public void showInfoDialog(View view) {
        if (popupWindow != null) {
            DailyForecast forecast = null;
            switch (view.getId()) {
//                case R.id.ll_1:
//                    forecast = forecasts.get(0);
//                    break;
                case R.id.ll_2:
                    forecast = forecasts.get(1);
                    break;
                case R.id.ll_3:
                    forecast = forecasts.get(2);
                    break;
                case R.id.ll_4:
                    forecast = forecasts.get(3);
                    break;
                case R.id.ll_5:
                    forecast = forecasts.get(4);
                    break;
                case R.id.ll_6:
                    forecast = forecasts.get(5);
                    break;
                case R.id.ll_7:
                    forecast = forecasts.get(6);
                    break;
            }
            tv_date_.setText(forecast.getDate());
            tv_sr_.setText(forecast.getSr());
            tv_ss_.setText(forecast.getSs());
            tv_txt_d_.setText(forecast.getTxt_d());
            tv_txt_n_.setText(forecast.getTxt_n());
            tv_hum_.setText(forecast.getHum());
            tv_pop_.setText(forecast.getPop());
            tv_pcpn_.setText(forecast.getPcpn());
            tv_pres_.setText(forecast.getPres());
            tv_vis_.setText(forecast.getVis());
            tv_wind_.setText(forecast.getWind());
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public void onBackPressed() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    public void getWeatherInfo(String city) {
        new MyAsyncTask().execute(city);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                showChoseBgDialog();
                break;
            case R.id.iv_location:
                enterLocationActivity();
                break;
            case R.id.iv_refresh:
                getWeatherInfo(city);
                break;
        }
    }

    private void enterLocationActivity() {
        startActivityForResult(new Intent(this, LocationActivity.class), TO_LOCATION_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("xsx", "onActivityResult");
        if (requestCode == TO_LOCATION_ACTIVITY && resultCode == LocationActivity.RRFRESH_WEATHER) {
            WeatherInfo weatherInfo = data.getParcelableExtra("weatherInfo");
            if (weatherInfo != null) {
                initDatas(weatherInfo);
            }
        }
    }


    private void showChoseBgDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择背景");
        String items[] = new String[]{
                "蓝色",
                "灰色",
                "红色",
                "青色",
                "深青色"
        };
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        main_layout.setBackgroundResource(R.drawable.layout_bg1);
                        SPUtil.getInstance(MainActivity.this).putBgId(R.drawable.layout_bg1);
                        break;
                    case 1:
                        main_layout.setBackgroundResource(R.drawable.layout_bg2);
                        SPUtil.getInstance(MainActivity.this).putBgId(R.drawable.layout_bg2);
                        break;
                    case 2:
                        main_layout.setBackgroundResource(R.drawable.layout_bg3);
                        SPUtil.getInstance(MainActivity.this).putBgId(R.drawable.layout_bg3);
                        break;
                    case 3:
                        main_layout.setBackgroundResource(R.drawable.layout_bg4);
                        SPUtil.getInstance(MainActivity.this).putBgId(R.drawable.layout_bg4);
                        break;
                    case 4:
                        main_layout.setBackgroundResource(R.drawable.layout_bg5);
                        SPUtil.getInstance(MainActivity.this).putBgId(R.drawable.layout_bg5);
                        break;

                }
            }
        });
        builder.show();
    }


    class MyAsyncTask extends AsyncTask<String, Void, WeatherInfo> {

        @Override
        protected WeatherInfo doInBackground(String... params) {
            return HttpUtil.getInstance(MainActivity.this).getWeatherInfo(params[0]);
        }

        @Override
        protected void onPostExecute(WeatherInfo weatherInfo) {
            super.onPostExecute(weatherInfo);
            if (weatherInfo != null) {
                txt = weatherInfo.getTxt();
                city = weatherInfo.getCity();
                initDatas(weatherInfo);
                myTimer = new MyTimer(handler);
                myTimer.startTimer();
            }
        }
    }

    public void log(String msg) {
        Log.i("xsx", msg);
    }
}

