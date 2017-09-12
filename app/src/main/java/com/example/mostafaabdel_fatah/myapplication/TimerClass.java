package com.example.mostafaabdel_fatah.myapplication;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mostafa on 10/09/16.
 */

class TimerClass implements Runnable {

    private Context mContext;
    private int oldHoure=0,oldMinutes=0,oldseconds=0;
    private int seconds,minutes ,houres;
    private long startTime=-1;
    private boolean isRunning;
    private static final long MILLIS_TO_MINTES = 60000,MILLIS_TO_HOURES = 36000000;

    public TimerClass(Context context){
        this.mContext=context;
    }

    @Override
    public void run() {
        while (isRunning){
            long since= System.currentTimeMillis()-startTime;
            seconds= (int)((since/1000)%60)+oldseconds;
            minutes= (int)((since/MILLIS_TO_MINTES)%60)+oldMinutes;
            houres= (int)((since/MILLIS_TO_HOURES)%24)+oldHoure;
            ((MainActivity)mContext).updateTimertext(String.format("%02d:%02d:%02d",houres,minutes,seconds));
        }

    }

    public void Start() {
        startTime=System.currentTimeMillis();
        isRunning=true;
    }

    public void Pause() {
        oldHoure=houres;
        oldMinutes=minutes;
        oldseconds=seconds;
        isRunning=false;
    }

    public void Stop() {
        startTime=0;
        oldHoure=oldMinutes=oldseconds=0;
        isRunning=false;
    }
}
