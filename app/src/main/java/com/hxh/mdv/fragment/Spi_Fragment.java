package com.hxh.mdv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hxh.mdv.R;

import static com.hxh.mdv.R.array.languages;

public class Spi_Fragment extends Fragment
{
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        View view = inflater.inflate(R.layout.frg_spi, null);

        initSpinner(view);

        return view;
    }

    private void initSpinner(View v)
    {
        final String[] array = getResources().getStringArray(languages);

        Spinner spi1 = v.findViewById(R.id.spi1);
        Spinner spi2 = v.findViewById(R.id.spi2);
        Spinner spi3 = v.findViewById(R.id.spi3);

        spi1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(mContext, "spi1" + array[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, array);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spi2.setAdapter(adapter);

        spi2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(mContext, "spi2" + array[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        spi3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(mContext, "spi3" + array[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }
}