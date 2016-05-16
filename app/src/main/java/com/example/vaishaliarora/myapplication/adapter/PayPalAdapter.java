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

/**
 * Created by vaishaliarora on 11/05/16.
 */
public class PayPalAdapter extends BaseAdapter {

    private PayPalListener payPalListener;
    public void setOnPayPalListener(PayPalListener listener){
        payPalListener = listener;
    }

    private List<Items> items;
    private Context context;

    public PayPalAdapter(Context ctx,List<Items> items , PayPalListener listener){
        this.context = ctx;
        this.items = items;
        setOnPayPalListener(listener);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    ViewHolder holder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = ViewGroup.inflate(context, R.layout.paypal_item, null);
            holder = new ViewHolder();
            holder.itemName = (TextView)convertView.findViewById(R.id.itemName);
            holder.itemPrice = (TextView)convertView.findViewById(R.id.itemPrice);
            holder.pay = (Button)convertView.findViewById(R.id.pay);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        holder.itemName.setText(items.get(position).getItemName());
        holder.itemPrice.setText("$ "+items.get(position).getItemPrice());

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPalListener.payPalAction(items.get(position));
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
