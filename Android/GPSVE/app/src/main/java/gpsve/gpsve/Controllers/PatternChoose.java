package gpsve.gpsve;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * Created by Nils on 2017-04-24.
 */

public class PatternChoose implements PatternInterface {
    private PApplet parent;
    private final int[] purpColor = new int[]{255, 0, 225};
    private final int[] turqColor = new int[]{0, 255, 235};
    private int colorCount = 0;

    public PatternChoose(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        if (colorCount < 4) {
            parent.fill(purpColor[0], purpColor[1], purpColor[2]);
            colorCount++;
        } else if (colorCount < 8) {
            parent.fill(turqColor[0], turqColor[1], turqColor[2]);
            colorCount++;
        } else {
            colorCount = 0;
        }
    }

    @Override
    public void drawPattern() {
        parent.background(0);
        parent.textSize(parent.width*(float)0.2);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text("Choose a\npattern", parent.width/2, parent.height/2);
    }

    @Override
    public boolean okToDraw() {
        return true;
    }
}
