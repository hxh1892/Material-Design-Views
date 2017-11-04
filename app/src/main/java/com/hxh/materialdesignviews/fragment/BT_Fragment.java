package com.hxh.materialdesignviews.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxh.materialdesignviews.R;

public class BT_Fragment extends Fragment
{
    FloatingActionButton fabt1;
    FloatingActionButton fabt2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frg_bt, null);

        initFloatingActionButton(view);

        return view;
    }

    private void initFloatingActionButton(View v)
    {
        fabt1 = (FloatingActionButton) v.findViewById(R.id.fabt1);
        fabt2 = (FloatingActionButton) v.findViewById(R.id.fabt2);

        fabt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        fabt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}