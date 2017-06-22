package com.example.tinnytimer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class AddTimerActivity extends AppCompatActivity {
    int numHours, numMinutes, numSeconds;
    public static final String NEW_TIMER_TYPE_KEY = "com.example.tinnytimer.NEW_TIMER_TYPE";
    public static final String NEW_TIMER_INFO = "com.example.tinnytimmer.NEW_TIMER_INFO";
    public static final String SAVE_TIMER = "save";
    public static final String START_TIMER = "start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer);

        //Set up hours spinner
        Spinner spnHours = (Spinner) findViewById(R.id.spnHours);
        ArrayAdapter<CharSequence> adpHours = ArrayAdapter.createFromResource(this,
                R.array.array_clock, android.R.layout.simple_spinner_item);
        adpHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHours.setAdapter(adpHours);
        spnHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numHours = Integer.parseInt((String)parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Set up minutes spinner
        Spinner spnMinutes = (Spinner) findViewById(R.id.spnMinutes);
        ArrayAdapter<CharSequence> adpMinutes = ArrayAdapter.createFromResource(this,
                R.array.array_clock, android.R.layout.simple_spinner_item);

        adpMinutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMinutes.setAdapter(adpMinutes);
        spnMinutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numMinutes = Integer.parseInt((String)parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Set up seconds spinner
        Spinner spnSeconds = (Spinner) findViewById(R.id.spnSeconds);
        ArrayAdapter<CharSequence> adpSeconds = ArrayAdapter.createFromResource(this,
                R.array.array_clock, android.R.layout.simple_spinner_item);
        adpSeconds.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSeconds.setAdapter(adpSeconds);
        spnSeconds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numSeconds = Integer.parseInt((String)parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        //Set up cancel button
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        //Set up save button
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numHours != 0 || numMinutes != 0 || numSeconds != 0){
                    Timer timer = new Timer(numHours, numMinutes, numSeconds);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(NEW_TIMER_TYPE_KEY, SAVE_TIMER);
                    resultIntent.putExtra(NEW_TIMER_INFO, timer);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        //Set up start button
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numHours != 0 || numMinutes != 0 || numSeconds != 0){
                    Timer timer = new Timer(numHours, numMinutes, numSeconds);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(NEW_TIMER_TYPE_KEY, START_TIMER);
                    resultIntent.putExtra(NEW_TIMER_INFO, timer);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }


}
