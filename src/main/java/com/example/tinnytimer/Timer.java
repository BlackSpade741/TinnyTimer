package com.example.tinnytimer;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

class Timer implements Parcelable {
    private String name = "Alarm";
    private int timerNum;
    private int numHours;
    private int numMinutes;
    private int numSeconds;
    private boolean vibrate = false;
    private boolean sound = true;
    private String ringtone = "default";
    private TimerCountdown activeTimer = null;

    Timer(int hours, int minutes, int seconds){
        numHours = hours;
        numMinutes = minutes;
        numSeconds = seconds;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

    public void setHours(int newHours){
        numHours = newHours;
    }

    public int getHours(){
        return numHours;
    }

    public void setMinutes(int newMinutes){
        numMinutes = newMinutes;
    }

    public int getMinutes(){
        return numMinutes;
    }

    public void setSeconds(int newSeconds){
        numHours = newSeconds;
    }

    public int getSeconds(){
        return numSeconds;
    }

    public void setRingtone(String newRingtone){
        ringtone = newRingtone;
    }

    public String getRingtone(){
        return ringtone;
    }

    public void setTimerNum(int num) {timerNum = num; }

    public int getTimerNum() {return timerNum; }

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(numHours);
        out.writeInt(numMinutes);
        out.writeInt(numSeconds);
        out.writeInt((vibrate) ? 1 : 0);
        out.writeInt((sound) ? 1 : 0);
        out.writeString(ringtone);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Timer> CREATOR = new Parcelable.Creator<Timer>() {
        public Timer createFromParcel(Parcel in) {
            return new Timer(in);
        }

        public Timer[] newArray(int size) {
            return new Timer[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Timer(Parcel in) {
        name = in.readString();
        numHours = in.readInt();
        numMinutes = in.readInt();
        numSeconds = in.readInt();
        vibrate = in.readInt() == 1;
        sound = in.readInt() == 1;
        ringtone = in.readString();
    }

    public String toString(){
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", numHours, numMinutes,
                numSeconds);
    }
}
