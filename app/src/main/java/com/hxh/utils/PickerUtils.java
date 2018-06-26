package com.hxh.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.lang.reflect.Field;

public class PickerUtils
{
    public static void setNumberPickerDividerColor(Context context, NumberPicker numberPicker, int color)
    {
        Field[] fields = NumberPicker.class.getDeclaredFields();

        for (Field field : fields)
        {
            if (field.getName().equals("mSelectionDivider"))
            {
                field.setAccessible(true);

                try
                {
                    //设置分割线的颜色值
                    field.set(numberPicker, new ColorDrawable(context.getResources().getColor(color, null)));
                }
                catch (IllegalArgumentException | IllegalAccessException | Resources.NotFoundException e)
                {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    public static void setDatePickerDividerColor(Context context, DatePicker datePicker, int color)
    {
        //获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        //获取 NumberPicker
        LinearLayout llFirstFirst = (LinearLayout) llFirst.getChildAt(0);

        for (int i = 0; i < llFirstFirst.getChildCount(); i++)
        {
            setNumberPickerDividerColor(context, (NumberPicker) llFirstFirst.getChildAt(i), color);

//            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);
//
//            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
//
//            for (Field pf : pickerFields)
//            {
//                if (pf.getName().equals("mSelectionDivider"))
//                {
//                    pf.setAccessible(true);
//                    try
//                    {
//                        pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.black_666, null)));
//                    }
//                    catch (IllegalArgumentException e) {e.printStackTrace();}
//                    catch (Resources.NotFoundException e) {e.printStackTrace();}
//                    catch (IllegalAccessException e) {e.printStackTrace();}
//
//                    break;
//                }
//            }
        }
    }

    public static void setTimePickerDividerColor(Context context, TimePicker timePicker, int color)
    {
        //获取 mSpinners
        LinearLayout llFirst = (LinearLayout) timePicker.getChildAt(0);

        //获取 NumberPicker
        LinearLayout llFirstFirst = (LinearLayout) llFirst.getChildAt(0);

        for (int i = 0; i < llFirstFirst.getChildCount(); i++)
        {
            setNumberPickerDividerColor(context, (NumberPicker) llFirstFirst.getChildAt(i), color);

//            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);
//
//            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
//
//            for (Field pf : pickerFields)
//            {
//                if (pf.getName().equals("mSelectionDivider"))
//                {
//                    pf.setAccessible(true);
//                    try
//                    {
//                        pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.black_666, null)));
//                    }
//                    catch (IllegalArgumentException e) {e.printStackTrace();}
//                    catch (Resources.NotFoundException e) {e.printStackTrace();}
//                    catch (IllegalAccessException e) {e.printStackTrace();}
//
//                    break;
//                }
//            }
        }
    }

    public static void setDatePickerDialogPositiveButtonColor(Context context, DatePickerDialog datePickerDialog, int coclor)
    {
        //修改按钮颜色这个必须在show或者create方法后面
        //确认按钮
        Button bt_pos = datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        bt_pos.setTextColor(context.getResources().getColor(coclor));
    }

    public static void setDatePickerDialogNegativeButtonColor(Context context, DatePickerDialog datePickerDialog, int coclor)
    {
        //修改按钮颜色这个必须在show或者create方法后面
        //取消按钮
        Button bt_neg = datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        bt_neg.setTextColor(context.getResources().getColor(coclor));
    }

    public static void setTimePickerDialogPositiveButtonColor(Context context, TimePickerDialog timePickerDialog, int coclor)
    {
        //修改按钮颜色这个必须在show或者create方法后面
        //确认按钮
        Button bt_pos = timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        bt_pos.setTextColor(context.getResources().getColor(coclor));
    }

    public static void setTimePickerDialogNegativeButtonColor(Context context, TimePickerDialog timePickerDialog, int coclor)
    {
        //修改按钮颜色这个必须在show或者create方法后面
        //取消按钮
        Button bt_neg = timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        bt_neg.setTextColor(context.getResources().getColor(coclor));
    }
}
