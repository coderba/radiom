/*
 * Copyright (c) 2016. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.andremion.musicplayer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.andremion.musicplayer.R;
import com.sample.andremion.musicplayer.music.MusicContent;
import com.sample.andremion.musicplayer.util.Helper;
import com.sample.andremion.musicplayer.view.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends PlayerActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private View mCoverView;
    private View mTitleView;
    private View mTimeView;
    private View mDurationView;
    private View mProgressView;
    private View mFabView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);

        List<MusicContent> ITEMS = new ArrayList<>();

        ITEMS.add(new MusicContent(R.drawable.album_cover_death_cab, "I will possess your heart", "Death Cab for Cutie", 515));
        ITEMS.add(new MusicContent(R.drawable.album_cover_the_1975, "You", "the 1975", 591));
        ITEMS.add(new MusicContent(R.drawable.album_cover_pinback, "The Yellow Ones", "Pinback", 215));
        ITEMS.add(new MusicContent(R.drawable.album_cover_soad, "Chop suey", "System of a down", 242));
        ITEMS.add(new MusicContent(R.drawable.album_cover_two_door, "Something good can work", "Two Door Cinema Club", 164));

        mCoverView = findViewById(R.id.cover);
        mTitleView = findViewById(R.id.title);
        mTimeView = findViewById(R.id.time);
        mDurationView = findViewById(R.id.duration);
        mProgressView = findViewById(R.id.progress);
        mFabView = findViewById(R.id.fab);

        // Set the recycler adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tracks);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(ITEMS, MainActivity.this,mCoverView,mTitleView));

    }

    public void onFabClick(View view) {

        //noinspection unchecked
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<>(mCoverView, ViewCompat.getTransitionName(mCoverView)),
                new Pair<>(mTitleView, ViewCompat.getTransitionName(mTitleView)),
                new Pair<>(mTimeView, ViewCompat.getTransitionName(mTimeView)),
                new Pair<>(mDurationView, ViewCompat.getTransitionName(mDurationView)),
                new Pair<>(mProgressView, ViewCompat.getTransitionName(mProgressView)),
                new Pair<>(mFabView, ViewCompat.getTransitionName(mFabView)));

        Toast.makeText(getApplicationContext(), TAG + " FAB CLICK!", Toast.LENGTH_SHORT).show();

        intent = new Intent(this, DetailActivity.class);

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

}
