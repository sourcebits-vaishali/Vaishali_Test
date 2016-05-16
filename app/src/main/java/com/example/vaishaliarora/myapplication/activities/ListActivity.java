package com.example.vaishaliarora.myapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaishaliarora.myapplication.R;
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


        List elements = getListElements();
        MyAdapter adapter = new MyAdapter(elements);
        mRecyclerView.setAdapter(adapter);
    }

    public List<CustomItems> getListElements(){
        List listElements = new ArrayList();

        CustomItems item1 = new CustomItems("Fruit","Apple");
        CustomItems item2 = new CustomItems("Fruit","Mango");
        CustomItems item3 = new CustomItems("Fruit","Orange");
        CustomItems item4 = new CustomItems("Fruit","Banana");
        CustomItems item5 = new CustomItems("Fruit","Grapes");
        CustomItems item6 = new CustomItems("Fruit","Pineapple");
        CustomItems item7 = new CustomItems("Vegetable","Tomato");
        CustomItems item8 = new CustomItems("Vegetable","Potato");
        CustomItems item9 = new CustomItems("Vegetable","Carrot");
        CustomItems item10 = new CustomItems("Vegetable","LadyFinger");
        CustomItems item11 = new CustomItems("Vegetable","Cauliflower");
        CustomItems item12 = new CustomItems("Vegetable","Spinach");
        CustomItems item13 = new CustomItems("Vegetable","Onion");

        listElements.add(item1);
        listElements.add(item2);
        listElements.add(item3);
        listElements.add(item4);
        listElements.add(item5);
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


    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<CustomItems> mDataset;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;
        private  String prevHeader = "Fruit";

        class HeaderViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitleHeader;

            public HeaderViewHolder (View itemView) {
                super (itemView);
                this.txtTitleHeader = (TextView) itemView.findViewById (R.id.header);
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView txtName;

            public ItemViewHolder (View itemView) {
                super (itemView);
                this.txtName = (TextView) itemView.findViewById (R.id.text);
            }
        }


        public MyAdapter(List<CustomItems> dataset) {
            mDataset = dataset;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

            if(viewType == TYPE_HEADER) {
                View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.custom_header, parent, false);
                return new HeaderViewHolder (v);
            }  else if(viewType == TYPE_ITEM) {
                View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.custom_list_item, parent, false);
                return new ItemViewHolder (v);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof HeaderViewHolder) {
                HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
                headerHolder.txtTitleHeader.setText(mDataset.get(position).getHeader());

            }  else if(holder instanceof ItemViewHolder) {
                CustomItems currentItem = getItem(position);
                ItemViewHolder genericViewHolder = (ItemViewHolder) holder;
                genericViewHolder.txtName.setText(currentItem.getItem());
            }

        }

        private CustomItems getItem (int position) {
            return mDataset.get (position-1);
        }

        @Override
        public int getItemCount() {
            return mDataset.size()+1;
        }

        @Override
        public int getItemViewType (int position) {
            if(isPositionHeader (position)) {
                return TYPE_HEADER;
            }
            return TYPE_ITEM;
        }

        private boolean isPositionHeader (int position) {
            if(position == 0){
                return true;
            }
          return false;
        }
    }
}
