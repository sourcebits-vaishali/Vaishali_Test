package com.example.vaishaliarora.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaishaliarora.myapplication.R;

/**
 * Created by vaishaliarora on 11/05/16.
 */
public class PlanetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calender, container, false);
    }
}
