package com.example.tinnytimer;


import android.content.Context;

import java.util.Locale;

class TimerCountdown implements Runnable {
    private static final int SEC_TO_MILLIS = 1000;
    private static final int MIN_TO_MILLIS = 60000;
    private static final int HOUR_TO_MILLIS = 3600000;
    private Timer timer;
    private long startTime, endTime;
    private Context mContext;
    private boolean isRunning;

    public TimerCountdown(Context context, Timer t){
        timer = t;
        mContext = context;
    }

    public void start(){
        startTime = System.currentTimeMillis();
        endTime = getEndTime(startTime);
        isRunning = true;
    }

    private long getEndTime(long start){
        return start + timer.getSeconds() * SEC_TO_MILLIS + timer.getMinutes() * MIN_TO_MILLIS +
                timer.getHours() * HOUR_TO_MILLIS;
    }

    private void finish(){
        isRunning = false;
    }

    @Override
    public void run(){
        while(isRunning){
            if (System.currentTimeMillis() >= endTime) finish();
            else{
                long since = System.currentTimeMillis() - startTime;

                //convert the resulted time difference into hours, minutes, seconds and milliseconds
                int seconds = (int) (since / SEC_TO_MILLIS) % 60;
                int minutes = (int) ((since / (MIN_TO_MILLIS)) % 60);
                int hours = (int) ((since / (HOUR_TO_MILLIS)));

                if (seconds > timer.getSeconds()) {
                    seconds = timer.getSeconds() + 60 - seconds;
                    minutes += 1;
                }
                else seconds = timer.getSeconds() - seconds;

                if (minutes > timer.getMinutes()) {
                    minutes = timer.getMinutes() + 60 - minutes;
                    hours += 1;
                }
                else minutes = timer.getMinutes() - minutes;

                hours = timer.getHours() - hours;

                ((MainActivity) mContext).updateTimerText(timer.getTimerNum(),
                        String.format(Locale.getDefault(), "%02d:%02d:%02d",
                                hours, minutes, seconds));

                //Sleep the thread for a short amount, to prevent high CPU usage!
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
