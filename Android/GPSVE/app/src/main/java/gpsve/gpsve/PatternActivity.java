package gpsve.gpsve;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import processing.android.PFragment;
import processing.core.PApplet;

public class PatternActivity extends AppCompatActivity {
    private Intent intent;
    private String pattern;
    private PatternController pApplet;
    private SoundConverter soundConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        soundConverter = new SoundConverter();
        pApplet = new PatternController(this, soundConverter);
    }

    @Override
    protected void onResume(){
        super.onResume();
            FragmentManager fragmentManager = getSupportFragmentManager();
            intent = getIntent();
            pattern = intent.getStringExtra("pattern");
            switch (pattern) {
                case "demo": pApplet.setPattern(new PatternPidde(pApplet));
                    break;
                case "demo2": pApplet.setPattern(new PatternCircle(pApplet));
                    break;
            }

            PFragment fragment = new PFragment();
            fragment.setSketch(pApplet);
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

