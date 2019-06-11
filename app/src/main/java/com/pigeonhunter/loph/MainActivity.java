package com.pigeonhunter.loph;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pigeonhunter.loph.GameActivity;
import com.pigeonhunter.loph.R;
import com.pigeonhunter.loph.view.MusicListActivity;
import com.pigeonhunter.loph.view.ResultActivity;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    protected void onCreate(Bundle savedInstanceState) {//onCreate用来创建一个activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startbt);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(newIntent);
            }
        });
    }

}

