package gpsve.gpsve;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import processing.android.PFragment;

public class PatternActivity extends AppCompatActivity {
    private PatternController patternController;
    private SoundConverter soundConverter;
    private PFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_pattern);
        soundConverter = new SoundConverter();
        fragment = new PFragment();
        patternController = new PatternController(this, soundConverter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment.setSketch(patternController);
        fragmentManager.beginTransaction().replace(R.id.pattern_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pattern, menu);
        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        patternController.reset();
        switch (item.getItemId()) {
            case R.id.item_pattern1:
                patternController.setPattern(new PatternPidde(patternController));
                return true;
            case R.id.item_pattern2:
                patternController.setPattern(new PatternNisse(patternController));
                return true;
            case R.id.item_pattern3:
                patternController.setPattern(new PatternCircle(patternController));
                return true;
            case R.id.item_pattern4:
                patternController.setPattern(new PatternPidde(patternController));
                return true;
            default:
                return false;
        }
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
}
