package gpsve.gpsve;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                integer);
    }

    public void startCircle(View view) {
        Intent intent = new Intent(this, PatternActivity.class);
        String circle = "circle";
        intent.putExtra("pattern", circle);
        startActivity(intent);
    }

    public void startSquare(View view) {
        Intent intent = new Intent(this, PatternActivity.class);
        String square = "square";
        intent.putExtra("pattern", square);
        startActivity(intent);
    }

    public void startDemo(View view) {
        Intent intent = new Intent(this, PatternActivity.class);
        String demo = "demo";
        intent.putExtra("pattern", demo);
        startActivity(intent);
    }
}
