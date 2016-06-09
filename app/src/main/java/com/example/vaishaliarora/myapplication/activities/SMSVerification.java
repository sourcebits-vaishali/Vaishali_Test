package com.example.vaishaliarora.myapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.helper.PrefManager;

import java.util.HashMap;


public class SMSVerification extends AppCompatActivity {

    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_verification);


        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView mobile = (TextView) findViewById(R.id.mobile);

        pref = new PrefManager(getApplicationContext());

        // Checking if user session
        // if not logged in, take user to sms screen
        if (!pref.isLoggedIn()) {
            logout();
        }

        // Displaying user information from shared preferences
        HashMap<String, String> profile = pref.getUserDetails();
        name.setText(String.format(getString(R.string.name_text), profile.get("name")));
        email.setText(String.format(getString(R.string.email_text), profile.get("email")));
        mobile.setText(String.format(getString(R.string.mobile_text), profile.get("mobile")));
    }

    /**
     * Logging out user
     * will clear all user shared preferences and navigate to
     * sms activation screen
     */
    private void logout() {
        pref.clearSession();

        Intent intent = new Intent(SMSVerification.this, OtpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}