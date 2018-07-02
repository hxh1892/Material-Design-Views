package com.hxh.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.File;
import java.util.List;

public class ApkUtils
{
    public static File getApkFile(Context context)
    {
        String apkFile = "";

        List<PackageInfo> list_packageInfo = context.getPackageManager().getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);

        for (PackageInfo packageInfo : list_packageInfo)
        {
            if (packageInfo.packageName.equals(context.getPackageName()))
            {
                apkFile = packageInfo.applicationInfo.sourceDir;
            }
        }

        return new File(apkFile);
    }
}
