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
import com.example.vaishaliarora.myapplication.activities.GooglePlusActivity;

/**
 * Created by vaishaliarora on 12/05/16.
 */
public class SocialNetworkFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.social_network,container,false);
        (view.findViewById(R.id.fb)).setOnClickListener(this);
        (view.findViewById(R.id.tw)).setOnClickListener(this);
        (view.findViewById(R.id.ln)).setOnClickListener(this);
        (view.findViewById(R.id.google_plus)).setOnClickListener(this);
        (view.findViewById(R.id.yahoo)).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        
        int id = v.getId();
        switch (id){
            case R.id.fb:
                break;
            case R.id.tw:
                break;
            case R.id.ln:
                break;
            case R.id.google_plus:
                startActivity(new Intent(getActivity() , GooglePlusActivity.class));
                break;
            case R.id.yahoo:
                break;
        }
    }
}
