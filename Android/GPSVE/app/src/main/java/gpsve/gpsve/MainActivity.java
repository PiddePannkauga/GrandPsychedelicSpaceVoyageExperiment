package gpsve.gpsve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button buttonCircle = (Button) findViewById(R.id.button_circle);
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
}
