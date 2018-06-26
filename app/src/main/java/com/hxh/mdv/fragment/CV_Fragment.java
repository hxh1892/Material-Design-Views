package com.hxh.mdv.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.Toast;

import com.hxh.mdv.R;
import com.hxh.mdv.Scr_Activity;
import com.hxh.mdv.recyclerview.RV_Linear_Activity;
import com.hxh.mdv.recyclerview.RV_RL_Activity;
import com.hxh.utils.FileUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.functions.Consumer;

import static android.os.Environment.getExternalStoragePublicDirectory;
import static com.hxh.utils.FileManageUtils.moveFile;

public class CV_Fragment extends Fragment implements View.OnClickListener, View.OnTouchListener
{
    private Context mContext;

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv4;

    private boolean isIv1 = false, isIv2 = false;

    private File newApkFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        newApkFile = new File(getExternalStoragePublicDirectory("mdv").toString() + "/mdv.apk");

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
                getPermission();

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
        CardView cv1 = v.findViewById(R.id.cv1);
        CardView cv2 = v.findViewById(R.id.cv2);
        CardView cv3 = v.findViewById(R.id.cv3);

        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
    }

    private void initImageView(View v)
    {
        iv1 = v.findViewById(R.id.iv1);
        iv2 = v.findViewById(R.id.iv2);
        ImageView iv3 = v.findViewById(R.id.iv3);
        iv4 = v.findViewById(R.id.iv4);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
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
                        startActivity(new Intent(mContext, Scr_Activity.class));

                        break;
                    }
                }

                return false;
            }
        });

        popupMenu.show();
    }

    @SuppressLint("CheckResult")
    private void getPermission()
    {
        //监听具体的某一个权限是否进行了授权
        new RxPermissions(getActivity())
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
                        }
                        else
                        {
                            //用户拒绝了该权限，并且选中『不再询问』
                            //启动系统权限设置界面
//                            Toast.makeText(mContext, "在该页面中点击“权限”进入，开启“文件”权限", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + mContext.getPackageName()));
                            startActivity(intent);
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