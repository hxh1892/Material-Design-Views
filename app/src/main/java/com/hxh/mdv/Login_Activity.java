package com.hxh.mdv;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity
{
    private Context mContext = this;

    private TextInputLayout til1, til2;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_log);

        initToolbar();
        initTextInputLayout();
        initEditText();
    }

    public void login(View v)
    {
        String phone = et1.getText().toString();
        String pass = et2.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            til1.setError("Input your phone number");
        }
        else
        {
            if (phone.length() != 11)
            {
                til1.setError("Invalid phone number");
            }
            else
            {
                if (TextUtils.isEmpty(pass))
                {
                    til1.setErrorEnabled(false);
                    til2.setError("Input your password");
                }
                else
                {
                    til2.setErrorEnabled(false);

                    Toast.makeText(mContext, "Login Success!", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        }
    }

    private void initToolbar()
    {
        Toolbar tb = findViewById(R.id.tb);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tb.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    private void initTextInputLayout()
    {
        til1 = findViewById(R.id.til1);
        til2 = findViewById(R.id.til2);
    }

    private void initEditText()
    {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }
}
