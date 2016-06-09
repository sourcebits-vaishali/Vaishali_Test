package com.example.vaishaliarora.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vaishaliarora.myapplication.R;

/**
 * Created by vaishaliarora on 06/06/16.
 */
public class ImageLoaderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_loader_fragment , container , false);
        /*ListView universalImageList = (ListView)view.findViewById(R.id.universal_list);
        universalImageList.setAdapter(new UniversalImageAdapter(getActivity()));*/
        return view;
    }


}
