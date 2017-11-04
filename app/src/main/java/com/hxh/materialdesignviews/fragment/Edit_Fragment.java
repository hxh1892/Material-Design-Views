package com.hxh.materialdesignviews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.hxh.materialdesignviews.R;

public class Edit_Fragment extends Fragment implements View.OnClickListener
{
    Context mContext;

    AutoCompleteTextView actv;
    EditText et1, et2, et3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        View view = inflater.inflate(R.layout.frg_edit, null);

        initEditText(view);
        initAutoCompleteTextView(view);

        return view;
    }

    private void initEditText(View v)
    {


    }

    private void initAutoCompleteTextView(View v)
    {
        actv = (AutoCompleteTextView) v.findViewById(R.id.actv);

        String[] res = {"air", "airline", "airbag", "airbus", "Airbnb", "airplane", "air dry", "air 123", "air @#@"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, res);

        actv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
//            case ll:
//            {
//
//            }
        }
    }
}