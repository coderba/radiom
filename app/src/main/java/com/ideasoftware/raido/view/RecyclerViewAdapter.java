package com.ideasoftware.raido.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andremion.music.MusicCoverView;
import com.ideasoftware.raido.R;
import com.ideasoftware.raido.activities.DetailActivity;
import com.ideasoftware.raido.music.MusicContent;
import com.ideasoftware.raido.util.Helper;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static String TAG = RecyclerViewAdapter.class.getSimpleName();

    private List<MusicContent> mValues;
    private Activity mActivity;
    private Intent intent;
    private View cover;
    private View titleView;

    public RecyclerViewAdapter(List<MusicContent> items, Activity activity, View cover, View titleView) {
        mActivity = activity;
        mValues = items;
        this.cover = cover;
        this.titleView = titleView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        ((ImageView) holder.mCoverView).setImageResource(holder.mItem.getCover());
        ((TextView) holder.mTitleView).setText(holder.mItem.getTitle());
        ((TextView) holder.mArtistView).setText(holder.mItem.getArtist());
        //((TextView)holder.mDurationView).setText(DateUtils.formatElapsedTime(holder.mItem.getDuration()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nothing to do
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity,
                        new Pair<>(holder.mCoverView, ViewCompat.getTransitionName(holder.mCoverView)),
                        new Pair<>(holder.mTitleView, ViewCompat.getTransitionName(holder.mTitleView)));
                //new Pair<>(holder.mPlayButton, ViewCompat.getTransitionName(holder.mPlayButton)));


                intent = new Intent(mActivity, DetailActivity.class);
                intent.putExtra("title", ((TextView) holder.mTitleView).getText());
                //((TextView)titleView).setText(((TextView) holder.mTitleView).getText());
                intent.putExtra("artist", ((TextView) holder.mArtistView).getText());

                if (((ImageView) holder.mCoverView).getDrawable() != null) {
                    intent.putExtra("cover", Helper.drawableToByteArray(((ImageView) holder.mCoverView).getDrawable()));
                    ((MusicCoverView)cover).setImageBitmap(Helper.byteArrayToBitmap(Helper.drawableToByteArray(((ImageView) holder.mCoverView).getDrawable())));
                }

                ActivityCompat.startActivity(mActivity, intent, options.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final View mCoverView;
        public final View mTitleView;
        public final View mArtistView;
        //public final View mPlayButton;

        public MusicContent mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCoverView = view.findViewById(R.id.cover);
            mTitleView = view.findViewById(R.id.title);
            mArtistView = view.findViewById(R.id.artist);
            //mPlayButton = view.findViewById(R.id.list_item_play_button);
        }
    }

}
