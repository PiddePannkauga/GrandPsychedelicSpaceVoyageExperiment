package gpsve.gpsve.Controllers;

import android.app.Activity;

import gpsve.gpsve.Interface.PatternInterface;
import gpsve.gpsve.Patterns.PatternChoose;
import processing.core.PApplet;

/**
 * Created by Petter Månsson on 2017-03-22.
 */

public class PatternController extends PApplet {
    private SoundConverter soundConverter;
    private PatternInterface pattern;
    private Activity activity;
    private Buffer waveBuffer, fftBuffer;

    public PatternController(Activity activity, SoundConverter soundConverter){
        this.activity = activity;
        this.soundConverter = soundConverter;
        waveBuffer = new Buffer();
        fftBuffer = new Buffer();
        pattern = new PatternChoose(this);
//        initiateDM();
    }

    public void setPattern(PatternInterface pattern) {
        this.pattern = pattern;
    }

//    public void initiateDM() {
//        DisplayMetrics dm = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"PatternController"});
    }

    public void settings() {
        size(width,height);
    }

    public void setup() {

    }

    public void draw() {
        fftBuffer.put(soundConverter.getFftBytes());
        waveBuffer.put(soundConverter.getWaveBytes());
//        System.out.println(fftBuffer.size());
        if(pattern.okToDraw()) {
            try {
                pattern.updatePattern(fftBuffer.get(), waveBuffer.get());
                pattern.drawPattern();
            } catch (InterruptedException e) {
            }
//            System.out.println(fftBuffer.size());
        }
    }

    public void reset() {
        background(0);
        noStroke();
        noFill();
        redraw();
    }
}

