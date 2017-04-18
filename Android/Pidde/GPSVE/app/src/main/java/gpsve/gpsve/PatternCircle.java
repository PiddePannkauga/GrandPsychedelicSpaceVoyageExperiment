package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-04-18.
 */

public class PatternCircle implements PatternInterface {
    private PApplet parent;


    public PatternCircle(PApplet parent){
        this.parent=parent;

    }


    @Override
    public void updatePattern(byte[] bytes) {
        for(byte s : bytes){
            drawCircle(s);
        }
    }

    public void drawCircle(float ellipseSize){
        parent.fill(255);
        parent.ellipse(parent.height/2, parent.width/2, ellipseSize*2,ellipseSize*2);

    }
}
