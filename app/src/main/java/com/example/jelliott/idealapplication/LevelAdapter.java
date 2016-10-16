package com.example.jelliott.idealapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jelliott on 10/10/2016.
 */

class LevelAdapter extends BaseAdapter {
    private Context CTX;
    private Integer levelID[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    LevelAdapter(Context CTX) {
        this.CTX = CTX;
    }

    @Override
    public int getCount() {
        return levelID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View levelView;
//        ImageView img = null;
//        TextView text;
        //for (int i = 0; i < 100; i++) {
        if (convertView == null) {

            LayoutInflater li = LayoutInflater.from(CTX);
            levelView = li.inflate(R.layout.level_adapter_layout, null);
            levelView.setLayoutParams(new GridView.LayoutParams(200, 300));
            TextView tv = (TextView) levelView.findViewById(R.id.levelIcon_title);
//                tv.setPadding(8, 8, 8, 8);
            tv.setText(String.valueOf(position));
            ImageView iv = (ImageView) levelView.findViewById(R.id.levelIcon_imageView);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                iv.setPadding(8, 8, 8, 8);
            iv.setImageResource(R.mipmap.ic_launcher);
//            img = new ImageView(CTX);
//            img.setLayoutParams(new GridView.LayoutParams(160, 160));
//            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            img.setPadding(8, 8, 8, 8);
//            text = new TextView(CTX);
//            text.setText("Level");
//            text.setLayoutParams(new GridView.LayoutParams(100, 100));
            //parent.addView(img);
            //parent.addView(text);
            //img.setImageResource(levelID[position]);
        } else {
//                img = (ImageView) convertView;
//                text = new TextView(CTX);
//                text.setText("Level");

            levelView = convertView;
        }
        //}
//        img.setImageResource(levelID[position]);
//        return img;
        return levelView;
    }
}
