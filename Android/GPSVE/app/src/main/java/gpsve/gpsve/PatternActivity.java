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
    private PApplet sketch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        FragmentManager fragmentManager = getSupportFragmentManager();
        intent = getIntent();
        pattern = intent.getStringExtra("pattern");
        switch (pattern) {
            case "circle": sketch = new Circle();
                break;
            case "square": sketch = new Square();
                break;
        }

        PFragment fragment = new PFragment();
        fragment.setSketch(sketch);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
