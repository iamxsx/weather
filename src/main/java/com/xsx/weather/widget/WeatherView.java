package com.xsx.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by XSX on 2016/3/9.
 */
public abstract class WeatherView extends View{

    protected int sleepTime=50;

    protected DrawThread drawThread;

    protected int width;
    protected int height;

    public WeatherView(Context context) {
        this(context,null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Rect rect = new Rect();
        //在没有measure之前获得屏幕宽高
        getWindowVisibleDisplayFrame(rect);
        width = rect.width();
        height = rect.height();
        //Log.i("xsx", "width=" + width + ",height=" + height);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawContent(canvas);
    }

    /**
     * 初始化操作
     */
    public abstract void init();

    /**
     * 画出内容
     */
    public abstract void drawContent(Canvas canvas);
    /**
     * 改变位置
     */
    public abstract void changePos();

    /**
     * 画物件的线程
     */
    class DrawThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                //更改雨滴位置
                changePos();
                //刷新UI
                postInvalidate();
                //睡眠一会，不然太快看不清
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
