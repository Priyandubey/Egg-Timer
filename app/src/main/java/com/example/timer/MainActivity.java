package com.example.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    boolean status = false ;
    Button button;
    CountDownTimer countDownTimer;

    public void display(long timeDisplay){
        long minutes = timeDisplay/60;
        long seconds = timeDisplay - minutes*60;

        String sm="",ss="";
        if(minutes < 10) sm+="0";
        sm+=minutes;
        if(seconds < 10) ss+="0";
        ss+=seconds;
        textView.setText(sm + " " + ":" + " " + ss);
    }


    public void controlTimer(View view){

        if(!status){

            button.setTextKeepState("STOP");

            seekBar.setEnabled(false);

            countDownTimer = new CountDownTimer(1000 * seekBar.getProgress() + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    display(millisUntilFinished / 1000);


                }

                @Override
                public void onFinish() {
                    seekBar.setEnabled(true);
                    display(30);
                    seekBar.setProgress(30);
                    button.setTextKeepState("GO");
                    status = false;
                }


            }.start();
            status = true;

        }else{
            seekBar.setEnabled(true);
            display(30);
            seekBar.setProgress(30);
            button.setTextKeepState("GO");
            status = false;
            countDownTimer.cancel();
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if(progress < 1){
                    progress = 1;
                    seekBar.setProgress(1);
                }


                long minutes = progress/60;
                long seconds = progress - minutes*60;

                String sm="",ss="";
                if(minutes < 10) sm+="0";
                sm+=minutes;
                if(seconds < 10) ss+="0";
                ss+=seconds;
                textView.setText(sm + " " + ":" + " " + ss);

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
