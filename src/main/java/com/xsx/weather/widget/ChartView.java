package com.xsx.weather.widget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xsx.weather.Temp;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义折线图
 * Created by XSX on 2016/3/6.
 */
public class ChartView extends View {

    /**
     * x轴原点
     */
    private static int OX = 0;
    /**
     * y轴原点
     */
    private static int OY = 0;
    /**
     * y轴高度
     */
    private static final int Y_LENGTH = 0;
    /**
     * X轴高度
     */
    private static final int X_LENGTH = 0;
    /**
     * 一条线上点的数量
     */
    private static final int POINT_NUM = 5;
    /**
     * 坐标上线段的长度
     */
    private static final int len = 20;

    /*
    * (21,14)
    * (26,17)
    * (26,18)
    * (26,22)
    * (21,14)
    * 这样子的一些点
    * */

    /**
     * 代表温度的点
     */
    private List<Temp> temps;
    private Context context;

    private int width;
    private int height;
    private Paint paint;
    /**
     * x轴间隙
     */
    private int spaceX;
    /**
     * y轴间隙
     */
    private int spaceY;
    /**
     * 记录最低温度的点
     */
    List<PointF> lPoints = new ArrayList<>();
    /**
     * 记录最高温度的点
     */
    List<PointF> hPoints = new ArrayList<>();

    public void setTemps(List<Temp> temps) {
        this.temps = temps;
    }

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        spaceX = width / 6;
        spaceY = height / 4;
        Log.i("xsx", "width2=" + width + ",height2=" + height);
        int x = 0;
        //画x轴
        canvas.drawLine(0, height, width, height, paint);
        //画x轴上的线段
        paint.setStrokeWidth(1);
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(i * spaceX, height, i * spaceX, height - len, paint);
        }
//        //画y轴
//        canvas.drawLine(0, 0, 0, height, paint);
//        for (int i = 0; i < 4; i++) {
//            canvas.drawLine(0, i * spaceY, len, i * spaceY, paint);
//        }
        if (temps != null) {
            paint.setStrokeWidth(2.0f);
            for (Temp t : temps) {
                float lt = t.getLt();
                float ht = t.getHt();
                showlog("ht=" + ht + ",lt=" + lt);
                lt = (4 - lt / 10) * spaceY;
                ht = (4 - ht / 10) * spaceY;
                showlog("ht=" + ht + ",lt=" + lt);
                canvas.drawCircle(x, lt, 2, paint);
                canvas.drawCircle(x, ht, 2, paint);
                lPoints.add(new PointF(x, lt));
                hPoints.add(new PointF(x, ht));
                x += spaceX;
            }
            paint.setStrokeWidth(2f);
            //画最低温度曲线
            for (int i = 0; i < lPoints.size() - 1; i++) {
                canvas.drawLine(lPoints.get(i).x, lPoints.get(i).y, lPoints.get(i + 1).x, lPoints.get(i + 1).y, paint);
            }
            //画最高温度曲线
            for (int i = 0; i < hPoints.size() - 1; i++) {
                canvas.drawLine(hPoints.get(i).x, hPoints.get(i).y, hPoints.get(i + 1).x, hPoints.get(i + 1).y, paint);
            }
        }

    }

    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void showlog(String msg) {
        Log.i("xsx", msg);
    }
}
