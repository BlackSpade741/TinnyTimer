package com.example.tinnytimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    ArrayList<Timer> timers = new ArrayList<Timer>();
    TimerCountdown[] runningTimers = new TimerCountdown[5];
    ArrayList<String> listContent = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int timerNums = 0;
    public static final String NEW_TIMER_TYPE_KEY = "com.example.tinnytimer.NEW_TIMER_TYPE";
    public static final String NEW_TIMER_INFO = "com.example.tinnytimmer.NEW_TIMER_INFO";
    public static final String SAVE_TIMER = "save";
    public static final String START_TIMER = "start";

    static final int ADD_TIMER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView = (ListView)findViewById(list);
        listContent.add("No timers yet. Add one now!");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, listContent);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimer(v);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTimer(View view) {
        Intent addTimerIntent = new Intent(this, AddTimerActivity.class);
        startActivityForResult(addTimerIntent, ADD_TIMER_REQUEST);

    }

    //// TODO: 2017-06-09 set this to work with any timer in the list 
    public void updateTimerText(int timerNum, final String timeAsText) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (ADD_TIMER_REQUEST) : {
                if (resultCode == Activity.RESULT_OK) {
                    String new_timer_type = data.getStringExtra(NEW_TIMER_TYPE_KEY);
                    Timer new_timer = (Timer) data.getParcelableExtra(NEW_TIMER_INFO);
                    addTimer(new_timer_type, new_timer);
                }
                break;
            }
        }
    }

    private void addTimer(String type, Timer new_timer){
        if(timerNums == 0){
            listContent.clear();
            adapter.notifyDataSetChanged();
        }
        timers.add(new_timer);
        listContent.add(new_timer.toString());
        adapter.notifyDataSetChanged();
        new_timer.setTimerNum(timerNums);
        timerNums += 1;
        if (type.equals(START_TIMER)){
            startTimer(timerNums - 1);
        }

    }

    private void startTimer(int timerNum){
        listContent.add("Timer number " + timerNum + " started!");
        adapter.notifyDataSetChanged();
    }

}
