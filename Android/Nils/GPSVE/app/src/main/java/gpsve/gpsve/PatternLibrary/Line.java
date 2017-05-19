package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils on 2017-05-17.
 */

public class Line implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;

    public Line(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
    }

    @Override
    public void show() {
        if (visible) {
            parent.stroke(255);
            parent.fill(200,0,130);
            parent.line(parent.width*(float)0.4, parent.height*(float)0.4, 100, 100);
        }
    }


    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
