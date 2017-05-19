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
                patternEditor.setVisible(item.getItemId());

                if(item.isChecked()){
                    item.setChecked(false);
                }else{
                    item.setChecked(true);
                }

                switch (item.getItemId()) {
                    case R.id.clearButton:
                        int size = popup.getMenu().size();
                        for(int i = 0; i<size; i++){
                            popup.getMenu().getItem(i).setChecked(false);
                        }
                        popup.getMenu().findItem(R.id.menu_none).setChecked(true);
                        popup.getMenu().findItem(R.id.menu_none2).setChecked(true);
                        patternController.reset();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        int item1 = R.id.menu_none, item2 = R.id.menu_none2, size = popup.getMenu().size();
        boolean savedFirst = false;
        for(int i = 0; i<size; i++){
            if(popup.getMenu().getItem(i).isChecked()) {
                if(!savedFirst) {
                    item1 = popup.getMenu().getItem(i).getItemId();
                    outState.putInt("item1", item1);
                    System.out.println("item1 " + item1);
                    savedFirst = true;
                } else {
                    item2 = popup.getMenu().getItem(i).getItemId();
                    outState.putInt("item2", item2);
                    System.out.println("item2 " + item2);
                }
            }
        }
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int item1, item2;
        item1 = savedInstanceState.getInt("item1");
        item2 = savedInstanceState.getInt("item2");

        patternEditor.setVisible(item1);
        popup.getMenu().findItem(item1).setChecked(true);

        patternEditor.setVisible(item2);
        popup.getMenu().findItem(item2).setChecked(true);
    }
}
