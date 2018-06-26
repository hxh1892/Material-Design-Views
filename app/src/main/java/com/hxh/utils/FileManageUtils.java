package com.hxh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManageUtils
{
    //return 0：移动成功
    //return 1：复制文件不存在
    //return 2：粘贴文件已存在
    public static int moveFile(File copyFile, File pasteFile)
    {
        if (pasteFile.exists())
        {
            return 2;
        }
        else
        {
            pasteFile.getParentFile().mkdirs();
        }

        if (copyFile.exists())
        {
            copyFile(copyFile, pasteFile);
        }
        else
        {
            return 1;
        }

        return 0;
    }

    private static void copyFile(File fromFile, File toFile)
    {
        try
        {
            InputStream is = new FileInputStream(fromFile);
            OutputStream os = new FileOutputStream(toFile);

            byte[] buffer = new byte[1024];
            int temp;

            while ((temp = is.read(buffer)) > 0)
            {
                os.write(buffer, 0, temp);
            }

            is.close();
            os.close();
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
}
