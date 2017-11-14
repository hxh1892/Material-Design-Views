package com.hxh.mdv.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.hxh.mdv.R;
import com.hxh.mdv.Scro_Activity;
import com.hxh.mdv.recyclerview.RV_Linear_Activity;
import com.hxh.mdv.recyclerview.RV_RL_Activity;

import java.util.List;

public class CV_Fragment extends Fragment implements View.OnClickListener, View.OnTouchListener
{
    Context mContext;

    CardView cv1, cv2, cv3;
    ImageView iv1, iv2, iv3, iv4;

    boolean isIv1 = false, isIv2 = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        View view = inflater.inflate(R.layout.frg_cv, null);

        initCardView(view);
        initImageView(view);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cv1:
            {
                break;
            }
            case R.id.cv2:
            {
                break;
            }
            case R.id.cv3:
            {
                break;
            }
            case R.id.iv1:
            {
                if (isIv1)
                {
                    iv1.setImageResource(R.mipmap.img_love);
                }
                else
                {
                    iv1.setImageResource(R.mipmap.img_love_c);
                }

                isIv1 = !isIv1;

                break;
            }
            case R.id.iv2:
            {
                if (isIv2)
                {
                    iv2.setImageResource(R.mipmap.img_bookmark);
                }
                else
                {
                    iv2.setImageResource(R.mipmap.img_bookmark_c);
                }

                isIv2 = !isIv2;

                break;
            }
            case R.id.iv3:
            {
                share();

                break;
            }
            case R.id.iv4:
            {
                showPopupMenu();

                break;
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationZ", 15);
                objectAnimator.setDuration(150);
                objectAnimator.setInterpolator(new DecelerateInterpolator());
                objectAnimator.start();

                break;
            }

            case MotionEvent.ACTION_UP: {}

            case MotionEvent.ACTION_CANCEL:
            {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationZ", 0);
                objectAnimator.setDuration(150);
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.start();

                break;
            }
        }

        return false;
    }

    private void initCardView(View v)
    {
        cv1 = (CardView) v.findViewById(R.id.cv1);
        cv2 = (CardView) v.findViewById(R.id.cv2);
        cv3 = (CardView) v.findViewById(R.id.cv3);

        cv1.setOnTouchListener(this);
        cv2.setOnTouchListener(this);
        cv3.setOnTouchListener(this);
        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
    }

    private void initImageView(View v)
    {
        iv1 = (ImageView) v.findViewById(R.id.iv1);
        iv2 = (ImageView) v.findViewById(R.id.iv2);
        iv3 = (ImageView) v.findViewById(R.id.iv3);
        iv4 = (ImageView) v.findViewById(R.id.iv4);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
    }

    private void share()
    {
        String apkFile = "";

        List<PackageInfo> list_packageInfo = mContext.getPackageManager().getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);

        for (PackageInfo packageInfo : list_packageInfo)
        {
            if (packageInfo.packageName.equals("com.hxh.materialdesignviews"))
            {
                apkFile = packageInfo.applicationInfo.sourceDir;
            }
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + apkFile));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "选择分享应用"));
    }

    private void showPopupMenu()
    {
        PopupMenu popupMenu = new PopupMenu(mContext, iv4);

        popupMenu.getMenuInflater().inflate(R.menu.popupmenu1, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.rv:
                    {
                        startActivity(new Intent(mContext, RV_Linear_Activity.class));

                        break;
                    }
                    case R.id.rv_rl:
                    {
                        startActivity(new Intent(mContext, RV_RL_Activity.class));

                        break;
                    }
                    case R.id.scro:
                    {
                        startActivity(new Intent(mContext, Scro_Activity.class));

                        break;
                    }
                }

                return false;
            }
        });

        popupMenu.show();
    }
}