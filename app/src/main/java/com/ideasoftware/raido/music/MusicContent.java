package com.ideasoftware.raido.music;

public class MusicContent {
    private final String mCover;
        private final String mTitle;
        private final String mArtist;
        private final long mDuration;

    public MusicContent(String cover, String title, String artist, long duration) {
            mCover = cover;
            mTitle = title;
            mArtist = artist;
            mDuration = duration;
        }

    public String getCover() {
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
