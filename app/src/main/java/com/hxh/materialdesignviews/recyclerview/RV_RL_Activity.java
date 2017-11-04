package com.hxh.materialdesignviews.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.hxh.DisplayUtils;
import com.hxh.materialdesignviews.R;

import java.util.ArrayList;
import java.util.List;

public class RV_RL_Activity extends AppCompatActivity
{
    Context mContext = this;

    Toolbar tb;
    SwipeRefreshLayout srl;
    RecyclerView rv;
    ProgressBar pb;

    boolean canLoad = true;

    List<String> list = new ArrayList<>();

    {
        list.add("This is line:\n11111");
        list.add("This is line:\n22222");
        list.add("This is line:\n33333");
        list.add("This is line:\n44444");
        list.add("This is line:\n55555");
        list.add("This is line:\n66666");
        list.add("This is line:\n77777");
        list.add("This is line:\n88888");
        list.add("This is line:\n99999");
        list.add("This is line:\n1010101010");
        list.add("This is line:\n1111111111");
        list.add("This is line:\n1212121212");
        list.add("This is line:\n1313131313");
        list.add("This is line:\n1414141414");
        list.add("This is line:\n1515151515");
    }

    RVAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rv_rl);

        adapter = new RVAdapter(mContext, list);

        pb = (ProgressBar) findViewById(R.id.pb);

        initToolbar();
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initToolbar()
    {
        tb = (Toolbar) findViewById(R.id.tb);

        tb.setTitle("RecyclerView Refresh Load");

        setSupportActionBar(tb);
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

    private void initSwipeRefreshLayout()
    {
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);

        srl.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        //设置进度圈的背景色
        srl.setProgressBackgroundColorSchemeResource(android.R.color.white);

        //设置进度圈的大小，只有两个值：DEFAULT、LARGE
        srl.setSize(SwipeRefreshLayout.DEFAULT);

//        srl.setProgressViewOffset(true, 100, 200);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                Timing(0);
            }
        });

        Timing(0);
    }

    private void initRecyclerView()
    {
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(mContext));

        rv.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                super.getItemOffsets(outRect, view, parent, state);

                outRect.right = DisplayUtils.dp_to_px(mContext, 5);
                outRect.left = DisplayUtils.dp_to_px(mContext, 5);

                if (parent.getChildAdapterPosition(view) == 0)
                {
                    outRect.top = DisplayUtils.dp_to_px(mContext, 8);
                }
                else if (parent.getChildAdapterPosition(view) == (list.size() - 1))
                {
                    outRect.bottom = DisplayUtils.dp_to_px(mContext, 2);
                }
            }
        });

        rv.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);

                if (!rv.canScrollVertically(1))
                {
                    if (canLoad)
                    {
                        Timing(1);
                    }

                    canLoad = false;
                }
//                if (recyclerView.canScrollVertically(1))
//                {
//                    Log.i("onScrollStateChanged","滚动到顶部");
//                }
//                else if (recyclerView.canScrollVertically(-1))
//                {
//                    Log.i("onScrollStateChanged","滚动到底部");
//                }
//
//                if (newState == RecyclerView.SCROLL_STATE_IDLE)
//                {
//                    Log.i("onScrollStateChanged","正在滚动---" + RecyclerView.SCROLL_STATE_IDLE);
//                }
//                else if (newState == RecyclerView.SCROLL_STATE_DRAGGING)
//                {
//                    Log.i("onScrollStateChanged","被外部拖拽---" + RecyclerView.SCROLL_STATE_DRAGGING);
//                }
//                else if (newState == RecyclerView.SCROLL_STATE_SETTLING)
//                {
//                    Log.i("onScrollStateChanged","自动滚动---" + RecyclerView.SCROLL_STATE_SETTLING);
//                }
            }
        });
    }

    int code = 0;

    private boolean Timing(final int type)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);

                    if (type == 0)
                    {
                        new doRefreshTask().execute();
                    }
                    else if (type == 1)
                    {
                        new doLoadTask().execute();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        return true;
    }

    private class doRefreshTask extends AsyncTask<Boolean, Integer, Boolean>
    {
        @Override
        protected Boolean doInBackground(Boolean... params)
        {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            super.onPostExecute(result);

            srl.setRefreshing(false);

            rv.setAdapter(adapter);

            pb.setVisibility(View.GONE);
        }
    }

    private class doLoadTask extends AsyncTask<Boolean, Integer, Boolean>
    {
        @Override
        protected Boolean doInBackground(Boolean... params)
        {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            super.onPostExecute(result);

            list.add("code=" + code);
            list.add("code=" + code);
            list.add("code=" + code);
            list.add("code=" + code);
            list.add("code=" + code);

            adapter.resetList(list);

            canLoad = true;

            code++;
        }
    }
}