package gpsve.gpsve;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import processing.android.PFragment;

public class PatternActivity extends AppCompatActivity {
    private Intent intent;
    private String pattern;
    private PatternController pApplet;
    private SoundConverter soundConverter;
    private PFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        soundConverter = new SoundConverter();
        fragment = new PFragment();
        pApplet = new PatternController(this, soundConverter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        intent = getIntent();
        pattern = intent.getStringExtra("pattern");
        switch (pattern) {
            case "pattern1": pApplet.setPattern(new PatternPidde(pApplet));
                break;
            case "pattern2": pApplet.setPattern(new PatternNisse(pApplet));
                break;
            case "pattern3": pApplet.setPattern(new PatternCircle(pApplet));
                break;
            case "pattern4": pApplet.setPattern(new PatternPidde(pApplet));
                break;
        }

        fragment.setSketch(pApplet);
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    protected void onStop() {
        super.onStop();
        System.out.println("onStop()");
        soundConverter.disableVisualizer();
    }
}


