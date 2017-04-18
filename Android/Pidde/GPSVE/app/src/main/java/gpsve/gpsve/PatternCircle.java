package gpsve.gpsve;

import android.graphics.Color;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-04-18.
 */

public class PatternCircle implements PatternInterface {
    private PApplet parent;
    private Color color;
    private boolean ok = true;


    public PatternCircle(PApplet parent){
        this.parent=parent;

    }

    public void setOk(boolean ok){
        this.ok=ok;
    }

    public boolean okToDraw(){
        return ok;
    }
    @Override
    public void updatePattern(byte[] bytes) {
        setOk(false);
        for(int i = 0; i<bytes.length; i++) {
            drawCircle(bytes[i]);
        }

        setOk(true);
    }

    public void drawCircle(float ellipseSize){
        parent.noStroke();
        parent.fill(parent.color(ellipseSize));
        parent.ellipse(parent.width/2,parent.height/2,ellipseSize*4,ellipseSize*4);

    }
}
