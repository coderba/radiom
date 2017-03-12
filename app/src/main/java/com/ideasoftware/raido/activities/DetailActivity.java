package com.ideasoftware.raido.activities;

import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.TextView;

import com.andremion.music.MusicCoverView;
import com.ideasoftware.raido.R;
import com.ideasoftware.raido.util.Helper;
import com.ideasoftware.raido.view.TransitionAdapter;


public class DetailActivity extends PlayerActivity {

    private MusicCoverView mCoverView;
    private Bundle bundle;
    private TextView songTextView;
    private TextView bandTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        mCoverView = (MusicCoverView) findViewById(R.id.cover);
        songTextView = (TextView) findViewById(R.id.song_name);
        bandTextView = (TextView) findViewById(R.id.band_name);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getString("title") != null) {
                songTextView.setText(bundle.getString("title"));
            }

            if (bundle.getString("artist") != null) {
                bandTextView.setText(bundle.getString("artist"));
            }

            if (bundle.getByteArray("cover") != null) {
                mCoverView.setImageBitmap(Helper.byteArrayToBitmap(bundle.getByteArray("cover")));
            }
        }

        mCoverView.setCallbacks(new MusicCoverView.Callbacks() {
            @Override
            public void onMorphEnd(MusicCoverView coverView) {
                // Nothing to do
            }

            @Override
            public void onRotateEnd(MusicCoverView coverView) {
                supportFinishAfterTransition();
            }
        });

        getWindow().getSharedElementEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                play();
                mCoverView.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        onFabClick(null);
    }

    public void onFabClick(View view) {
        pause();
        mCoverView.stop();
    }

}