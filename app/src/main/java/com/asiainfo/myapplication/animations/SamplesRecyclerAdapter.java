package com.asiainfo.myapplication.animations;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;
import com.asiainfo.myapplication.util.TransitionHelper;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder>{

    private final Activity mActivity;
    private final List<Sample> mSampleList;
    private LayoutInflater mInflater;

    SamplesRecyclerAdapter(Activity activity, List<Sample> samples){
        this.mActivity = activity;
        this.mSampleList = samples;
        this.mInflater = LayoutInflater.from(activity);
    }
    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SamplesViewHolder(mInflater.inflate(R.layout.row_sample, parent, false));
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder holder, final int position) {
        final Sample sample = mSampleList.get(holder.getAdapterPosition());
        holder.mTitle.setText(mSampleList.get(position).getName());
        holder.mContent.setColorFilter(sample.getColor());
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        transitionToActivity(TransitionActivity1.class, sample);
                        break;
                    case 1:
                        transitionToActivity(SharedElementActivity.class, holder, sample);
                        break;
                    case 2:
                        transitionToActivity(AnimationActivity1.class, sample);
                        break;
                    case 3:
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSampleList==null?0:mSampleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class SamplesViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLayout;

        TextView mTitle;

        ImageView mContent;

        public SamplesViewHolder(View rootView) {
            super(rootView);
            mLayout = (LinearLayout) rootView.findViewById(R.id.sample_layout);
            mTitle = (TextView) rootView.findViewById(R.id.sample_name);
            mContent = (ImageView) rootView.findViewById(R.id.sample_icon);
        }
    }

    private void transitionToActivity(Class target, Sample sample){
        final Pair<View, String>[]  pairs = TransitionHelper.createSafeTransitionParticipants(mActivity, true);
        startActivity(target,pairs,sample);
    }

    private void transitionToActivity(Class target, SamplesViewHolder viewHolder, Sample sample){
        final Pair<View,String>[] pairs = TransitionHelper.createSafeTransitionParticipants(mActivity, false,
                new Pair<>(viewHolder.mContent,mActivity.getString(R.string.square_blue_name)),
                new Pair<>(viewHolder.mTitle, mActivity.getString(R.string.sample_blue_title)));
        startActivity(target,pairs,sample);
    }

    private void transitionToActivity(Class target, SamplesViewHolder viewHolder, Sample sample, int transitionName){
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(mActivity, false,
                new Pair<>(viewHolder.mContent, mActivity.getString(transitionName)));
        startActivity(target, pairs, sample);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, Sample sample){
        Intent mIntent = new Intent(mActivity, target);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, pairs);
        mIntent.putExtra(BaseActivity.EXTRA_SAMPLE, sample);
        mActivity.startActivity(mIntent, options.toBundle());

    }
}
