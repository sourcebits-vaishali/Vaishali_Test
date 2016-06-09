package com.example.vaishaliarora.myapplication.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.adapter.ImageAdapter;
import com.example.vaishaliarora.myapplication.model.DummyData;

import java.io.IOException;


/**
 * Created by vaishaliarora on 09/06/16.
 */
public class OthersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.others, container, false);

        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        final Integer[] elements = DummyData.getWallpapers();
        ImageAdapter adapter = new ImageAdapter(getActivity() ,elements );
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Are you sure you want to change the wallpaper of your device?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            setWallpaper(elements[position]);
                            Toast.makeText(getActivity(), " Wallpaper changed", Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            Toast.makeText(getActivity(), "Error in settinhg wallpaper", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return view;
    }


    private void setWallpaper(int id) throws IOException{
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getActivity());
        myWallpaperManager.setResource(id);
    }
}
