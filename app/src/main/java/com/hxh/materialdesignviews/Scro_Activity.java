package com.hxh.materialdesignviews;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class Scro_Activity extends AppCompatActivity
{
    Context mContext = this;

    CollapsingToolbarLayout ctl;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_scro);

        initToolbar();
        initCollapsingToolbarLayout();
    }

    public void share(View v)
    {
        String apkFile = "";

        List<PackageInfo> list_packageInfo = getPackageManager().getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);

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

    private void initToolbar()
    {
        tb = (Toolbar) findViewById(R.id.tb);

        setSupportActionBar(tb);
        //显示左上角的返回按钮
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

    private void initCollapsingToolbarLayout()
    {
        ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);

        //不使用左下角的大标题
//        ctl.setTitleEnabled(false);

        ctl.setTitle("Scrolling");
        //收缩后在Toolbar上显示时的title的颜色
        ctl.setCollapsedTitleTextColor(getColor(R.color.white));
        //扩张时候的title颜色
        ctl.setExpandedTitleColor(getColor(R.color.colorPrimaryDark));
    }

//    private void initAppBarLayout()
//    {
//        AppBarLayout app_bar = (AppBarLayout) findViewById(R.id.abl);
//
//        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
//        {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
//            {
//                if (verticalOffset == 0)
//                {
//                    if (state != CollapsingToolbarLayoutState.EXPANDED)
//                    {
//                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
//                        collapsingToolbarLayout.setTitle("EXPANDED");//设置title为EXPANDED
//                    }
//                }
//                else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange())
//                {
//                    if (state != CollapsingToolbarLayoutState.COLLAPSED)
//                    {
//                        collapsingToolbarLayout.setTitle("");//设置title不显示
//                        playButton.setVisibility(View.VISIBLE);//隐藏播放按钮
//                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
//                    }
//                }
//                else
//                {
//                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE)
//                    {
//                        if (state == CollapsingToolbarLayoutState.COLLAPSED)
//                        {
//                            playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
//                        }
//                        collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
//                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
//                    }
//                }
//            }
//        });
//    }
}
