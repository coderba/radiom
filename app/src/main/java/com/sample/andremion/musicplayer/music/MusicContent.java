package com.sample.andremion.musicplayer.music;

import com.sample.andremion.musicplayer.R;

import java.util.ArrayList;
import java.util.List;

public class MusicContent {
        private final int mCover;
        private final String mTitle;
        private final String mArtist;
        private final long mDuration;

        public MusicContent(int cover, String title, String artist, long duration) {
            mCover = cover;
            mTitle = title;
            mArtist = artist;
            mDuration = duration;
        }

        public int getCover() {
            return mCover;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getArtist() {
            return mArtist;
        }

        public long getDuration() {
            return mDuration;
        }

}
