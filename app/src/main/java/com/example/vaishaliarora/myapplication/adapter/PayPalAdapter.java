package com.example.vaishaliarora.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.model.Items;

import java.util.List;


public class PayPalAdapter extends BaseAdapter {

    private PayPalListener payPalListener;
    private void setOnPayPalListener(PayPalListener listener){
        payPalListener = listener;
    }

    private final List<Items> mItems;
    private final Context mContext;

    public PayPalAdapter(Context ctx,List<Items> items , PayPalListener listener){
        this.mContext = ctx;
        this.mItems = items;
        setOnPayPalListener(listener);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(null == convertView){
            convertView = ViewGroup.inflate(mContext, R.layout.paypal_item, null);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView)convertView.findViewById(R.id.itemName);
            viewHolder.itemPrice = (TextView)convertView.findViewById(R.id.itemPrice);
            viewHolder.pay = (Button)convertView.findViewById(R.id.pay);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.itemName.setText(mItems.get(position).getItemName());
        viewHolder.itemPrice.setText(String.format(mContext.getString(R.string.dollar_symbol) , mItems.get(position).getItemPrice()));

        viewHolder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPalListener.payPalAction(mItems.get(position));
            }
        });


        return convertView;
    }

    class ViewHolder{
        TextView itemName,itemPrice;
        Button pay;
    }

    public interface PayPalListener{
        void payPalAction(Items item);
    }
}
