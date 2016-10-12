package com.example.jelliott.idealapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jelliott on 10/10/2016.
 */

public class LevelAdapter extends BaseAdapter {
    private Context CTX;
    private Integer levelID[] = {R.drawable.family_photo};


    public LevelAdapter(Context CTX) {
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
        ImageView img = null;
        TextView text;
        for (int i = 0; i < 100; i++) {
            //if (convertView == null) {
            img = new ImageView(CTX);
            img.setLayoutParams(new GridView.LayoutParams(160, 160));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8, 8, 8, 8);
            text = new TextView(CTX);
            text.setText("Level");
            text.setLayoutParams(new GridView.LayoutParams(100, 100));
            img.setImageResource(levelID[position]);

//            } else {
//                img = (ImageView) convertView;
//                text = new TextView(CTX);
//                text.setText("Level");
//            }
        }
        //img.setImageResource(levelID[position]);
        return img;
    }
}
