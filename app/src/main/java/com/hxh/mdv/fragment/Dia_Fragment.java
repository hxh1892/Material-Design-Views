package com.hxh.mdv.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hxh.mdv.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hxh.PickerUtils.setDatePickerDialogNegativeButtonColor;
import static com.hxh.PickerUtils.setDatePickerDialogPositiveButtonColor;
import static com.hxh.PickerUtils.setDatePickerDividerColor;
import static com.hxh.PickerUtils.setNumberPickerDividerColor;
import static com.hxh.PickerUtils.setTimePickerDialogNegativeButtonColor;
import static com.hxh.PickerUtils.setTimePickerDialogPositiveButtonColor;
import static com.hxh.mdv.R.id.set;

public class Dia_Fragment extends Fragment implements View.OnClickListener
{
    private Context mContext;

    private Button bt_ad1, bt_ad2, bt_ad3, bt_ad_list, bt_ad_sc, bt_ad_mc, bt_ad_et, bt_pd1, bt_pd2, bt_fsd1, bt_fsd2, bt_fsd3, bt_dpd, bt_tpd, bt_npd, bt_bsd, bt_sb, bt_pm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = getContext();

        View view = inflater.inflate(R.layout.frg_dia, null);

        initView(view);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_ad1:
            {
                showAlertDialog1();

                break;
            }
            case R.id.bt_ad2:
            {
                showAlertDialog2();

                break;
            }
            case R.id.bt_ad3:
            {
                showAlertDialog3();

                break;
            }
            case R.id.bt_ad_list:
            {
                showAlertDialogList();

                break;
            }
            case R.id.bt_ad_sc:
            {
                showAlertDialogSingleChoice();

                break;
            }
            case R.id.bt_ad_mc:
            {
                showAlertDialogMultiChoice();

                break;
            }
            case R.id.bt_ad_et:
            {
                showAlertDialogEditText();

                break;
            }
            case R.id.bt_pd1:
            {
                showProgressDialog1();

                break;
            }
            case R.id.bt_pd2:
            {
                showProgressDialog2();

                break;
            }
            case R.id.bt_fsd1:
            {
                showFullScreenDialog1();

                break;
            }
            case R.id.bt_fsd2:
            {
                showFullScreenDialog2();

                break;
            }
            case R.id.bt_fsd3:
            {
                showFullScreenDialog3();

                break;
            }
            case R.id.bt_dpd:
            {
                showDatePickerDialog();

                break;
            }
            case R.id.bt_tpd:
            {
                showTimePickerDialog();

                break;
            }
            case R.id.bt_npd:
            {
                showNumberPickerDialog();

                break;
            }
            case R.id.bt_bsd:
            {
                showBottomSheetDialog();

                break;
            }
            case R.id.bt_sb:
            {
                showSnackbar(v);

                break;
            }
            case R.id.bt_pm:
            {
                showPopupMenu();

                break;
            }
            default: {break;}
        }
    }

    private void initView(View view)
    {
        bt_ad1 = (Button) view.findViewById(R.id.bt_ad1);
        bt_ad2 = (Button) view.findViewById(R.id.bt_ad2);
        bt_ad3 = (Button) view.findViewById(R.id.bt_ad3);
        bt_ad_list = (Button) view.findViewById(R.id.bt_ad_list);
        bt_ad_sc = (Button) view.findViewById(R.id.bt_ad_sc);
        bt_ad_mc = (Button) view.findViewById(R.id.bt_ad_mc);
        bt_ad_et = (Button) view.findViewById(R.id.bt_ad_et);
        bt_pd1 = (Button) view.findViewById(R.id.bt_pd1);
        bt_pd2 = (Button) view.findViewById(R.id.bt_pd2);
        bt_fsd1 = (Button) view.findViewById(R.id.bt_fsd1);
        bt_fsd2 = (Button) view.findViewById(R.id.bt_fsd2);
        bt_fsd3 = (Button) view.findViewById(R.id.bt_fsd3);
        bt_dpd = (Button) view.findViewById(R.id.bt_dpd);
        bt_tpd = (Button) view.findViewById(R.id.bt_tpd);
        bt_npd = (Button) view.findViewById(R.id.bt_npd);
        bt_pm = (Button) view.findViewById(R.id.bt_pm);
        bt_sb = (Button) view.findViewById(R.id.bt_sb);
        bt_bsd = (Button) view.findViewById(R.id.bt_bsd);

        bt_ad1.setOnClickListener(this);
        bt_ad2.setOnClickListener(this);
        bt_ad3.setOnClickListener(this);
        bt_ad_list.setOnClickListener(this);
        bt_ad_sc.setOnClickListener(this);
        bt_ad_mc.setOnClickListener(this);
        bt_ad_et.setOnClickListener(this);
        bt_pd1.setOnClickListener(this);
        bt_pd2.setOnClickListener(this);
        bt_fsd1.setOnClickListener(this);
        bt_fsd2.setOnClickListener(this);
        bt_fsd3.setOnClickListener(this);
        bt_dpd.setOnClickListener(this);
        bt_tpd.setOnClickListener(this);
        bt_npd.setOnClickListener(this);
        bt_pm.setOnClickListener(this);
        bt_sb.setOnClickListener(this);
        bt_bsd.setOnClickListener(this);
    }

    private void showAlertDialog1()
    {
        new AlertDialog.Builder(mContext).
//                setIcon(R.mipmap.ic_icon).
//                setTitle("AlertDialog1").
        setMessage("This is a AlertDialog1").
                setCancelable(true).
                setPositiveButton("ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showAlertDialog2()
    {
        new AlertDialog.Builder(mContext).
//                setIcon(R.mipmap.ic_icon).
//                setTitle("AlertDialog2").
        setMessage("This is a AlertDialog2").
                setCancelable(true).
                setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "no", Toast.LENGTH_SHORT).show();
                    }
                }).
                setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "yes", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showAlertDialog3()
    {
        new AlertDialog.Builder(mContext).
//                setIcon(R.mipmap.ic_icon).
//                setTitle("AlertDialog3").
        setMessage("This is a AlertDialog3").
                setCancelable(true).
                setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "no", Toast.LENGTH_SHORT).show();
                    }
                }).
                setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "yes", Toast.LENGTH_SHORT).show();
                    }
                }).
                setNeutralButton("about", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "about", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private String[] items = {"This is 1", "This is 2", "This is 3", "This is 4", "This is 5"};

    private void showAlertDialogList()
    {
        new AlertDialog.Builder(mContext).
                setIcon(R.mipmap.ic_icon).
                setTitle("AlertDialogList").
                setCancelable(true).
                //设置setMessage会让该方法无效
                        setItems(items, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, items[which], Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private int position = 0;

    private void showAlertDialogSingleChoice()
    {
        new AlertDialog.Builder(mContext).
                setIcon(R.mipmap.ic_icon).
                setTitle("AlertDialogSingleChoice").
                setCancelable(true).
                //设置setMessage会让该方法无效
                        setSingleChoiceItems(items, position, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        position = which;
                    }
                }).
                setPositiveButton("ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, items[position], Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private boolean choiceSets[] = {false, false, false, false, false};
    private List<String> list_choice = new ArrayList<>();

    private void showAlertDialogMultiChoice()
    {
        new AlertDialog.Builder(mContext).
                setIcon(R.mipmap.ic_icon).
                setTitle("AlertDialogMultiChoice").
                setCancelable(true).
                //设置setMessage会让该方法无效
                        setMultiChoiceItems(items, choiceSets, new DialogInterface.OnMultiChoiceClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked)
                    {
                        if (isChecked)
                        {
                            list_choice.add(items[which]);
                        }
                        else
                        {
                            list_choice.remove(items[which]);
                        }
                    }
                }).
                setPositiveButton("ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String str = "";

                        for (int i = 0; i < list_choice.size(); i++)
                        {
                            str += list_choice.get(i) + " ";
                        }

                        Toast.makeText(mContext, "You choice " + str, Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showAlertDialogEditText()
    {
        final EditText et = new EditText(mContext);
        et.setHint("Input your text");

        new AlertDialog.Builder(mContext).
                setIcon(R.mipmap.ic_icon).
                setTitle("AlertDialogEditText").
//                setMessage("This is a EditTextDialog").
        setCancelable(true).
                setView(et).
                setPositiveButton("ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, et.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showProgressDialog1()
    {
        ProgressDialog dialog = new ProgressDialog(mContext);
//        dialog.setIcon(R.mipmap.ic_icon);
//        dialog.setTitle("ProgressDialog1");
        dialog.setMessage("loading...");
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    private void showProgressDialog2()
    {
        final ProgressDialog dialog = new ProgressDialog(mContext);
//        dialog.setIcon(R.mipmap.ic_icon);
//        dialog.setTitle("ProgressDialog2");
//        dialog.setMessage("loading...");
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.show();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int progress = 0;

                while (progress < 100)
                {
                    try
                    {
                        Thread.sleep(100);
                        progress++;
                        dialog.setProgress(progress);
                    }
                    catch (InterruptedException e) {e.printStackTrace();}
                }

                dialog.cancel();
            }
        }).start();
    }

    private void showFullScreenDialog1()
    {
        final Dialog fullscreenDialog = new Dialog(mContext, R.style.FullscreenDialogTheme1);
        fullscreenDialog.setContentView(R.layout.dia_fsd1);

        ImageView iv_close = (ImageView) fullscreenDialog.findViewById(R.id.dia_fsd_iv_close);
        ImageView iv_main = (ImageView) fullscreenDialog.findViewById(R.id.dia_fsd_iv_main);

        Glide.with(mContext).load(R.mipmap.img_dia_fsd).into(iv_main);

        iv_close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fullscreenDialog.dismiss();
            }
        });

        fullscreenDialog.show();
    }

    private void showFullScreenDialog2()
    {
        final Dialog fullscreenDialog = new Dialog(mContext, R.style.FullscreenDialogTheme2);
        fullscreenDialog.setContentView(R.layout.dia_fsd2);

        fullscreenDialog.findViewById(R.id.tv_cam).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "Camera", Toast.LENGTH_SHORT).show();

                fullscreenDialog.dismiss();
            }
        });

        fullscreenDialog.findViewById(R.id.tv_alm).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "Album", Toast.LENGTH_SHORT).show();

                fullscreenDialog.dismiss();
            }
        });

        fullscreenDialog.show();
    }

    private void showFullScreenDialog3()
    {
        final Dialog fullscreenDialog = new Dialog(mContext, R.style.FullscreenDialogTheme3);
        fullscreenDialog.setContentView(R.layout.dia_fsd3);

        fullscreenDialog.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fullscreenDialog.dismiss();
            }
        });

        fullscreenDialog.findViewById(R.id.tv_cam).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "Camera", Toast.LENGTH_SHORT).show();

                fullscreenDialog.dismiss();
            }
        });

        fullscreenDialog.findViewById(R.id.tv_alm).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "Album", Toast.LENGTH_SHORT).show();

                fullscreenDialog.dismiss();
            }
        });

        fullscreenDialog.show();
    }

    private void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                Toast.makeText(mContext, year + "-" + (month + 1) + "-" + day, Toast.LENGTH_SHORT).show();
            }
        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE));

//        datePickerDialog.setTitle("Select your date");
        datePickerDialog.show();

        setDatePickerDividerColor(mContext, datePickerDialog.getDatePicker(), R.color.textColorPrimary);
        setDatePickerDialogPositiveButtonColor(mContext, datePickerDialog, R.color.colorPrimaryDark);
        setDatePickerDialogNegativeButtonColor(mContext, datePickerDialog, R.color.colorAccent);
    }

    private void showTimePickerDialog()
    {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute)
            {
                Toast.makeText(mContext, hour + ":" + minute, Toast.LENGTH_SHORT).show();
            }
        },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true);

//        timePickerDialog.setTitle("Select your time");
        timePickerDialog.show();

//        setTimePickerDividerColor(mContext, timePickerDialog.getTimePicker(),R.color.textColorPrimary);
        setTimePickerDialogPositiveButtonColor(mContext, timePickerDialog, R.color.colorPrimaryDark);
        setTimePickerDialogNegativeButtonColor(mContext, timePickerDialog, R.color.colorAccent);
    }

    private int num = 0;
    private String City, Province;
    private String[] province = {};

    private final Map<String, String[]> adress = new HashMap<>();

    private final String[] city = {"北京", "天津", "上海", "南京", "深圳", "广州"};

    private String[] province1 = {"朝阳区", "丰台区", "西城区", "海淀区", "通州区", "房山区"};
    private String[] province2 = {"和平区", "河东区", "津南区", "南开区", "西青区", "红桥区"};
    private String[] province3 = {"虹桥区", "徐汇区", "虹口区", "浦东新区", "长宁区", "闵行区"};
    private String[] province4 = {"鼓楼区", "秦淮区", "玄武区", "建邺区", "栖霞区", "江宁区"};
    private String[] province5 = {"盐田区", "罗湖区", "福田区", "南山区", "宝安区", "大鹏新区"};
    private String[] province6 = {"白云区", "海珠区", "天河区", "黄浦区", "番禺区", "花都区"};

    {
        adress.put("北京", province1);
        adress.put("天津", province2);
        adress.put("上海", province3);
        adress.put("南京", province4);
        adress.put("深圳", province5);
        adress.put("广州", province6);

        City = city[0];
        province = adress.get(City);
        Province = province[0];
    }

    private void showNumberPickerDialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        LinearLayout ll = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.dia_numberpicker, null);

        final AlertDialog alertDialog = builder.
//                setIcon(R.mipmap.ic_icon).
//                setTitle("EditTextDialog").
//                setMessage("This is a EditTextDialog").
        setCancelable(true).
                        setView(ll).
                        show();

        NumberPicker np1 = (NumberPicker) ll.findViewById(R.id.dia_np_np_1);
        NumberPicker np2 = (NumberPicker) ll.findViewById(R.id.dia_np_np_2);
        final NumberPicker np3 = (NumberPicker) ll.findViewById(R.id.dia_np_np_3);

        //设置不输入文本
        np1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //设置np1的最小值和最大值
        np1.setMinValue(1);
        np1.setMaxValue(5);
        //设置np1的当前值
        np1.setValue(3);
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                // TODO Auto-generated method stub
                num = newVal;
            }
        });

        np2.setDisplayedValues(city);
        np2.setMinValue(0);
        np2.setMaxValue(city.length - 1);
        //设置np2的当前值
        for (int i = 0; i < city.length; i++)
        {
            if (City.equals(city[i]))
            {
                np2.setValue(i);
            }
        }
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                // TODO Auto-generated method stub
                City = city[newVal];

                province = adress.get(City);
                Province = province[0];

                np3.setDisplayedValues(province);
                np3.setMinValue(0);
                np3.setMaxValue(province.length - 1);
            }
        });

        //设置np3的最小值和最大值
        np3.setDisplayedValues(province);
        np3.setMinValue(0);
        np3.setMaxValue(province.length - 1);
        //设置np1的当前值
        for (int i = 0; i < province.length; i++)
        {
            if (Province.equals(province[i]))
            {
                np3.setValue(i);
            }
        }
        np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                // TODO Auto-generated method stub
                Province = province[newVal];
            }
        });

        setNumberPickerDividerColor(mContext, np1, R.color.textColorPrimary);
        setNumberPickerDividerColor(mContext, np2, R.color.purple);
        setNumberPickerDividerColor(mContext, np3, R.color.pink);

        TextView tv_acc = (TextView) ll.findViewById(R.id.dia_np_tv_accept);
        TextView tv_can = (TextView) ll.findViewById(R.id.dia_np_tv_cancel);

        tv_acc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();

                Toast.makeText(mContext, "num=" + num + " City=" + City + " Province=" + Province, Toast.LENGTH_SHORT).show();
            }
        });

        tv_can.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();
            }
        });
    }

    private void showBottomSheetDialog()
    {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dia_bsd, null);
        bottomSheetDialog.setContentView(dialogView);

        Button bt_ok = (Button) dialogView.findViewById(R.id.btn_dialog_bottom_sheet_ok);
        Button bt_cancel = (Button) dialogView.findViewById(R.id.btn_dialog_bottom_sheet_cancel);

        bt_ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bottomSheetDialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bottomSheetDialog.dismiss();
            }
        });

        ImageView iv = (ImageView) dialogView.findViewById(R.id.dia_bsd_iv);

        Glide.with(mContext).load(R.mipmap.img_dia_bsd).into(iv);

        bottomSheetDialog.show();
    }

    private void showSnackbar(View view)
    {
        Snackbar snackbar = Snackbar.make(view, "This is a Snackbar\r\nAt top is also ok!", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                    }
                });

//        //获取Snackbar的view
//        View snackbarview = snackbar.getView();
//
//        //背景色
//        snackbarview.setBackgroundColor(Color.parseColor("#00bb00"));
//
//        //文字颜色
//        ((TextView) snackbarview.findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#0000ff"));
//        //按钮文字颜色
//        ((Button) snackbarview.findViewById(R.id.snackbar_action)).setTextColor(Color.parseColor("#ff0000"));
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER_VERTICAL;
//
//        //要添加图片的布局
//        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(R.layout.snackbar, null);
//
//        //添加布局
//        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;
//        snackbarLayout.addView(add_view, 0, params);

        snackbar.show();
    }

    private void showPopupMenu()
    {
        PopupMenu popupMenu = new PopupMenu(mContext, bt_pm);

        popupMenu.getMenuInflater().inflate(R.menu.popupmenu2, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.exit:
                    {
                        Toast.makeText(mContext, "exit", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case set:
                    {
                        Toast.makeText(mContext, "set", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case R.id.about:
                    {
                        Toast.makeText(mContext, "about", Toast.LENGTH_SHORT).show();

                        break;
                    }
                }

                return false;
            }
        });

        popupMenu.show();
    }
}