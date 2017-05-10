package gpsve.gpsve;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private AboutFragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("main.onCreate()");
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS}, 0);

    }

    public void startPatternActivity(View view) {
        intent = new Intent(this, PatternActivity.class);
        startActivity(intent);
    }

    public void showAbout(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        frag = new AboutFragment();
        fragmentTransaction.replace(android.R.id.content,frag)
                .addToBackStack("about").commit();


    }

    protected void onStart() {
        super.onStart();
        System.out.println("main.onStart()");
    }

    protected void onResume() {
        super.onResume();
        System.out.println("main.onResume()");
    }

    protected void onStop() {
        super.onStop();
        System.out.println("main.onStop()");
    }

    protected void onPause() {
        super.onPause();
        System.out.println("main.onPause()");
    }

    protected void onDestroy() {
        super.onDestroy();
        System.out.println("main.onDestroy()");
    }

    protected void onRestart() {
        super.onRestart();
        System.out.println("main.onRestart()");
    }


}
