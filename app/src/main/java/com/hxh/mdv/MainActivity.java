package com.hxh.mdv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hxh.mdv.fragment.BT_Fragment;
import com.hxh.mdv.fragment.CV_Fragment;
import com.hxh.mdv.fragment.Dia_Fragment;
import com.hxh.mdv.fragment.Edit_Fragment;
import com.hxh.mdv.fragment.Prg_Fragment;
import com.hxh.mdv.fragment.SB_Fragment;
import com.hxh.mdv.fragment.Spi_Fragment;
import com.hxh.mdv.fragment.Swi_Fragment;
import com.hxh.mdv.recyclerview.RV_Linear_Activity;
import com.hxh.mdv.recyclerview.RV_RL_Activity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity
{
    private Context mContext = this;

    private DrawerLayout dl;
    private LinearLayout ll_left;
    private RelativeLayout rl_right;
    private Toolbar tb;
    private TabLayout tl;
    private ViewPager vp;

    private List<String> list_title = new ArrayList<>();
    private List<Fragment> list_fragment = new ArrayList<>();

    {
        list_title.add("CardViews");
        list_title.add("Buttons");
        list_title.add("Switches");
        list_title.add("EditTexts");
        list_title.add("Dialogs");
        list_title.add("Progresses");
        list_title.add("SeekBars");
        list_title.add("Spinner");

        CV_Fragment fragment1 = new CV_Fragment();
        BT_Fragment fragment2 = new BT_Fragment();
        Swi_Fragment fragment3 = new Swi_Fragment();
        Edit_Fragment fragment4 = new Edit_Fragment();
        Dia_Fragment fragment5 = new Dia_Fragment();
        Prg_Fragment fragment6 = new Prg_Fragment();
        SB_Fragment fragment7 = new SB_Fragment();
        Spi_Fragment fragment8 = new Spi_Fragment();

        list_fragment.add(fragment1);
        list_fragment.add(fragment2);
        list_fragment.add(fragment3);
        list_fragment.add(fragment4);
        list_fragment.add(fragment5);
        list_fragment.add(fragment6);
        list_fragment.add(fragment7);
        list_fragment.add(fragment8);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        initDrawerLayout();
        initToolbar();
        initViewPager();
        initTabLayout();
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed()
    {
        if (dl.isDrawerOpen(GravityCompat.START))
        {
            dl.closeDrawer(GravityCompat.START);
        }
        else
        {
            if (currentTimeMillis() - exitTime > 2000)
            {
                Toast.makeText(mContext, "Click again to quit", Toast.LENGTH_SHORT).show();

                exitTime = currentTimeMillis();
            }
            else
            {
                super.onBackPressed();
            }
        }
    }

    public void login(View v)
    {
        dl.closeDrawer(ll_left);
        startActivity(new Intent(mContext, Login_Activity.class));
    }

    public void rv(View v)
    {
        dl.closeDrawer(ll_left);
        startActivity(new Intent(mContext, RV_Linear_Activity.class));
    }

    public void rv_l_r(View v)
    {
        dl.closeDrawer(ll_left);
        startActivity(new Intent(mContext, RV_RL_Activity.class));
    }

    public void scro(View v)
    {
        dl.closeDrawer(ll_left);
        startActivity(new Intent(mContext, Scro_Activity.class));
    }

    private void initToolbar()
    {
        tb = (Toolbar) findViewById(R.id.tb);

//        //设置导航栏图标
//        tb.setNavigationIcon(R.mipmap.bt_menu);
//        //设置app logo
//        tb.setLogo(R.mipmap.ic_launcher);
//        //设置主标题
//        tb.setTitle("Title");
//        //设置子标题
//        tb.setSubtitle("Subtitle");
//        //设置右侧菜单栏按钮
//        tb.setOverflowIcon(getDrawable(R.mipmap.bt_menu));

        tb.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dl.openDrawer(ll_left);
            }
        });

        tb.inflateMenu(R.menu.toolbar1);
//        tb.inflateMenu(R.menu.toolbar2);

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if (item.getItemId() == R.id.about)
                {
                    Toast.makeText(mContext, "Developed by hxh1892 @_@", Toast.LENGTH_SHORT).show();
                }

//                if (item.getItemId() == R.id.tm1)
//                {
//                    Toast.makeText(mContext, "tm1", Toast.LENGTH_SHORT).show();
//                }
//                else if (item.getItemId() == R.id.tm2)
//                {
//                    Toast.makeText(mContext, "tm2", Toast.LENGTH_SHORT).show();
//                }
//                else if (item.getItemId() == R.id.tm3)
//                {
//                    Toast.makeText(mContext, "tm3", Toast.LENGTH_SHORT).show();
//                }
//                else if (item.getItemId() == R.id.tm4)
//                {
//                    Toast.makeText(mContext, "tm4", Toast.LENGTH_SHORT).show();
//                }
//                else if (item.getItemId() == R.id.tm5)
//                {
//                    Toast.makeText(mContext, "tm5", Toast.LENGTH_SHORT).show();
//                }
//                else if (item.getItemId() == R.id.tm6)
//                {
//                    Toast.makeText(mContext, "tm6", Toast.LENGTH_SHORT).show();
//                }

                return false;
            }
        });
    }

    private void initTabLayout()
    {
        tl = (TabLayout) findViewById(R.id.tl);

        tl.setupWithViewPager(vp);

        //TabLayout切换tab
//        tl.getTabAt(1).select();
    }

    private void initDrawerLayout()
    {
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        rl_right = (RelativeLayout) findViewById(R.id.rl_right);

        dl = (DrawerLayout) findViewById(R.id.dl);

        dl.addDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                if (drawerView.getId() == ll_left.getId())
                {
//                    Toast.makeText(mContext, "rl_left在滑动", Toast.LENGTH_SHORT).show();
                }
                else if (drawerView.getId() == rl_right.getId())
                {
//                    Toast.makeText(mContext, "rl_right在滑动", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                if (drawerView.getId() == ll_left.getId())
                {
//                    Toast.makeText(mContext, "rl_left打开", Toast.LENGTH_SHORT).show();
                }
                else if (drawerView.getId() == rl_right.getId())
                {
//                    Toast.makeText(mContext, "rl_right打开", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                if (drawerView.getId() == ll_left.getId())
                {
//                    Toast.makeText(mContext, "rl_left关闭", Toast.LENGTH_SHORT).show();
                }
                else if (drawerView.getId() == rl_right.getId())
                {
//                    Toast.makeText(mContext, "rl_right关闭", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDrawerStateChanged(int newState)
            {
            }
        });
    }

    private void initViewPager()
    {
        vp = (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(new VPAdapter(getSupportFragmentManager()));
    }

    private class VPAdapter extends FragmentPagerAdapter
    {
        public VPAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            return list_fragment.get(position);
        }

        @Override
        public int getCount()
        {
            return list_fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return list_title.get(position);
        }
    }
}
