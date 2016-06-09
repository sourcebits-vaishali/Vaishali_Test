package com.example.vaishaliarora.myapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.adapter.RecyclerViewAdapter;
import com.example.vaishaliarora.myapplication.model.DividerItemDecoration;
import com.example.vaishaliarora.myapplication.model.UIConfiguration;

public class RecyclerViewActivity extends Activity {

    private GridLayoutManager mManager;

    private RecyclerViewAdapter mAdapter;
    private UIConfiguration.ViewMode mCurrentMode;
    private UIConfiguration.ViewMode mNormalMode = UIConfiguration.ViewMode.NORMAL_VIEW;
    private UIConfiguration.ViewMode mGridMode = UIConfiguration.ViewMode.GRID_VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        Button toggle = (Button)findViewById(R.id.toggle);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);


        mManager = new GridLayoutManager(this, RecyclerViewAdapter.getUiSpanCount());
        recyclerView.setLayoutManager(mManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));

        mAdapter = new RecyclerViewAdapter(this , UIConfiguration.getListElements());
        mAdapter.createDataModel();
        recyclerView.setAdapter(mAdapter);

        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mAdapter == null || !mAdapter.isHeader(position)) ? (mAdapter.isFooter(position) ? mManager.getSpanCount() : 1) : mManager.getSpanCount();
            }
        });

        mCurrentMode = mNormalMode;
        UIConfiguration.setViewMode(mCurrentMode);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager != null) {
                    mCurrentMode = (mCurrentMode == UIConfiguration.ViewMode.NORMAL_VIEW) ? mGridMode : mNormalMode;
                    UIConfiguration.setViewMode(mCurrentMode);
                    mManager.setSpanCount(RecyclerViewAdapter.getUiSpanCount());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
  }
