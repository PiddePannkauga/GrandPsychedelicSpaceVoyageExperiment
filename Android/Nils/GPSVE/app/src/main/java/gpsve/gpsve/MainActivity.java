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
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS},
                0);
    }

    public void startCircle(View view) {
        intent = new Intent(this, PatternActivity.class);
        String circle = "circle";
        intent.putExtra("pattern", circle);
        startActivity(intent);
    }

    public void startSquare(View view) {
        intent = new Intent(this, PatternActivity.class);
        String square = "square";
        intent.putExtra("pattern", square);
        startActivity(intent);
    }

    public void startDemo(View view) {
        intent = new Intent(this, PatternActivity.class);
        String demo = "demo";
        intent.putExtra("pattern", demo);
        startActivity(intent);
    }

    public void startDemo2(View view) {
        intent = new Intent(this, PatternActivity.class);
        String demo = "demo2";
        intent.putExtra("pattern", demo);
        startActivity(intent);
    }
}
