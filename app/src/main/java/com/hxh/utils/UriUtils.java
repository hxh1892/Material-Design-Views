package com.hxh.utils;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

public class UriUtils
{
    public static Uri getUriFromFile(Context context, File file)
    {
        //要和AndroidManifest中的provider中的android:authorities="com.hxh.mdv.provider.FileProviderFile"一一对应
        //改名称必须唯一，否则出现重复会安装不了
        return FileProvider.getUriForFile(context, "com.hxh.mdv.provider.FileProviderFile", file);
    }
}
