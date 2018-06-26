package com.hxh.mdv;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hxh.utils.FileUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.functions.Consumer;

import static android.os.Environment.getExternalStoragePublicDirectory;
import static com.hxh.utils.FileManageUtils.moveFile;

public class Scr_Activity extends AppCompatActivity
{
    private Context mContext = this;

    private File newApkFile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_scr);

        newApkFile = new File(getExternalStoragePublicDirectory("mdv").toString() + "/mdv.apk");

        initToolbar();
        initCollapsingToolbarLayout();
    }

    private void initToolbar()
    {
        Toolbar tb = findViewById(R.id.tb);

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
        CollapsingToolbarLayout ctl = findViewById(R.id.ctl);

        //不使用左下角的大标题
//        ctl.setTitleEnabled(false);

        ctl.setTitle("Scrolling");
        //收缩后在Toolbar上显示时的title的颜色
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        //扩张时候的title颜色
        ctl.setExpandedTitleColor(getResources().getColor(R.color.colorPrimaryDark));
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

    public void share(View v)
    {
       getPermission();
    }

    @SuppressLint("CheckResult")
    private void getPermission()
    {
        //监听具体的某一个权限是否进行了授权
        new RxPermissions(this)
                .requestEach( Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>()
                {
                    @Override
                    public void accept(Permission permission)
                    {
                        if (permission.granted)
                        {
                            //用户已经同意权限
                            //执行操作
                            new doMoveApkTask().execute();
                        }
                        else if (permission.shouldShowRequestPermissionRationale)
                        {
                            //用户拒绝了该权限，没有选中『不再询问』,再次启动时，还会提示请求权限的对话框
//                            Toast.makeText(mContext, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, "No permission no work", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                        else
                        {
                            //用户拒绝了该权限，并且选中『不再询问』
                            //启动系统权限设置界面
//                            Toast.makeText(mContext, "在该页面中点击“权限”进入，开启“文件”权限", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);

                            finish();
                        }
                    }
                });
    }

    private void share()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, FileUtils.getUriFromFile(mContext, newApkFile));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "Choose to share"));
    }

    @SuppressLint("StaticFieldLeak")
    private class doMoveApkTask extends AsyncTask<String, Integer, Integer>
    {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            dialog = new ProgressDialog(mContext);
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Integer doInBackground(String... params)
        {
            return moveFile(FileUtils.getApkFile(mContext), newApkFile);
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            if (integer == 0)
            {
//                Toast.makeText(mContext, "完成,Apk保存路径：" + newApkFile.toString(), Toast.LENGTH_LONG).show();

                share();
            }
            else if (integer == 1)
            {
//                Toast.makeText(mContext, "原Apk文件不存在", Toast.LENGTH_SHORT).show();
            }
            else if (integer == 2)
            {
//                Toast.makeText(mContext, "Apk已生成", Toast.LENGTH_SHORT).show();

                share();
            }

            dialog.cancel();
        }
    }
}
