package com.xsx.weather.widget;

import java.util.Random;

/**
 * 代表一个雨滴
 * Created by XSX on 2016/3/8.
 */
public class RainLine {

    private int startX;
    private int startY;
    private int stopX;
    private int stopY;
    private int deltaX=10;
    private int deltaY=20;

    /**
     * 最大X
     */
    private int maxX;
    /**
     * 最大Y
     */
    private int maxY;
    private Random random;

    public RainLine(int maxX, int maxY) {
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
     * 开始下雨
     */
    public void startRain() {
        //为了使雨不是一样长
        startX += deltaX;
        startY += deltaY;
        stopX += deltaX;
        stopY += deltaY;
    }

    public boolean isOutofBounds() {
        boolean out = false;
        if (startX > maxX || startY > maxY) {
            //重置起点，再次开始移动
            reset();
            out = true;
        }
        return out;
    }

    public void reset() {
        //随机使雨点从x轴或从y轴出现
        if (random.nextBoolean()) { //从y轴出现
            startX = 0;
            startY = random.nextInt(maxY);
        } else {    //从x轴出现
            startY = 0;
            startX = random.nextInt(maxX);
        }
        int n=random.nextInt(10);
        stopX = (int) (startX + deltaX*(n/10f));
        stopY = (int) (startY + deltaY*(n/10f));
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
