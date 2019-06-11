package com.pigeonhunter.loph.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pigeonhunter.loph.GameActivity;
import com.pigeonhunter.loph.R;
import com.pigeonhunter.loph.handlers.Song;
import com.pigeonhunter.loph.handlers.SongAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* 这个Activity用于展现选歌列表，在xml中设置一个ListView
 * 它将会调用启动GameActivity，并且会传递选歌参数
 * 传递资源文件的id，对应raw中的一个谱面txt文件
 * InputStream的使用方式详见于GameActivity.debugReadFile()方法
*/

public class MusicListActivity extends AppCompatActivity {

        // TODO: 寻找用户点击的歌曲对应谱面的文件资源的id和音乐文件的id
        int txtid = 0;
        int mp3id = 0;
    private List<Song> songList = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);// TODO: Code in layout for ListView

        initSongs(); // 初始化数据
        SongAdapter adapter = new SongAdapter(MusicListActivity.this, R.layout.song_item, songList);
        ListView listView = (ListView) findViewById(R.id.music_listview);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(MusicListActivity.this,"你选择了"+i+"级曲",Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Toast.makeText(MusicListActivity.this,"你选择了"+i+"级曲",Toast.LENGTH_SHORT).show();
                        txtid=1;
                        mp3id=1;
                        break;

                    case 2:
                        Toast.makeText(MusicListActivity.this,"你选择了"+i+"级曲",Toast.LENGTH_SHORT).show();
                        txtid=2;
                        mp3id=2;
                        break;
                }
            }
        });



        // TODO: 将生成的参数存入传递给GameActivity，并启动GameActivity
        Intent intent = new Intent(MusicListActivity.this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("TxtId",txtid);
        bundle.putInt("Mp3Id",mp3id);
        intent.putExtra("MusicSelection",bundle);
        startActivity(intent);

    }
    private void initSongs()
    {
        Song song1 = new Song("little_star", R.drawable.back);
        songList.add(song1);
        Song song2 = new Song("goodbye", R.drawable.button);
        songList.add(song2);
        Song song3 = new Song("...", R.drawable.bbutton);
        songList.add(song3);
    }

}
