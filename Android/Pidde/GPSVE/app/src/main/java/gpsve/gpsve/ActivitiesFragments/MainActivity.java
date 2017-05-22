package gpsve.gpsve.ActivitiesFragments;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gpsve.gpsve.ActivitiesFragments.AboutFragment;
import gpsve.gpsve.ActivitiesFragments.PatternActivity;
import gpsve.gpsve.R;

/**
 * @author Nils Lindkvist and Petter Månsson 2017-04-01
 * First screen that is shown when app is started.
 */

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private AboutFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("main.onCreate()");
        setContentView(R.layout.activity_main);
        frag = new AboutFragment();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS}, 0);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            }
            });

    }

    /**
     * Method for calling when a button is pressed to shown new activity.
     * @param view
     */
    public void startPatternActivity(View view) {
        intent = new Intent(this, PatternActivity.class);
        startActivity(intent);
    }

    /**
     * Method for calling when a button is pressed to show a new Fragment with the backstory text.
     * @param view
     */
    public void showAbout(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(android.R.id.content,frag,"about")
                .addToBackStack("about").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();


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
