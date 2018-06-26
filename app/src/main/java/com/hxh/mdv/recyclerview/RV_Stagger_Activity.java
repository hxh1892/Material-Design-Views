package com.hxh.mdv.recyclerview;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.hxh.utils.DisplayUtils;
import com.hxh.mdv.R;

import java.util.ArrayList;
import java.util.List;

public class RV_Stagger_Activity extends AppCompatActivity
{
    private Context mContext = this;

    private RecyclerView rv;
    private RecyclerViewAdapter adapter = new RecyclerViewAdapter();
    private List<String> list = new ArrayList<>();

    private int lines = 4;

    {
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
        Toolbar tb = findViewById(R.id.tb);

        tb.setTitle("RecyclerView Stagger");

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
        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new StaggeredGridLayoutManager(lines, StaggeredGridLayoutManager.VERTICAL));

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

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private class Viewholder extends RecyclerView.ViewHolder
        {
            private CardView cv;
            private TextView tv;

            private Viewholder(View view)
            {
                super(view);

                cv = view.findViewById(R.id.cv);
                tv = view.findViewById(R.id.tv);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new Viewholder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_stagger, parent, false));
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
        {
            ViewGroup.LayoutParams params = ((Viewholder) holder).cv.getLayoutParams();

            //把随机的高度赋予item布局
            params.height = (int) (300 + Math.random() * 300);
            //把params设置item布局
            ((Viewholder) holder).cv.setLayoutParams(params);

            ((Viewholder) holder).tv.setText(list.get(position) + "");

            ((Viewholder) holder).cv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(mContext, "click-" + position, Toast.LENGTH_SHORT).show();
                }
            });

            ((Viewholder) holder).cv.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View view, MotionEvent event)
                {
                    switch (event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                        {
                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationZ", 10);
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

        void addItem(int position)
        {
            list.add(position, "RV_Stagger");
            notifyItemInserted(position);
        }

        void removeItem(int position)
        {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }
}
