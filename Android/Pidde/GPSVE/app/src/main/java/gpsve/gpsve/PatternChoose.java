package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Nils on 2017-04-24.
 */

public class PatternChoose implements PatternInterface {
    private PApplet parent;

    public PatternChoose(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {

    }

    @Override
    public void drawPattern() {
        parent.background(0);
    }

    @Override
    public boolean okToDraw() {
        return true;
    }
}
