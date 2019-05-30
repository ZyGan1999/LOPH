package com.pigeonhunter.loph.handlers;

import android.os.CountDownTimer;

class TimeCountDown extends CountDownTimer {

    public TimeCountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // millisInFuture 总时间, countDownInterval 倒计时时间间隔
        // TODO Auto-generated constructor stub
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
