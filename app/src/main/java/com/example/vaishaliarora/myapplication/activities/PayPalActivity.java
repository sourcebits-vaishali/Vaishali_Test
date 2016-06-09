package com.example.vaishaliarora.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.adapter.PayPalAdapter;
import com.example.vaishaliarora.myapplication.model.Items;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PayPalActivity extends Activity implements PayPalAdapter.PayPalListener{

    /**
     * To obtain your live credentials, you need to have a business account. After that you just need to
     * change client_id to live client id and config environment variable to ENVIRONMENT_PRODUCTION in your activity .
     */

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;

    private static final int REQUEST_PAYPAL_PAYMENT = 1;

    private static final String CONFIG_CLIENT_ID = "AVZUbOX3ry-gyvTBVykh9TnK1v49hM0ycQiquryr8NjuRwnayplCFEm1M4ZnK5Q9JCcWzn5_briWUeRH";

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
                    // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Android Hub 4 You")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    private Items itemBought;
    private List listElements;
    private PayPalAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal_activity);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        ListView listView = (ListView)findViewById(R.id.paypal_list);
        listElements = getItems();
        PayPalAdapter.PayPalListener listener = this;

         adapter = new PayPalAdapter(this , listElements , listener);
        listView.setAdapter(adapter);

    }

    private static List<Items> getItems(){
        List results = new ArrayList<>();
        Items item1 = new Items();
        item1.setItemName("Item1");
        item1.setItemPrice(10);

        Items item2 = new Items();
        item2.setItemName("Item2");
        item2.setItemPrice(30);

        Items item3 = new Items();
        item3.setItemName("Item3");
        item3.setItemPrice(10);

        Items item4 = new Items();
        item4.setItemName("Item4");
        item4.setItemPrice(130);

        Items item5 = new Items();
        item5.setItemName("Item5");
        item5.setItemPrice(1550);

        Items item6 = new Items();
        item6.setItemName("Item6");
        item6.setItemPrice(160);

        results.add(item1);
        results.add(item2);
        results.add(item3);
        results.add(item4);
        results.add(item5);
        results.add(item6);


        return results;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PAYPAL_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data
                        .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        System.out.println("Responseeee"+confirm);
                        Log.i("paymentExample", confirm.toJSONObject().toString());


                        JSONObject jsonObj=new JSONObject(confirm.toJSONObject().toString());

                        String paymentId=jsonObj.getJSONObject("response").getString("id");
                        System.out.println("payment id:-=="+paymentId);
                        listElements.remove(itemBought);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), paymentId, Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment was submitted. Please see the docs.");
            }
        }
    }

    @Override
    public void payPalAction(Items item) {
        itemBought = item;
        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(item.getItemPrice()), "USD", "androidhub4you.com",
                        PayPalPayment.PAYMENT_INTENT_SALE);

                Intent intent = new Intent(PayPalActivity.this, PaymentActivity.class);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
                startActivityForResult(intent, REQUEST_PAYPAL_PAYMENT);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
