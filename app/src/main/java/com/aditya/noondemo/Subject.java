package com.aditya.noondemo;

import android.graphics.Bitmap;

/**
 * Created by Aditya on 6/14/2017.
 */

public class Subject {

    private String mSubjectName;

    private String mSubjectDescription;

    //private static final int NO_IMAGE_PROVIDED = -1;
    private Bitmap mImageResourceId ;

    public Subject(String name, String description, Bitmap resourceId){
        mSubjectName = name;
        mSubjectDescription = description;
        mImageResourceId = resourceId;

    }

    public String getSubjectName(){
       return mSubjectName;
    }
    public String getSubjectDescription(){
        return mSubjectDescription;
    }
    public Bitmap getImageResourceId(){

        return mImageResourceId;
    }

    /*public boolean hasImage(){

        return mImageResourceId != NO_IMAGE_PROVIDED;
    }*/
}
