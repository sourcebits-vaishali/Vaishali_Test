package com.example.vaishaliarora.myapplication.activities;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.fragments.CalenderFragment;
import com.example.vaishaliarora.myapplication.fragments.ImageLoaderFragment;
import com.example.vaishaliarora.myapplication.fragments.ListCardFragment;
import com.example.vaishaliarora.myapplication.fragments.MapFragment;
import com.example.vaishaliarora.myapplication.fragments.OthersFragment;
import com.example.vaishaliarora.myapplication.fragments.PaymentFragment;
import com.example.vaishaliarora.myapplication.fragments.SocialNetworkFragment;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(navigationView);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        if( getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = setupDrawerToggle();

        mDrawerLayout.addDrawerListener(mDrawerToggle);


    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_payment_fragment:
                fragmentClass = PaymentFragment.class;
                break;
            case R.id.nav_calender_fragment:
                fragmentClass = CalenderFragment.class;
                break;
            case R.id.nav_social_netw_fragment:
                fragmentClass = SocialNetworkFragment.class;
                break;

            case R.id.nav_list_card_fragment:
                fragmentClass = ListCardFragment.class;
                break;

            case R.id.nav_map_fragment:
                fragmentClass = MapFragment.class;
                break;
            case R.id.nav_sqlite_fragment:
                fragmentClass = MapFragment.class;
                break;

            case R.id.nav_imgLib_fragment:
                fragmentClass = ImageLoaderFragment.class;
                break;

            case R.id.nav_others_fragment :
                fragmentClass = OthersFragment.class;
                break;



            default:
                fragmentClass = PaymentFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
