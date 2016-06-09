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
import com.example.vaishaliarora.myapplication.activities.OtpActivity;
import com.example.vaishaliarora.myapplication.activities.PayPalActivity;

/**
 * Created by vaishaliarora on 11/05/16.
 */
public class PaymentFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View view = View.inflate(getActivity(),R.layout.payement,null);
        Button paypal = (Button)view.findViewById(R.id.paypal);
        paypal.setOnClickListener(this);

        Button otp = (Button)view.findViewById(R.id.otp);
        otp.setOnClickListener(this);
        return view;
   
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.paypal :
                startActivity(new Intent(getActivity() , PayPalActivity.class));
                break;

            case R.id.otp :
                startActivity(new Intent(getActivity() , OtpActivity.class));
                break;
        }
    }
}
