package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Oskar on 2017-04-20.
 */

public class PatternOskar implements PatternInterface {
    private boolean okToDraw = true;
    private PApplet parent;

    public PatternOskar(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void updatePattern(byte[] bytes) {

    }

    @Override
    public void drawPattern() {

    }

    @Override
    public void setOkToDraw(boolean okToDraw) {
        this.okToDraw = okToDraw;

    }

    @Override
    public boolean getOkToDraw() {
        return okToDraw;
    }
}
