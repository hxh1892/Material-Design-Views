package com.hxh.materialdesignviews.recyclerview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.hxh.DisplayUtils;
import com.hxh.materialdesignviews.R;

import java.util.ArrayList;
import java.util.List;

public class RV_Grid_Activity extends AppCompatActivity
{
    Context mContext = this;

    Toolbar tb;
    RecyclerView rv;
    RecyclerViewAdapter adapter = new RecyclerViewAdapter();
    List<String> list = new ArrayList<>();

    int lines = 4;

    {
        list.add("Click this view to goto RV_HorizonGrid_Activity");

        for (int i = 'A'; i <= 'Z'; i++)
        {
            list.add((char) i + ":" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rv);

        initToolbar();
        initRecyclerView();
    }

    public void up(View v)
    {
        rv.smoothScrollToPosition(0);
    }

    public void add(View v)
    {
        adapter.addItem(5);
    }

    public void red(View v)
    {
        adapter.removeItem(5);
    }

    public void down(View v)
    {
        rv.smoothScrollToPosition(list.size());
    }

    private void initToolbar()
    {
        tb = (Toolbar) findViewById(R.id.tb);

        tb.setTitle("RecyclerView Grid");

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

    private void initRecyclerView()
    {
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new GridLayoutManager(mContext, lines));

        rv.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                super.getItemOffsets(outRect, view, parent, state);

                if (parent.getChildAdapterPosition(view) <= (lines - 1))
                {
                    outRect.top = DisplayUtils.dp_to_px(mContext, 8);
                }

                if ((parent.getChildAdapterPosition(view) + 1) % lines == 0)
                {
                    outRect.right = DisplayUtils.dp_to_px(mContext, 5);
                }
                else if ((parent.getChildAdapterPosition(view)) % lines == 0)
                {
                    outRect.left = DisplayUtils.dp_to_px(mContext, 5);
                }

                if (list.size() % 4 == 0)
                {
                    if (parent.getChildAdapterPosition(view) >= (list.size() - 1 - lines) &&
                            parent.getChildAdapterPosition(view) <= (list.size() - 1))
                    {
                        outRect.bottom = DisplayUtils.dp_to_px(mContext, 2);
                    }
                }
                else
                {
                    if (parent.getChildAdapterPosition(view) >= (list.size() - list.size() % 4 - 1) &&
                            parent.getChildAdapterPosition(view) <= (list.size() - 1))
                    {
                        outRect.bottom = DisplayUtils.dp_to_px(mContext, 2);
                    }
                }
            }
        });

        rv.setAdapter(adapter);
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder>
    {
        class Viewholder extends RecyclerView.ViewHolder
        {
            CardView cv;
            TextView tv;

            public Viewholder(View view)
            {
                super(view);

                cv = (CardView) view.findViewById(R.id.cv);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }

        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new Viewholder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_grid, parent, false));
        }

        @Override
        public void onBindViewHolder(Viewholder holder, final int position)
        {
            holder.tv.setText(list.get(position) + "");

            holder.cv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (position == 0)
                    {
                        startActivity(new Intent(mContext, RV_HorizonGrid_Activity.class));
                    }
                }
            });

            holder.cv.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View view, MotionEvent event)
                {
                    switch (event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                        {
                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationZ", 3);
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
            });
        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }

        public void addItem(int position)
        {
            list.add(position, "RV_Grid");
            notifyItemInserted(position);
        }

        public void removeItem(int position)
        {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }
}
