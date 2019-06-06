package com.pigeonhunter.loph.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pigeonhunter.loph.GameActivity;
import com.pigeonhunter.loph.R;

import java.io.InputStream;

/* 这个Activity用于展现选歌列表，在xml中设置一个ListView
 * 它将会调用启动GameActivity，并且会传递选歌参数
 * 传递资源文件的id，对应raw中的一个谱面txt文件
 * InputStream的使用方式详见于GameActivity.debugReadFile()方法
*/

public class MusicListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);// TODO: Code in layout for ListView

        // TODO: 寻找用户点击的歌曲对应谱面的文件资源的id
        int id = 0;

        // TODO: 将生成的参数存入传递给GameActivity，并启动GameActivity
        Intent intent = new Intent(MusicListActivity.this, GameActivity.class);
        intent.putExtra("MusicSelection",id);
        startActivity(intent);

    }

}
