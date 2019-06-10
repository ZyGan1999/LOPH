

package com.pigeonhunter.loph.handlers;

import android.os.CountDownTimer;
import android.widget.Button;

import com.pigeonhunter.loph.GameActivity;

class TimeCountDown extends CountDownTimer {
    private Button[][] bts;
    private long CurrentTimeLeft;
    private long TimeSplit;

    public TimeCountDown(long millisInFuture, long countDownInterval, Button[][] InputBts) {
        super(millisInFuture, countDownInterval);
        // millisInFuture 总时间, countDownInterval 倒计时时间间隔 单位：毫秒
        // TODO Auto-generated constructor stub
        bts = InputBts;
        CurrentTimeLeft = millisInFuture;
        TimeSplit = countDownInterval;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
    }

}

