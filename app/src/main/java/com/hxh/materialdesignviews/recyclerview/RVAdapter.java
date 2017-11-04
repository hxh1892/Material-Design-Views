package com.hxh.materialdesignviews.recyclerview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxh.materialdesignviews.R;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int TYPE_DEFAULT = 0;
    private static final int TYPE_FOOTER = 1;

    private boolean showFooter = true;

    Context mContext;
    List<String> list = new ArrayList<>();

    public RVAdapter(Context context, List<String> list)
    {
        this.mContext = context;
        this.list = list;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder
    {
        public FooterViewHolder(View view)
        {
            super(view);
        }
    }

    public class DefaultViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        ImageView iv;
        TextView tv;

        public DefaultViewHolder(View view)
        {
            super(view);

            cv = (CardView) view.findViewById(R.id.cv);
            iv = (ImageView) view.findViewById(R.id.iv);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position + 1 == getItemCount())
        {
            return TYPE_FOOTER;
        }
        else
        {
            return TYPE_DEFAULT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == TYPE_DEFAULT)
        {
            return new DefaultViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_default, parent, false));
        }
        else
        {
            return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_loader, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        if (holder instanceof DefaultViewHolder)
        {
            ((DefaultViewHolder) holder).iv.setImageResource(R.mipmap.ic_icon);
            ((DefaultViewHolder) holder).tv.setText(list.get(position));

            ((DefaultViewHolder) holder).cv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });

            ((DefaultViewHolder) holder).cv.setOnTouchListener(new View.OnTouchListener()
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
    }

    @Override
    public int getItemCount()
    {
        if (showFooter)
        {
            return list.size() + 1;
        }
        else
        {
            return list.size();
        }
    }

    public void setShowFooter(boolean showFooter)
    {
        this.showFooter = showFooter;
    }

    public boolean isShowFooter()
    {
        return this.showFooter;
    }

    public void resetList(List<String> list)
    {
        this.list = list;

        notifyDataSetChanged();
    }
}
