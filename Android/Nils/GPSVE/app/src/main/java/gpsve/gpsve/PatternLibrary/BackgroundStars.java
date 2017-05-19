package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils on 2017-05-17.
 */

public class BackgroundStars implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;

    public BackgroundStars(PApplet parent) {
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
            parent.background(255, 255, 200);
        }
    }


    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
