/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aditya.noondemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.Map;


/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "SUBJECT", "FORM", "CANVAS" };
    private Context mContext;
    Bundle argument;
    private Map<Integer,String> mFragmentTags;
    private FragmentManager mFragmentManager;
    private static SubjectFragment mFrag;
    private FormFragment tFrag;
    private CanvasFragment wFrag;
    private static SimpleFragmentPagerAdapter sFPA;
    private static final String MONDAY_ARG = "MONDAY";



    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFrag = SubjectFragment.newInstance("SUBJECT");
        tFrag = FormFragment.newInstance("FORM");
        wFrag = CanvasFragment.newInstance("CANVAS");
    }



    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mFrag;
        } else if (position == 1){
            return tFrag;
        } else {
            return wFrag;
        }
    }

    public void getData(Bundle bundle){
        Log.d(BlaActivity.TAG, "Inside SimpleFragment");

        mFrag.adListener(bundle);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


}
