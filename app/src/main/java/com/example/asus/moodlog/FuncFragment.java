package com.example.asus.moodlog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FuncFragment extends Fragment {


    public FuncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_func, container, false);
        Button btne=(Button)view.findViewById(R.id.btn_e);
        Button btngrowth=(Button)view.findViewById(R.id.btn_growth);
        Button btnlife=(Button)view.findViewById(R.id.btn_life);
        btne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),FoYuActivity.class);
                startActivity(intent);
            }
        });
        btngrowth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),GrowthActivity.class);
                startActivity(intent);
            }
        });
        btnlife.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),LifeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
