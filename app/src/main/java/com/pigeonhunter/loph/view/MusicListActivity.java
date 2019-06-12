package com.pigeonhunter.loph.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private List<Song> songList = new ArrayList<Song>();
    private final String TAG = "MusicListActivity";
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
                // TODO: 寻找用户点击的歌曲对应谱面的文件资源的id和音乐文件的id
                int txtid;
                int mp3id;
                // TODO: 将生成的参数存入传递给GameActivity，并启动GameActivity

                switch (i){
                    case 0:
                        Intent intent = new Intent(MusicListActivity.this, GameActivity.class);
                        Bundle bundle = new Bundle();
                        Toast.makeText(MusicListActivity.this,"你选择了"+i+"级曲",Toast.LENGTH_SHORT).show();
                        txtid=R.raw.txt_littlestar;
                        mp3id=R.raw.music_littlestar;
                        bundle.putInt("TxtId",txtid);
                        bundle.putInt("Mp3Id",mp3id);
                        intent.putExtra("MusicSelection",bundle);
                        startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(MusicListActivity.this, GameActivity.class);
                        Bundle bundle1 = new Bundle();
                        Toast.makeText(MusicListActivity.this,"你选择了"+i+"级曲",Toast.LENGTH_SHORT).show();
                        txtid=R.raw.txt_chaos;
                        mp3id=R.raw.music_chaos;
                        bundle1.putInt("TxtId",txtid);
                        bundle1.putInt("Mp3Id",mp3id);
                        intent1.putExtra("MusicSelection",bundle1);
                        startActivity(intent1);
                        break;

                }
            }
        });






    }
    private void initSongs()
    {
        Song song1 = new Song("               小星星", R.drawable.star);
        songList.add(song1);
        Song song2 = new Song("               chaos", R.drawable.chaos);
        songList.add(song2);
    }

}
