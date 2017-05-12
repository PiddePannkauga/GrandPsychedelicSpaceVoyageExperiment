package gpsve.gpsve.Patterns;

import android.graphics.Color;

import gpsve.gpsve.Interface.PatternInterface;
import processing.core.PApplet;

/**
 * Created by bajs on 2017-04-18.
 */

public class PatternCircle implements PatternInterface {
    private PApplet parent;
    private Color color;
    private boolean okToDraw = true;
    private byte[] wave, fft;


    public PatternCircle(PApplet parent){
        this.parent=parent;

    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
    }

    @Override
    public void drawPattern() {
        okToDraw = false;
        drawCircle(wave[100]);
        okToDraw = true;
    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }

    public void drawCircle(float ellipseSize){
        parent.background(255,35);
        parent.noStroke();
        parent.fill(parent.color(ellipseSize));
        parent.ellipse(parent.width/2,parent.height/2,ellipseSize*4,ellipseSize*4);
    }
}
