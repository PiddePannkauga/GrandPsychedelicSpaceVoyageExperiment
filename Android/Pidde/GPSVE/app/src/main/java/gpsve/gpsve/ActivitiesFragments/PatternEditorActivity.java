package gpsve.gpsve.ActivitiesFragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import gpsve.gpsve.Controllers.PatternController;
import gpsve.gpsve.Controllers.SoundConverter;
import gpsve.gpsve.Patterns.PatternCircle;
import gpsve.gpsve.Patterns.PatternEditor;
import gpsve.gpsve.Patterns.PatternNisse;
import gpsve.gpsve.Patterns.PatternOskar;
import gpsve.gpsve.Patterns.PatternPidde;
import gpsve.gpsve.R;
import processing.android.PFragment;

import static gpsve.gpsve.R.id.imageButton2;

/**
 * Created by Petter on 2017-05-16.
 */

public class PatternEditorActivity extends AppCompatActivity {

    private PatternController patternController;
    private SoundConverter soundConverter;
    private PFragment fragment;
    private FragmentManager fragmentManager;
    private PatternEditor patternEditor;
    private PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_patterneditor);
        soundConverter = new SoundConverter();
        fragment = new PFragment();
        patternController = new PatternController(this, soundConverter);
        patternEditor = new PatternEditor(patternController);
        patternController.setPattern(patternEditor);
        fragmentManager = getSupportFragmentManager();
        fragment.setSketch(patternController);
        fragmentManager.beginTransaction().replace(R.id.pattern_container, fragment).commit();
        popup = new PopupMenu(this, findViewById(imageButton2));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_patterneditor,popup.getMenu());
    }

    protected void onPause(){
        super.onPause();
        soundConverter.disableVisualizer();
    }

    protected void onRestart() {
        super.onRestart();
        soundConverter.initiateVisualizer();
        System.out.println("onRestart()");
    }

    public void showEditorPopup(View v) {


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                patternController.reset();
                switch (item.getItemId()) {
                    case R.id.circleCheck:
                    patternEditor.setVisibiltyCircle(true);
                        if (item.isChecked()){
                            item.setChecked(false);
                        }
                        else{
                            item.setChecked(true);
                        }
                        return true;
                    default:
                        return false;
                }
            }

        });
        popup.show();
    }
}
