package gpsve.gpsve;

import android.app.Activity;
import android.util.DisplayMetrics;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-03-22.
 */

public class PatternController extends PApplet {
    private SoundConverter soundConverter;
    private PatternPidde pidde;
    private PatternCircle circle;
    private String chosenPattern;
    private Activity activity;
    private Buffer buffer;




    public PatternController(SoundConverter soundConverter,Activity activity,String chosenPattern){
        this.soundConverter = soundConverter;
        this.chosenPattern = chosenPattern;
        pidde = new PatternPidde(this);
        circle = new PatternCircle(this);
        this.activity = activity;
        buffer = new Buffer();

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"PatternController"});
    }

    public void settings() {

    }

    public void setup() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        size(width,height);
        stroke(255);
        background(0,0,150);


    }

    public void drawPattern(){

        redraw();
    }

    public void draw() {

        stroke(255);
        background(0,0,150);
        buffer.put(soundConverter.getFftBytes());
        System.out.println(buffer.size());
        if(pidde.getOkToDraw()) {
            try {
                pidde.updatePattern(buffer.get());
                pidde.drawPattern();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(buffer.size());


    }
}

