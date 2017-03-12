package com.ideasoftware.raido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ideasoftware.raido.R;
import com.ideasoftware.raido.model.Station;
import com.ideasoftware.raido.music.MusicContent;
import com.ideasoftware.raido.view.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends PlayerActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String value;
    private Station station;
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
        station = new Station();

        final List<MusicContent> ITEMS = new ArrayList<>();

        //Firebase connections
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String coverUrl = "";

                System.out.println("\n**********\n");

                for (DataSnapshot alert : dataSnapshot.getChildren()) {

                    Station station = new Station();

                    System.out.println("ID: " + alert.child("id").getValue());
                    System.out.println("NAME: " + alert.child("name").getValue());

                    station.setStationName(alert.child("name").getValue().toString());

                    for (DataSnapshot recipient : alert.child("streams").getChildren()) {
                        System.out.println("STREAM URL: " + recipient.child("stream").getValue());
                        station.setStreamUrl(recipient.child("stream").getValue().toString());
                    }

                    for (DataSnapshot recipient : alert.child("image").getChildren()) {

                        coverUrl = alert.child("image").getChildren().iterator().next().getValue().toString();

                        coverUrl = coverUrl.substring(5, coverUrl.length() - 1);
                        station.setCoverUrl(coverUrl);
                        System.out.println("THUMB URL: " + coverUrl);

                    }

                    ITEMS.add(new MusicContent(station.getCoverUrl(), station.getStationName(), station.getPlayingSongName(), 515));

                    System.out.println("\n**********\n");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
/*
                System.out.print(dataSnapshot.getValue());
*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


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
        recyclerView.setAdapter(new RecyclerViewAdapter(ITEMS, MainActivity.this, mCoverView, mTitleView));

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
