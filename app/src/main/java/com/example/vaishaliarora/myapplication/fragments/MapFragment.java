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
import com.example.vaishaliarora.myapplication.activities.ClusterActivity;
import com.example.vaishaliarora.myapplication.activities.GoogleMapActivity;
import com.example.vaishaliarora.myapplication.activities.PathActivity;

/**
 * Created by vaishaliarora on 13/05/16.
 */
public class MapFragment extends Fragment  {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map, container, false);
        Button map = (Button) view.findViewById(R.id.map1);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoogleMapActivity.class));
            }
        });

        Button path = (Button) view.findViewById(R.id.path);
        path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PathActivity.class));
            }
        });

        Button cluster = (Button) view.findViewById(R.id.cluster);
        cluster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClusterActivity.class));
            }
        });
        return view;
    }
}
