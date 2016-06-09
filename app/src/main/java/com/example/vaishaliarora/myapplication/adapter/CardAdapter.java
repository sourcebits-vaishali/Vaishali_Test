package com.example.vaishaliarora.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.model.CustomItems;

import java.util.List;

/**
 * Created by vaishaliarora on 09/06/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context mContext;
    private List<CustomItems> mElements;

    public CardAdapter(Context context, List<CustomItems> items){
        mContext = context;
        mElements = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_activity, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitleView.setText(mElements.get(position).getItem());
        holder.mTypeView.setText(mElements.get(position).getHeader());
    }


    @Override
    public int getItemCount() {
        return mElements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView, mTypeView;
        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView)v.findViewById(R.id.name);
            mTypeView = (TextView)v.findViewById(R.id.type);
        }
    }
}
