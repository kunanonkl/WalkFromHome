package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class main_6mwt_page extends AppCompatActivity implements SensorEventListener, StepListener{



    private static final long START_TIME_IN_MILLIS = 370000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    MediaPlayer player;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "";
    private int numSteps = 0;
    private TextView TvSteps;

    private Button finish_test_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_6mwt_page);

        // เชื่อมต่อปุ่มกับหน้า Interface
        finish_test_but = findViewById(R.id.finish_walking_test);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

       // TvSteps = (TextView) findViewById(R.id.tv_steps);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        // create new activity
        Intent to_distance_show = new Intent(main_6mwt_page.this,distance_show.class);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    sensorManager.unregisterListener(main_6mwt_page.this);
                    pauseTimer();
                } else {
                    startTimer();

                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    resetTimer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        updateCountDownText();

        finish_test_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    pauseTimer();
                    sensorManager.unregisterListener(main_6mwt_page.this);
                    GlobalVariable.step = numSteps;
                    startActivity(to_distance_show);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    sensorManager.unregisterListener(main_6mwt_page.this);
                    GlobalVariable.step = numSteps;
                    startActivity(to_distance_show);
                    finish();
                }

            }
        });


    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
                try{
                    pauseTimer();
                    GlobalVariable.step = numSteps;
                    Intent to_distance_show = new Intent(main_6mwt_page.this,distance_show.class);
                    startActivity(to_distance_show);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    pauseTimer();
                    GlobalVariable.step = numSteps;
                    Intent to_distance_show = new Intent(main_6mwt_page.this,distance_show.class);
                    startActivity(to_distance_show);
                    finish();
                }
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        numSteps =0;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        if ((minutes == 5) && (seconds == 59)){
            sensorManager.registerListener(main_6mwt_page.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
            player = MediaPlayer.create(this,R.raw.start);
            player.start();
        }
        else if ((minutes == 5) && (seconds == 0)){
            player = MediaPlayer.create(this,R.raw.min5);
            player.start();
        }
        else if ((minutes == 4) && (seconds == 0)){
            player = MediaPlayer.create(this,R.raw.min4);
            player.start();
        }
        else if ((minutes == 3) && (seconds == 0)){
            player = MediaPlayer.create(this,R.raw.min3);
            player.start();
        }
        else if ((minutes == 2) && (seconds == 0)){
            player = MediaPlayer.create(this,R.raw.min2);
            player.start();
        }
        else if ((minutes == 1) && (seconds == 0)){
            player = MediaPlayer.create(this,R.raw.min1);
            player.start();
        }
        else if ((minutes == 0) && (seconds == 0)){
            player = MediaPlayer.create(this,R.raw.end);
            player.start();
        }

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        numSteps = numSteps+1;
       // TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }
}