package com.example.asus.moodlog;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyinfoFragment extends Fragment {

    private static final String TAG ="MyinfoFragment";
    public MyinfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_myinfo, container, false);
        Button btnadd=(Button)view.findViewById(R.id.btn_addinfo);
        Button btnupdate=(Button)view.findViewById(R.id.btn_udinfo);
        Button btnyao=(Button)view.findViewById(R.id.btn_yao);
        Button btngrade=(Button)view.findViewById(R.id.btn_grade);
        btnadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),EncourageActivity.class);
                startActivity(intent);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),GrowthActivity.class);
                startActivity(intent);
            }
        });
        btngrade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage("功能尚在完善中").setPositiveButton("确认",null).setNegativeButton("否",null);
                builder.create().show();
            }
        });
        return view;
    }

}
