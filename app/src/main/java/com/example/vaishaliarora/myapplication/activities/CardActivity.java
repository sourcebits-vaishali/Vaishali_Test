package com.example.vaishaliarora.myapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.adapter.CardAdapter;
import com.example.vaishaliarora.myapplication.model.CustomItems;
import com.example.vaishaliarora.myapplication.model.DummyData;

import java.util.List;

/**
 * Created by vaishaliarora on 12/05/16.
 */
public class CardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);
        (findViewById(R.id.toggle)).setVisibility(View.GONE);

        RecyclerView listView = (RecyclerView)findViewById(R.id.list_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        List<CustomItems> elements = DummyData.getItems();

        CardAdapter adapter = new CardAdapter(this, elements);
        listView.setAdapter(adapter);
    }
}
