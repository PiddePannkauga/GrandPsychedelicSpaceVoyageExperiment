package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils on 2017-05-16.
 */

public class BackgroundAlien implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;

    public BackgroundAlien(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
    }

    @Override
    public void show() {
        if(visible) {
            parent.background(150, 50, 255);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
