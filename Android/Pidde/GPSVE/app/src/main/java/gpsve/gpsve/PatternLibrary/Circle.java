package gpsve.gpsve.PatternLibrary;

import processing.core.PApplet;

/**
 * Created by Petter on 2017-05-16.
 */

public class Circle {

    private PApplet parent;
    private float width,height;

    public Circle(PApplet parent){
        this.parent = parent;
    }

    public void update(float ellipseSize){
        this.height = ellipseSize;
        this.width = ellipseSize;
    }

    public void show(){
        parent.noStroke();
        parent.fill(255,255,0);
        parent.ellipse(parent.width/2,parent.height/2,height,width);
    }
}
