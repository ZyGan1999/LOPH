package com.pigeonhunter.loph.handlers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pigeonhunter.loph.R;

import java.util.List;

public class SongAdapter extends ArrayAdapter {
    private final int resourceId;

    public SongAdapter(Context context, int textViewResourceId, List<Song> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = (Song) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        ImageView songImage = (ImageView) view.findViewById(R.id.song_image);//获取该布局内的图片视图
        TextView songName = (TextView) view.findViewById(R.id.song_name);//获取该布局内的文本视图
        songImage.setImageResource(song.getImageId());//为图片视图设置图片资源
        songName.setText(song.getName());//为文本视图设置文本内容
        return view;
    }
}
