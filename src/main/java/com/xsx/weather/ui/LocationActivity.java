package com.xsx.weather.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xsx.weather.R;
import com.xsx.weather.bean.WeatherInfo;
import com.xsx.weather.utils.HttpUtil;

public class LocationActivity extends AppCompatActivity {

    public static final int RRFRESH_WEATHER = 1;
    private EditText et_city;
    private ProgressBar pb_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initViews();
    }

    private void initViews() {
        et_city = (EditText) findViewById(R.id.et_city);
        pb_search = (ProgressBar) findViewById(R.id.pb_search);
    }

    public void onSearch(View view) {
        pb_search.setVisibility(View.VISIBLE);
        final String city = et_city.getText().toString();
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "城市名不能为空", Toast.LENGTH_SHORT);
            return;
        }
        new MyAsyncTask().execute(city);

    }

    public void finish(View view){
        this.finish();
    }

    class MyAsyncTask extends AsyncTask<String, Void, WeatherInfo> {

        @Override
        protected WeatherInfo doInBackground(String... params) {
            return HttpUtil.getInstance(LocationActivity.this).getWeatherInfo(params[0]);
        }

        @Override
        protected void onPostExecute(WeatherInfo weatherInfo) {
            super.onPostExecute(weatherInfo);
            Intent intent = new Intent();
            intent.putExtra("weatherInfo", weatherInfo);
            setResult(RRFRESH_WEATHER, intent);
            pb_search.setVisibility(View.GONE);
            finish();
        }
    }
}
