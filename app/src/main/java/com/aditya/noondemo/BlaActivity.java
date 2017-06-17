package com.aditya.noondemo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.fragment;

/**
 * Displays a {@link ViewPager} where each page shows a different day of the week.
 */
public class BlaActivity extends AppCompatActivity implements FormFragment.SubjectFragmentCallback{

    FragmentManager fragmentManager;
    SimpleFragmentPagerAdapter adapter;
    public static final String TAG = "MAINACTIVITY";
    // MainCallback callback = new MondayFragment();

   /* public interface MainCallback{
        public void addSubject(String name,String description);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_bla);

        // Find the view pager that will allow the user to swipe between fragments
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter= new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void addSubject(String name, String description, Bitmap bitmap) {
        Log.d(TAG,"callback in mainactivity" + name +description);
        Bundle arguments = new Bundle();
        arguments.putString("keyName",name);
        arguments.putString("keyDescription",description);
        arguments.putParcelable("BitmapImage",bitmap);
        // arguments.
        adapter.getData(arguments);
    }




}
