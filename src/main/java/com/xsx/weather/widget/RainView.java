package com.xsx.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XSX on 2016/3/8.
 */
public class RainView extends WeatherView{

    /**
     * 雨滴的集合
     */
    private List<RainLine> rainLines;


    private static int RAIN_COUNT = 200;


    private Paint paint;

    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 绘画线程休眠时间
     */

    public static void setRainCount(int rainCount) {
        RAIN_COUNT = rainCount;
    }


    @Override
    public void init() {
        rainLines = new ArrayList<>();
        for (int i = 0; i < RAIN_COUNT; i++) {
            rainLines.add(new RainLine(width, height));
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0.5f);
    }



    @Override
    public void drawContent(Canvas canvas) {
        for (RainLine rainLine : rainLines) {
            canvas.drawLine(rainLine.getStartX(), rainLine.getStartY(), rainLine.getStopX(), rainLine.getStopY(), paint);
        }
        if (drawThread == null) {
            drawThread = new DrawThread();
            drawThread.start();
        }
    }

    @Override
    public void changePos() {
        //更改雨滴位置
        for (RainLine rainLine : rainLines) {
            rainLine.startRain();
            if (rainLine.isOutofBounds()) {
                rainLine.reset();
            }
        }
    }

}
