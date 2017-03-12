package com.ideasoftware.raido.test;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ideasoftware.raido.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by cembasarbaskan on 05/12/2016.
 */

public class TestActivity extends Activity {

    private Button playButton;
    private MediaPlayer mediaPlayer;

    private String url = "http://opml.radiotime.com/Tune.ashx?id=s161286";

    /*private String url = "http://maxpag.eu:8000/metropole";
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_test);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        playButton = (Button) findViewById(R.id.play_button);

        ArrayList<String> temp = readURLs(url);
        Ion.with(getApplicationContext())
                .load("http://17703.live.streamtheworld.com:80/METRO_FMAAC_SC")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        System.out.println("JSON is : ");
                        System.out.print(result);
                        // do stuff with the result or error
                    }
                });

        try {
            // Simply pass string in which stored URL of music.
            mediaPlayer.setDataSource("http://17703.live.streamtheworld.com:80/METRO_FMAAC_SC");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_SHORT).show();
            }
        });

/*        // Whenever media player is prepared with source this method is invoke.
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "onPrepared",Toast.LENGTH_SHORT).show();
                mp.start();
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public ArrayList<String> readURLs(String url) {
        if (url == null) return null;
        ArrayList<String> allURls = new ArrayList<String>();
        try {

            URL urls = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(urls
                    .openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                allURls.add(str);
            }
            in.close();
            return allURls;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
