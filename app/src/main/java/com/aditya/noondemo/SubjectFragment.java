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

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.text.style.TtsSpan.ARG_TEXT;

/**
 * Fragment that displays "Monday".
 */
public class SubjectFragment extends Fragment {

    private static SubjectFragment frag;
    SubjectAdapter myAdapter;

    public static SubjectFragment newInstance(String tag){
        frag = new SubjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, tag);
        frag.setArguments(args);
        return frag;
    }


    final ArrayList<Subject> subjects =new ArrayList<Subject>();


    ListView listView;

    public void adListener(Bundle bundle){
        Log.d(BlaActivity.TAG, "Recieved inside MondayFragment");
        String name = bundle.getString("keyName");
        String description = bundle.getString("keyDescription");
        Bitmap bP = bundle.getParcelable("BitmapImage");
        if(name != null && description != null && bP != null) {
            subjects.add(new Subject(name, description, bP));
            myAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getActivity(),"Please fill all the fields before clicking on Add Subject",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


         View rootView = inflater.inflate(R.layout.fragment_subject, container, false);


        subjects.add(new Subject("Math","Mathematics is the study of numbers, quantities, and shapes. When mathematics is taught as a subject at school, it is usually called maths in British English, and math in American English.", BitmapFactory.decodeResource(getResources(), R.drawable.number_one)));
        subjects.add(new Subject("Science","The intellectual and practical activity encompassing the systematic study of the structure and behaviour of the physical and natural world through observation and experiment.", BitmapFactory.decodeResource(getResources(), R.drawable.number_two)));
        subjects.add(new Subject("Physics","The branch of science concerned with the nature and properties of matter and energy. The subject matter of physics includes mechanics, heat, light and other radiation, sound, electricity, magnetism, and the structure of atoms.", BitmapFactory.decodeResource(getResources(), R.drawable.number_three)));
        subjects.add(new Subject("Chemistry","The branch of science concerned with the substances of which matter is composed, the investigation of their properties and reactions, and the use of such reactions to form new substances.", BitmapFactory.decodeResource(getResources(), R.drawable.number_four)));
        subjects.add(new Subject("Biology","The study of living organisms, divided into many specialized fields that cover their morphology, physiology, anatomy, behaviour, origin, and distribution.", BitmapFactory.decodeResource(getResources(), R.drawable.number_five)));
        subjects.add(new Subject("Socialogy","The study of the development, structure, and functioning of human society.", BitmapFactory.decodeResource(getResources(), R.drawable.number_six)));
        subjects.add(new Subject("English","Relating to England or its people or language.", BitmapFactory.decodeResource(getResources(), R.drawable.number_seven)));
        subjects.add(new Subject("Arts","The expression or application of human creative skill and imagination, typically in a visual form such as painting or sculpture, producing works to be appreciated primarily for their beauty or emotional power.", BitmapFactory.decodeResource(getResources(), R.drawable.number_eight)));
        subjects.add(new Subject("Commerce","The activity of buying and selling, especially on a large scale.", BitmapFactory.decodeResource(getResources(), R.drawable.number_nine)));
        subjects.add(new Subject("History","The study of past events, particularly in human affairs.", BitmapFactory.decodeResource(getResources(), R.drawable.number_ten)));



        myAdapter  = new SubjectAdapter(getActivity(),subjects);
        listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder messageDial  = new AlertDialog.Builder(getActivity());
                messageDial.setIcon(android.R.drawable.ic_dialog_dialer);
                messageDial.setTitle("Delete");
                messageDial.setMessage("Do you want to delete this subject?");
                //messageDial.setCancelable()
                messageDial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myAdapter.remove(subjects.get(position));
                        //myAdapter.notifyDataSetChanged();
                    }
                });
                messageDial.show();

            }
        });


        return rootView;

    }

    /*@Override
    public void addSubject(String name, String description) {
        Log.d("Aditya","insidecallback"+name+description);
        subjects.add(new Subject(name,description,R.drawable.number_ten));
        /*//*myAdapter = new SubjectAdapter(getActivity(),subjects);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


    }*/

    @Override
    public void onResume() {
        super.onResume();
       // subjects.add(new Subject("name","description",R.drawable.number_ten));
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("Aditya","inside onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        String name=null,description=null;
        Bundle arguments = getArguments();
        Log.d("Aditya","outside arguments"+arguments);
        if(arguments != null){
            Log.d("Aditya","inside arguments");
             name = arguments.getString("keyName");
             description = arguments.getString("keyDescription");
        }

    }*/




}
