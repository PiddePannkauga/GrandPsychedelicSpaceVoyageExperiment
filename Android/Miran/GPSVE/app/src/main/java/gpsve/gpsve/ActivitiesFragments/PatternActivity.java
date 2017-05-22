package gpsve.gpsve.ActivitiesFragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import gpsve.gpsve.Controllers.PatternController;
import gpsve.gpsve.Patterns.PatternMirre;
import gpsve.gpsve.Patterns.PatternNisse;
import gpsve.gpsve.Patterns.PatternOskar;
import gpsve.gpsve.Patterns.PatternPidde;
import gpsve.gpsve.R;
import gpsve.gpsve.Controllers.SoundConverter;
import processing.android.PFragment;

public class PatternActivity extends AppCompatActivity {
    private PatternController patternController;
    private SoundConverter soundConverter;
    private PFragment fragment;
    private FragmentManager fragmentManager;
    private int currentPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_pattern);

        soundConverter = new SoundConverter();
        fragment = new PFragment();
        patternController = new PatternController(this, soundConverter);

        fragmentManager = getSupportFragmentManager();
        fragment.setSketch(patternController);
        fragmentManager.beginTransaction().replace(R.id.pattern_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pattern, menu);
        return true;
    }

    public void showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.menu_pattern,popup.getMenu());
    popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                patternController.reset();
                switch (item.getItemId()) {
                    case R.id.item_pattern1:
                        currentPattern = 1;
                        patternController.setPattern(new PatternPidde(patternController));
                        return true;
                    case R.id.item_pattern2:
                        currentPattern = 2;
                        patternController.setPattern(new PatternNisse(patternController));
                        return true;
                    case R.id.item_pattern3:
                        currentPattern = 3;
                        patternController.setPattern(new PatternMirre(patternController));
                        return true;
                    case R.id.item_pattern4:
                        currentPattern = 4;
                        patternController.setPattern(new PatternOskar(patternController));
                        return true;
                    default:
                        return false;
                }
            }

            });

    }

    protected void onStart() {
        super.onStart();
        System.out.println("onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("onResume()");
    }


    protected void onStop() {
        super.onStop();
        System.out.println("onStop()");
        soundConverter.disableVisualizer();
    }

    protected void onPause() {
        super.onPause();
        System.out.println("onPause()");
    }

    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy()");
    }

    protected void onRestart() {
        super.onRestart();
        soundConverter.initiateVisualizer();
        System.out.println("onRestart()");
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("pattern", currentPattern);
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        currentPattern = savedInstanceState.getInt("pattern");
        switch (currentPattern) {
            case 1:
                patternController.setPattern(new PatternPidde(patternController));
                break;
            case 2:
                patternController.setPattern(new PatternNisse(patternController));
                break;
            case 3:
                patternController.setPattern(new PatternMirre(patternController));
                break;
            case 4:
                patternController.setPattern(new PatternOskar(patternController));
                break;
        }
    }
}
