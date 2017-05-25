package gpsve.gpsve.Controllers;

import android.app.Activity;

import gpsve.gpsve.Interface.PatternInterface;
import gpsve.gpsve.Patterns.PatternChoose;
import processing.core.PApplet;

/**
 * Created by Petter Månsson and Nils Lindkvist on 2017-03-22.
 * Class for controlling the flow between different application layers.
 */

public class PatternController extends PApplet {
    private SoundConverter soundConverter;
    private PatternInterface pattern;
    private Activity activity;
    private Buffer waveBuffer, fftBuffer;

    /**
     * Constructor which sets parent activity and which SouncConverter reference the app should use.
     * @param activity
     * @param soundConverter
     */
    public PatternController(Activity activity, SoundConverter soundConverter){
        this.activity = activity;
        this.soundConverter = soundConverter;
        waveBuffer = new Buffer();
        fftBuffer = new Buffer();
        pattern = new PatternChoose(this);
    }

    /**
     * Method called to set current showing pattern.
     * @param pattern
     */
    public void setPattern(PatternInterface pattern) {
        this.pattern = pattern;
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"PatternController"});
    }

    /**
     * Processing method to initiate the canvas on which to draw.
     */
    public void settings() {
        size(width,height);
    }

    /**
     * Processing method to setup the canvas.
     */
    public void setup() {
        background(0);
    }

    /**
     * Processing method which is called over and over to draw and update the canvas.
     */
    public void draw() {
        fftBuffer.put(soundConverter.getFftBytes());
        waveBuffer.put(soundConverter.getWaveBytes());
        if(pattern.okToDraw()) {
            try {
                pattern.updatePattern(fftBuffer.get().clone(), waveBuffer.get().clone());
                pattern.drawPattern();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Method to call to reset the canvas between patterns.
     */
    public void reset() {
        background(0);
        noStroke();
        noFill();
        redraw();
    }
}

