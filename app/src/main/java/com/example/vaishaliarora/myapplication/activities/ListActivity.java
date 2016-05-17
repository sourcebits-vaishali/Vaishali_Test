package com.example.vaishaliarora.myapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.adapter.TestAdapter;
import com.example.vaishaliarora.myapplication.model.CustomItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishaliarora on 12/05/16.
 */
public class ListActivity extends Activity {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        mRecyclerView = (RecyclerView)findViewById(R.id.list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        List elements = getListElements();
        TestAdapter adapter = new TestAdapter(this , elements);
        adapter.createDataModel();
        mRecyclerView.setAdapter(adapter);
    }

    public List<CustomItems> getListElements(){
        List listElements = new ArrayList();

        CustomItems item1 = new CustomItems("Fruit","Apple", true);
        CustomItems item2 = new CustomItems("Fruit","Mango", true);
        CustomItems item3 = new CustomItems("Fruit","Orange", true);
        CustomItems item4 = new CustomItems("Fruit","Banana", true);
        CustomItems item5 = new CustomItems("Fruit","Grapes", true);
        CustomItems item51 = new CustomItems("Fruit","WaterMelon", true);
        CustomItems item52 = new CustomItems("Fruit","Papaya", true);
        CustomItems item53 = new CustomItems("Fruit","MuskMelon", true);
        CustomItems item54 = new CustomItems("Fruit","XYZ", true);
        CustomItems item6 = new CustomItems("Fruit","Pineapple", true);
        CustomItems item7 = new CustomItems("Vegetable","Tomato", false);
        CustomItems item8 = new CustomItems("Vegetable","Potato", false);
        CustomItems item9 = new CustomItems("Vegetable","Carrot", false);
        CustomItems item10 = new CustomItems("Vegetable","LadyFinger", false);
        CustomItems item11 = new CustomItems("Vegetable","Cauliflower", false);
        CustomItems item12 = new CustomItems("Vegetable","Spinach", false);
        CustomItems item13 = new CustomItems("Vegetable","Onion", false);

        listElements.add(item1);
        listElements.add(item2);
        listElements.add(item3);
        listElements.add(item4);
        listElements.add(item5);
        listElements.add(item51);
        listElements.add(item52);
        listElements.add(item53);
        listElements.add(item54);
        listElements.add(item6);
        listElements.add(item7);
        listElements.add(item8);
        listElements.add(item9);
        listElements.add(item10);
        listElements.add(item11);
        listElements.add(item12);
        listElements.add(item13 );



        return listElements;
    }

}
