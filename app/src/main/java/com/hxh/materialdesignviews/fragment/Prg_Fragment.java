package com.hxh.materialdesignviews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hxh.materialdesignviews.R;

public class Prg_Fragment extends Fragment
{
    ProgressBar pb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frg_prg, null);

        initProgresssBar(view);

        return view;
    }

    private void initProgresssBar(View v)
    {
        pb = (ProgressBar) v.findViewById(R.id.pb);
    }
}