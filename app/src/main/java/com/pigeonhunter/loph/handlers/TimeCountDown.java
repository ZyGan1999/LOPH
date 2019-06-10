

package com.pigeonhunter.loph.handlers;

import android.os.CountDownTimer;
import android.widget.Button;
import com.pigeonhunter.loph.handlers.KeyPress;

import com.pigeonhunter.loph.GameActivity;

public class TimeCountDown extends CountDownTimer {
    private Button[][] bts;
    private long CurrentTimeLeft;
    private long TimeSplit;
    private GameActivity gameActivity;
    public TimeCountDown(long millisInFuture, long countDownInterval, GameActivity g) {
        super(millisInFuture, countDownInterval);
        // millisInFuture 总时间, countDownInterval 倒计时时间间隔 单位：毫秒
        // TODO Auto-generated constructor stub

        CurrentTimeLeft = millisInFuture;
        TimeSplit = countDownInterval;
        gameActivity = g;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        gameActivity.onNotifyButton(0, 0);
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub

    }

}

