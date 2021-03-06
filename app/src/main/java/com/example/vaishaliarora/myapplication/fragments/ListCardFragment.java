package com.example.vaishaliarora.myapplication.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.activities.CardActivity;
import com.example.vaishaliarora.myapplication.activities.RecyclerViewActivity;

/**
 * Created by vaishaliarora on 12/05/16.
 */
public class ListCardFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_card, container, false);
        (view.findViewById(R.id.recycler_list)).setOnClickListener(this);
        (view.findViewById(R.id.card)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Class targetClass = null;
        switch(id){
            case R.id.recycler_list:
                targetClass = RecyclerViewActivity.class;
                break;
            case R.id.card:
                targetClass = CardActivity.class;
                break;
        }

        startActivity(new Intent(getActivity(), targetClass));
    }
}
