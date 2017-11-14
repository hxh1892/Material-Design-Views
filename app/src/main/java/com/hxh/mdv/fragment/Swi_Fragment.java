package com.hxh.mdv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.hxh.mdv.R;

public class Swi_Fragment extends Fragment
{
    Context mContext;

    SwitchCompat sc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        View view = inflater.inflate(R.layout.frg_swi, null);

        initSwitchCompat(view);

        return view;
    }

    private void initSwitchCompat(View v)
    {
        sc = (SwitchCompat) v.findViewById(R.id.sc);

        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    Toast.makeText(mContext, "isChecked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(mContext, "notChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}