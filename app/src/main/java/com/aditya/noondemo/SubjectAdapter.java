package com.aditya.noondemo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aditya on 6/14/2017.
 */

public class SubjectAdapter extends ArrayAdapter<Subject> {


    public SubjectAdapter(Activity context, ArrayList<Subject> subjects){
        super(context,0,subjects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Subject currentSubject = getItem(position);

        TextView subjectNameTV = (TextView) listItemView.findViewById(R.id.subjectName);

        subjectNameTV.setText(currentSubject.getSubjectName());


        TextView subjectDescriptionTV = (TextView) listItemView.findViewById(R.id.subjectDescription);

        subjectDescriptionTV.setText(currentSubject.getSubjectDescription());

        ImageView myImageView = (ImageView) listItemView.findViewById(R.id.subjectImage);

           // myImageView.setImageResource(currentSubject.getImageResourceId());
        myImageView.setImageBitmap(currentSubject.getImageResourceId());
           // myImageView.setVisibility(View.VISIBLE);


        return listItemView;
    }
}
