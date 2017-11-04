package com.hxh;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DisplayUtils
{
    //获取屏幕信息
    public static List<Float> getdisplayin(Activity activity)
    {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        float density = metric.density;
        int densityDpi = metric.densityDpi;

        List<Float> list_in = new ArrayList<>();

        list_in.add((float) width);
        list_in.add((float) height);
        list_in.add(density);
        list_in.add((float) densityDpi);

        return list_in;
    }

    public static List<Float> getdisplayin(Context context)
    {
        List<Float> list_in = new ArrayList<>();

        list_in.add((float) context.getResources().getDisplayMetrics().widthPixels);
        list_in.add((float) context.getResources().getDisplayMetrics().heightPixels);
        list_in.add(context.getResources().getDisplayMetrics().density);
        list_in.add((float) context.getResources().getDisplayMetrics().densityDpi);

        return list_in;
    }

    public static int getHeight(Activity activity)
    {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);

        return metric.heightPixels;
    }

    public static int getHeight(Context context)
    {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDensity(Activity activity)
    {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);

        return (int) metric.density;
    }

    public static int getDensity(Context context)
    {
        return (int) context.getResources().getDisplayMetrics().density;
    }

    //根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static int dp_to_px(Context context, float dpValue)
    {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    //根据手机的分辨率从 px(像素) 的单位 转成为 dp
    public static int px_to_dp(Context context, float pxValue)
    {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    //获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
    public static int getViewHW(View view, boolean isHeight)
    {
        int result;

        if(view==null)
        {
            return 0;
        }

        if(isHeight)
        {
            int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            view.measure(h,0);
            result =view.getMeasuredHeight();
        }
        else
        {
            int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            view.measure(0,w);
            result =view.getMeasuredWidth();
        }

        return result;
    }
}
