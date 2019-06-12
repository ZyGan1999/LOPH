package com.pigeonhunter.loph;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.pigeonhunter.loph.handlers.KeyPress;
import com.pigeonhunter.loph.handlers.TimeCountDown;
import com.pigeonhunter.loph.view.ResultActivity;

public class GameActivity extends Activity {
    private int score = 0;
    private TextView scoretv;
    private static final String TAG = "GameActivity";
    private Button bts[][];
    private TimeCountDown tcd;
    private Queue<KeyPress> keyPressQueue;
    private long totalMusicTime; // 毫秒
    private long currentTimeLeft;// 毫秒
    private List<KeyPress> thePressedKeys;
    private MediaPlayer mp;
    private int highestCombo;

    private int txtID;
    private int mp3ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_game);

        // 获取从MusicListActivity传入的参数信息
        getMusicListParams();


        // 初始化播放器
        mp = new MediaPlayer();

        // 初始化队列
        keyPressQueue = new LinkedList<KeyPress>();
        thePressedKeys = new LinkedList<KeyPress>();

        // 计算屏幕宽度和高度
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

        // 传入GridLayout相关参数
        GridLayout gl = findViewById(R.id.GridLayout1); // rowcount = 4 , columncount = 7

        // 动态添加GridLayout中的控件-combo数，TextView
        scoretv = new TextView(this);
        scoretv.setText(""+score);
        scoretv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 70);
        GridLayout.LayoutParams tvparams = new GridLayout.LayoutParams();
        tvparams.rowSpec = GridLayout.spec(0,1);
        tvparams.columnSpec = GridLayout.spec(0,gl.getColumnCount());
        tvparams.setGravity(Gravity.CENTER);
        gl.addView(scoretv,tvparams);

        bts = new Button[gl.getRowCount()-1][gl.getColumnCount()];// one row for textview

        // Buttons
        for(int i = 0;i < (gl.getRowCount()-1)*gl.getColumnCount();++i){
            Button bt = new Button(this);
            bt.setId(i);
            bt.setBackgroundColor(getResources().getColor(R.color.aqua));
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width/(gl.getColumnCount());
            params.height = height/(gl.getRowCount());
            params.setMargins(12,2,12,2);

            final int rowid = i/(gl.getColumnCount());
            final int colid = i%(gl.getColumnCount());

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO 将被点击的按键的坐标和时间存储到容器中
                    KeyPress kp = new KeyPress(rowid,colid,currentTimeLeft);
                    kp.log();
                    thePressedKeys.add(kp);
                    //Log.e(TAG, "onClick: -------------------" );
                    //printThePressedKeys();
                }
            });
            bts[rowid][colid] = bt;
            //button2kp.put(bt,new KeyPress(i/(gl.getColumnCount()),i%(gl.getColumnCount())));
            gl.addView(bt,params);
        }

        ReadFile(txtID);
        //ReadFile(R.raw.txt_chaos);

        tcd = new TimeCountDown(totalMusicTime, 10, this);

        playMusic(mp3ID);
        //playMusic(R.raw.music_chaos);
        tcd.start();


    }

    @Override
    public void onBackPressed() {
        // 拦截返回键
        // TODO: 添加暂停功能
        mp.stop();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void ReadFile(int Rid){
        // 通过 Rid 读取对应谱面资源文件，并按照文件内容获取总时间并生成对应的 KeyPress 对象
        InputStream inputStream = getResources().openRawResource(Rid);

        String str = getString(inputStream);
        String[] arr = str.split("\n");
        for(int i = 0;i < arr.length;++i){
            //Log.e(TAG, "debugReadFile: curLine: "+arr[i] );
            if(i == 0){
                totalMusicTime = Integer.parseInt(arr[i]);
                currentTimeLeft = totalMusicTime;
                Log.e(TAG,"firstline: "+totalMusicTime);
            }
            else {
                String[] info = arr[i].split(" ");
                int rowId = Integer.parseInt(info[1]);
                int colId = Integer.parseInt(info[2]);
                int pretime = Integer.parseInt(info[0]);
                Log.e(TAG, "curline: "+rowId + colId + pretime);
                KeyPress kp = new KeyPress(rowId, colId, pretime);
                keyPressQueue.add(kp);
            }
        }
    }

    public void playMusic(int Rid){
        mp = MediaPlayer.create(this,Rid);
        mp.start();
    }

    public void pauseMusic(){
        mp.pause();
    }

    public void continueMusic(){
        mp.start();
    }

    public Button[][] getButtons(){
        return bts;
    }

    public Queue<KeyPress> getKPQueue() {
        return keyPressQueue;
    }


    public void onNotifyButton(int x, int y) {
        Log.e(TAG, "onNotifyButton: "+ x + " " +y );
        ObjectAnimator oba = ObjectAnimator.ofInt(bts[x][y],"backgroundColor",getResources().getColor(R.color.aqua),getResources().getColor(R.color.firebrick),getResources().getColor(R.color.aqua));
        oba.setDuration(500);
        oba.start();
        ObjectAnimator obaa = ObjectAnimator.ofFloat(bts[x][y],"rotation",0,360);
        obaa.setDuration(500);
        obaa.start();
    }
    public void onReadyButton(int x, int y) {
        bts[x][y].setBackgroundColor(getResources().getColor(R.color.burlywood));
    }

    /*public void onNotifyButton(int x, int y) {
        bts[x][y].setVisibility(View.INVISIBLE);

    }
    */

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



    public long getCurrentTimeLeft(){
        return currentTimeLeft;
    }

    public void minusCurrentTimeLeft(long timesplit){
        currentTimeLeft -= timesplit;
    }

    public List<KeyPress> getThePressedKeys(){
        return thePressedKeys;
    }

    public void clearThePressesKeys(){
        thePressedKeys.clear();
    }

    public void addCombo(){
        score++;
    }

    public void clearCombo(){
        score = 0;
    }

    public void Refresh(){
        scoretv.setText(""+score);
    }

    public void setHighestCombo(){
        if(score > highestCombo){
            highestCombo = score;
        }
    }

    public void getMusicListParams(){
        Intent fromMusicListIntent = getIntent();
        Bundle bundle = fromMusicListIntent.getBundleExtra("MusicSelection");
        txtID = bundle.getInt("TxtId");
        mp3ID = bundle.getInt("Mp3Id");
    }

    public void EndPlay(){
        mp.stop();
        Intent resultIntent = new Intent(GameActivity.this, ResultActivity.class);
        resultIntent.putExtra("highestCombo",highestCombo);
        startActivity(resultIntent);
    }

    public void printThePressedKeys(){
        for(KeyPress kp:thePressedKeys){
            kp.log();
        }
    }

}
