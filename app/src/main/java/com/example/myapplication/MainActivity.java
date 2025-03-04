package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private static final long START_TIME_IN_MILLIS= 600000; // ustawiamy czas do odliczania
private TextView mTextViewCountDown;
private Button mButtonStartPause;
private Button mButtonReset;
private CountDownTimer mCountDownTimer;
private boolean mTimerRunning;
private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                if(mTimerRunning) {
                    pauseTimer();
                }else {
                    startTimer();
                }

        }
        });//przycisk do resetowania timera
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });
        updateCountDownText();
}//przycisk do startu timera
private void startTimer(){
    mCountDownTimer= new CountDownTimer(mTimeLeftInMillis,1000 ) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTimeLeftInMillis=millisUntilFinished;
            updateCountDownText();

        }
//okienko po wcisnieciu pause
        @Override
        public void onFinish() {
            mTimerRunning=false;
            mButtonStartPause.setText("Start");
            mButtonStartPause.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.VISIBLE);

        }
        //pojawienie sie okna pause i znikniecie reset, uruchomienie odliczania
    }.start();
    mTimerRunning=true;
    mButtonStartPause.setText("pause");
    mButtonReset.setVisibility(View.INVISIBLE);
}//pause odliczania pojawienie sie okienka start i reset
private void pauseTimer(){
    mCountDownTimer.cancel();
    mTimerRunning=false;
    mButtonStartPause.setText("Start");
    mButtonReset.setVisibility(View.VISIBLE);
}
private void resetTimer(){
    mTimeLeftInMillis=START_TIME_IN_MILLIS;
    updateCountDownText();
    mButtonReset.setVisibility(View.INVISIBLE);
    mButtonStartPause.setVisibility(View.VISIBLE);
}

private void updateCountDownText(){
    int minutes = (int) (mTimeLeftInMillis / 1000 / 60);
    int seconds = (int) (mTimeLeftInMillis / 1000 % 60);

    String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
    mTextViewCountDown.setText(timeLeftFormatted);
    }

}