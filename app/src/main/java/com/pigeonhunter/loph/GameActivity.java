package com.pigeonhunter.loph;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
    private int score = 0;
    private TextView scoretv;
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

        GridLayout gl = findViewById(R.id.GridLayout1); // rowcount = 4 , columncount = 7
        scoretv = new TextView(this);
        scoretv.setText(""+score);
        GridLayout.LayoutParams tvparams = new GridLayout.LayoutParams();
        tvparams.rowSpec = GridLayout.spec(0,4);
        tvparams.setGravity(Gravity.CENTER);
        gl.addView(scoretv,tvparams);

        Button bts[][] = new Button[gl.getRowCount()][gl.getColumnCount()];


        for(int i = 0;i < 28;++i){
            Button bt = new Button(this);
            bt.setId(i);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width/gl.getColumnCount();
            params.height = height/gl.getRowCount();
            params.setMargins(2,2,2,2);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score++;
                    scoretv.setText(""+score);
                }
            });
            bts[i/gl.getRowCount()][i%gl.getColumnCount()] = bt;
            gl.addView(bt,params);
        }



    }

    @Override
    public void onBackPressed() {
        // 拦截返回键
        // TODO: 添加暂停功能
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
