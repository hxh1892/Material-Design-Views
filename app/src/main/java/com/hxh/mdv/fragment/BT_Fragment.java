package com.hxh.mdv.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxh.mdv.R;

public class BT_Fragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frg_bt, null);

        initFloatingActionButton(view);

        return view;
    }

    private void initFloatingActionButton(View v)
    {
        FloatingActionButton fab1 = v.findViewById(R.id.fabt1);
        FloatingActionButton fab2 = v.findViewById(R.id.fabt2);

        fab1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        fab2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}