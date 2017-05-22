package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist on 2017-05-17.
 */

public class Square implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;

    public Square(PApplet parent) {
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
            parent.fill(255, 0, 200);
            parent.rect(parent.width*(float)0.4, parent.height*(float)0.4, 100, 100);
        }
    }


    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
