package com.example.vaishaliarora.myapplication.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.activities.CustomCalender;
import com.example.vaishaliarora.myapplication.activities.SimpleCalender;

/**
 * Created by vaishaliarora on 10/05/16.
 */
public class CalenderFragment extends Fragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calender , container , false);
        (view.findViewById(R.id.simple_cal)).setOnClickListener(this);
        (view.findViewById(R.id.custom_cal)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Class targetClass = null;
        switch (id){
            case R.id.simple_cal:
                targetClass = SimpleCalender.class;
                break;
            case R.id.custom_cal:
                targetClass = CustomCalender.class;
                break;
        }
        startActivity(new Intent(getActivity(), targetClass));
    }
}
