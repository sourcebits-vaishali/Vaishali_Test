package com.example.vaishaliarora.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.vaishaliarora.myapplication.R;

/**
 * Created by vaishaliarora on 09/06/16.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] mImages;

    public ImageAdapter(Context context, Integer[] images){
        mContext = context;
        mImages = images;
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public Object getItem(int position) {
        return mImages[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = ViewGroup.inflate(mContext, R.layout.custom_image_view, null);
            holder.imageView = (ImageView)convertView.findViewById(R.id.custom_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imageView.setImageResource(mImages[position]);
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
    }
}
