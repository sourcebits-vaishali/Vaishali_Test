package com.example.vaishaliarora.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.model.CustomItems;
import com.example.vaishaliarora.myapplication.model.UIConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishaliarora on 17/05/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_GRID_FILE = 2;
    private static final int VIEW_TYPE_FOOTER = 3;

    private LayoutInflater mInflater;
    private List<RowItem> mRowItemList = new ArrayList<>();
    private  List<CustomItems> mAllFiles;

    public RecyclerViewAdapter(Context context, List<CustomItems> inputElements) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mAllFiles = inputElements;
    }
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId ;
        if (viewType == VIEW_TYPE_ITEM) {
            resId = R.layout.custom_list_item;
        } else if (viewType == VIEW_TYPE_HEADER) {
            resId = R.layout.custom_header;
        } else if (viewType == VIEW_TYPE_GRID_FILE) {
            resId = R.layout.grid_layout;
        } else {
            resId = R.layout.footer;
        }
        View view = mInflater.inflate(resId, parent, false);
        return new DataViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, final int position) {

        final RowItem item = mRowItemList.get(position);

        if (item.itemType == VIEW_TYPE_ITEM) {
            holder.mTextViewName.setText(item.fileName);
        } else if (item.itemType == VIEW_TYPE_HEADER) {
            holder.mTextViewName.setText(item.headerText);
        } else {
            holder.mTextViewName.setText(item.headerText);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position > -1 && position < mRowItemList.size()) {
            return mRowItemList.get(position).getItemType();
        }
        return VIEW_TYPE_HEADER; // Should not come here
    }

    @Override
    public int getItemCount() {
        return mRowItemList.size();
    }

    public static int getUiSpanCount() {
        return (UIConfiguration.getViewMode() == UIConfiguration.ViewMode.GRID_VIEW ? 2 : 1);
    }

    public boolean isHeader(int position) {
        return mRowItemList.get(position).itemType == VIEW_TYPE_HEADER;
    }

    public boolean isFooter(int position) {
        return mRowItemList.get(position).itemType == VIEW_TYPE_FOOTER;
    }

    public static class RowItem {
        private int lastHeaderIndex;
        private String headerText;
        private int itemType;
        private CustomItems dkFile;
        String fileName;

        public RowItem(CustomItems file, int itemType, int lastHeaderIndex) {
            this.itemType = itemType;
            this.dkFile = file;
            this.lastHeaderIndex = lastHeaderIndex;
            this.fileName = dkFile.getItem();
        }

        public RowItem(int itemType, String header, int lastHeaderIndex) {
            this.itemType = itemType;
            this.dkFile = null;
            this.fileName = null;
            this.headerText = header;
            this.lastHeaderIndex = lastHeaderIndex;
        }


        public int getItemType(){
            if (itemType == VIEW_TYPE_ITEM && UIConfiguration.getViewMode() == UIConfiguration.ViewMode.GRID_VIEW) {
                return dkFile.isFruit() ? VIEW_TYPE_GRID_FILE : VIEW_TYPE_ITEM;
            }
            return itemType;
        }
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewName;

        public DataViewHolder(View view, int viewType) {
            super(view);
            mTextViewName = (TextView) view.findViewById(R.id.text);
            if (viewType == VIEW_TYPE_HEADER ) {
                return;
            }
        }
    }

    public void createDataModel() {
        this.mRowItemList.clear();

        Boolean lastHeader = null;
        int lastHeaderIndex = -1;
        for (int i = 0; i < mAllFiles.size(); i++) {
            Boolean curr = mAllFiles.get(i).isFruit();
            if (!(lastHeader ==  curr)) {
                lastHeader = curr;
                lastHeaderIndex = mRowItemList.size(); // Index on which the last header has been inserted
                mRowItemList.add(new RowItem(VIEW_TYPE_HEADER, mAllFiles.get(i).isFruit() ?  "Fruit": "Vegetable", lastHeaderIndex));
            }
            mRowItemList.add(new RowItem(mAllFiles.get(i), VIEW_TYPE_ITEM, lastHeaderIndex));
        }mRowItemList.add(new RowItem(VIEW_TYPE_FOOTER, "This is the footer", lastHeaderIndex+1));

    }
}
