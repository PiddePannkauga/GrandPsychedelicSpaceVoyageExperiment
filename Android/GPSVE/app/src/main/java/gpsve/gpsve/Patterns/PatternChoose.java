package gpsve.gpsve.Patterns;

import gpsve.gpsve.Interface.PatternInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist on 2017-04-24.
 */

public class PatternChoose implements PatternInterface {
    private PApplet parent;
    private final int[] purpleColor = new int[]{255, 0, 225};
    private final int[] turqoiseColor = new int[]{0, 255, 235};
    private int colorCount = 0;

    public PatternChoose(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        if (colorCount < 4) {
            parent.fill(purpleColor[0], purpleColor[1], purpleColor[2]);
            parent.stroke(purpleColor[0], purpleColor[1], purpleColor[2]);
            colorCount++;
        } else if (colorCount < 8) {
            parent.fill(turqoiseColor[0], turqoiseColor[1], turqoiseColor[2]);
            parent.stroke(turqoiseColor[0], turqoiseColor[1], turqoiseColor[2]);
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

        parent.strokeWeight(20);
        parent.line(75, 125, 75, 250);
        parent.line(75, 125, 50, 175);
        parent.line(75, 125, 100, 175);
    }

    @Override
    public boolean okToDraw() {
        return true;
    }
}
