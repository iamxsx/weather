package com.xsx.weather.widget;

import android.animation.ObjectAnimator;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机产生闪电，显示完后小时，再继续显示
 * Created by XSX on 2016/3/9.
 */
public class Thunder {

    private int startX;
    private int startY;
    private int stopX;
    private int stopY;
    private int deltaX = 200;
    private int deltaY = 320;

    /**
     * 最大X
     */
    private int maxX;
    /**
     * 最大Y
     */
    private int maxY;
    private Random random;

    public Thunder(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        random = new Random();
        init();
    }

    public void init() {
        startX = random.nextInt(maxX);
        startY = random.nextInt(maxY);
        stopX = startX + deltaX;
        stopY = startY + deltaY;
    }

    /**
     * 控制闪电的显示动画，闪现之后消失
     */
    public void animate() {
        Looper.prepare();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 0.3f, 0.5f, 0.7f, 1f, 0.7f, 0.5f, 0.3f, 0f);
        objectAnimator.setDuration(1000).start();
        Looper.loop();
    }

    Paint paint;

    /**
     * 绘制闪电的方法
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        drawLightning(startX, startY, stopX, stopY, 10, canvas);
        //drawThunder(canvas);
        //canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    private void drawThunder(Canvas canvas) {
        //闪电节点的偏移量
        int delta = random.nextInt(10);
        //闪电的节点数量/2
        int count = random.nextInt(4);
        //记录所有的节点
        List<Point> points = new ArrayList<>();
        int centerX, centerY;
        int staX = startX;
        int stoX = stopX;
        int staY = startY;
        int stoY = stopY;

        //中点X
        centerX = (staX + stoX) / 2;
        //中点Y
        centerY = (staY + stoY) / 2;
        points.add(new Point(centerX, centerY));
        Point p1 = getCenterPoint(staX, staY, centerX, centerY);
        Point p2 = getCenterPoint(centerX, centerY, stopX, stopY);
        points.add(p1);
        points.add(p2);

    }

    Paint mLighnitngColorPaint;
    Paint mLighnitngGlowPaintBold;

    public void drawLightning(float x1, float y1, float x2, float y2,
                              int paramInt, Canvas paramCanvas) {

        setUpPaint();
        Random localRandom = new Random();
        if (paramInt < localRandom.nextInt(7)) {
            paramCanvas.drawLine(x1, y1, x2, y2, mLighnitngColorPaint);
            paramCanvas.drawLine(x1, y1, x2, y2, mLighnitngColorPaint);
            paramCanvas.drawLine(x1, y1, x2, y2, mLighnitngGlowPaintBold);
            return;
        }
        float x3 = 0, y3 = 0;
        if (localRandom.nextBoolean()) {
            x3 = (float) ((x2 + x1) / 2.0F + ((localRandom.nextInt(8) - 0.5D) * paramInt));
        } else {
            x3 = (float) ((x2 + x1) / 2.0F - ((localRandom.nextInt(8) - 0.5D) * paramInt));
        }
        if (localRandom.nextBoolean()) {
            y3 = (float) ((y2 + y1) / 2.0F + ((localRandom.nextInt(5) - 0.5D) * paramInt));
        } else {
            y3 = (float) ((y2 + y1) / 2.0F - ((localRandom.nextInt(5) - 0.5D) * paramInt));
        }
        drawLightning(x1, y1, x3, y3, paramInt / 2, paramCanvas);
        drawLightning(x2, y2, x3, y3, paramInt / 2, paramCanvas);
        return;

    }

    /**
     * 设置画笔
     */
    private void setUpPaint() {
        mLighnitngColorPaint=new Paint();
        mLighnitngColorPaint.setAntiAlias(true);
        mLighnitngColorPaint.setColor(Color.WHITE);
        mLighnitngGlowPaintBold=new Paint();
        mLighnitngGlowPaintBold.setAntiAlias(true);
        mLighnitngGlowPaintBold.setColor(Color.parseColor("#7526f7"));
        mLighnitngGlowPaintBold.setMaskFilter(new BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID));
    }

    public Point getCenterPoint(int startX, int startY, int stopX, int stopY) {
        return new Point((startX + stopX) / 2, (startY + stopY) / 2);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStopX() {
        return stopX;
    }

    public int getStopY() {
        return stopY;
    }


}
