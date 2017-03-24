package com.gpsve.sounddemo;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import processing.android.PFragment;

public class MainActivity extends AppCompatActivity {
    private VisualizerDemo demo;
    private MediaPlayer player;
    private int yo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        demo = new VisualizerDemo();
        PFragment fragment = new PFragment();
        fragment.setSketch(demo);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit();
        init();
    }

    private void init()
    {
        player = MediaPlayer.create(this, R.raw.disco);
        player.setLooping(true);
        player.start();

        Log.v("HEJ",""+(player.getAudioSessionId()));
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                yo);

        demo.link(player);

        // Start with just line renderer

    }



}
