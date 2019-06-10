package com.pigeonhunter.loph;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class GameActivity extends Activity {
    private int score = 0;
    private TextView scoretv;
    private static final String TAG = "GameActivity";
    private Button bts[][];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_game);

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
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width/(gl.getColumnCount());
            params.height = height/(gl.getRowCount());
            params.setMargins(12,2,12,2);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score++;
                    scoretv.setText(""+score);
                }
            });
            bts[i/(gl.getColumnCount())][i%(gl.getColumnCount())] = bt;
            gl.addView(bt,params);
        }

        //Intent intent = getIntent();
        //int MusicSelection = intent.getIntExtra("MusicSelection",-1);
        //ReadFile(MusicSelection);

    }

    @Override
    public void onBackPressed() {
        // 拦截返回键
        // TODO: 添加暂停功能
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void ReadFile(int Rid){
        InputStream inputStream = getResources().openRawResource(Rid);

        String str = getString(inputStream);
        String[] arr = str.split("\n");
        for(int i = 0;i < arr.length;++i){
            Log.e(TAG, "debugReadFile: curLine: "+arr[i] );
        }
    }

    public Button[][] getButtons(){
        return bts;
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
