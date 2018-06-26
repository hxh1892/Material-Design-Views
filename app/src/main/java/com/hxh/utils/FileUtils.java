package com.hxh.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.List;

public class FileUtils
{
    public static File getApkFile(Context context)
    {
        String apkFile = "";

        List<PackageInfo> list_packageInfo = context.getPackageManager().getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);

        for (PackageInfo packageInfo : list_packageInfo)
        {
            if (packageInfo.packageName.equals("com.hxh.mdv"))
            {
                apkFile = packageInfo.applicationInfo.sourceDir;
            }
        }

        return new File(apkFile);
    }

    public static Uri getUriFromFile(Context context, File file)
    {
        Uri uri;

        if (Build.VERSION.SDK_INT >= 24)
        {
            uri = FileProvider.getUriForFile(context, "com.hxh.mdv.provider.FileProviderFile", file);
        }
        else
        {
            uri = Uri.fromFile(file);
        }

        return uri;
    }
}
