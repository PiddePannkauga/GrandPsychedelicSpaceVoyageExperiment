package gpsve.gpsve;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import processing.android.PFragment;
import processing.core.PApplet;

public class PatternActivity extends AppCompatActivity {
    private Intent intent;
    private String pattern;
    private PApplet pApplet;
    private int integer;
    private SoundConverter sC;
    private VisualizerDemo demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        sC = new SoundConverter();
        FragmentManager fragmentManager = getSupportFragmentManager();
        intent = getIntent();
        pattern = intent.getStringExtra("pattern");
        switch (pattern) {
            case "circle": pApplet = new Circle();
                break;
            case "square": pApplet = new Square();
                break;
            case "demo": pApplet = demo = new VisualizerDemo(sC);
                initMediaPlayer();
                break;
        }

        PFragment fragment = new PFragment();
        fragment.setSketch(pApplet);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private void initMediaPlayer()
    {
        sC.link();

        // Start with just line renderer

    }
}
