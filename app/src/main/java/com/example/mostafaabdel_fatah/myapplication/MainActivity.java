package com.example.mostafaabdel_fatah.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private TextView texttimer;
    private int numbtn1 = 0 , numbtn2 = 0;
    private TimerClass timer;
    private Thread thread;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        final TextView text = (TextView) findViewById(R.id.text);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("You click button :" + ++numbtn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("You click Custom :" + ++numbtn2);
            }
        });
        //*********************    Timer    **************************
        texttimer = (TextView) findViewById(R.id.timer);

        Button start = (Button) findViewById(R.id.btn1start);
        Button pause = (Button) findViewById(R.id.btn2Pause);
        Button stop = (Button) findViewById(R.id.btn2stop);
        //******************** Start Buttton Listener ******************
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (timer ==null) {
                        timer = new TimerClass(context);
                        thread = new Thread(timer);
                        thread.start();
                        timer.Start();
                    }else  if (timer!=null){
                        thread = new Thread(timer);
                        thread.start();
                        timer.Start();
                    }
                    Toast.makeText(MainActivity.this,"You Start Timer",Toast.LENGTH_LONG).show();
                }
            });

        //***************  Pause Button Listener **********************
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer !=null){
                    timer.Pause();
                    thread.interrupt();
                    thread=null;
                }
                Toast.makeText(MainActivity.this,"You Pause Timer",Toast.LENGTH_LONG).show();
            }

        });

        //***************  Stop Button Listener **********************
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer !=null){
                    timer.Stop();
                    thread.interrupt();
                    thread=null;
                    timer =null;
                }
                Toast.makeText(MainActivity.this,"You Stop Timer",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void updateTimertext(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                texttimer.setText(time);
            }
        });
    }

}

