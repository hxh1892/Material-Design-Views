package com.hxh.mdv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hxh.mdv.R;

public class SB_Fragment extends Fragment
{
    private Context mContext;

    private CardView cv;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        View view = inflater.inflate(R.layout.frg_sb, null);

        cv = view.findViewById(R.id.card_view);
        tv = view.findViewById(R.id.tv);

        initSeekBar(view);

        return view;
    }

    private void initSeekBar(View v)
    {
        SeekBar sb1 = v.findViewById(R.id.sb1);
        SeekBar sb2 = v.findViewById(R.id.sb2);
        SeekBar sb3 = v.findViewById(R.id.sb3);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if (b)
                {
                    cv.setCardElevation(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if (b)
                {
                    cv.setRadius(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        sb3.setMax(100);
        sb3.setProgress(25);

        tv.setText("25%");

        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if (b)
                {
                    tv.setText(i + "%");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });
    }
}
