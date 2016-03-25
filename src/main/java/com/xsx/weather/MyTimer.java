package com.xsx.weather;

import android.os.Handler;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by XSX on 2016/3/6.
 */
public class MyTimer{

    public static final int REFERSH_UI = 200;
    Handler handler;
    Timer timer;
    TimerTask timerTask;
    boolean isStart;

    public MyTimer(Handler handler){
        this.handler=handler;
        timer=new Timer();
    }
    
    public void startTimer(){
        if (timer==null){
            return;
        }
        timerTask=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(REFERSH_UI);
            }
        };
        timer.schedule(timerTask,1000,30);
        isStart=true;
    }

    public void stopTimer(){
        if (!isStart){
            return;
        }
        if (timerTask!=null){
            timerTask.cancel();
            isStart=false;
            timerTask=null;
        }
        Log.i("xsx","停止计时");
    }
}
