package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Boolean counterisActive= false;
    Button button;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        textView.setText("0:00");
        seekBar.setProgress(00);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go");
        counterisActive = false;
    }
    public void clicked(View view){

        if(counterisActive){
           resetTimer();
        }else {
            counterisActive = true;
            seekBar.setEnabled(false);
            button.setText("STOP");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer.create(MainActivity.this, R.raw.timer).start();
                    resetTimer();
                }
            }.start();
        }
    }

public void updateTimer(int secondsLeft){
    int minutes = secondsLeft/60;
    int seconds= secondsLeft-(minutes*60);

    String secondsString = Integer.toString(seconds);
    if(seconds <= 9){
        secondsString = "0"+ secondsString ;
    }
    textView.setText(Integer.toString(minutes)+ ":" + secondsString);

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        textView = (TextView) findViewById(R.id.textView);
        button = findViewById(R.id.button2);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}