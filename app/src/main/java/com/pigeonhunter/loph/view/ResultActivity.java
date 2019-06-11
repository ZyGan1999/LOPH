package com.pigeonhunter.loph.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pigeonhunter.loph.MainActivity;
import com.pigeonhunter.loph.R;

public class ResultActivity extends AppCompatActivity {
    private TextView scoretv;
    private Button backbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_result);
        // TODO: 从Intent中获得分数参数score
        final Intent intent = getIntent();
        int Score = intent.getIntExtra("Score",-1);

        // 将获取的分数转化为TextView
        scoretv = findViewById(R.id.score);
        scoretv.setText(""+Score);

        // 绑定返回键
        backbt = findViewById(R.id.BackBt);
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(backToMainIntent);
            }
        });

    }
}
