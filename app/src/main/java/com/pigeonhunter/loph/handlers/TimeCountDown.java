

package com.pigeonhunter.loph.handlers;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import com.pigeonhunter.loph.handlers.KeyPress;

import com.pigeonhunter.loph.GameActivity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeCountDown extends CountDownTimer {
    private Button[][] bts;
    private static final String TAG = "TimeCountDown";
    //private long CurrentTimeLeft;
    private long TimeSplit;
    private GameActivity gameActivity;
    private Queue<KeyPress> keyPressQueue;
    private List<KeyPress> activekeyPressList;
    public TimeCountDown(long millisInFuture, long countDownInterval, GameActivity g) {
        super(millisInFuture, countDownInterval);
        // millisInFuture 总时间, countDownInterval 倒计时时间间隔 单位：毫秒
        // TODO Auto-generated constructor stub
        keyPressQueue = new LinkedList<KeyPress>();
        activekeyPressList = new LinkedList<KeyPress>();

        TimeSplit = countDownInterval;
        gameActivity = g;
        keyPressQueue = g.getKPQueue();

        for(KeyPress k : keyPressQueue){
            k.log();
        }

        if(keyPressQueue.isEmpty()){
            Log.e(TAG, "Empty");
        }

    }

    @Override
    public void onTick(long millisUntilFinished) {

        //gameActivity.onNotifyButton(0, 0);
        gameActivity.minusCurrentTimeLeft(TimeSplit);
        // TODO 判定 GameActivity 中 KeyPress 队列中需要被 notify 的 button 对象，并对其调用 onNotyfyButton 方法
        if(keyPressQueue.isEmpty()){
            return;
        }
        else {
            // step1 激活应当激活的按键

            while( keyPressQueue.peek()!=null && keyPressQueue.peek().getPressTime() > gameActivity.getCurrentTimeLeft() - 50){
                // 在精确判定时间的前 50ms 激活对应的 button
                KeyPress acKP = keyPressQueue.remove();
                Log.e(TAG, "NotifyButton: "+acKP.getRowId()+" "+acKP.getColId() );
                gameActivity.onNotifyButton(acKP.getRowId(), acKP.getColId());
                activekeyPressList.add(acKP);
            }

            // step2 对被按下的按键进行判定
            List<KeyPress> thePressesKeys = gameActivity.getThePressedKeys();
            gameActivity.clearThePressesKeys();
            for (KeyPress PressedKey:thePressesKeys){
                boolean flag = false;
                for (KeyPress activeKey:activekeyPressList){
                    if (PressedKey.compareAccuracy(activeKey)){
                        flag = true;
                        activekeyPressList.remove(activeKey);
                    }
                }
                if (flag){
                    gameActivity.addCombo();
                }
                else {
                    gameActivity.clearCombo();
                }
            }

            // step3 清算被激活但是没有被按下的按键
            List<Integer> indexNeedtoRemove;
            /*
            for (KeyPress activeKey:activekeyPressList) {
                if (activeKey.getPressTime() > gameActivity.getCurrentTimeLeft() + 50){
                    // 在精确判定时间的后 50ms 清除对应的 kp 对象
                    indexNeedtoRemove.add()
                    activekeyPressList.remove(activeKey);
                    // 清空combo
                    gameActivity.clearCombo();
                }
            }
            */
            Iterator<KeyPress> iterator = activekeyPressList.iterator();
            while (iterator.hasNext()) {
                KeyPress kp = iterator.next();
                if (kp.getPressTime() > gameActivity.getCurrentTimeLeft() + 50) {
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
        }
        gameActivity.Refresh();
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub

    }

}

