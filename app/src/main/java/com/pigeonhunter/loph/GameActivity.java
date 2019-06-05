package com.pigeonhunter.loph;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
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

public class GameActivity extends Activity {
    private int score = 0;
    private TextView scoretv;
    private static final String TAG = "GameActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_game);


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

        GridLayout gl = findViewById(R.id.GridLayout1); // rowcount = 4 , columncount = 6


        Button bts[][] = new Button[gl.getRowCount()][gl.getColumnCount()];


        for(int i = 0;i < 24;++i){
            Button bt = new Button(this);
            bt.setId(i);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width/(gl.getColumnCount()+1);
            params.height = height/gl.getRowCount();
            params.setMargins(2,2,2,2);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score++;
                    scoretv.setText(""+score);
                }
            });
            //Log.i(TAG, ""+i/gl.getColumnCount()+i%gl.getColumnCount());
            bts[i/(gl.getColumnCount())][i%(gl.getColumnCount())] = bt;
            gl.addView(bt,params);
        }
        scoretv = new TextView(this);
        scoretv.setText(""+score);
        GridLayout.LayoutParams tvparams = new GridLayout.LayoutParams();
        tvparams.rowSpec = GridLayout.spec(0,4);
        tvparams.columnSpec = GridLayout.spec(0,2);
        tvparams.setGravity(Gravity.CENTER);
        gl.addView(scoretv,tvparams);

        debugReadFile();

    }

    @Override
    public void onBackPressed() {
        // 拦截返回键
        // TODO: 添加暂停功能
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void debugReadFile(){
        InputStream inputStream = getResources().openRawResource(R.raw.test);

        String str = getString(inputStream);
        Log.e(TAG, "onCreate: ----str------" + str);

        String[] arr = str.split("\\s+");
        for (String ss : arr) {
            Log.e(TAG, "onCreate: -------ss------" + ss);
        }
    }

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


}
