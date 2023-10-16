package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSave;
    Button btnStart;
    Button btnResume;
    Button btnPause;
    TextView statusTime;
    ProgressBar progressBar;
    long totalTime = 0;
    long saveTotalTime;
    int interval = 1;
    long s1 = 0;

    NumberPicker hourPicker;
    NumberPicker minutePicker;
    NumberPicker secondPicker;
    CountDownTimer countDownTimer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.resume);
        btnPause = findViewById(R.id.pause);
        statusTime = findViewById(R.id.countDownTime);
        progressBar = findViewById(R.id.progressBar);
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);
        btnSave = dialog.findViewById(R.id.btnSave);
        hourPicker = dialog.findViewById(R.id.hourPicker);
        minutePicker = dialog.findViewById(R.id.minutePicker);
        secondPicker = dialog.findViewById(R.id.secondPicker);
        //hourPicker.setDisplayedValues(new String[] {"01", "02"});
        hourPicker.setMaxValue(23);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        statusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int hour = hourPicker.getValue();
                        int minute = minutePicker.getValue();
                        int second = secondPicker.getValue();
                        String time = String.format("%02d:%02d:%02d", hour, minute, second);
                        statusTime.setText(time);
                        totalTime = convertTextViewTimeToSeconds(statusTime) * 1000 + 1000;
                        saveTotalTime = totalTime;
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPause.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
                countDownTimer = new CountDownTimer(totalTime, interval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000);
                        int hours = seconds / (60 * 60);
                        int tempMint = (seconds - (hours * 60 * 60));
                        int minutes = tempMint / 60;
                        seconds = tempMint - (minutes * 60);
                        s1 = (long) millisUntilFinished;

                        int progress = (int) ((saveTotalTime - millisUntilFinished) * 100 / saveTotalTime);
                        progressBar.setProgress(progressBar.getMax() - progress, true);
                        statusTime.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                    }

                    @Override
                    public void onFinish() {
                        progressBar.setProgress(100);
                        countDownTimer.cancel();
                        btnPause.setVisibility(View.INVISIBLE);
                        btnStart.setVisibility(View.VISIBLE);
                        saveTotalTime = 0;
                        totalTime = 0;
                    }
                }.start();
            }
        });



        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnResume.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                countDownTimer.cancel();


            }
        });
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                totalTime = s1;
                countDownTimer = null;
                countDownTimer = new CountDownTimer(totalTime, interval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000);
                        int hours = seconds / (60 * 60);
                        int tempMint = (seconds - (hours * 60 * 60));
                        int minutes = tempMint / 60;
                        seconds = tempMint - (minutes * 60);
                        s1 = (long) millisUntilFinished;
                        int progress = (int) ((saveTotalTime - millisUntilFinished) * 100 / saveTotalTime);
                        progressBar.setProgress(progressBar.getMax() - progress, true);
                        statusTime.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                    }

                    @Override
                    public void onFinish() {
                        totalTime = 0;
                        saveTotalTime = 0;
                        progressBar.setProgress(100);
                        countDownTimer.cancel();
                        btnPause.setVisibility(View.INVISIBLE);
                        btnStart.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        });

    }
    public long convertTextViewTimeToSeconds(TextView textView) {
        String str = textView.getText().toString();
        String[] timeParts = str.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        int second = Integer.parseInt(timeParts[2]);

        long seconds = (hour * 3600) + (minute * 60) + second;
        return seconds;
    }

}