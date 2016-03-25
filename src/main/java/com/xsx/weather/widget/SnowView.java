package com.xsx.weather.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;

import com.xsx.weather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XSX on 2016/3/9.
 */
public class SnowView extends WeatherView{

    /**
     * 雪的集合
     */
    private List<Snow> snows;

    private static int SNOW_COUNT = 50;

    private Paint paint;

    private Bitmap bitmap;

    public SnowView(Context context) {
        super(context);
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.outWidth=24;
        options.outHeight=24;
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ice,options);
        snows = new ArrayList<>();
        for (int i = 0; i < SNOW_COUNT; i++) {
            snows.add(new Snow(width, height));
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0.5f);
    }

    @Override
    public void drawContent(Canvas canvas) {
        for (Snow snow : snows) {
            //canvas.drawBitmap(bitmap,snow.getStartX(),snow.getStartY(),paint);
            canvas.drawCircle(snow.getStartX(),snow.getStartY(),snow.getRadius(),paint);
        }
        if (drawThread == null) {
            drawThread = new DrawThread();
            drawThread.start();
        }
    }

    @Override
    public void changePos() {
        for (Snow snow : snows) {
            snow.startSnow();
            if (snow.isOutofBounds()) {
                snow.reset();
            }
        }
    }
}
