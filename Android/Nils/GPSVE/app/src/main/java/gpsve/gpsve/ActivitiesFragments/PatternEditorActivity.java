package gpsve.gpsve.ActivitiesFragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

import gpsve.gpsve.Controllers.PatternController;
import gpsve.gpsve.Controllers.SoundConverter;

import gpsve.gpsve.Patterns.PatternEditor;

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
    private ArrayList<MenuItem> clearChecks = new ArrayList<>();


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
        initPopup();
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
        popup.show();
    }


    private void initPopup(){
        popup = new PopupMenu(this, findViewById(imageButton2));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_patterneditor,popup.getMenu());
        final RadioGroup radioGrpBackground = (RadioGroup) popup.getMenu().findItem(R.id.groupeditorbackground);
        final RadioGroup radioGrpForeground= (RadioGroup) popup.getMenu().findItem(R.id.groupeditorforeground);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
                item.setActionView(new View(getBaseContext()));
                patternController.reset();

                switch (item.getItemId()) {
                    case R.id.background1check:
                        if (item.isChecked()){
                            item.setChecked(false);
                            clearChecks.remove(item);
                            patternEditor.setVisible(R.id.background1check,true);
                        }
                        else{
                            item.setChecked(true);
                            clearChecks.add(item);
                            patternEditor.setVisible(R.id.background1check,true);

                        }
                        return false;
                    case R.id.background2check:
                        patternEditor.setVisible(R.id.background2check,true);
                        if (item.isChecked()){
                            item.setChecked(false);
                            clearChecks.remove(item);
                        }
                        else{
                            item.setChecked(true);
                            clearChecks.add(item);
                        }
                        return false;
                    case R.id.background3check:
                        patternEditor.setVisible(R.id.background3check,true);
                        if (item.isChecked()){
                            item.setChecked(false);
                            clearChecks.remove(item);
                        }
                        else{
                            item.setChecked(true);
                            clearChecks.add(item);
                        }
                        return false;
                    case R.id.circleCheck:
                        patternEditor.setVisible(R.id.circleCheck,true);
                        if (item.isChecked()){
                            item.setChecked(false);
                            clearChecks.remove(item);
                        }
                        else{
                            item.setChecked(true);
                            clearChecks.add(item);
                        }
                        return false;
                    case R.id.lineCheck:
                        patternEditor.setVisible(R.id.lineCheck,true);
                        if (item.isChecked()){
                            item.setChecked(false);
                            clearChecks.remove(item);
                        }
                        else{
                            item.setChecked(true);
                            clearChecks.add(item);
                        }
                        return false;
                    case R.id.squareCheck:
                        patternEditor.setVisible(R.id.squareCheck, true);
                        if (item.isChecked()){
                            item.setChecked(false);
                            clearChecks.remove(item);
                        }
                        else{
                            item.setChecked(true);
                            clearChecks.add(item);
                        }
                        return false;

                    case R.id.clearButton:
                        radioGrpBackground.clearCheck();
                        radioGrpForeground.clearCheck();
//                        for(int i = 0; i<clearChecks.size(); i++){
//                            clearChecks.get(i).setChecked(false);
//                            System.out.println(clearChecks.get(i));
//                        }
                        return false;
                    default:
                        return false;
                }
            }
        });
    }
}
