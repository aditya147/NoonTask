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

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;
import static android.text.style.TtsSpan.ARG_TEXT;

/**
 * Fragment that displays "Tuesday".
 */
public class FormFragment extends Fragment {

    EditText subjectName;
    EditText subjectDescription;
    Button addButton;
    Button selectImage;
    ImageView imageView;
    SubjectFragmentCallback callback;
    private static int RESULT_LOAD_IMAGE = 1;
    private static FormFragment frag;
    Bitmap returningBitmap = null;
    boolean  permissionGranted ;
    //SharedPreferences prefs;

    public static FormFragment newInstance(String tag) {
        frag = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, tag);
        frag.setArguments(args);
        return frag;
    }

    public interface SubjectFragmentCallback {

        public void addSubject(String name, String description, Bitmap bitmap);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SubjectFragmentCallback) {
            callback = (SubjectFragmentCallback) context;
        } else {
            throw new IllegalStateException("Activity has not implemented the firstfragmentcallback interface");
        }
    }

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        subjectName = (EditText) getView().findViewById(R.id.subject_name);
        subjectDescription = (EditText) getView().findViewById(R.id.subject_description);
        addButton = (Button) getView().findViewById(R.id.add_subject);
        selectImage = (Button) getView().findViewById(R.id.select_image);
        imageView = (ImageView) getView().findViewById(R.id.image_view);


        addButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Log.d(BlaActivity.TAG, "buttonClick in form");
                                             //Toast.makeText(getActivity(),"first fragment button clicked",Toast.LENGTH_SHORT).show();
                                             String name = subjectName.getText().toString();
                                             String description = subjectDescription.getText().toString();
                                             callback.addSubject(name, description,returningBitmap);
                                         }
                                     }
        );

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Man", "bool" + permissionGranted);
            if(! permissionGranted){
                Log.d("Man", "permission not granted");
                checkPermission();

            }else{
               // selectImage();
            }


            }
        });
    }

    void selectImage(){
        Log.d("Man", "inside pick image");
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    void checkPermission(){
        if (android.os.Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else{
            selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"Premission granted");
                    permissionGranted = true;
                   //prefs.edit().putBoolean("grant", true).commit();
                    Log.d("Man", "permission granted");
                    selectImage();
                }else {
                    Log.d(TAG,"Premission denied");

                }
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Rama","Insideactivityforresult");
        if (requestCode == RESULT_LOAD_IMAGE  && null != data) {
            Log.d("Rama","InsideInsideactivityforresult");
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            cursor.close();

            Log.d("Rama",picturePath);
            returningBitmap = BitmapFactory.decodeFile(picturePath);

                imageView.setImageBitmap(returningBitmap);


           // imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
           // imageView.setImageResource(R.drawable.number_five);

        }


    }
}
